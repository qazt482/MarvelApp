package com.example.rakesh.marvelapp.data;


import java.util.List;

public class ComicsDto {

    public int code;
    public Data data;
}

class Data {

    private int count;
    private int total;
    public List<Results> results;

}

class Results {

    private long id;
    public String title;
    public String description;
    public int pageCount;
    public List<Prices> prices;
    public Creators creators;
    public ImageDto thumbnail;
//    private ImageDto images;
}

class Prices {
    public String type;
    public double price;
}

class Creators {
    public List<Item> items;
}

class Item {
    public String name;
}

class ImageDto {
    public String path;
    public String extension;
}