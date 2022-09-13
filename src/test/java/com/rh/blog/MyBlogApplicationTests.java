package com.rh.blog;

import com.rh.blog.mapper.BlogMapper;
import com.rh.blog.mapper.CommentMapper;
import com.rh.blog.pojo.Blog;
import com.rh.blog.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MyBlogApplicationTests {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private CommentMapper commentMapper;



    @Test
    void contextLoads() {
        //MD5加密
        System.out.println(DigestUtils.md5DigestAsHex("0530lu".getBytes()));
    }

    @Test
    void bloglist(){
        List<Blog> all = blogMapper.findAllBlog();

        System.out.println(all);
    }

    @Test
    void comment(){

        Comment comment = new Comment();
        comment.setComment_sta((byte) 0);
        comment.setId((long) 1);

        commentMapper.update_sta(comment);

    }

}
