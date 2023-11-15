package jpa_JooLib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpa_JooLib.entity.Borrow;
import jpa_JooLib.entity.User;

@Repository("borrowRepository")
public interface BorrowRepository extends JpaRepository<Borrow, Integer>{

	List<Borrow> findByUser(User user);
	
}
