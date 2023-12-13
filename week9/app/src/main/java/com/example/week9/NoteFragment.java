package com.example.week9;

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

import com.example.week9.placeholder.PlaceholderContent;

import java.util.ArrayList;

public class NoteFragment extends Fragment {
    private static final String ARG_NOTES = "notes";
    private ArrayList<Note> notes;
    private  OnNotesListInteractionListener listener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoteFragment newInstance(ArrayList<Note> notes) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTES, notes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            notes = (ArrayList<Note>) getArguments().getSerializable(ARG_NOTES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyNoteRecyclerViewAdapter(notes, listener));
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNotesListInteractionListener){
            listener = (OnNotesListInteractionListener) context;
        }
        else{
            throw new RuntimeException(context.getClass().getName() + "should implement OnNotesListInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnNotesListInteractionListener{
        void onNoteSelected(Note note);
    }


}