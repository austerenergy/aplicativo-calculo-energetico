package br.com.austerenergy.app_eficiencia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Resultado extends AppCompatActivity {

    String economia, preco, payback;
    TextView varEconomia, varPreco, varPayback;
    Button btnFechar;

    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            economia = extras.getString("economia");
            //Log.d("teste",economia);
            preco = extras.getString("preco");
            //Log.d("teste", preco);
            payback = extras.getString("payback");
            //Log.d("teste", payback);
        }

        ativaTimer();

        varEconomia = (TextView)findViewById(R.id.varEconomia);
        varPreco = (TextView)findViewById(R.id.varPreco);
        varPayback= (TextView)findViewById(R.id.varPayback);
        btnFechar = (Button)findViewById(R.id.btnFechar);

        varEconomia.setText(economia);
        varPreco.setText(preco);
        varPayback.setText(payback);

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ativaTimer(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {

                        findViewById(R.id.layout_carregando).setVisibility(View.GONE);
                        //show webview
                        findViewById(R.id.layout_done).setVisibility(View.VISIBLE);
                    }
                });
            }};

        timerAtual.schedule(task, 3000);
    }
}
