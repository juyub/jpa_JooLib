package jpa_JooLib.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.Board;
import jpa_JooLib.repository.BoardRepository;
import jpa_JooLib.service.BoardService;

@Controller
@RequestMapping("/")
public class BoardController {
    
    private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
    
	@RequestMapping("boardList")
	public String boardList(Model model,
	                       @RequestParam(defaultValue = "0") int page,
	                       @RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by("regTime").descending());
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
        model.addAttribute("board", board);
        return "board/getBoard";
    }
    
    @RequestMapping("addReply")
    public String addReply(Board board, @RequestParam("id") Integer parentId) {
        boardService.addReply(board, parentId);
        return "redirect:/boardList";
    }
    
    @PutMapping("/{id}")
    public Board update(@PathVariable Integer id, @RequestBody Board board) {
        return boardService.update(id, board);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        boardService.delete(id);
    }
   
}