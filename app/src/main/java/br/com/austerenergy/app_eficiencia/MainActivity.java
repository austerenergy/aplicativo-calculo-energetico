package br.com.austerenergy.app_eficiencia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            ativaTimer();
    }
    private void ativaTimer(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MainActivity.this, NovoEstudo.class));
                        finish();

                    }
                });
            }};

        timerAtual.schedule(task, 5000);
    }
}
