package jpa_JooLib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jpa_JooLib.entity.User;
import jpa_JooLib.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public void updateUser(int userNo, User updatedUser) {
	    User user = userRepository.findById(userNo)
	                               .orElseThrow(() -> new RuntimeException("User not found"));
	    user.setPassword(updatedUser.getPassword());
	    user.setName(updatedUser.getName());
	    user.setPhone(updatedUser.getPhone());
	    user.setBorrown(updatedUser.getBorrown());
	    user.setRole(updatedUser.getRole());
	    userRepository.save(user);
	}
	
	public void deleteUser(int userNo) {
		userRepository.deleteById(userNo);
	}
	
	public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        if(user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password.");
        }
        return user;
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUser(int userNo) {
		return userRepository.findById(userNo);
	}
	
	
}
