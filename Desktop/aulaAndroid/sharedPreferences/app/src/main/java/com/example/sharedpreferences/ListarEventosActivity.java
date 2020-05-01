package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class ListarEventosActivity extends AppCompatActivity {

    private ListView listView;
    private EventoDAO dao;
    private List<Evento> listEventos;
    private List<Evento> listEventosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);


        listView = findViewById(R.id.lista_evento);
        dao = new EventoDAO(this);

        listEventos = dao.obterTodos();

        listEventosFiltrados.addAll(listEventos);

        ArrayAdapter<Evento> adaptador = new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1,listEventosFiltrados);

        listView.setAdapter(adaptador);

        registerForContextMenu(listView);

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
