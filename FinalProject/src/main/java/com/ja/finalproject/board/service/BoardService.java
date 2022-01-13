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
}
