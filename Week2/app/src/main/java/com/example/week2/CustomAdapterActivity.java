package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {

    final List<Animal> animalList = new ArrayList<Animal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);

        animalList.add(new Animal("Cat", R.mipmap.hatelife));
        animalList.add(new Animal("Dog", R.mipmap.ic_launcher));

        final ListView listView = findViewById(R.id.listView);
        AnimalAdapter animalAdapter = new AnimalAdapter(this, animalList);
        listView.setAdapter(animalAdapter);

    }
}