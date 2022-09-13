package com.rh.blog.service;

import com.rh.blog.pojo.Comment;
import com.rh.blog.pojo.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAllMassage();

    void saveMessage(Message message);

    List<Message> selectAllMessage();

    void message_delete(List<Integer> message_id);

    void message_pass(List<Integer> message_id);

    int coutMessage();

    void admin_message(Long id,String admin_message);

}
