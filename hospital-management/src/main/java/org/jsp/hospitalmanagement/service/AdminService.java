package org.jsp.hospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmanagement.dao.AdminDao;
import org.jsp.hospitalmanagement.dto.Admin;
import org.jsp.hospitalmanagement.dto.ResponseStructure;
import org.jsp.hospitalmanagement.exception.IdNotFoundException;
import org.jsp.hospitalmanagement.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	public ResponseStructure<Admin> saveAdmin(Admin admin){
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		structure.setMessage("Admin Saved");
		structure.setData(adminDao.saveAdmin(admin));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin){
		Optional<Admin> recAdmin=adminDao.findById(admin.getId());
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		if(recAdmin.isPresent()) {
			Admin dbAdmin=recAdmin.get();
			dbAdmin.setName(admin.getName());
			dbAdmin.setPhone(admin.getPhone());
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setPassword(admin.getPassword());
			structure.setMessage("Admin Updated");
			structure.setData(adminDao.saveAdmin(admin));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Admin>> findById(int id){
		Optional<Admin> recAdmin=adminDao.findById(id);
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		if(recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteById(int id){
		Optional<Admin> recAdmin=adminDao.findById(id);
		ResponseStructure<String> structure=new ResponseStructure<>();
		if(recAdmin.isPresent()) {
			structure.setData("Admin Deleted");
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			adminDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setData("Cannot Delete Admin as Id is Invalid");
		structure.setMessage("Admin not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseStructure<List<Admin>>> findAll(){
		ResponseStructure<List<Admin>> structure=new ResponseStructure<>();
			structure.setData(adminDao.findAll());
			structure.setMessage("List Of Admins");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
		}
	
	public ResponseEntity<ResponseStructure<Admin>> verifyAdmin(long phone,String password){
		Optional<Admin> recAdmin=adminDao.verifyAdmin(phone, password);
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		if(recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Verification Successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Phone Number Or Password");
	}
	
	public ResponseEntity<ResponseStructure<List<Admin>>> findByName(String name){
		ResponseStructure<List<Admin>> structure=new ResponseStructure<>();
		List<Admin> admins=adminDao.verifyByName(name);
		structure.setData(admins);
		if(admins.size()>0) {
			structure.setMessage("List of Admins with entered name");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("No Admin Found with the Entered name");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.NOT_FOUND);
	}
}
