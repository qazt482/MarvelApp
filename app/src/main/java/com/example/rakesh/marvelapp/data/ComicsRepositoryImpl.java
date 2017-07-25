package com.example.rakesh.marvelapp.data;


import com.example.rakesh.marvelapp.model.Comic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicsRepositoryImpl implements ComicsRepository {

    private final MarvelService marvelService;

    public ComicsRepositoryImpl(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        marvelService = retrofit.create(MarvelService.class);
    }

    @Override
    public Observable<Comic> getComics() {

        return marvelService.getComics()
                .flatMap(comicsDto -> observer -> {
                    for (Results result : comicsDto.data.results) {

                        BigDecimal price = new BigDecimal(0);
                        for (Prices prices : result.prices) {
                            if ("printPrice".equalsIgnoreCase(prices.type)) {
                                price = new BigDecimal(prices.price);
                            }
                        }

                        List<String> creators = new ArrayList<>();
                        for (Item item : result.creators.items) {
                            creators.add(item.name);
                        }

                        Comic comic = new Comic.ComicBuilder()
                                .setTitle(result.title)
                                .setDescription(result.description)
                                .setPageCount(result.pageCount)
                                .setPrice(price)
                                .setCreators(creators)
                                .setImageUrl(result.thumbnail.path + "/standard_medium." + result.thumbnail.extension)
                                .build();
                        observer.onNext(comic);
                    }

                    observer.onComplete();
                });

    }
}
