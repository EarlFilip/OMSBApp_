package com.example.athena.omsbapp_;

public class Movie {
    public Movie() {
    }

    public Movie(String title, String urlImage, String plot) {
        this.title = title;
        this.urlImage = urlImage;
        this.plot = plot;
    }

    private String title;
    private String urlImage;
    private String plot;

    //region Gets Sets
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
    //endregion
}
