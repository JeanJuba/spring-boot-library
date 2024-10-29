package com.juba.spring_boot_library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juba.spring_boot_library.exception.ObjectNotFoundException;
import com.juba.spring_boot_library.model.Role;
import com.juba.spring_boot_library.repository.RoleRepository;


@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	
	public Role insert(Role role) {
		return repository.save(role);
	}
	
	public Role find(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(String.format("Id: %s Tipo: %s", id, Role.class.getName())) );
	}
}
