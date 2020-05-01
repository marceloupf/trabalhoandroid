package com.example.sharedpreferences;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static  final String name = "banco.db";
    private static  final   int version = 53;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table inscricao(codinscricao integer primary key autoincrement, "
                + "codevento integer not null, "
                + "codusuario integer not null)");


        db.execSQL("create table usuario(codusuario integer primary key autoincrement, "
                + "nome varchar(50) not null, "
                + "email varchar(100) not null, "
                + "senha varchar(10) not null, "
                + "tpusuario varchar(1) not null)");

        db.execSQL("create table evento(codevento integer primary key autoincrement, "
                     + "nome varchar(50) not null, "
                     + "descricao varchar(100) not null,"
                     + "data varchar(14),"
                     +" valor varchar(10),"
                     + "numvagas varchar(10),"
                     + "local varchar(100))");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table IF EXISTS inscricao;");
        db.execSQL("DROP table IF EXISTS usuario;");
        db.execSQL("DROP table IF EXISTS evento;");
       onCreate(db);
    }
}
