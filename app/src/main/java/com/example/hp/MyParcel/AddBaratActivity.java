package com.example.hp.MyParcel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;


public class AddBaratActivity extends AppCompatActivity
{
    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextName, editTextResepi, editTextKategori;
    String NameHolder, ResepiHolder, KategoriHolder, SQLiteDataBaseQueryHolder;
    Button EnterData, ButtonDisplayData;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barat);

        EnterData = (Button) findViewById(R.id.button_tambah_barat);
        ButtonDisplayData = (Button) findViewById(R.id.button_show_barat);
        editTextName = (EditText) findViewById(R.id.edit_nama_barat);
        editTextResepi = (EditText) findViewById(R.id.edit_resepi_barat);
        editTextKategori = (EditText) findViewById(R.id.edit_kategori_barat);

        EnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
                EmptyEditTextAfterDataInsert();
            }
        });


        ButtonDisplayData.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AddBaratActivity.this, DisplaySQLiteDataActivity.class);
                startActivity(intent);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddBaratActivity.this, "You are good", Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        Thread thr = new Thread(run);
        thr.start();
    }
    });
}



    public void SQLiteDataBaseBuild()
    {
        sqLiteDatabaseObj = openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);
    }

    public void SQLiteTableBuild()
    {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS AndroidJSonTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, resepi VARCHAR, kategori VARCHAR);");
    }

    public void CheckEditTextStatus()
    {
        NameHolder = editTextName.getText().toString() ;
        ResepiHolder = editTextResepi.getText().toString();
        KategoriHolder = editTextKategori.getText().toString();
        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(ResepiHolder) || TextUtils.isEmpty(KategoriHolder))
        {
            EditTextEmptyHold = false ;
        }
        else
        {
            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase()
    {
        if(EditTextEmptyHold == true)
        {
            SQLiteDataBaseQueryHolder = "INSERT INTO AndroidJSonTable (name,resepi,kategori) VALUES('"+NameHolder+"', '"+ResepiHolder+"', '"+KategoriHolder+"');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            Toast.makeText(AddBaratActivity.this,"DATA BERJAYA DIMASUKKAN", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(AddBaratActivity.this,"SILA ISIKAN RUANGAN TERSEBUT TERLEBIH DAHULU", Toast.LENGTH_SHORT).show();
        }
    }

    public void EmptyEditTextAfterDataInsert()
    {
        editTextName.getText().clear();
        editTextResepi.getText().clear();
        editTextKategori.getText().clear();

    }
}