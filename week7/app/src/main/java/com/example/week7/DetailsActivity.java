package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DetailsFragment detailsFragment = DetailsFragment.newInstance(movie);
        fragmentTransaction.add(R.id.container, detailsFragment);
        fragmentTransaction.commit();
    }
}