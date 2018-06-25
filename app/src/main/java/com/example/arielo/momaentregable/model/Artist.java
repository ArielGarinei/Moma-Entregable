package com.example.arielo.momaentregable.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arielo on 24/6/2018.
 */

public class Artist {
    @SerializedName("Influenced_by")
    @Expose
    private String influencedBy;
    @SerializedName("artistId")
    @Expose
    private String artistId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("paint1")
    @Expose
    private String paint1;
    @SerializedName("paint2")
    @Expose
    private String paint2;
    @SerializedName("paint3")
    @Expose
    private String paint3;
    @SerializedName("paint4")
    @Expose
    private String paint4;
    @SerializedName("paint5")
    @Expose
    private String paint5;
    @SerializedName("paint6")
    @Expose
    private String paint6;
    @SerializedName("paint7")
    @Expose
    private String paint7;

    public String getInfluencedBy() {
        return influencedBy;
    }

    public void setInfluencedBy(String influencedBy) {
        this.influencedBy = influencedBy;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPaint1() {
        return paint1;
    }

    public void setPaint1(String paint1) {
        this.paint1 = paint1;
    }

    public String getPaint2() {
        return paint2;
    }

    public void setPaint2(String paint2) {
        this.paint2 = paint2;
    }

    public String getPaint3() {
        return paint3;
    }

    public void setPaint3(String paint3) {
        this.paint3 = paint3;
    }

    public String getPaint4() {
        return paint4;
    }

    public void setPaint4(String paint4) {
        this.paint4 = paint4;
    }

    public String getPaint5() {
        return paint5;
    }

    public void setPaint5(String paint5) {
        this.paint5 = paint5;
    }

    public String getPaint6() {
        return paint6;
    }

    public void setPaint6(String paint6) {
        this.paint6 = paint6;
    }

    public String getPaint7() {
        return paint7;
    }

    public void setPaint7(String paint7) {
        this.paint7 = paint7;
    }


    @Override
    public String toString() {
        return "Artist{" +
                "artistId='" + artistId + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", influencedBy='" + influencedBy + '\'' +
                '}';
    }
}
