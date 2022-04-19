package com.example.alrajhi_m2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    EditText edtxtName,edtxtSurName,edtxtId,edtxtNationId;
    Button btnInsert,btnActivity1,btnAcitivity3;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btnInsert =  findViewById(R.id.btnInsert);
        btnActivity1 =  findViewById(R.id.btnActivity1);
        btnAcitivity3 =  findViewById(R.id.btnActivity3);
        edtxtId = findViewById(R.id.edtxtId);
        edtxtName = findViewById(R.id.edtxtName);
        edtxtNationId = findViewById(R.id.edtxtNationId);
        edtxtSurName = findViewById(R.id.edtxtSurname);

        db= new DBHelper(Activity2.this);

        btnActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAcitivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtxtId.getText().toString().trim();
                String name = edtxtName.getText().toString().trim();
                String nat_id = edtxtNationId.getText().toString().trim();
                String surname = edtxtSurName.getText().toString().trim();
                if(!id.equals("") && !name.equals("") && !surname.equals("") && !nat_id.equals("")  ){
                    User user=new User();
                    user.set_Id(id);
                    user.set_firstName(name);
                    user.set_Surname(surname);
                    user.set_NationalId(nat_id);
                    if(db.insertUser(user)>0){
                        edtxtId.getText().clear();
                        edtxtSurName.getText().clear();
                        edtxtNationId.getText().clear();
                        edtxtName.getText().clear();
                        Toast.makeText(Activity2.this,"Added Successfully!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Activity2.this,"Could not add data",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Activity2.this,"Please enter values",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}