package com.example.databasetutorials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText e0, e1,e2,e3,e4;
    TextView t1;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);

        e0=(EditText)findViewById(R.id.ed0);
        e1=(EditText)findViewById(R.id.ed1);
        e2=(EditText)findViewById(R.id.ed2);
        e3=(EditText)findViewById(R.id.ed3);
        t1= (TextView)findViewById(R.id.t1);

        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,update_delete.class);
                startActivity(in);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickinsert();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickread();
            }
        });

    }

    private void Clickinsert()
    {
        String id = e0.getText().toString();
        String username = e1.getText().toString();
        String password = e2.getText().toString();
        String marks = e3.getText().toString();
        Boolean result = myDB.insertDATA(id, username, password, marks);
        if(id.equals(null)==true||id.equals("")==true||username.equals(null)==true||username.equals("")==true||password.equals(null)==true||password.equals("")==true||marks.equals(null)==true||marks.equals("")==true)

        {
            Toast.makeText(MainActivity.this, "Data insertion failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Data inserted sucessfully",Toast.LENGTH_SHORT).show();
        }

    }

    // this function for read the data from database due to clickread//

    private void Clickread() {
        Cursor res = myDB.getALLData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("ID: " + res.getString(0) + "\n");
                stringBuffer.append("USERNAME: " + res.getString(1) + "\n");
                stringBuffer.append("PASSWORD: " + res.getString(2) + "\n");
                stringBuffer.append("MARKS: " + res.getString(3) + "\n" + "\n");
            }
            t1.setText(stringBuffer.toString());
            Toast.makeText(MainActivity.this, "data retrieved sucessfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "no data found", Toast.LENGTH_SHORT).show();
        }

    }}
