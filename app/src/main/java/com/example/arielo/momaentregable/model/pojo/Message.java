package com.example.arielo.momaentregable.model.pojo;

/**
 * Created by Arielo on 11/7/2018.
 */

public class Message {

    private String message;
    private String urlPhoto;
    private String name;
    private String photoUser;
    private String type_message;
    private String emisor;

    public Message() {
    }


    public Message(String message, String name, String photoUser, String type_message, String emisor) {
        this.message = message;
        this.name = name;
        this.photoUser = photoUser;
        this.type_message = type_message;
        this.emisor = emisor;
    }

    public Message(String message, String urlPhoto, String name, String photoUser, String type_message, String emisor) {
        this.message = message;
        this.urlPhoto = urlPhoto;
        this.name = name;
        this.photoUser = photoUser;
        this.type_message = type_message;
        this.emisor = emisor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }
}
