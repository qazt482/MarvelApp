package com.example.rakesh.marvelapp.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Comic {

    private String imageUrl;
    private String title;
    private String description;
    private int pageCount;
    private BigDecimal price;
    private List<String> creators = new ArrayList<>();

    public Comic(String imageUrl, String title, String description, int pageCount, BigDecimal price, List<String> creators) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.price = price;
        this.creators = creators;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<String> getCreators() {
        return creators;
    }

    public static class ComicBuilder {
        private String imageUrl;
        private String title;
        private String description;
        private int pageCount;
        private BigDecimal price;
        private List<String> creators;

        public ComicBuilder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ComicBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ComicBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ComicBuilder setPageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public ComicBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ComicBuilder setCreators(List<String> creators) {
            this.creators = creators;
            return this;
        }

        public Comic build() {
            return new Comic(imageUrl, title, description, pageCount, price, creators);
        }
    }
}
