package com.example.rakesh.marvelapp.ui;


import com.example.rakesh.marvelapp.data.ComicsRepository;

import io.reactivex.Scheduler;

public class ComicListPresenter {

    private ComicListView view;
    private ComicsRepository comicsRepository;
    private final Scheduler backgroundScheduler;
    private final Scheduler mainScheduler;

    public ComicListPresenter(ComicListView view, ComicsRepository comicsRepository, Scheduler backgroundScheduler, Scheduler mainScheduler) {
        this.view = view;
        this.comicsRepository = comicsRepository;
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;
    }

    public void getComics() {
        comicsRepository.getComics()
                .subscribeOn(backgroundScheduler)
                .toList()
                .observeOn(mainScheduler)
                .subscribe(comics -> view.addComics(comics));
    }
}
