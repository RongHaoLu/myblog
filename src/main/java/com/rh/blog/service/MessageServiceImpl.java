package com.rh.blog.service;

import com.rh.blog.mapper.MessageMapper;
import com.rh.blog.pojo.Message;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    @Cacheable(value = "findAllMassage")
    public List<Message> findAllMassage() {

        List<Message> firstMassage = messageMapper.findFirstMassage();
        for (int i = 0;i<firstMassage.size();i++){
            List<Message> sonMassage = messageMapper.findSonMassage(firstMassage.get(i).getId());
            firstMassage.get(i).setSonMassage(sonMassage);
        }


        return firstMassage;
    }

    @Override
    @CacheEvict(value = "findAllMassage",allEntries = true)
    public void saveMessage(Message message) {

        if (message.getParent_message_id()==null){
            message.setParent_message_id((long) 0);
        }

        message.setMessage_sta((byte) 0);
        message.setAvatar("/images/cr.png");
        message.setAdmin_message(0);
        messageMapper.saveMessage(message);

    }

    @Override
    public List<Message> selectAllMessage() {
        List<Message> allMassage = messageMapper.findAllMassage();
        for (int i=0;i<allMassage.size();i++){
            List<Message> messages = messageMapper.selectByIdFindParent(allMassage.get(i).getNickname());
            if(messages.size()!=0){
                allMassage.get(i).setMeMassage(messages.get(0));
            }

        }

        return allMassage;
    }


    @Override
    @CacheEvict(value = "findAllMassage",allEntries = true)
    public void message_delete(List<Integer> message_id) {
        messageMapper.messages_delete(message_id);
    }

    @Override
    @CacheEvict(value = "findAllMassage",allEntries = true)
    public void message_pass(List<Integer> message_id) {

       messageMapper.messages_pass(message_id);
    }

    @Override
    public int coutMessage() {
        return messageMapper.coutMessage();
    }

    @Override
    @CacheEvict(value = "findAllMassage",allEntries = true)
    public void admin_message(Long id, String admin_message) {

        Message message = messageMapper.selectMessageById(id);
        Message adminMessage = new Message();
        if(message.getParent_message_id()==0){
            adminMessage.setParent_message_id(message.getId());
        }else {
            adminMessage.setParent_message_id(message.getParent_message_id());
        }
        adminMessage.setAvatar("/images/gourd.jpg");
        adminMessage.setContent(admin_message);
        adminMessage.setMessage_sta((byte) 1);
        adminMessage.setAdmin_message(2);
        adminMessage.setParent_nickname(message.getNickname());
        adminMessage.setNickname("葫鲁娃娃");

        messageMapper.saveMessage(adminMessage);
    }
}
