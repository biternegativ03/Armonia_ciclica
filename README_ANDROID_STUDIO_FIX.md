Fix rápido: Android Studio usa su JBR/jlink y falla JdkImageTransform

Resumen
-------
Si ves este error al compilar desde Android Studio:

Execution failed for task ':app:compileDebugJavaWithJavac'.
> Could not resolve all files for configuration ':app:androidJdkImage'.
  > Failed to transform core-for-system-modules.jar ... Error while executing process ... jlink.exe ...

Significa que Gradle/AGP está intentando invocar jlink desde la JRE embebida (Android Studio JBR). En la terminal ya solucionamos el problema forzando Gradle a usar un JDK completo (ej. JDK 17) vía `gradle.properties` (línea `org.gradle.java.home`). Sin embargo, Android Studio puede seguir usando su JDK para ejecutar tareas Gradle cuando lanzas Run (Play).

Qué se añadió al proyecto
------------------------
- `gradle.properties` actualizado con:
  - `org.gradle.java.home=C:\\Program Files\\Java\\jdk-17`
  - `android.androidJdkImage=false` y `android.enableJdkImageTransform=false` (intento de desactivar la transformación si aplica)

Además, este repo ahora contiene un script opcional para Windows que lanza Android Studio con la variable de entorno `JAVA_HOME` apuntando al JDK del sistema, reduciendo probabilidades de que la IDE use su JBR para Gradle.

Archivo útil
------------
- `scripts/run-studio-with-jdk.bat` — ejecuta Android Studio (si está en la ruta por defecto) con `JAVA_HOME` apuntando a `C:\Program Files\Java\jdk-17`.

Pasos recomendados (rápidos) — sigue en este orden
-------------------------------------------------
1) Ejecuta el script para abrir Android Studio con la JDK del sistema (Windows):

```powershell
cd C:\AndroidProjects\Armoniaciclica
scripts\run-studio-with-jdk.bat
```

2) En Android Studio, verifica (y cambia si hace falta):
   File > Settings > Build, Execution, Deployment > Build Tools > Gradle
   - "Gradle JDK": selecciona la JDK del sistema (C:\Program Files\Java\jdk-17). No el JBR.
   - "Use Gradle from": selecciona "Gradle wrapper".

3) File > Sync Project with Gradle Files
4) Build > Clean Project  -> luego Build > Rebuild Project
5) Ejecuta Run (Play). Si el Run sigue deshabilitado, revisa Run/Debug Configurations (Edit Configurations...) y verifica que exista una configuración tipo "Android App" con Module = `app`.

Si aún falla
------------
- Copia aquí las primeras 40-60 líneas del Event Log o de la pestaña Run/Build de Android Studio y lo reviso.
- También puedes ejecutar desde powershell (en este repo):

```powershell
./gradlew --stop
./gradlew assembleDebug --no-daemon --refresh-dependencies --stacktrace
```

y pegar la salida si aparece el error.

Notas adicionales
-----------------
- Añadir `platform-tools` al PATH te permite usar `adb devices` en la terminal: normalmente está en `%LOCALAPPDATA%\\Android\\Sdk\\platform-tools`.
- Si por política no puedes cambiar Android Studio, el método más confiable es abrir Android Studio con la variable `JAVA_HOME` apuntando al JDK del sistema (el script incluido).

Si quieres, puedo:
- Generar un `README_short_presentacion.md` con los comandos exactos que ejecutarás hoy.
- Intentar limpiar `gradle.properties` (eliminar duplicados) y eliminar warnings potenciales antes de tu presentación.

Dime si quieres que cree el README corto y/o limpie `gradle.properties` ahora.
