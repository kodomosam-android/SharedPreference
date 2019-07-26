package com.casting99.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText textoNome;
    private Button botaoSalvar;
    private TextView textoExibicao;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNome = (EditText) findViewById(R.id.editTextNome);
        botaoSalvar = (Button)findViewById(R.id.buttonSalvar);
        textoExibicao = (TextView)findViewById(R.id.TextViewDescricao);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(textoNome.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Informe o nome", Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("nome", textoNome.getText().toString());
                    editor.commit();
                    textoExibicao.setText("Olá, "  + textoNome.getText().toString());
                }
            }
        });

        //recupera os dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        if(sharedPreferences.contains("nome")){
            String nomeUsuario = sharedPreferences.getString("nome", "usuario nao encontrado");
            textoExibicao.setText("Olá, " + nomeUsuario);
        }else{
            textoExibicao.setText("Ola usúario nao definido");
        }

    }
}
