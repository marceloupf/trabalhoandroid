package com.example.sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText descricao;
    private EditText valor;
    private EditText data;
    private EditText numvagas;
    private EditText local;
    private EventoDAO dao;
    private Evento evento = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.edtNome);
        descricao = findViewById(R.id.edtdescricao);
        valor   = findViewById(R.id.edtvalor);
        data = findViewById(R.id.edtdata);
        numvagas = findViewById(R.id.edtvagas);
        local = findViewById(R.id.edtlocal);
        dao = new EventoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("evento")){
            evento = (Evento) it.getSerializableExtra("evento");
            nome.setText(evento.getNome());
            descricao.setText(evento.getDescricao());
            valor.setText(evento.getValor());
            data.setText(evento.getData());
            numvagas.setText(evento.getNumvagas());
            local.setText(evento.getLocal());

        }
    }

    public void salvar(View view){
        if(!validaCampos()) {
        if(evento == null) {

                Evento evento = new Evento();
                evento.setNome(nome.getText().toString());
                evento.setDescricao(descricao.getText().toString());
                evento.setValor(valor.getText().toString());
                evento.setNumvagas(numvagas.getText().toString());
                evento.setLocal(local.getText().toString());
                evento.setData(data.getText().toString());
                long codevento = dao.inserir(evento);
                Toast.makeText(this, "Evento inserido com Sucesso. Código " + codevento, Toast.LENGTH_SHORT).show();

        }else{
                evento.setNome(nome.getText().toString());
                evento.setDescricao(descricao.getText().toString());
                evento.setValor(valor.getText().toString());
                evento.setNumvagas(numvagas.getText().toString());
                evento.setLocal(local.getText().toString());
                evento.setData(data.getText().toString());
                dao.atualizar(evento);
                Toast.makeText(this, "Evento atualiado com Sucesso. ", Toast.LENGTH_SHORT).show();

        }

        Intent i = new Intent(MainActivity.this,ListarEventosActivity.class);

        startActivity(i);

        }
    }




    private boolean validaCampos(){
        boolean res = false;

        String edtnome  =  nome.getText().toString();
        String edtdescricao  = descricao.getText().toString();
        String edtvalor  = valor.getText().toString();
        String edtnumvagas  =  numvagas.getText().toString();
        String edtlocal  = local.getText().toString();
        String edtdata  = data.getText().toString();

        if(res = isCampoVazio(edtnome)){
            nome.requestFocus();
        }else if (res = isCampoVazio(edtdescricao)){
            descricao.requestFocus();
        }else if (res = isCampoVazio(edtvalor)){
            valor.requestFocus();
        }else if (res = isCampoVazio(edtnumvagas)){
            numvagas.requestFocus();
        }else if (res = isCampoVazio(edtlocal)){
            local.requestFocus();
        }else if (res = isCampoVazio(edtdata)){
            data.requestFocus();
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


}
