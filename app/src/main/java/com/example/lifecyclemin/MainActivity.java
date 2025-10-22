package com.example.lifecyclemin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



    public class MainActivity extends AppCompatActivity {

        private static final String TAG = "LCYCLE";
        private static final String KEY_COUNTER = "instance_counter";
        private static final String KEY_LOGTEXT = "log_text";

        private int instanceCounter = 1;
        private TextView txtLog, txtInstance;

        private void addEvent(String method) {
            String line = System.currentTimeMillis() + " • MainActivity." + method + "\n";
            txtLog.append(line);
            Log.d(TAG, "MainActivity." + method);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            txtLog = findViewById(R.id.txtLog);
            txtInstance = findViewById(R.id.txtInstance);
            Button btnRecreate = findViewById(R.id.btnRecreate);
            Button btnClear = findViewById(R.id.btnClear);

            // Restaurar estado previo (si lo hay)
            if (savedInstanceState != null) {
                instanceCounter = savedInstanceState.getInt(KEY_COUNTER, 0) + 1;
                String old = savedInstanceState.getString(KEY_LOGTEXT, "");
                txtLog.setText(old);
            }
            txtInstance.setText("Instancia #: " + instanceCounter);

            btnRecreate.setOnClickListener(v -> recreate());
            btnClear.setOnClickListener(v -> txtLog.setText(""));

            addEvent("onCreate(recreada=" + (savedInstanceState != null) + ")");


        }

        @Override
        protected void onStart() {
            super.onStart();
            addEvent("onStart()");
        }

        @Override
        protected void onResume() {
            super.onResume();
            addEvent("onResume()");
        }

        @Override
        protected void onPause() {
            super.onPause();
            addEvent("onPause()");
        }

        @Override
        protected void onStop() {
            super.onStop();
            addEvent("onStop()");
        }

        @Override
        protected void onRestart() {
            super.onRestart();
            addEvent("onRestart()");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            addEvent("onDestroy()");
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt(KEY_COUNTER, instanceCounter);
            outState.putString(KEY_LOGTEXT, txtLog.getText().toString());
            addEvent("onSaveInstanceState()");
        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            addEvent("onRestoreInstanceState()");
        }

        }