package ro.telacad.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ro.telacad.dao.UserDao;
import ro.telacad.db.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void registration(User user) {
		Optional<User> optionalUser = userDao.findByUsername(user.getUsername());
		if (optionalUser.isEmpty()) {
			userDao.addUser(user);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public int login(User user) {
		Optional<User> optionalUser = userDao.findByUsername(user.getUsername());
		if (!optionalUser.isEmpty()) {
			if (optionalUser.get().getPassword().equals(user.getPassword())) {
				return optionalUser.get().getId();
			}
		}
		throw new IllegalArgumentException();
	}

	public User findUserById(int userId) {
		return userDao.findUserById(userId);
	}
}
