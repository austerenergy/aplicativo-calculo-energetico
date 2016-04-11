package br.com.austerenergy.app_eficiencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NovoEstudo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_estudo);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void NovoCalculo(View view){

        startActivity( new Intent(NovoEstudo.this, InsereDados.class));
    }

}
