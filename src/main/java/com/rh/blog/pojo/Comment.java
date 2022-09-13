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
public class Comment implements Serializable {
    private Long id;

    private Integer admin_comment;

    private String avatar;

    @JSONField(format = "yyyy-MM-dd")
    private Date create_time;

    private String email;

    private String nickname;

    private Long blog_id;

    private Long parent_comment_id;

    private String content;

    private Byte comment_sta;

    private Integer adminComment;

    private String parent_nickname;

    private List<Comment> replyComments;

    private Comment meComment;
}