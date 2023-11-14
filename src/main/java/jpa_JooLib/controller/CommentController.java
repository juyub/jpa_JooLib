package jpa_JooLib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.Board;
import jpa_JooLib.entity.Comment;
import jpa_JooLib.service.CommentService;

@Controller
@RequestMapping("/")
public class CommentController {
	
    private final CommentService commentService;
    
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment) {
    	commentService.create(comment);
    	return "redirect:/getBoard?id=" + comment.getBoardId();
    }

    @RequestMapping(value = "updateComment", method = RequestMethod.POST)
    public String updateComment(@ModelAttribute Comment comment) {
        commentService.update(comment);
        return "redirect:/getBoard?id=" + comment.getBoardId();
    }
    
    @RequestMapping("deleteComment")
    public String deleteComment(@RequestParam("id") Integer id,
    					 @RequestParam("boardId") Integer boardId) {
    	commentService.delete(id);
        return "redirect:/getBoard?id=" + boardId;
    }
    
//    @GetMapping("/{commentId}")
//    public Comment read(@PathVariable Integer boardId, @PathVariable Integer commentId) {
//        return commentService.read(boardId, commentId);
//    }

}
