# LifecycleMini

La recreación de una Activity (ya sea por rotación del dispositivo o mediante el método `recreate()`) implica que Android destruye completamente la instancia actual y crea una nueva. Este proceso ocurre porque ciertos cambios de configuración (como orientación) requieren reiniciar la Activity para aplicar los nuevos recursos. En una recreación, se ejecuta el ciclo completo: `onPause() → onSaveInstanceState() → onStop() → onDestroy()` seguido por la creación de una nueva instancia con `onCreate() → onStart() → onRestoreInstanceState() → onResume()`.

Cuando una Activity va al background (al presionar Home), no se destruye: simplemente pasa a un estado detenido. El sistema mantiene su instancia, por lo que al volver no se ejecuta `onCreate()`, sino `onRestart()`, reanudando la misma Activity.

El método `onRestart()` se ejecuta cuando una Activity que estaba detenida (después de `onStop()`) vuelve a primer plano, sin haber sido destruida. Indica que la misma instancia de la Activity continúa activa. 

Android invoca `onSaveInstanceState()` antes de destruir una Activity durante una recreación para guardar su estado temporal (por ejemplo, texto ingresado, posición del scroll, variables, etc.). Esto permite que, al crear la nueva instancia, se restaure el contenido previo mediante `onCreate()`, evitando que se pierdan datos o cambios al rotar la pantalla o cambiar la configuración.
