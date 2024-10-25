package com.gudee.urban.map;

import org.springframework.stereotype.Service;

import com.gudee.urban.dao.MapMapper;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Service
public class MapService {
	
	private final MapMapper mapMapper;
	
	public MapService(MapMapper mapMapper) {
		this.mapMapper = mapMapper;
	}
	
	public MapVO getList() throws Exception {
		return mapMapper.getList();
	}
}
