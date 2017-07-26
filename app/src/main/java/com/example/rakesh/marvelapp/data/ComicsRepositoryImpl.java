package com.example.rakesh.marvelapp.data;


import com.example.rakesh.marvelapp.model.Comic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicsRepositoryImpl implements ComicsRepository {

    private final MarvelService marvelService;
    private String publicKey;
    private String privateKey;

    public ComicsRepositoryImpl(String baseUrl, String publicKey, String privateKey) {
        if (publicKey == null || privateKey == null) throw new RuntimeException("Keys not found!");
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        marvelService = retrofit.create(MarvelService.class);
    }

    @Override
    public Observable<Comic> getComics() {

        String now = String.valueOf(System.currentTimeMillis());

        Map<String, String> options = new HashMap<>();
        options.put("limit", "100");
        options.put("ts", now);
        options.put("apikey", publicKey);
        options.put("hash", getMD5EncryptedString(now + privateKey + publicKey));

        return marvelService.getComics(options)
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

    private static String getMD5EncryptedString(String encTarget) {
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        mdEnc.update(encTarget.getBytes(), 0, encTarget.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }
        return md5;
    }
}
