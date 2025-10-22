# LifecycleMini

La recreación de una Activity (ya sea por rotación del dispositivo o mediante el método `recreate()`) implica que Android destruye completamente la instancia actual y crea una nueva. Este proceso ocurre porque ciertos cambios de configuración (como orientación) requieren reiniciar la Activity para aplicar los nuevos recursos. En una recreación, se ejecuta el ciclo completo: `onPause() → onSaveInstanceState() → onStop() → onDestroy()` seguido por la creación de una nueva instancia con `onCreate() → onStart() → onRestoreInstanceState() → onResume()`.

Cuando una Activity va al background (al presionar Home), no se destruye: simplemente pasa a un estado detenido. El sistema mantiene su instancia, por lo que al volver no se ejecuta `onCreate()`, sino `onRestart()`, reanudando la misma Activity.

El método `onRestart()` se ejecuta cuando una Activity que estaba detenida (después de `onStop()`) vuelve a primer plano, sin haber sido destruida. Indica que la misma instancia de la Activity continúa activa. 

Android invoca `onSaveInstanceState()` antes de destruir una Activity durante una recreación para guardar su estado temporal (por ejemplo, texto ingresado, posición del scroll, variables, etc.). Esto permite que, al crear la nueva instancia, se restaure el contenido previo mediante `onCreate()`, evitando que se pierdan datos o cambios al rotar la pantalla o cambiar la configuración.

(1)---------------------------- simulador de rotacion ----------------------------
2025-10-22 17:05:58.368 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onCreate(recreada=false)
2025-10-22 17:05:58.418 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onStart()
2025-10-22 17:05:58.427 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onResume()
--------Rote pantalla ------
2025-10-22 17:08:56.402 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onPause()
2025-10-22 17:08:56.413 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onStop()
2025-10-22 17:08:56.421 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onSaveInstanceState()
2025-10-22 17:08:56.430 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onDestroy()
2025-10-22 17:08:56.541 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onCreate(recreada=true)
2025-10-22 17:08:56.549 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onStart()
2025-10-22 17:08:56.551 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onRestoreInstanceState()
2025-10-22 17:08:56.555 13502-13502 LCYCLE                  com.example.lifecyclemin             D  MainActivity.onResume()
<img width="1612" height="932" alt="image" src="https://github.com/user-attachments/assets/5e6071e8-00b0-441a-8cc6-afe60bf914c4" />
