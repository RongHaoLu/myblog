package com.rh.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;

    private String avatar;

    private Date create_time;

    private String email;

    private String nickname;

    private String password;

    private Integer type;

    private Date update_time;

    private String username;


}