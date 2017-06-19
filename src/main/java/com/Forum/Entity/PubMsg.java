package com.Forum.Entity;

import java.util.Date;

/**
 * Created by Tomal on 2017-05-14.
 */
public class PubMsg {
    private int public_message_id;
    private String user_name;
    private String topic;
    private String content;
    private String date;
    private String category;
    private int ilosc_plusow;

    public PubMsg(int public_message_id, String user_name, String topic, String content, String date) {
        this.public_message_id = public_message_id;
        this.user_name = user_name;
        this.topic = topic;
        this.content = content;
        this.date = date;
        this.category = category;
        this.ilosc_plusow = ilosc_plusow;
    }

    public PubMsg(){};

    public int getPublic_message_id() {
        return public_message_id;
    }

    public void setPublic_message_id(int public_message_id) {
        this.public_message_id = public_message_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIlosc_plusow() {
        return ilosc_plusow;
    }

    public void setIlosc_plusow(int ilosc_plusow) {
        this.ilosc_plusow = ilosc_plusow;
    }
}
