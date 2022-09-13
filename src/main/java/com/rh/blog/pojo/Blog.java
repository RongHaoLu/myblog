package com.rh.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
    private Long id;

    private Byte commentabled;

    @JSONField(format = "yyyy-MM-dd")
    private Date create_time;

    private String first_picture;

    private String flag;

    private Byte published;

    private Byte recommend;

    private String title;

    @JSONField(format = "yyyy-MM-dd")
    private Date update_time;

    private Integer views;

    private Long type_id;

    private Long user_id;

    private String content;

    private String description;

    private Type type;

    private Tag tag;


}