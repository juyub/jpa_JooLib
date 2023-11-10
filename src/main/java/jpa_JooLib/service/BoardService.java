package jpa_JooLib.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jpa_JooLib.entity.Board;
import jpa_JooLib.repository.BoardRepository;

@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public Page<Board> readAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board create(Board board) {
		return boardRepository.save(board);
	}

	public Board read(Integer id) {
		return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board not found."));
	}

	public Board update(Integer id, Board board) {
		Board existingBoard = read(id);
		existingBoard.setTitle(board.getTitle());
		existingBoard.setContent(board.getContent());
		return boardRepository.save(existingBoard);
	}

	public void delete(Integer id) {
		boardRepository.deleteById(id);
	}

}
