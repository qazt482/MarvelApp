package com.example.rakesh.marvelapp;

import android.app.Application;

import com.example.rakesh.marvelapp.data.ComicsRepository;
import com.example.rakesh.marvelapp.data.ComicsRepositoryImpl;


public class MarvelApplication extends Application {

    public ComicsRepository comicsRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        comicsRepository = new ComicsRepositoryImpl("http://gateway.marvel.com/", BuildConfig.MARVEL_PUBLIC_KEY, BuildConfig.MARVEL_PRIVATE_KEY);
    }
}
