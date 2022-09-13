package com.rh.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
    private Long id;

    private Integer admin_message;

    private String avatar;

    @JSONField(format = "yyyy-MM-dd")
    private Date create_time;

    private String email;

    private String nickname;

    private byte message_sta;

    private Long parent_message_id;

    private String content;

    private String parent_nickname;

    private List<Message> sonMassage;

    private Message meMassage;


}