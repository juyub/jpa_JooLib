package jpa_JooLib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpa_JooLib.entity.Comment;

@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	Optional<Comment> findByIdAndBoardId(Integer id, Integer boardId);
	
}
