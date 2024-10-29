package com.gudee.urban.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gudee.urban.map.MapVO;

import lombok.RequiredArgsConstructor;

@Mapper
public interface MapMapper {
	
	MapVO getList() throws Exception;
	void addList(MapVO mapVO) throws Exception;
}
