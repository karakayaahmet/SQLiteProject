package com.ahmetkarakaya.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id integer primary key, name Varchar, age int)");

            database.execSQL("Insert into musicians(name, age) values('james',50)");
            database.execSQL("insert into musicians(name, age) values('lars',60)");
            database.execSQL("insert into musicians(name, age) values('kirk',55)");

            database.execSQL("update musicians set age=61 where name='lars'");
            database.execSQL("update musicians set name = 'kirk hammet' where id = 2 ");

            database.execSQL("delete from musicians where id=2");

            //Cursor cursor = database.rawQuery("select * from musician",null);
            //Cursor cursor = database.rawQuery("select * from musician where name='james'",null);
            Cursor cursor = database.rawQuery("select * from musicians where name like 'k%'", null);
            Cursor cursor1 = database.rawQuery("select * from musicians where name like '%s'",null);


            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                System.out.println("Name: "+nameIx);
                System.out.println("Age: "+ageIx);
                System.out.println("Id: "+idIx);
            }

            cursor.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}