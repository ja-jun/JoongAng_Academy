package com.ja.finalproject.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.board.mapper.BoardSQLMapper;
import com.ja.finalproject.member.mapper.MemberSQLMapper;
import com.ja.finalproject.vo.BoardVo;
import com.ja.finalproject.vo.MemberVo;

@Service
public class BoardService {

	@Autowired
	private MemberSQLMapper memberSQLMapper;
		
	@Autowired
	private BoardSQLMapper boardSQLMapper;
	
	public void writeContent(BoardVo vo) {
		boardSQLMapper.insertBoard(vo);
		
	}
	
	public ArrayList<HashMap<String, Object>> getBoardList() {
	
		ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		
		ArrayList<BoardVo> boardVoList = boardSQLMapper.getBoardList(); // SELECT * FROM FP_Board ORDER BY board_no DESC;
		
		for(BoardVo boardVo : boardVoList) {
			
			int memberNo = boardVo.getMember_no(); // 작성자 번호...
			MemberVo memberVo = memberSQLMapper.getMemberByNo(memberNo); // SELECT * FROM FP_Member WHERE member_no = #{no};
		
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("boardVo", boardVo);
		
			dataList.add(map);
		}
		
		return dataList;
	}
	
	public HashMap<String, Object> getBoard(int board_no){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		BoardVo boardVo = boardSQLMapper.getBoardByNo(board_no);
		int memberNo = boardVo.getMember_no();
		MemberVo memberVo = memberSQLMapper.getMemberByNo(memberNo);
		
		map.put("memberVo", memberVo);
		map.put("boardVo", boardVo);
		
		return map;
	}
	
	public void increaseReadCount(int board_no) {
		boardSQLMapper.increaseReadCount(board_no);
	}
	
	public void deleteBoard(int board_no) {
		// 예외.. 처리..
		
		boardSQLMapper.deleteBoard(board_no);
	}
	
	public void updateBoard(BoardVo vo) {
		boardSQLMapper.updateBoard(vo);
	}
}
