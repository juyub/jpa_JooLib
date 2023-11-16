package jpa_JooLib.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	// 부모글 10개 기준 페이징
//	@Transactional
//	public Page<Board> getBoardList(Pageable pageable) {
//	    Page<Board> boards = boardRepository.findByParentIsNull(pageable);
//	    List<Board> allBoards = new ArrayList<>();
//	    for (Board board : boards.getContent()) {
//	        allBoards.add(board);
//	        getAllChildren(board, allBoards);
//	    }
//	    return new PageImpl<>(allBoards, pageable, allBoards.size());
//	}
	
//	private void getAllChildren(Board board, List<Board> list) {
//		for (Board child : board.getChildren()) {
//			list.add(child);
//			getAllChildren(child, list);
//		}
//	}	

	// 부모글+자식글 10개 기준 페이징
	@Transactional
	public Page<Board> getBoardList(Pageable pageable) {
	    List<Board> allBoards = new ArrayList<>();
	    List<Board> parents = boardRepository.findByParentIsNull();
	    parents.sort(Comparator.comparing(Board::getBoardNo).reversed());
	    for (Board board : parents) {
	        allBoards.add(board);
	        addAllChildren(board, allBoards);
	    }
	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), allBoards.size());
	    
	    // 요청 페이지가 범위를 초과하는 경우 처리
	    if (start > end) {
	        return Page.empty();
	    }
	    
	    return new PageImpl<>(allBoards.subList(start, end), pageable, allBoards.size());
	}

	private void addAllChildren(Board parent, List<Board> list) {
	    List<Board> children = new ArrayList<>(parent.getChildren());
	    children.sort(Comparator.comparing(Board::getBoardNo).reversed());
	    for (Board child : children) {
	        list.add(child);
	        addAllChildren(child, list);
	    }
	}

	public Board create(Board board) {
		return boardRepository.save(board);
	}

	public Board read(Integer boardNo) {
		return boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("Board not found."));
	}

	public void update(Board board) {
		Board existingBoard = boardRepository.findById(board.getBoardNo())
				.orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + board.getBoardNo()));
		existingBoard.setTitle(board.getTitle());
		existingBoard.setContent(board.getContent());
		boardRepository.save(existingBoard);
	}

//	public void delete(Integer id) {
//		boardRepository.deleteById(id);
//	}	

	@Transactional
	public void deleteWithChildren(Integer boardNo) {
		Board board = boardRepository.findById(boardNo)
				.orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + boardNo));
		for (Board child : board.getChildren()) {
			boardRepository.delete(child);
		}
		boardRepository.delete(board);
	}

	@Transactional
	public void addReply(Board board, Integer parentno) {
		Board parent = boardRepository.findById(parentno)
				.orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + parentno));

		Board newBoard = new Board();
		newBoard.setParent(parent);
		newBoard.setUserId(board.getUserId());
		newBoard.setTitle(board.getTitle());
		newBoard.setContent(board.getContent());
		newBoard.setRegTime(board.getRegTime());
		newBoard.setHit(board.getHit());
		newBoard.setLevel(parent.getLevel() + 1); // Set level

		boardRepository.save(newBoard);
	}

}
