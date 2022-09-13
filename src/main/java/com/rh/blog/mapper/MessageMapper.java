package com.rh.blog.mapper;

import com.rh.blog.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {


    List<Message> findAllMassage();

    @Insert("insert into message (admin_message,avatar,content,create_time,email,nickname,parent_message_id,message_sta,parent_nickname)" +
            " value (#{admin_message},#{avatar},#{content},now(),#{email},#{nickname},#{parent_message_id},#{message_sta},#{parent_nickname})")
    void saveMessage(Message message);

    @Select("select * from message where parent_message_id=0 and message_sta=1 order by create_time desc")
    List<Message> findFirstMassage();

    @Select("select * from message where parent_message_id=#{id} and message_sta=1 order by create_time desc")
    List<Message> findSonMassage(Long id);

    void messages_delete(List<Integer> message_id);

    void messages_pass(List<Integer> message_id);

    @Select("select count(id) from message")
    int coutMessage();

    List<Message> selectByIdFindParent(String nickname);

    @Select("select * from message where id=#{id}")
    Message selectMessageById(Long id);
}
