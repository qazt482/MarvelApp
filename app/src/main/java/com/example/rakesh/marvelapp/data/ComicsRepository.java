package com.example.rakesh.marvelapp.data;

import com.example.rakesh.marvelapp.model.Comic;

import io.reactivex.Observable;


public interface ComicsRepository {

    Observable<Comic> getComics();
}
