package com.Forum.Dao;

import com.Forum.Entity.PrivMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Tomal on 2017-05-19.
 */
@Repository
public class PrivMsgDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Collection<PrivMsg> showReceivedPrivateMessages(String user_name) {
        String sql;
        List<PrivMsg> private_messages;


        sql = "SELECT * FROM private_message WHERE receiver_name=? ORDER BY date DESC";
        private_messages = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PrivMsg.class), user_name);

        return private_messages;


    }

    public Collection<PrivMsg> showSentPrivateMessages(String user_name) {
        String sql = "SELECT * FROM private_message WHERE sender_name=? ORDER BY date DESC";
        Collection<PrivMsg> sent_private_messages = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PrivMsg.class), user_name);

        return sent_private_messages;


    }


    public void sendPrivateMessage(String user_name, String receiver_name, String topic, String content) {
        final String sql = "INSERT INTO private_message (sender_name, receiver_name, topic, content) VALUES (?,?,?,?)";

        jdbcTemplate.update(sql, new Object[]{user_name, receiver_name, topic, content});


    }


    public int checkIfMessageBelongsToUser(int id, String user_name) {
        String sql = "SELECT * FROM private_message WHERE private_message_id=? AND (receiver_name=? OR sender_name=?)";

        try {
            jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(PrivMsg.class), id, user_name, user_name);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
        return 1;


    }

    public Collection<PrivMsg> showPrivateMessage(int id) {

        String sql = "SELECT * FROM private_message WHERE private_message_id=?";
        Collection<PrivMsg> message = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PrivMsg.class), id);

        return message;


    }
}
