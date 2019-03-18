package com.theacquits.mobile.theaquits.model;

/**
 * Created by root on 3/18/19.
 */


public class TopicItem extends ListItem {

    private Topics topics;

    public Topics getTopics() {
        return topics;
    }

    public void setTopics(Topics topics) {
        this.topics = topics;
    }
    // here getters and setters
    // for title and so on, built
    // using event

    @Override
    public int getType() {
        return TYPE_EVENT;
    }

}