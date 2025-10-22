# LifecycleMini

## Descripción

La aplicación **LifecycleMini** tiene como objetivo mostrar y registrar los eventos del ciclo de vida de una Activity en Android. Permite observar cómo se comporta la Activity al recrearse, al ir a segundo plano y al volver al primer plano, mostrando los logs en pantalla y en Logcat.

## Análisis del ciclo de vida

La recreación de una Activity (ya sea por rotación del dispositivo o mediante el método `recreate()`) implica que Android destruye completamente la instancia actual y crea una nueva. Este proceso ocurre porque ciertos cambios de configuración (como orientación) requieren reiniciar la Activity para aplicar los nuevos recursos.  

En una recreación, se ejecuta el ciclo completo:  
`onPause() → onSaveInstanceState() → onStop() → onDestroy()` seguido por la creación de una nueva instancia con  
`onCreate() → onStart() → onRestoreInstanceState() → onResume()`.

Cuando una Activity va al background (al presionar Home), **no se destruye**: simplemente pasa a un estado detenido. El sistema mantiene su instancia, por lo que al volver no se ejecuta `onCreate()`, sino `onRestart()`, reanudando la misma Activity.

El método `onRestart()` se ejecuta cuando una Activity que estaba detenida (después de `onStop()`) vuelve a primer plano, sin haber sido destruida. Indica que la misma instancia de la Activity continúa activa.

Android invoca `onSaveInstanceState()` antes de destruir una Activity durante una recreación para **guardar su estado temporal** (por ejemplo, texto ingresado, posición del scroll, variables, etc.). Esto permite que, al crear la nueva instancia, se restaure el contenido previo mediante `onCreate()`, evitando que se pierdan datos o cambios al rotar la pantalla o cambiar la configuración.

## Funcionalidades de la app

- Mostrar el ciclo de vida completo de la Activity en pantalla y en Logcat (`LCYCLE`).  
- Contador de instancias para ver cuántas veces se ha recreado la Activity.  
- Botón **Recrear**: simula una rotación o recreación de la Activity.  
- Botón **Limpiar**: borra el log de la pantalla.  
- Persistencia de estado mediante `onSaveInstanceState` y restauración en `onCreate`/`onRestoreInstanceState`.  
