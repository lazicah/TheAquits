package com.theacquits.mobile.theaquits.model;

/**
 * Created by root on 3/18/19.
 */

public class HeaderItem extends ListItem {

   private String header;
   private int icon;
    // here getters and setters
    // for title and so on, built
    // using date

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

}