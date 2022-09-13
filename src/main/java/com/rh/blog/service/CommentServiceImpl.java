package com.rh.blog.service;

import com.rh.blog.mapper.CommentMapper;
import com.rh.blog.pojo.Comment;
import com.rh.blog.pojo.Message;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectAllComment() {
        List<Comment> allComment = commentMapper.selectAllComment();
        for (int i=0;i<allComment.size();i++){
            List<Comment> comments = commentMapper.selectByIdFindParent(allComment.get(i).getNickname());
            if(comments.size()!=0){
                allComment.get(i).setMeComment(comments.get(0));
            }

        }
        return allComment;
    }

    @Override
    @CacheEvict(value = "selectAllComment",allEntries = true)
    public void update_sta(Comment comment) {

        commentMapper.update_sta(comment);
    }

    @Override
    @CacheEvict(value = "selectAllComment",allEntries = true)
    public void add_admin_comment(Comment comment) {

        commentMapper.add_admin_comment(comment);
    }

    @Override
    @CacheEvict(value = "selectAllComment",allEntries = true)
    public void comment_delete(List<Integer> comment_id) {

        commentMapper.comment_delete(comment_id);
    }

    @Override
    @CacheEvict(value = "selectAllComment",allEntries = true)
    public void comment_pass(List<Integer> list) {

        commentMapper.comment_pass(list);
    }

    @Override
    public int countComment() {

        return commentMapper.countComment();
    }

    @Override
    @Cacheable(value = "selectAllComment")
    public List<Comment> findBlogComment(Long blog_id) {
        List<Comment> commentList = commentMapper.findBlogComment(blog_id);
        for (int i=0;i<commentList.size();i++){
            commentList.get(i).setReplyComments(commentMapper.parent_comment(commentList.get(i).getId()));
        }

        return commentList;
    }

    @Override
    @CacheEvict(value = "selectAllComment",allEntries = true)
    public void saveComment(Comment comment) {


        if(comment.getParent_comment_id()==null){
            comment.setParent_comment_id((long) 0);
        }
        comment.setAvatar("/images/cr.png");
        comment.setComment_sta((byte) 0);
        comment.setAdminComment(0);
        comment.setAdmin_comment(0);

        commentMapper.saveComment(comment);
    }

    @Override
    @CacheEvict(value = "selectAllComment",allEntries = true)
    public void adminComment(Long id,String meComment) {

        Comment comment=commentMapper.findCommentById(id);

        Comment admin_comment = new Comment();
        if(comment.getParent_comment_id()==0){
            admin_comment.setParent_comment_id(comment.getId());
        }else {
            admin_comment.setParent_comment_id(comment.getParent_comment_id());
        }
        admin_comment.setNickname("葫鲁娃娃");
        admin_comment.setAdminComment(2);
        admin_comment.setComment_sta((byte) 1);
        admin_comment.setAvatar("/images/gourd.jpg");
        admin_comment.setContent(meComment);
        admin_comment.setAdmin_comment(2);
        admin_comment.setParent_nickname(comment.getNickname());
        admin_comment.setBlog_id(comment.getBlog_id());

        System.out.println(admin_comment);

        commentMapper.saveComment(admin_comment);
    }

}
