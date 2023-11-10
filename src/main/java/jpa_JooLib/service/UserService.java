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
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public Optional<User> getUser(int userno) {
		return userRepository.findById(userno);
	}

	public void deleteUser(int userno) {
		userRepository.deleteById(userno);
	}
	
    public User login(String userid, String password) {
        User user = userRepository.findByUserid(userid);
        if(user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password.");
        }
        return user;
	}
	
	public void updateUser(int userno, User updatedUser) {
	    User user = userRepository.findById(userno)
	                               .orElseThrow(() -> new RuntimeException("User not found"));
	    user.setPassword(updatedUser.getPassword());
	    user.setName(updatedUser.getName());
	    user.setPhone(updatedUser.getPhone());
	    user.setBorrown(updatedUser.getBorrown());
	    user.setRole(updatedUser.getRole());
	    userRepository.save(user);
	}
	
}
