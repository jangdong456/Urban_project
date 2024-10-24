package com.gudee.urban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrbanService {
	
	@Autowired
	private UrbanMapper urbanMapper;
	
	public void getList() throws Exception {
		urbanMapper.getList();
	
	}
}
