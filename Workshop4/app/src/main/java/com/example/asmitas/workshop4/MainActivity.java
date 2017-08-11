package com.example.asmitas.workshop4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asmitas.workshop4.Adapter.StudentListAdapter;
import com.example.asmitas.workshop4.data.Student;
import com.example.asmitas.workshop4.database.DatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name, address, roll;
    Button save, cancel, listView;
    DatabaseHelper databaseHelper;
//    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.address);
        roll = (EditText)findViewById(R.id.roll);

        save = (Button)findViewById(R.id.Save);
        cancel =(Button)findViewById(R.id.Cancel);
        listView = (Button) findViewById(R.id.listview);
        databaseHelper = new DatabaseHelper(this, null, null, 0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(
                        1,
                        name.getText().toString(),
                        address.getText().toString(),
                        Integer.parseInt(roll.getText().toString())
                );
//                Toast.makeText(MainActivity.this, "Name:"+ student.getName()+"Address:"+
//                        student.getAddress()+"Roll"+student.getRoll(),Toast.LENGTH_LONG).show();
                Boolean status = databaseHelper.insertStudent(student);
                if(status) {
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Failed",Toast.LENGTH_SHORT ).show();
                }
                Intent intent = new Intent(MainActivity.this, ListStudents.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                address.setText("");
                roll.setText("");
            }
        });
//        fetchAllStudent();
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListStudents.class);
                startActivity(intent);
            }
        });
    }

//    public void fetchAllStudent(){
//        List<Student> studentList = databaseHelper.getStudents();
//        StudentListAdapter studentListAdapter = new StudentListAdapter(this, studentList, databaseHelper);
//        listView.setAdapter(studentListAdapter);
//    }

}
