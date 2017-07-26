package com.example.rakesh.marvelapp.data;



import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MarvelService {

    @GET("v1/public/comics")
    Observable<ComicsDto> getComics(@QueryMap Map<String, String> options);


}
