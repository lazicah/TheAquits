package com.theacquits.mobile.theaquits.model;

import com.theacquits.mobile.theaquits.MainActivity;

/**
 * Created by root on 3/18/19.
 */

public class TopicHeader  {
    private String Header;
    private int icon;

    public TopicHeader(String header) {
        Header = header;

    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


}
