package jpa_JooLib.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpa_JooLib.entity.Board;

@Repository("boardRepository")
public interface BoardRepository extends JpaRepository<Board, Integer> {
    
	Page<Board> findByParentIsNull(Pageable pageable);
	
}
