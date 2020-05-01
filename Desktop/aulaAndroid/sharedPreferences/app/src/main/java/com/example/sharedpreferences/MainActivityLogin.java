package com.example.sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityLogin extends AppCompatActivity {

    Button btRegistrar, btEntrar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);

        btEntrar = (Button) findViewById(R.id.btEntrar);
        btRegistrar = (Button) findViewById(R.id.btRegistrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityLogin.this,LoginActivity.class);
                i.getStringExtra("tpusuario").toString();
                startActivity(i);
            }
        });


        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivityLogin.this,CadastrarActivity.class);
                startActivity(i);


            }
        });
    }
}
