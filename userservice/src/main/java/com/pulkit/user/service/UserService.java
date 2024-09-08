package com.pulkit.user.service;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pulkit.user.entity.User;
import com.pulkit.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		kafkaTemplate.send("user-events", "User Registered: " + savedUser.getEmail());
		return savedUser;
	}

	public User getUser(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
	}

	public User updateUser(Long id, User userDetails) {
		User user = getUser(id);
		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());
		user.setPassword(user.getPassword());
		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
