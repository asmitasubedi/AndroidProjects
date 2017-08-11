package com.example.asmitas.workshopclass3;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by AsmitaS on 7/1/2017.
 */
public class Home extends AppCompatActivity {
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        String name = getIntent().getStringExtra("name");
        int a = getIntent().getIntExtra("int", 0);
        boolean ab =getIntent().getBooleanExtra("bool", true);
        Toast.makeText(Home.this, "name"+name, Toast.LENGTH_SHORT).show();
    }
}
