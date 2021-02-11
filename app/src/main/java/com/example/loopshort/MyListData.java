package com.example.loopshort;

import com.amplifyframework.datastore.generated.model.Comment;

import java.util.List;

public class MyListData{
    public   String  ID ;//= ("id");
    public   String TITLE;// = ("title");
    public   String OWNER ;//= ("owner");
    public   String VIEW ;//= ("view");
    public   String  DESCRIPTION;// = ("description");
    public   String IMAGE_URL;//= ("imageUrl");
    public   String VIDEO_URL;

    public MyListData(String id, List<Comment> comments, String description, String imageUrl, String owner, String title, String videoUrl, String view) {
    this.ID =id;
    this.TITLE=title;
    this.OWNER=owner;
    this.VIEW=view;
    this.DESCRIPTION=description;
    this.IMAGE_URL=imageUrl;
    this.VIDEO_URL=videoUrl;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }

    public void setVIEW(String VIEW) {
        this.VIEW = VIEW;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    public void setVIDEO_URL(String VIDEO_URL) {
        this.VIDEO_URL = VIDEO_URL;
    }

    public  String getID() {
        return ID;
    }

    public  String getTITLE() {
        return TITLE;
    }

    public  String getOWNER() {
        return OWNER;
    }

    public  String getVIEW() {
        return VIEW;
    }

    public  String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public  String getImageUrl() {
        return IMAGE_URL;
    }

    public  String getVideoUrl() {
        return VIDEO_URL;
    }
}