:: execute pytest and generate report using allure tools

::关闭回显
@echo off
::获取当前cmd路径
set current_dir=%cd%
::获取当前磁盘的盘符
set disk_name=%~d0
::获取本脚本的所在目录路径
set bat_dir=%~dp0
::设置测试用例的根目录，如automatedtest
set root_dir=%current_dir%

::初始化pytest和allure的报告路径为当前路径下
set report_dir=%current_dir%\pytest_report\allure-report
set pytest_dir=%current_dir%\pytest_report\allure-results

::将环境配置文件复制到报告结果目录中
set environment_path=%bat_dir%environment.properties

goto loop

::---------------------------------------------------------------------------
::使用循环的方式判断当前的路径中是否包含automatedtest字符串
::如果包含则认为当前目录为测试包的目录，将pytest和allure的报告路径修改为automatedtest\res\pytest_report
::如果当前目录未在automatedtest下，则继续使用当前路径为报告的生成路径
::---------------------------------------------------------------------------
:loop
	cd ..
	set tmp_dir=%cd%
	if "%tmp_dir:~-13%"=="automatedtest" (
		set root_dir=%tmp_dir%
		set report_dir=%tmp_dir%\res\pytest_report\allure-report
		set pytest_dir=%tmp_dir%\res\pytest_report\allure-results
		goto main
	)else{
		set root_dir=%tmp_dir%
		set report_dir=%tmp_dir%\allure-report
		set report_dir=%tmp_dir%\allure-results
		goto main
	}
	if "%tmp_dir%"=="%disk_name%" (
		goto main
	)
	goto loop

	

	
::---------------------------------------------------------------------------
::执行pytest生成测试结果数据
::执行allure生成测试报告
::---------------------------------------------------------------------------
:main
	::需要输入要测试的文件夹或文件或类的全路径，直接回车表示使用当前路径执行测试
	set work_dir=%current_dir%
	set /p work_dir="Input the test path or file or class or case :":
	::是否指定要执行的用例mark
	set markers=all
	set /p markers="Input the marks, example: 'mark1 and not mark2', default to all :":
	::是否需要在用例执行失败后重试
	set retry=0
	set /p retry="Input the number of times to re-run failed tests, defulat to 0 :":
	
	cd %current_dir%
	::执行pytest命令开始进行测试
	if "%markers%"=="all" (
		call pytest --alluredir=%pytest_dir% --clean-alluredir --reruns %retry% %work_dir%
	) else (
		call pytest -v -m "%markers%" --alluredir=%pytest_dir% --clean-alluredir --reruns %retry% %work_dir%
	)
	
	::自动搜索当前路径或测试根目录下的所有子目录中的环境配置文件
	set tmp_environment=%pytest_dir%\environment.properties
	for /R %root_dir% %%a in (environment.properties) do (
		if exist %%a (
			if "%%a" NEQ "%tmp_environment%" (
				set environment_path=%%a
			)
		)
	)
	::复制环境配置文件
	call xcopy %environment_path% %pytest_dir% /Y
	
	::复制历史数据到results中，便于生成趋势图
	call xcopy %report_dir%\history\* %pytest_dir%\history /E /Y /I
	::执行allure命令，使用上一步的结果生成测试报告
	call allure generate %pytest_dir% -c -o %report_dir%
	
	echo =========================================================================
	echo root workspace is : %root_dir%
	echo current test set is : %work_dir%
	echo pytest results have been saved to : %pytest_dir%
	echo allure report has been saved to : %report_dir%
	echo environment config file path : %environment_path%
	echo used markers : %markers%
	echo trend status : True
	echo =========================================================================

::pause
