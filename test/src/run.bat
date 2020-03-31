::获取当前cmd路径
set current_dir=%cd%
set report_dir=%current_dir%\allure-report
set pytest_dir=%current_dir%\allure-results

::执行pytest命令开始进行测试
call pytest --alluredir=%pytest_dir% --clean-alluredir %current_dir%
::执行allure命令，使用上一步的结果生成测试报告
call allure generate %pytest_dir% -c -o %report_dir%



