package com.example.rakesh.marvelapp.ui;


import com.example.rakesh.marvelapp.data.ComicsRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ComicListPresenter {

    private ComicListView view;
    private ComicsRepository comicsRepository;

    public ComicListPresenter(ComicListView view, ComicsRepository comicsRepository) {
        this.view = view;
        this.comicsRepository = comicsRepository;
    }

    public void getComics() {
        comicsRepository.getComics()
                .subscribeOn(Schedulers.io())
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comics -> view.addComics(comics));
    }
}
