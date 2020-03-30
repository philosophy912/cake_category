import smtplib
from email import encoders
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.base import MIMEBase
import threading
import datetime
import readConfig
import os


localreadconfig= readConfig.ReadConfig()

class EMAIL:
    def __init__(self):
        global server,sender,password,port,title,content
        server=localreadconfig.get_email("mail_server")
        sender=localreadconfig.get_email("mail_sender")
        password=localreadconfig.get_email("mail_pass")
        port=localreadconfig.get_email("mail_port")
        title=localreadconfig.get_email("subject")
        content=localreadconfig.get_email("content")

        self.value=localreadconfig.get_email("receiver")
        self.receiver=[]

        for i in str(self.value).split("/"):
            self.receiver.append(i)

        date=datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        self.subject=title+""+date
        self.msg=MIMEMultipart('mixed')

    def config_header(self):
        self.msg['subject']=self.subject
        self.msg['FROM']=sender
        self.msg['TO']=";".join(self.receiver)

    def config_content(self):
        content_plain=MIMEText(content,'plain','utf-8')
        self.msg.attach(content_plain)

     #构造测试报告作为附件
        attachments_path=localreadconfig.get_filePath("file_path")
        list=os.listdir(attachments_path)
        list.sort(key=lambda fn: os.path.getctime(attachments_path+'\\'+fn))
        newfile_path=os.path.join(attachments_path,list[-1])

        with open(newfile_path, 'rb') as f:
            mime = MIMEBase('file', 'xls', filename=os.path.basename(newfile_path))
            # 加上必要的头信息:
            mime.add_header('Content-Disposition', 'attachment', filename=os.path.basename(newfile_path))
            mime.add_header('Content-ID', '<0>')
            mime.add_header('X-Attachment-Id', '0')
            # 把附件的内容读进来:
            mime.set_payload(f.read())
            # 用Base64编码:
            encoders.encode_base64(mime)
            # 添加到MIMEMultipart:
            self.msg.attach(mime)

    def send_mail(self):
        self.config_header()
        self.config_content()

        smtp=smtplib.SMTP()
        smtp.connect(server,port)
        smtp.login(sender,password)
        smtp.sendmail(sender,self.receiver,self.msg.as_string)

if __name__ == "__main__":
    EMAIL.send_mail()