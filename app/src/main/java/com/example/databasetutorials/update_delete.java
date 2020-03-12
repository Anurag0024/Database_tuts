package com.example.databasetutorials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_delete extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button b1,b2;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        myDB = new DatabaseHelper(this);

        e1=(EditText)findViewById(R.id.ed1);
        e2=(EditText)findViewById(R.id.ed2);
        e3=(EditText)findViewById(R.id.ed3);
        e4=(EditText)findViewById(R.id.ed4);
        e5=(EditText)findViewById(R.id.ed5);

        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickme();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickdel();
            }
        });
    }
    private void Clickme()
    {
        String id = e1.getText().toString();
        String username = e2.getText().toString();
        String password = e3.getText().toString();
        String marks = e4.getText().toString();
        Boolean result = myDB.updateData(id,username,password,marks);
        if(result==true)
        {
            Toast.makeText(update_delete.this,"data updated sucessfully",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(update_delete.this,"no data affected",Toast.LENGTH_SHORT).show();
        }
    }
    private  void clickdel()
    {
        String id = e1.getText().toString();
        int result = myDB.deleteData(id);
        Toast.makeText(update_delete.this,"rows deleted",Toast.LENGTH_SHORT).show();
    }

}
