package com.rh.blog.mapper;

import com.rh.blog.pojo.Comment;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment order by create_time desc")
    List<Comment> selectAllComment();

    @Update("update comment set comment_sta=#{comment_sta} where id=#{id}")
    void update_sta(Comment comment);

    @Update("update comment set admin_comment=#{admin_comment} where id=#{id}")
    void add_admin_comment(Comment comment);

    void comment_delete(List<Integer> comment_id);

    void comment_pass(List<Integer> comment_id);

    @Select("select count(id) from comment")
    int countComment();

    List<Comment> findBlogComment(Long blog_id);

    @Select("select * from comment where parent_comment_id=#{id} order by create_time desc")
    List<Comment> parent_comment(Long id);

    @Insert("insert into comment(avatar,admin_comment,create_time,email,nickname,blog_id,parent_comment_id,comment_sta,adminComment,parent_nickname,content)" +
            " value(#{avatar},#{admin_comment},now(),#{email},#{nickname},#{blog_id},#{parent_comment_id},#{comment_sta},#{adminComment},#{parent_nickname},#{content})")
    void saveComment(Comment comment);

    @Select("select * from comment where parent_nickname=#{nickname} and nickname='葫鲁娃娃' and comment_sta=1 ORDER BY create_time DESC")
    List<Comment> selectByIdFindParent(String parent_nickname);

    @Select("select * from comment where id=#{id}")
    Comment findCommentById(Long id);
}
