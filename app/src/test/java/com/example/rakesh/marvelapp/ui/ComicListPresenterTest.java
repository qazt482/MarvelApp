package com.example.rakesh.marvelapp.ui;

import com.example.rakesh.marvelapp.data.ComicsRepository;
import com.example.rakesh.marvelapp.model.Comic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class ComicListPresenterTest {

    @Mock
    ComicListView view;

    @Mock
    ComicsRepository comicsRepository;

    @InjectMocks
    ComicListPresenter presenter;

    @Test
    public void shouldLoadComics() {

        Mockito.when(comicsRepository.getComics()).thenReturn(Observable.just(new Comic.ComicBuilder().build()));

        presenter.getComics();

        Mockito.verify(view).addComics(isA(List.class));
    }

}