@echo off
REM Script para lanzar Android Studio con JAVA_HOME apuntando al JDK del sistema.
REM Ajusta STUDIO_EXE si Android Studio está instalado en otra ruta.
SETLOCAL
SET "SYS_JAVA=C:\Program Files\Java\jdk-17"
SET "STUDIO_EXE=C:\Program Files\Android\Android Studio\bin\studio64.exe"

nREM Comprueba existencia del JDK
IF NOT EXIST "%SYS_JAVA%\bin\java.exe" (
    echo ERROR: No se encontro el JDK en %SYS_JAVA%
    echo Por favor instala JDK 17 en esa ruta o edita el script.
    pause
    EXIT /B 1
)

nREM Comprueba existencia del Android Studio executable
IF NOT EXIST "%STUDIO_EXE%" (
    echo No se encontro Android Studio en %STUDIO_EXE%.
    echo Si Android Studio esta en otra ruta, edita scripts\run-studio-with-jdk.bat y ajusta STUDIO_EXE.
    pause
    EXIT /B 2
)

nREM Lanza Android Studio con JAVA_HOME apuntando al JDK del sistema
SET "JAVA_HOME=%SYS_JAVA%"
SET "PATH=%SYS_JAVA%\bin;%PATH%"

necho Lanzando Android Studio con JAVA_HOME=%JAVA_HOME%
start "Android Studio (with JDK17)" "%STUDIO_EXE%"
ENDLOCAL
exit /B 0
