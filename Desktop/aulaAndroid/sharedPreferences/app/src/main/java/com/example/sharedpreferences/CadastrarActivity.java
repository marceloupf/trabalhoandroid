package com.example.sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CadastrarActivity extends AppCompatActivity {
    private LoginDAO loginDao;
    private EventoDAO eventoDao;
    EditText edtNome,edtEmail,edtSenha;
    Button btnInserirUsuario;
    String eventoCLicado;
    Integer codEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        loginDao = new LoginDAO(this);
        eventoDao = new EventoDAO(this);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnInserirUsuario = (Button) findViewById(R.id.btnInserirUsuario);

        btnInserirUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = getIntent();

                Usuario u = new Usuario();

                u.setNome(edtNome.getText().toString());
                u.setEmail(edtEmail.getText().toString());
                u.setSenha(edtSenha.getText().toString());
                u.setTpusuario(it.getStringExtra("tpusuario").toString());

                       if(!validaCampos()){

                        long codusuario = loginDao.inserirUsuario(u);

                        Toast.makeText(CadastrarActivity.this, "Usuário inserido com sucesso!" + codusuario, Toast.LENGTH_SHORT).show();

                        if (it.getStringExtra("tpusuario").toString().equals("A")) {
                            Intent i = new Intent(CadastrarActivity.this, LoginActivity.class);
                            i.putExtra("tpusuario", "A");

                            startActivity(i);
                        } else if (it.getStringExtra("tpusuario").toString().equals("P")) {
                            Intent i = new Intent(CadastrarActivity.this, LoginActivity.class);
                            i.putExtra("tpusuario", "P");
                            startActivity(i);
                        }
                    }

            }
        });
    }

    private boolean validaCampos(){
        boolean res = false;

        String nome  = edtNome.getText().toString();
        String email  = edtEmail.getText().toString();
        String senha  = edtSenha.getText().toString();

        if(res = isCampoVazio(nome)){
            edtNome.requestFocus();
        }else if (res = !isValidaEmail(email)){
            edtEmail.requestFocus();
        }else if (res = isCampoVazio(senha)){
            edtSenha.requestFocus();
        }

        if(res){
            AlertDialog.Builder dlg =  new AlertDialog.Builder(this);
            dlg.setTitle("Atenção");
            dlg.setMessage("Existem campos que devem ser preenchidos corretamente!");
            dlg.setNeutralButton("OK",null);
            dlg.show();

        }
return res;

    }

    private boolean isCampoVazio(String valor){
        boolean resultado = ( TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
     }

    private boolean isValidaEmail(String email){
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }
}
