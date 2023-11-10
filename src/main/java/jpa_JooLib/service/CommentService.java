package jpa_JooLib.service;

import org.springframework.stereotype.Service;

import jpa_JooLib.entity.Board;
import jpa_JooLib.entity.Comment;
import jpa_JooLib.repository.BoardRepository;
import jpa_JooLib.repository.CommentRepository;

@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public CommentService(BoardRepository boardRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public Comment create(Integer boardId, Comment comment) {  
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found."));
        comment.setBoard(board);
        return commentRepository.save(comment);
    }

    public Comment read(Integer boardId, Integer commentId) {  
        return commentRepository.findByIdAndBoardId(commentId, boardId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found."));
    }

    public Comment update(Integer boardId, Integer commentId, Comment comment) {  
        Comment existingComment = read(boardId, commentId);
        existingComment.setContent(comment.getContent());
        return commentRepository.save(existingComment);
    }

    public void delete(Integer boardId, Integer commentId) {  
        Comment comment = read(boardId, commentId);
        commentRepository.delete(comment);
    }

    public Comment reply(Integer boardId, Integer parentCommentId, Comment comment) {  // Changed from Long to Integer
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found."));
        Comment parent = commentRepository.findByIdAndBoardId(parentCommentId, boardId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found."));
        comment.setBoard(board);
        comment.setParent(parent);
        return commentRepository.save(comment);
    }
}