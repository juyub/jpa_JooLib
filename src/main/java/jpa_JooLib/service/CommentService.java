package jpa_JooLib.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jpa_JooLib.entity.Comment;
import jpa_JooLib.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

//  public Comment read(Integer id) {  
//  return commentRepository.findById(id)
//          .orElseThrow(() -> new IllegalArgumentException("Comment not found."));
//}
    
    public List<Comment> getCommentsByBoardNo(Integer boardNo) {
        return commentRepository.findByBoardNo(boardNo);
    }
    
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public void update(Comment comment) {
        Comment existingComment = commentRepository.findById(comment.getCommentNo())
            .orElseThrow(() -> new IllegalArgumentException("Invalid comment Id:" + comment.getCommentNo()));
        existingComment.setContent(comment.getContent());
        commentRepository.save(existingComment);
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }
    
    public void deleteByBoardNo(Integer boardNo) {
        commentRepository.deleteByBoardNo(boardNo);
    }
   
}