package com.Forum.Entity;

/**
 * Created by Tomal on 2017-05-14.
 */
public class Likes {
    private int like_id;
    private int public_message_id;
    private String user_name;

    public Likes(int like_id, int public_message_id, String user_name) {
        this.like_id = like_id;
        this.public_message_id = public_message_id;
        this.user_name = user_name;
    }

    public int getLike_id() {
        return like_id;
    }

    public void setLike_id(int like_id) {
        this.like_id = like_id;
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
}
