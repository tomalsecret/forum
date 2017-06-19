package com.Forum.Service;

import com.Forum.Dao.PubMsgDao;
import com.Forum.Entity.PubAnswer;
import com.Forum.Entity.PubMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Tomal on 2017-05-14.
 */
@Service
public class PubMsgService {
    @Autowired
    private PubMsgDao pubMsgDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Collection<PubMsg> showPosts(String sort_by, String category) {
        return this.pubMsgDao.showPosts(sort_by, category);
    }

    public Collection<PubAnswer> showAnswers(int id) {
        return this.pubMsgDao.showAnswers(id);

    }

    public Collection<PubMsg> showSinglePost(int id) {
        return this.pubMsgDao.showSinglePost(id);
    }

    public void addAnswer(int id,String user_name,String answer) {
        this.pubMsgDao.addAnswer(id,user_name,answer);
    }

    public void addPubMsg(String category, String user_name, String content, String topic) {
        this.pubMsgDao.addPubMsg(category,user_name,content, topic);
    }

    public void addLike(int public_message_id, String user_name) {
        this.pubMsgDao.addLike(public_message_id,user_name);

    }

    public boolean showIfLiked(int id, String user_name) {
        return this.pubMsgDao.showIfLiked(id,user_name);
    }

    public void deletePublicMessage(int id) {
        this.pubMsgDao.deletePublicMessage(id);
    }

    public void deleteAnswer(int answer_id) {
        this.pubMsgDao.deleteAnswer(answer_id);
    }

    public void deleteDislikedPosts() {

        this.pubMsgDao.deleteDislikedPosts();
    }
}
