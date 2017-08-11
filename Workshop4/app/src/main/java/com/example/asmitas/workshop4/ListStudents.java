package com.example.asmitas.workshop4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.asmitas.workshop4.Adapter.StudentListAdapter;
import com.example.asmitas.workshop4.data.Student;
import com.example.asmitas.workshop4.database.DatabaseHelper;

import java.util.List;

/**
 * Created by AsmitaS on 7/15/2017.
 */
public class ListStudents extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        listView = (ListView) findViewById(R.id.listviews);

        databaseHelper = new DatabaseHelper(this, null, null, 0);
        List<Student> studentList = databaseHelper.getStudents();
        StudentListAdapter studentListAdapter = new StudentListAdapter(this,studentList, databaseHelper);
        listView.setAdapter(studentListAdapter);

    }
}
