package com.example.sharedpreferences;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListarEventosInscricaoActivity extends AppCompatActivity {

    private ListView listView;
    private EventoDAO dao;
    private List<Evento> listEventos;
    private List<Evento> listEventosFiltrados = new ArrayList<>();
    private InscricaoDAO inscricaoDao;
    String usuarioLogado ;
    String codusuarioLogado;
    String tpUsuario;
    Long res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);
        listView = findViewById(R.id.lista_evento);
        dao = new EventoDAO(this);
        inscricaoDao = new InscricaoDAO(this);

        listEventos = dao.obterTodos();

        listEventosFiltrados.addAll(listEventos);

        ArrayAdapter<Evento> adaptador = new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_checked,listEventosFiltrados);

        listView.setAdapter(adaptador);

        Intent it = getIntent();

        if(it.getStringExtra("tpusuario").toString().equals("P") & listEventosFiltrados.size() <= 0){
            Toast.makeText(ListarEventosInscricaoActivity.this,"Não Existem eventos para realizar sua Inscrição!",Toast.LENGTH_LONG).show();
            Intent i = new Intent(ListarEventosInscricaoActivity.this,MainActivityEscolha.class);
            startActivity(i);
        }

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = getIntent();
                if(it.getStringExtra("usuarioLogado") != null ) {
                    usuarioLogado = it.getStringExtra("usuarioLogado").toString();
                    codusuarioLogado =  it.getStringExtra("codusuarioLogado").toString();
                    tpUsuario =  it.getStringExtra("tpusuario").toString();
                }else{
                    usuarioLogado = "0";
                }

                if (usuarioLogado.equals("1")){
                    res = inscricaoDao.inserir(Integer.parseInt(codusuarioLogado),dao.buscaEvento(listEventosFiltrados.get(position).toString()));
                    if(res <= 0){
                        Toast.makeText(ListarEventosInscricaoActivity.this,"Erro ao realizar a inscricão!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ListarEventosInscricaoActivity.this,"Inscrição realizada com sucesso!",Toast.LENGTH_SHORT).show();
                    }
                    // startActivity(i);
                }else{
                    Toast.makeText(ListarEventosInscricaoActivity.this,"Verifique Usuário e Senha!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ListarEventosInscricaoActivity.this,LoginActivity.class);
                    i.putExtra("tpusuario", "P");
                    startActivity(i);


                }
                //Intent i = new Intent(ListarEventosInscricaoActivity.this,MainActivityLogin.class);
               // i.putExtra("eventoCLicado", listEventosFiltrados.get(position).toString());
                       // startActivity(i);


            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i  = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraEvento(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu,v,menuInfo);
            MenuInflater i = getMenuInflater();
            i.inflate(R.menu.menu_contexto,menu);

    }

    public void procuraEvento(String nome){
        listEventosFiltrados.clear();
        for(Evento e : listEventos){
                if(e.getNome().toLowerCase().contains(nome.toLowerCase())){
                    listEventosFiltrados.add(e);
                }
        }
        listView.invalidateViews();
    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Evento eventoAtualizar = listEventosFiltrados.get(menuInfo.position);

        Intent it = new Intent(this,MainActivity.class);

        it.putExtra("evento",eventoAtualizar);

        startActivity(it);

    }

    public void excluir (MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Evento eventoExcluir = listEventosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Atenção").setMessage("Deseja excluir este evento ?").setNegativeButton("Não",null).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    listEventosFiltrados.remove(eventoExcluir);
                    listEventos.remove(eventoExcluir);
                    dao.excuir(eventoExcluir);

                    listView.invalidateViews();
            }
        }).create();

        dialog.show();


    }

    @Override
    public void onResume(){
        super.onResume();
        listEventos = dao.obterTodos();
        listEventosFiltrados.clear();
        listEventosFiltrados.addAll(listEventos);
        listView.invalidateViews();
    }
}
