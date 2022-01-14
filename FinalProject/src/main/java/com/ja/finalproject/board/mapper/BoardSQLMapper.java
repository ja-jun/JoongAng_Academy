package com.ja.finalproject.board.mapper;

import java.util.ArrayList;

import com.ja.finalproject.vo.BoardVo;

public interface BoardSQLMapper {
	
	// insert, update, delete => return type: void
	// select => vo(value object)
		
	public void insertBoard(BoardVo vo);
	
	public ArrayList<BoardVo> getBoardList(); // 1개의 행을 받을 때는 단일 객체 리턴, N개의 행을 받을 때는 List계열로 받는다.
	// select만 return type 나머진 void 게시글 보기 커리
	public BoardVo getBoardByNo(int no);
	// 조회수 늘리기
	public void increaseReadCount(int no);
	// 글삭제 
	public void deleteBoard(int no);
	// 글 수정
	public void updateBoard(BoardVo vo);
	
}
