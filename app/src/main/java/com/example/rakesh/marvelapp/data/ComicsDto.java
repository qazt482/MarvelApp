package com.example.rakesh.marvelapp.data;


import java.util.List;

public class ComicsDto {

    private Data data;
}

class Data {

    private int count;
    private int total;
    private List<Results> results;

}

class Results {

    private long id;
    private String title;
    private String description;
    private int pageCount;
    private List<Prices> prices;
    private Creators creators;
    private ImageDto thumbnail;
    private ImageDto images;
}

class Prices {
    private String type;
    private double price;
}

class Creators {

    private int available;
    private List<Item> items;

}

class Item {
    private String name;
    private String role;
}

class ImageDto {
    private String path;
    private String extension;
}