package com.example.lhenryaxelmeteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView search;
    private ImageView gps;
    private ImageView next;
    private ImageView prev;
    private EditText text;
    private TextView coord;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.search);
        gps = findViewById(R.id.gps);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        text = findViewById(R.id.editTextTextPersonName2);
        coord = findViewById(R.id.coord);
    }
}