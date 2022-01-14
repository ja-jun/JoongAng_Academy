package com.ja.finalproject.board.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.board.service.BoardService;
import com.ja.finalproject.vo.BoardVo;
import com.ja.finalproject.vo.MemberVo;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService boardService; // D.I
	// private com.ja.finalproject.board.service.BoardService
	

	@RequestMapping("mainPage")
	public String mainPage(Model model) {
		// 여러분 멘탈 파괴시킬 구간...
		ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList(); // getBoardList() 자료구조
		
		model.addAttribute("dataList", dataList); // model : request와 비슷한 것 
		
		return "board/mainPage";
	}
	
	@RequestMapping("writeContentPage")
	public String writeContentPage() {
		return "board/writeContentPage";
	}
	
	@RequestMapping("writeContentProcess")
	public String writeContentProcess(BoardVo param , HttpSession session) {
		
		// 파라미터 2개 값 + 세션(행위자)에서 회원번호 뽑아서 param 메모리에서 세팅해서 결과적으로 3개값 세팅...
		// 매우중요 아래 4문장!!!
		MemberVo sessionUser = (MemberVo)session.getAttribute("sessionUser");
		// 나중엔 sessionUser != null (if문 설정)
		int memberNo = sessionUser.getMember_no();
		param.setMember_no(memberNo);
		
		// service(class) -> mapper(xml, interface) -> insert
		boardService.writeContent(param);
		
		return "redirect:./mainPage";
		
	}
	
	@RequestMapping("readContentPage")
	public String readContentPage(int board_no , Model model) {
		
		boardService.increaseReadCount(board_no);
		
		HashMap<String, Object> map = boardService.getBoard(board_no);
		model.addAttribute("data", map);
		
		return "board/readContentPage";
	}
	// 파라미터 받는 법 1개 : int board_no, 2개는 BoardVo
	@RequestMapping("deleteContentProcess")
	public String deleteContentProcess(int board_no) {
				
		boardService.deleteBoard(board_no);
		
		return "redirect:./mainPage";
	}
	// 업데이트
	@RequestMapping("updateContentPage")
	public String updateContentPage(int board_no , Model model) {
		
		HashMap<String, Object> map = boardService.getBoard(board_no);
		model.addAttribute("data", map);
		
		return "board/updateContentPage";
	}
	
	@RequestMapping("updateContentProcess")
	public String updateContentProcess(BoardVo param) {
				
		boardService.updateBoard(param);
		
		return "redirect:./mainPage";
	}
		
}