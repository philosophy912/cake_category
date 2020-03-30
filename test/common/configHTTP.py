import requests
import readConfig as readConfig

# from xlrd import open_workbook

localReadConfig = readConfig.ReadConfig()


class ConfigHttp:
    def __init__(self):
        global host, port, timeout
        host = localReadConfig.get_http("baseurl")
        port = localReadConfig.get_http("port")
        timeout = localReadConfig.get_http("timeout")
        # xls_path=localReadConfig.get_filePath("casefile_path")
        self.headers = {}
        self.params = {}
        self.data = {}
        self.url = None
        self.files = {}
        # self.row=0

    def set_url(self, url):
        self.url = host + url()

    def set_headers(self, header):
        self.headers = header

    def set_params(self, params):
        self.params = params

    def set_data(self, data):
        self.data = data

    def set_files(self, file):
        self.files = file

    def get(self):
        try:
            response = requests.get(self.url, params=self.params, headers=self.headers, timeout=float(timeout))
            return response
        except TimeoutError:
            print("Time out!")
            return None

    def post(self):
        try:
            response = requests.get(self.url, params=self.params, headers=self.headers, data=self.data,
                                    files=self.files, timeout=float(timeout))
            return response
        except TimeoutError:
            print("Time out!")
            return None
