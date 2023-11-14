package jpa_JooLib.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.Board;
import jpa_JooLib.entity.Comment;
import jpa_JooLib.service.BoardService;
import jpa_JooLib.service.CommentService;

@Controller
@RequestMapping("/")
public class BoardController {
    
    private final BoardService boardService;
    private final CommentService commentService;
    
	public BoardController(BoardService boardService, CommentService commentService) {
		this.boardService = boardService;
		this.commentService = commentService;
	}
    
	@RequestMapping("boardList")
	public String boardList(Model model,
	                       @RequestParam(defaultValue = "0") int page,
	                       @RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending().and(Sort.by("parent").descending()));
	    Page<Board> boardPage = boardService.getBoardList(pageable);
	    model.addAttribute("boardList", boardPage.getContent());
	    model.addAttribute("page", boardPage);
	    return "board/boardList";
	}
    
    @RequestMapping("addBoard")
    public String addBoard(@ModelAttribute Board board) {
        boardService.create(board);
        return "redirect:/boardList";
    }
    
    @RequestMapping("getBoard")
    public String getBoard(@RequestParam("id") Integer id, Model model) {
        Board board = boardService.read(id);
        List<Comment> commentList =  commentService.getCommentsByBoardId(id);
        model.addAttribute("board", board);
        model.addAttribute("commentList", commentList);
        return "board/getBoard";
    }
    
    @RequestMapping(value = "updateBoard", method = RequestMethod.POST)
    public String updateBoard(@ModelAttribute Board board) {
        boardService.update(board);
        return "redirect:/getBoard?id=" + board.getId();
    }
    
    @RequestMapping("addReply")
    public String addReply(Board board, @RequestParam("id") Integer parentId) {
        boardService.addReply(board, parentId);
        return "redirect:/boardList";
    }
    
    @RequestMapping("deleteBoard")
    public String deleteBoard(@RequestParam("id") Integer id) {
    	commentService.deleteByBoardId(id);
    	boardService.deleteWithChildren(id);
//    	boardService.delete(id);
    	return "redirect:/boardList";
    }
   
}