package com.juba.spring_boot_library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juba.spring_boot_library.model.Role;
import com.juba.spring_boot_library.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService service;

	@PostMapping
	public ResponseEntity<Role> post(@RequestBody Role role) {
		return ResponseEntity.ok().body(service.insert(role));
	}

	
	
}
