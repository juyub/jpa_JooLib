package jpa_JooLib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpa_JooLib.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserid(String userid);
	
}
