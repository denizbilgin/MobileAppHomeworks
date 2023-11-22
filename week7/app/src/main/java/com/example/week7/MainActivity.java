package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements MovieFragment.OnMovieSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void movieSelected(Movie movie) {
        int displayMode = getResources().getConfiguration().orientation;
        if (displayMode == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("movie", (Serializable) movie);
            startActivity(intent);
        }
        else {
            DetailsFragment detailsFragment = (DetailsFragment)
                    getSupportFragmentManager().findFragmentByTag("details");
            if (detailsFragment == null) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                detailsFragment = DetailsFragment.newInstance(movie);
                fragmentTransaction.add(R.id.container, detailsFragment, "details");
                fragmentTransaction.commit();
            }
            else {
                detailsFragment.setMovie(movie, findViewById(R.id.container));
            }
        }
    }
}