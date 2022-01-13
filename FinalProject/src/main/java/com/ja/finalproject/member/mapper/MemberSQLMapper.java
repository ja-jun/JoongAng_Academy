package com.ja.finalproject.member.mapper;

import com.ja.finalproject.vo.MemberVo;

public interface MemberSQLMapper {

	// insert, update, delete => return type: void
	// select => vo(value object)
	
	public void joinMember(MemberVo vo); // insert...
	public MemberVo getMemberByIdAndPw(MemberVo vo); // select.. 객체(MemberVo) 일때는 MemberVo 안에 있는 내부 필드명을 쓴다.
	
	public MemberVo getMemberByNo(int no); // 단일 값(int, String)은 변수명(no)이 중요하다. xml에서 #{no}쓰면 된다. 
	
}
