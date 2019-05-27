package com.kyra.moviedisplay;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecyclerViewData implements Serializable {
    @SerializedName("Title")
    @Expose
    private String name;
    @SerializedName("Poster")
    @Expose
    private String image;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;

    public RecyclerViewData(String image, String movieName,String imdbID) {
        setImage(image);
        setName(movieName);
        setImdbID(imdbID);

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}
