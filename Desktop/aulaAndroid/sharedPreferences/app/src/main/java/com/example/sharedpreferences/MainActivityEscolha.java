package com.example.sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityEscolha extends AppCompatActivity {

    Button btAdmin, btParticipante ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha);

        btAdmin = (Button) findViewById(R.id.btAdmin);
        btParticipante = (Button) findViewById(R.id.btParticipante);

        btParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityEscolha.this,ListarEventosInscricaoActivity.class);
                i.putExtra("tpusuario", "P");
                startActivity(i);
            }
        });


        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivityEscolha.this,LoginActivity.class);
                i.putExtra("tpusuario", "A");
                startActivity(i);


            }
        });
    }
}
