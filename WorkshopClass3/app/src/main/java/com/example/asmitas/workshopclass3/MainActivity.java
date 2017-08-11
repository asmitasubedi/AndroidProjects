package com.example.asmitas.workshopclass3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText title, roll;
    Button save, cancel;
    Spinner spiner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (EditText)findViewById(R.id.title);
        roll = (EditText)findViewById(R.id.roll);
        save = (Button)findViewById(R.id.Save);
        cancel =(Button)findViewById(R.id.Cancel);

        spiner = (Spinner) findViewById(R.id.spiner);
        List<String> list = new ArrayList<>();
        list.add("list 1");
        list.add("list 2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(dataAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Home.class);
                intent.putExtra("name","test");
                intent.putExtra("bool", true);
                intent.putExtra("int", 123);
                startActivity(intent);
//                finish();
            }
        });
//        save.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                //title.setText(roll.getText().toString());
//                //roll.setText(roll.getText().toString());
//                Toast.makeText(MainActivity.this, "Title: "+title.getText()
//                ,Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "Roll "+roll.getText(), Toast.LENGTH_LONG).show();
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
//            }
//        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
