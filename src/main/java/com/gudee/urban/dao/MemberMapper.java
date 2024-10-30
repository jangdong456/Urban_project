package com.gudee.urban.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gudee.urban.member.MemberVO;

@Mapper
public interface MemberMapper {
	
	MemberVO signUp(MemberVO memberVO) throws Exception; 
	MemberVO signIn(MemberVO memberVO) throws Exception; 
}
