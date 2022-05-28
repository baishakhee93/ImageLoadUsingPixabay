package com.shakhee.imageloadusingpixabay.model;

import java.io.Serializable;

public class Item implements Serializable {
    String imageUrl,tags,comments,likes,type;
    String id, webformatWidth,webformatHeight,views,downloads,collections,user_id,user;




    public Item(String webformatURL, String likes, String tags, String comments) {
        this.imageUrl=webformatURL;
        this.likes=likes;
        this.tags=tags;
        this.comments=comments;
    }

    public Item(String id, String webformatURL, String likes, String tags, String comments, String webformatWidth, String webformatHeight, String views, String downloads, String collections, String user_id, String user, String type) {
        this.id=id;
        this.imageUrl=webformatURL;
        this.likes=likes;
        this.tags=tags;
        this.webformatWidth=webformatWidth;
        this.webformatHeight=webformatHeight;
        this.views=views;
        this.downloads=downloads;
        this.collections=collections;
        this.comments=comments;
        this.user_id=user_id;
        this.user=user;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(String webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public String getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(String webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getCollections() {
        return collections;
    }

    public void setCollections(String collections) {
        this.collections = collections;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
