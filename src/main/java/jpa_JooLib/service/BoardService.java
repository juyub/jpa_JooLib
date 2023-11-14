package jpa_JooLib.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpa_JooLib.entity.Board;
import jpa_JooLib.repository.BoardRepository;

@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
//	public Page<Board> readAll(Pageable pageable) {
//		return boardRepository.findAll(pageable);
//	}
	
	@Transactional
	public Page<Board> getBoardList(Pageable pageable) {
	    Page<Board> boards = boardRepository.findByParentIsNull(pageable);
	    List<Board> allBoards = new ArrayList<>();
	    for (Board board : boards.getContent()) {
	        allBoards.add(board);
	        getAllChildren(board, allBoards);
	    }
	    return new PageImpl<>(allBoards, pageable, allBoards.size());
	}

    private void getAllChildren(Board board, List<Board> list) {
        for (Board child : board.getChildren()) {
            list.add(child);
            getAllChildren(child, list);
        }
    }
    
    public Board create(Board board) {
		return boardRepository.save(board);
	}
    
	public Board read(Integer id) {
		return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board not found."));
	}
	
	public void update(Board board) {
	    Board existingBoard = boardRepository.findById(board.getId())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + board.getId()));
	    existingBoard.setTitle(board.getTitle());
	    existingBoard.setContent(board.getContent());
	    boardRepository.save(existingBoard);
	}

//	public void delete(Integer id) {
//		boardRepository.deleteById(id);
//	}	
	
	@Transactional
	public void deleteWithChildren(Integer id) {
	    Board board = boardRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
	    for(Board child : board.getChildren()){
	        boardRepository.delete(child);
	    }
	    boardRepository.delete(board);
	}  
    
	@Transactional
	public void addReply(Board board, Integer parentId) {
	    Board parent = boardRepository.findById(parentId)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + parentId));

	    Board newBoard = new Board();
	    newBoard.setParent(parent);
	    newBoard.setUserId(board.getUserId());
	    newBoard.setTitle(board.getTitle());
	    newBoard.setContent(board.getContent());
	    newBoard.setRegTime(board.getRegTime());
	    newBoard.setHit(board.getHit());
	    newBoard.setLevel(parent.getLevel() + 1);  // Set level

	    boardRepository.save(newBoard);
	}
	
}
