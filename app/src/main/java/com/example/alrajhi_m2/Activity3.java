package com.example.alrajhi_m2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    ListView listView;
    DBHelper db;
    Button btnActivity2,btnDelete;
    List<String> list;
    EditText edtxtDelete;
    ArrayAdapter<String> itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        db= new DBHelper(Activity3.this);
        listView = findViewById(R.id.listview);
        edtxtDelete = findViewById(R.id.edtxtDelete);
        btnActivity2 = findViewById(R.id.btnAcvitiy2d);
        btnDelete = findViewById(R.id.btnDelete);
        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String term = edtxtDelete.getText().toString().trim();
                db.deleteData(term);
                Toast.makeText(Activity3.this,"Deleted Successfully!",Toast.LENGTH_SHORT).show();
                 itemsAdapter.notifyDataSetChanged();

            }
        });
        list= new ArrayList<>();
        list=  db.getAllData();
        itemsAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemsAdapter);



    }
}