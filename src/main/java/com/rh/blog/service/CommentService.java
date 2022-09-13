package com.rh.blog.service;

import com.rh.blog.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectAllComment();

    void update_sta(Comment comment);

    void add_admin_comment(Comment comment);

    void comment_delete(List<Integer> comment_id);

    void comment_pass(List<Integer> list);

    int countComment();

    List<Comment> findBlogComment(Long blog_id);

    void saveComment(Comment comment);

    void adminComment(Long id,String meComment);

}
