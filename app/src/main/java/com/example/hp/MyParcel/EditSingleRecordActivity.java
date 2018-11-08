package com.example.hp.MyParcel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSingleRecordActivity extends AppCompatActivity {

    EditText name, resepi, kategori;
    Button update;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String IDholder;
    String SQLiteDataBaseQueryHolder ;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_record);

        name = (EditText) findViewById(R.id.EditTextName);
        resepi = (EditText) findViewById(R.id.editText3);
        kategori = (EditText) findViewById(R.id.EditTextKategori);
        update = (Button) findViewById(R.id.buttonUpdate);

        sqLiteHelper = new SQLiteHelper(this);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String GetName = name.getText().toString();
                String GetResepi = resepi.getText().toString();
                String GetKategori = kategori.getText().toString();
                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "UPDATE " + SQLiteHelper.TABLE_NAME + " SET "+SQLiteHelper.Table_Column_1_Name+" = '"+GetName+"' , "+SQLiteHelper.Table_Column_2_Resepi+" = '"+GetResepi+"' , "+SQLiteHelper.Table_Column_3_Kategori+" = '"+GetKategori+"' WHERE id = " + IDholder + "";

                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();

                Toast.makeText(EditSingleRecordActivity.this,"DATA BERJAYA DIKEMASKINI", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {

        ShowSRecordInEditText();

        super.onResume();
    }

    public void ShowSRecordInEditText() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("EditID");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                name.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));

                resepi.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Resepi)));

                kategori.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Kategori)));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

}
