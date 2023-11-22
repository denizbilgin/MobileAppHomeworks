package com.example.week7;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MovieFragment extends Fragment {

    private OnMovieSelected listener;
    private List<Movie> movies = new ArrayList<>();

    // TODO: Customize parameters
    private int mColumnCount = 1;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MovieFragment newInstance(int columnCount) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        movies.add(new Movie("The Shawshank Redemption","Frank Darabont",1994,
                Arrays.asList(new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}),
                "Two imprisoned men bond over a number of years, " +
                        "finding solace and eventual redemption through acts of common decency."));
        movies.add(new Movie("The Godfather","Francis Ford Coppola",1972,
                Arrays.asList(new String[]{"Marlon Brando", "Al Pacino", " James Caan"}),
                "The aging patriarch of an organized crime dynasty transfers control of his "
                        +
                        "clandestine empire to his reluctant son."));
        movies.add(new Movie("Pulp Fiction","Quentin Tarantino",1994,
                Arrays.asList(new String[]{"John Travolta", "Uma Thurman", "Samuel L. Jackson"}),
                        "The aging patriarch of an organized crime dynasty transfers control of " +
                                "his clandestine empire to his reluctant son."));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMovieRecyclerViewAdapter(movies, listener));
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnMovieSelected){
            listener = (OnMovieSelected) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnMovieSelected{
        void movieSelected(Movie movie);
    }
}