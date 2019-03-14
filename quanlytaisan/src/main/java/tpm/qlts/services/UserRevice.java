package tpm.qlts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpm.qlts.entitys.Users;
import tpm.qlts.repositorys.UserRepository;

@Service("userRevice")
public class UserRevice {
	@Autowired
	private UserRepository userRepository;
	
	public void deleteById(String id)
	{
		userRepository.deleteById(id);
	}
	
	public Users update(Users user) {
		return userRepository.save(user);
	}
	
	public Optional<Users> findById(String id)
	{
		return userRepository.findById(id);
	}
	
	public List<Users> findAll()
	{
		return (List<Users>) userRepository.findAll();
	}
}
