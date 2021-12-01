package com.masoodahmad.i180755_i181579;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    TextView log;
    EditText signemail,signpass,signcofirmpass;
    ImageButton signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        log=findViewById(R.id.loginbtn);
        signemail=findViewById(R.id.signemail);
        signpass=findViewById(R.id.signpass);
        signcofirmpass=findViewById(R.id.signconfirmpass);
        signupbtn=findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signpass.getText().toString().equals(signcofirmpass.getText().toString())  ){
                    DbHelper dbh= new DbHelper(SignUp.this);
                    SQLiteDatabase db=dbh.getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    cv.put(Database.users._EMAIL,signemail.getText().toString());
                    cv.put(Database.users._PASSWORD,signpass.getText().toString());
                    db.insert(Database.users.tablename,null,cv);
                    db.close();
                    dbh.close();
                    Intent intent=new Intent(SignUp.this, login.class);
                    startActivity(intent );
                    finish();
                    Toast.makeText(SignUp.this, "Sign Up Successfull!!!", Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(SignUp.this, "Password Does not match!!!!", Toast.LENGTH_SHORT).show();
                }


            }
        });



        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this, login.class);
                startActivity(intent );
                finish();
            }
        });

    }
}