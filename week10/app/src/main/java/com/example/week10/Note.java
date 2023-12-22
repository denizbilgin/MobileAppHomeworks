package com.example.week10;

import com.google.firebase.database.Exclude;

public class Note {
    @Exclude  // Veri tabanına kaydederken istemediğin kolonu yazma diyoruz
    private String id;

}
