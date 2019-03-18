package com.theacquits.mobile.theaquits.model;

/**
 * Created by root on 3/18/19.
 */

public abstract class ListItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_EVENT = 1;

    abstract public int getType();
}
