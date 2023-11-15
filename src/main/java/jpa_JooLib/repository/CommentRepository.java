package jpa_JooLib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpa_JooLib.entity.Comment;

@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByBoardNo(Integer boardNo);
	
	void deleteByBoardNo(Integer boardNo);
	
}
