package com.example.sharedpreferences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public  EventoDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }
    public long inserir(Evento evento){

        ContentValues values  =  new ContentValues();
        values.put("nome",evento.getNome());
        values.put("descricao",evento.getDescricao());
        values.put("data",evento.getData());
        values.put("local",evento.getLocal());
        values.put("valor",evento.getValor());
        values.put("numvagas",evento.getNumvagas());
        return banco.insert("evento",null,values);
    }

    public List<Evento> obterTodos(){
        List<Evento>  listEventos = new ArrayList<>();
        Cursor cursor  = banco.query("evento", new String[]{"codevento","nome","descricao","data","valor","numvagas","local"},null,null,null,null,null);

        while (cursor.moveToNext()){
            Evento e = new Evento();
            e.setCodevento(cursor.getInt(0));
            e.setNome(cursor.getString(1));
            e.setDescricao(cursor.getString(2));
            e.setData(cursor.getString(3));
            e.setValor(cursor.getString(4));
            e.setNumvagas(cursor.getString(5));
            e.setLocal(cursor.getString(6));
            listEventos.add(e);
        }
        return  listEventos;
    }

    public void excuir(Evento e){
        banco.delete("evento","codevento = ?",new String[]{e.getCodevento().toString()});
    }

    public void atualizar(Evento evento){

        ContentValues values  =  new ContentValues();
        values.put("nome",evento.getNome());
        values.put("descricao",evento.getDescricao());
        values.put("data",evento.getData());
        values.put("local",evento.getLocal());
        values.put("valor",evento.getValor());
        values.put("numvagas",evento.getNumvagas());
         banco.update("evento",values,
                "codevento = ?", new String[]{evento.getCodevento().toString()});
         }

    public Integer buscaEvento(String nomeevento){

        Cursor cursor  = banco.rawQuery("select * from evento where nome=?",new String[]{nomeevento});
        cursor.moveToFirst();
        if (cursor.getCount() > 0 ){
           return cursor.getInt(cursor.getColumnIndex("codevento"));
        }else{
            return 0;
       }
    }

    public List<Inscricao> obterEventosUsuario(Integer codusuario){

        List<Inscricao>  listEventosUsuario = new ArrayList<>();
        Cursor cursor  = banco.rawQuery("select * from inscricao  where codusuario=?",new String[]{codusuario.toString()});
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Inscricao i = new Inscricao();
            i.setCodevento(cursor.getInt(0));
            i.setCodusuario(cursor.getInt(1));
            listEventosUsuario.add(i);
        }
        return  listEventosUsuario;
    }

    public List<Inscricao> obterEventosUsuario(){
        List<Inscricao>  listInscricao = new ArrayList<>();
        Cursor cursor  = banco.query("inscricao", new String[]{"codinscricao","codevento","codusuario"},null,null,null,null,null);

        while (cursor.moveToNext()){
            Inscricao i = new Inscricao();
            i.setCodInscricao(cursor.getInt(0));
            i.setCodusuario(cursor.getInt(1));
            i.setCodevento(cursor.getInt(2));
            listInscricao.add(i);
        }
        return  listInscricao;
    }


}
