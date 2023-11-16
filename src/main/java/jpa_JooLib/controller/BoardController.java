package jpa_JooLib.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	// 1page부터
	@RequestMapping("boardList")
	public String boardList(Model model,
	                       @RequestParam(defaultValue = "1") int page,
	                       @RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page - 1, size, Sort.by("boardNo").descending().and(Sort.by("parent").descending()));
	    Page<Board> boardPage = boardService.getBoardList(pageable);
	    model.addAttribute("boardList", boardPage.getContent());
	    model.addAttribute("page", boardPage);
	    return "board/boardList";
	}

	// 0page부터
//	@RequestMapping("boardList")
//	public String boardList(Model model,
//	                       @RequestParam(defaultValue = "0") int page,
//	                       @RequestParam(defaultValue = "10") int size) {
//	    Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending().and(Sort.by("parent").descending()));
//	    Page<Board> boardPage = boardService.getBoardList(pageable);
//	    model.addAttribute("boardList", boardPage.getContent());
//	    model.addAttribute("page", boardPage);
//	    return "board/boardList";
//	}
    
    @RequestMapping("addBoard")
    public String addBoard(@ModelAttribute Board board) {
        boardService.create(board);
        return "redirect:/boardList";
    }
    
    @RequestMapping("getBoard")
    public String getBoard(@RequestParam("boardNo") Integer boardNo, Model model) {
        Board board = boardService.read(boardNo);
        List<Comment> commentList =  commentService.getCommentsByBoardNo(boardNo);
        model.addAttribute("board", board);
        model.addAttribute("commentList", commentList);
        return "board/getBoard";
    }
    
    @RequestMapping(value = "updateBoard", method = RequestMethod.POST)
    public String updateBoard(@ModelAttribute Board board) {
        boardService.update(board);
        return "redirect:/getBoard?boardNo=" + board.getBoardNo();
    }
    
    @RequestMapping("addReply")
    public String addReply(Board board, @RequestParam("boardNo") Integer parentno) {
        boardService.addReply(board, parentno);
        return "redirect:/boardList";
    }
    
    @Transactional
    @RequestMapping("deleteBoard")
    public String deleteBoard(@RequestParam("boardNo") Integer boardNo) {
    	commentService.deleteByBoardNo(boardNo);
    	boardService.deleteWithChildren(boardNo);
//    	boardService.delete(id);
    	return "redirect:/boardList";
    }
   
}