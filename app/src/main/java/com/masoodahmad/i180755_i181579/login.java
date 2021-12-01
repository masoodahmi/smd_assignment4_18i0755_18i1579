package com.masoodahmad.i180755_i181579;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class login extends AppCompatActivity {
    TextView reg;
    EditText logemail,logpass;
    ImageButton logbtn;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);










        reg=findViewById(R.id.registerbtn);

        logemail=findViewById(R.id.logemail);
        logpass=findViewById(R.id.logpass);
        logbtn=findViewById(R.id.logbtn);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();


            }
        });




        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, SignUp.class);
                startActivity(intent );
                finish();
            }
        });

    }



    @SuppressLint("Range")
    void GetData(){
        boolean flag=false;
        DbHelper dbh= new DbHelper(login.this);
        SQLiteDatabase db=dbh.getReadableDatabase();
        Cursor c=db.rawQuery("select * from " + Database.users.tablename,null);
        while(c.moveToNext()){
            if(logemail.getText().toString().equals(c.getString(2)) && logpass.getText().toString().equals(c.getString(3)) )
            {




                Toast.makeText(login.this, "Log in Successfull!!!", Toast.LENGTH_SHORT).show();
                flag=true;
                Intent intent=new Intent(login.this, home.class);
                intent.putExtra("id",c.getString(0));
                System.out.println(c.getString(0));
                startActivity(intent );
                finish();
                break;
            }

        }
        if(flag==false) {
            Toast.makeText(login.this, "Log in Failed!!!", Toast.LENGTH_SHORT).show();
        }


    }

}