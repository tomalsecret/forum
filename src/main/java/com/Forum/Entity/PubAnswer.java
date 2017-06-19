package com.Forum.Entity;

/**
 * Created by Tomal on 2017-05-14.
 */
public class PubAnswer {
    private int public_answer_id;
    private int public_message_id;
    private String user_name;
    private String date;
    private String content;


    public PubAnswer(int public_answer_id, int public_message_id, String user_name, String date, String content) {
        this.public_answer_id = public_answer_id;
        this.public_message_id = public_message_id;
        this.user_name = user_name;
        this.date = date;
        this.content = content;
    }

    PubAnswer() {
    }

    ;


    public int getPublic_answer_id() {
        return public_answer_id;
    }

    public void setPublic_answer_id(int public_answer_id) {
        this.public_answer_id = public_answer_id;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
