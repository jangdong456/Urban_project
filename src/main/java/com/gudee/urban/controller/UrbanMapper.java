package com.gudee.urban.controller;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UrbanMapper {
	public void getList() throws Exception;
}
