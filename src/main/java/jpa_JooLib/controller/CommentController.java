package jpa_JooLib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jpa_JooLib.entity.Comment;
import jpa_JooLib.service.CommentService;

@Controller
@RequestMapping("/boards/{boardId}/comments")
public class CommentController {
	
    private final CommentService commentService;
    
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment create(@PathVariable Integer boardId, @RequestBody Comment comment) {
        return commentService.create(boardId, comment);
    }

    @GetMapping("/{commentId}")
    public Comment read(@PathVariable Integer boardId, @PathVariable Integer commentId) {
        return commentService.read(boardId, commentId);
    }

    @PutMapping("/{commentId}")
    public Comment update(@PathVariable Integer boardId, @PathVariable Integer commentId, @RequestBody Comment comment) {
        return commentService.update(boardId, commentId, comment);
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Integer boardId, @PathVariable Integer commentId) {
        commentService.delete(boardId, commentId);
    }
    
    @PostMapping("/reply/{parentCommentId}")
    public Comment reply(@PathVariable Integer boardId, @PathVariable Integer parentCommentId, @RequestBody Comment comment) {
        return commentService.reply(boardId, parentCommentId, comment);
    }
    
}
