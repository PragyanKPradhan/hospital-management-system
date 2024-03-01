package org.jsp.hospitalmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmanagement.dto.Admin;
import org.jsp.hospitalmanagement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Optional<Admin> findById(int id){
		return adminRepository.findById(id);
	}
	
	public List<Admin> findAll(){
		return adminRepository.findAll();
	}
	
	public void deleteById(int id) {
		adminRepository.deleteById(id);
	}
	
	public Optional<Admin> verifyAdmin(long phone,String password){
		return adminRepository.verify(phone, password);
	}
	
	public List<Admin> verifyByName(String name){
		return adminRepository.verifyByName(name);
	}
}
