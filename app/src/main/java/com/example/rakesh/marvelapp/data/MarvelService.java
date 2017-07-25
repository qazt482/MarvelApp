package com.example.rakesh.marvelapp.data;



import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MarvelService {

    @GET("v1/public/comics")
    Observable<ComicsDto> getComics();


}
