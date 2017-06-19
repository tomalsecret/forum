package com.Forum.Service;

import com.Forum.Dao.PrivMsgDao;
import com.Forum.Entity.PrivMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Tomal on 2017-05-19.
 */
@Service
public class PrivMsgService {

    @Autowired
    private PrivMsgDao privMsgDao;

    public Collection<PrivMsg> showReceivedPrivateMessages(String user_name) {

        return this.privMsgDao.showReceivedPrivateMessages(user_name);


    }

    public Collection<PrivMsg> showSentPrivateMessages(String user_name) {
        try {
            return this.privMsgDao.showSentPrivateMessages(user_name);
        } catch (NullPointerException e) {
            return null;
        }

    }


    public void sendPrivateMessage(String user_name, String receiver_name, String topic, String content) {

        this.privMsgDao.sendPrivateMessage(user_name, receiver_name, topic, content);


    }


    public int checkIfMessageBelongsToUser(int id, String user_name) {
        return this.privMsgDao.checkIfMessageBelongsToUser(id, user_name);
    }

    public Collection<PrivMsg> showPrivateMessage(int id) {
        return this.privMsgDao.showPrivateMessage(id);
    }
}
