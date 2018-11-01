package com.finplapp.service;

import java.util.List;

import com.finplapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finplapp.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return repository.findOne(id);
	}

	public User findBySSO(String sso) {
		User user = repository.findBySsoId(sso);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = repository.findOne(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			//entity.setFirstName(user.getFirstName());
			//entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		//	entity.setPeriodOfTimes(user.getPeriodOfTimes());
		}
	}

	
	public void deleteUserBySSO(String sso) {
		repository.delete(repository.findBySsoId(sso));
	}

	public List<User> findAllUsers() {
		return repository.findAll();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
}
