package com.example.sharedpreferences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public LoginDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }


    public long inserirUsuario(Usuario usuario){

        ContentValues values  =  new ContentValues();
        values.put("nome",usuario.getNome());
        values.put("email",usuario.getEmail());
        values.put("senha",usuario.getSenha());
        values.put("tpusuario",usuario.getTpusuario());

        return banco.insert("usuario",null,values);
    }

    public Integer validarLogin(String nome, String email,String tpUsuario){
        Cursor cursor  = banco.rawQuery("select * from usuario where nome=? and senha= ? and tpusuario=?",new String[]{nome,email,tpUsuario});
        cursor.moveToFirst();
        if (cursor.getCount() > 0 ){
             return cursor.getInt(cursor.getColumnIndex("codusuario"));
        }else{
            return 0;
        }
    }

}
