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
public class Tag implements Serializable {
    private Long tag_id;

    private String tag_name;

    @JSONField(format = "yyyy-MM-dd")
    private Date tag_create_time;


}