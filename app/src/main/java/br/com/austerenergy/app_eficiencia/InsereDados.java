package br.com.austerenergy.app_eficiencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InsereDados extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnSalvar, btnFinalizarEstudo;
    EditText edtQtdeLamps, edtHorasDia;
    Spinner tipoLamp;
    ListView lstLamps;
    String item;
    int contador = 0;
    public static ArrayAdapter<Lampada> adpLampada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_dados);

        btnSalvar = (Button)findViewById(R.id.btn_salvar_lamp);
        btnFinalizarEstudo = (Button)findViewById(R.id.btn_finalizar);
        edtQtdeLamps = (EditText)findViewById(R.id.input_qtde_lamps);
        edtHorasDia = (EditText)findViewById(R.id.input_hrs_dia);
        tipoLamp = (Spinner)findViewById(R.id.spn_type_lamp);
        edtHorasDia.setText(null);
        edtQtdeLamps.setText(null);
        lstLamps = (ListView)findViewById(R.id.lst_lamps);
        registerForContextMenu(lstLamps);
        adpLampada = new ArrayAdapter<>(InsereDados.this, android.R.layout.simple_list_item_1);
        lstLamps.setAdapter(adpLampada);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!salvaLampada()){
                    Toast.makeText(InsereDados.this, "Preencha todos os campos solicitados", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnFinalizarEstudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double precoLed18 = 29.0, precoLed9 = 27.0, precoEnergia = 0.7;
                double payback = 0.0, preco=0.0, economia=0.0;

                if(salvaLampada() || contador >0) {

                    for (int i = 0; i < contador; i++) {
                        Lampada lampada = adpLampada.getItem(i);
                        double qtde = lampada.getQuantidade();

                        if (lampada.getModeloLampada() == "Lâmpada 20W") {
                            economia += qtde * lampada.getHorasLigadas() * precoEnergia * 30.0 * 11.0 / 1000.0;
                            preco += qtde * precoLed9 * 1.5;

                        } else if (lampada.getModeloLampada() == "Lâmpada 32W") {
                            economia += qtde * lampada.getHorasLigadas() * precoEnergia * 30.0 * 14.0 / 1000.0;
                            preco += qtde * precoLed18 * 1.5;

                        } else if (lampada.getModeloLampada() == "Lâmpada 40W") {
                            economia += qtde * lampada.getHorasLigadas() * precoEnergia * 30.0 * 22.0 / 1000.0;
                            preco += qtde * precoLed18 * 1.5;
                        }
                    }
                    payback = preco / economia;

                    // converte pra 2 casas decimais

                    preco = ((int) preco * 100.0) / 100.0;
                    economia = ((int) economia * 100.0) / 100.0;
                    payback = ((int) payback * 10.0) / 10.0;

                    // abre nova intent
                    Intent i = new Intent(InsereDados.this, Resultado.class);
                    i.putExtra("economia", "R$ " + String.valueOf(economia));
                    i.putExtra("preco", "R$ " + String.valueOf(preco));
                    i.putExtra("payback", String.valueOf(payback) + " meses");
                    startActivity(i);
                    adpLampada.clear();
                    contador = 0;
                } else {
                    Toast.makeText(InsereDados.this, "Preencha todos os campos solicitados", Toast.LENGTH_LONG).show();
                }
            }
        });

        /************************************* spinner ***********************************/
        // Spinner click listener
        tipoLamp.setOnItemSelectedListener(InsereDados.this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Lâmpada 20W");
        categories.add("Lâmpada 32W");
        categories.add("Lâmpada 40W");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipoLamp.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public boolean salvaLampada(){
        Lampada lampada = new Lampada();

        String qtde = String.valueOf(edtQtdeLamps.getText());
        String horas = String.valueOf(edtHorasDia.getText());
        if(!qtde.trim().equals("") && !horas.trim().equals("")){
            int qtdeLamps = Integer.parseInt(qtde);
            int horasDia = Integer.parseInt(horas);
            lampada.setHorasLigadas(horasDia);
            lampada.setQuantidade(qtdeLamps);
            lampada.setModeloLampada(item);
            adpLampada.add(lampada);
            contador++;
            edtHorasDia.setText(null);
            edtQtdeLamps.setText(null);
            return true;
        } else {
            return false;
        }
    }
}
