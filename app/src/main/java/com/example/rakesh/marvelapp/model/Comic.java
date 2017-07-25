package com.example.rakesh.marvelapp.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Comic {

    private String imageUrl;
    private String title;
    private String description;
    //  page count, price and authors of the comic
    private int pageCount;
    private BigDecimal price;
    private List<String> authors = new ArrayList<>();



}
