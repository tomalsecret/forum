package com.Forum.Entity;

/**
 * Created by Tomal on 2017-05-19.
 */
public class PrivMsg {
    private int private_message_id;
    private String sender_name;
    private String receiver_name;
    private String topic;
    private String content;
    private String date;


    public PrivMsg(int private_message_id, String sender_name, String receiver_name, String topic, String content, String date) {
        this.private_message_id = private_message_id;
        this.sender_name = sender_name;
        this.receiver_name = receiver_name;
        this.topic = topic;
        this.content = content;
        this.date = date;
    }

    PrivMsg() {
    }


    public int getPrivate_message_id() {
        return private_message_id;
    }

    public void setPrivate_message_id(int private_message_id) {
        this.private_message_id = private_message_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
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
}
