package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtLogin,edtSenha;
    Button btnLogin,btnCadastrarUsuario;
    private LoginDAO loginDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginDao = new LoginDAO(this);

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCadastrarUsuario = (Button) findViewById(R.id.btnCadastrarUsuario);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = getIntent();

                String login = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();
                String tpUsuario =  it.getStringExtra("tpusuario").toString();

                    if (login.equals("")){
                        Toast.makeText(LoginActivity.this,"Digite Seu Login!",Toast.LENGTH_SHORT).show();
                    }else  if (senha.equals("")){
                        Toast.makeText(LoginActivity.this,"Digite Sua Senha!",Toast.LENGTH_SHORT).show();
                    }else{
                        Integer res = loginDao.validarLogin(login,senha,tpUsuario);

                        if(res != 0){
                            if (tpUsuario.equals("P")) {
                                Toast.makeText(LoginActivity.this, "Usuário Logado!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, ListarEventosInscricaoActivity.class);
                                i.putExtra("usuarioLogado", "1");
                                i.putExtra("codusuarioLogado", res.toString());
                                i.putExtra("tpusuario", "P");

                                startActivity(i);
                            }else{
                                Toast.makeText(LoginActivity.this, "Usuário Logado!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, ListarEventosActivity.class);
                               // i.putExtra("usuarioLogado", "1");
                               // i.putExtra("codusuarioLogado", res.toString());
                                startActivity(i);
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"Verifique Usuário e Senha!",Toast.LENGTH_SHORT).show();

                        }
                    }
            }
        });

        btnCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = getIntent();
                Intent i = new Intent(LoginActivity.this,CadastrarActivity.class);
                i.putExtra("tpusuario", it.getStringExtra("tpusuario").toString());
                startActivity(i);

            }
        });
    }
}

