package com.rh.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {
    private Long id;

    private String name;

    @JSONField(format = "yyyy-MM-dd")
    private Date create_time;

    private List<Blog> blogs = new ArrayList<>();

}