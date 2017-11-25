@echo off
set /A n=%1 || set /A n=100

set /A list=8
for /l %%x in (1, 1, %list%) do (
	echo "start java,exe -jar strassen.jar %n%"
	start /wait java,exe -jar "C:\Users\Andrea Scognamiglio\Desktop\Script Prodotto Matriciale\prodotto_strassen_java\strassen.jar" %n% || goto QUIT
)
exit

:QUIT
exit
