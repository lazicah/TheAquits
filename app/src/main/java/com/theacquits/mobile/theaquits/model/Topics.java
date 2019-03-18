package com.theacquits.mobile.theaquits.model;

import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by root on 3/18/19.
 */

public class Topics {

    private String Topic;
    private String Description;
    private String Posts;
    private String Comments;

    private String Category;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }


    public Topics(String topic, String description, String posts, String comments, String category) {
        Topic = topic;
        Description = description;
        Posts = posts;
        Comments = comments;
        Category = category;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPosts() {
        return Posts;
    }

    public void setPosts(String posts) {
        Posts = posts;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

}
