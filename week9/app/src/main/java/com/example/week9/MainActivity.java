package com.example.week9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NoteFragment.OnNotesListInteractionListener {
    private boolean dislayingEditor = false;
    private Note editingNote;
    private ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes = retrieveNotes();

        if (!dislayingEditor){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, NoteFragment.newInstance(notes));
            fragmentTransaction.commit();
        }
        else{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, EditNoteFragment.newInstance(readContent(editingNote)));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dislayingEditor = !dislayingEditor;
        invalidateOptionsMenu();
        switch (item.getItemId()){
            case R.id.action_new:
                editingNote = createNote();
                notes.add(editingNote);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, EditNoteFragment.newInstance(""), "edit_note");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.action_close:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        EditNoteFragment editFragment = (EditNoteFragment)
                getSupportFragmentManager().findFragmentByTag("edit_note");
        if (editFragment != null){
            String content = editFragment.getContent();
            saveContent(editingNote, content);
        }
        super.onBackPressed();

    }

    private void saveContent(Note editingNote, String content) {
        editingNote.setDate(new Date());
        String header = content.length() < 30 ? content : content.substring(0,30);
        editingNote.setHeader(header.replaceAll("\n", " "));
        FileWriter writer = null;
        File file = new File(editingNote.getFilePath());
        try {
            writer = new FileWriter(file);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
        Log.d("Saving tp Pref","key = " + file.getName() +" value = " + editingNote.getHeader());
        editor.putString(file.getName(),editingNote.getHeader());
        editor.commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_new).setVisible(!dislayingEditor);
        menu.findItem(R.id.action_close).setVisible(dislayingEditor);
        return super.onPrepareOptionsMenu(menu);
    }

    private Note createNote() {
        Note note = new Note();
        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        int next = pref.getInt("next",1);
        File dir = getFilesDir();
        String filePath = dir.getAbsolutePath()+"/note_"+next;
        Log.d("Create Note with path",filePath);
        note.setFilePath(filePath);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("next", next+1);
        editor.commit();
        return note;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private String readContent(Note editingNote) {
        Log.d("Readin Note with path",editingNote.getFilePath());
        StringBuffer content = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(new
                File(editingNote.getFilePath())))) {
            String line;
            while ((line = reader.readLine()) != null){
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private ArrayList<Note> retrieveNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        File dir = getFilesDir();
        File[] files = dir.listFiles();
        for (File file : files) {
            Log.d("Retrieving", "absolute path = " + file.getAbsolutePath());
            Log.d("Retrieving", "name = " + file.getName());
            Note note = new Note();
            note.setFilePath(file.getAbsolutePath());
            note.setDate(new Date(file.lastModified()));
            String header =
                    getPreferences(Context.MODE_PRIVATE).getString(file.getName(), "No Header!");
            note.setHeader(header);
        }
        return notes;
    }

    @Override
    public void onNoteSelected(Note note) {
        editingNote = note;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, EditNoteFragment.newInstance(readContent(editingNote)), "edit_note");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        dislayingEditor = !dislayingEditor;
        invalidateOptionsMenu();
    }
}