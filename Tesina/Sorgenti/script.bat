@echo off

cd .\prodotto_strassen_java(NoJIT)\
start /wait script_strassen.bat 1024
cd ..\prodotto_strassen_c++\
start /wait script_strassen.bat 1024
cd ..\prodotto_strassen_java\
start /wait script_strassen.bat 1024
cd ..\prodotto_strassen_c++(-O2)\
start /wait script_strassen.bat 1024
cd ..\prodotto_strassen_java(NoJIT)\
start /wait script_strassen.bat 2048
cd ..\prodotto_strassen_c++\
start /wait script_strassen.bat 2048
cd ..\prodotto_strassen_java\
start /wait script_strassen.bat 2048
cd ..\prodotto_strassen_c++(-O2)\
start /wait script_strassen.bat 2048
cd ..\prodotto_strassen_java(NoJIT)\
start /wait script_strassen.bat 4096
cd ..\prodotto_strassen_c++\
start /wait script_strassen.bat 4096
cd ..\prodotto_strassen_java\
start /wait script_strassen.bat 4096
cd ..\prodotto_strassen_c++(-O2)\
start /wait script_strassen.bat 4096


pause
exit