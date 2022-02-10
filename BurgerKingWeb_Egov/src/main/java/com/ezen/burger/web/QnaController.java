package com.ezen.burger.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.burger.service.MemberService;
import com.ezen.burger.service.QnaService;

@Controller
public class QnaController {
	@Resource(name="QnaService")
	QnaService qs;
	
	@Resource(name="MemberService")
	MemberService ms;
	
	
	
	// 고객센터 문의
			@RequestMapping(value="/qnaForm.do")
			public String qna_list(Model model, HttpServletRequest request) {
				HttpSession session = request.getSession();
				HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
				if( loginUser == null ) {
						return "ServiceCenter/qnaList";
					}else {
						HashMap<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("id", loginUser.get("ID").toString() );
						paramMap.put("ref_curser", null);
						if(session.getAttribute("memberkind") != null) {
							int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
							/* int memberKind = (int)session.getAttribute("memberkind"); */
							// 회원 종류 검사 (1:회원, 2:비회원)
							if(memberKind == 1) {	// 회원
									qs.listQna( paramMap );
									ArrayList<HashMap<String, Object>> list 
									= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
									model.addAttribute("qnaList", list);	
									return "ServiceCenter/qnaList";
							}else if(memberKind == 2 ) {
									model.addAttribute("message", "Qna문의를 하려면 로그인을 하셔야합니다.");
									return "member/loginForm";	
								}
						}
				}
				return " ServiceCenter/qnaList";
			}
	
	
		// 고객센터 문의작성
		@RequestMapping(value="qnaWriteForm.do")
		public String qnaWriteForm(Model model, HttpServletRequest request) {
			return "ServiceCenter/qnaWrite";
		}
		
		
		// 고객센터 문의내용전송
		@RequestMapping(value="/qnaWrite.do")
		public String qnaWrite(Model model, HttpServletRequest request,
				@RequestParam("subject") String subject, @RequestParam("content") String content,
				 @RequestParam("pass") String pass) {
			HttpSession session = request.getSession();
			HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
			if( loginUser == null ) {
					return "ServiceCenter/qnaList";
				}else {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("id", loginUser.get("ID").toString() );
					paramMap.put("ref_curser", null);
					if(session.getAttribute("memberkind") != null) {
						int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
						/* int memberKind = (int)session.getAttribute("memberkind"); */
						// 회원 종류 검사 (1:회원, 2:비회원)
						if(memberKind == 1) {	// 회원
							paramMap.put("id", loginUser.get("ID").toString() );
							paramMap.put("subject", subject );
							paramMap.put("content", content);
							paramMap.put("pass", pass);
							qs.b_insertQna( paramMap );	
								return "ServiceCenter/qnaList";
						}else if(memberKind == 2 ) {
								model.addAttribute("message", "Qna문의를 하려면 로그인을 하셔야합니다.");
								return "member/loginForm";	
							}
					}
			}
			return " ServiceCenter/qnaList";
		}
		
		
		
		
		// 고객센터 qna passform
		@RequestMapping(value="/passCheckForm")
		 public String passCheckForm(@RequestParam("qseq")int qseq,
				 @RequestParam(value="message", required = false)String message,
				 HttpServletRequest request, Model model) {
			HttpSession session = request.getSession();
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if(session.getAttribute("loginUser") == null) {	// 비로그인 상태
				return "redirect:/loginForm";
			}else {
				if(message != null) {
					model.addAttribute("message", "비밀번호를 확인하세요");	// qna 게시물의 pass가 틀린경우 passChk의 message를 출력 
				}
				paramMap.put("qseq", qseq);
				return "ServiceCenter/passChk";
			}
		 }
		
		
	
		// 고객센터 qna pass검사
					@RequestMapping(value="/passChk", method=RequestMethod.POST)
					public String passChk (HttpServletRequest request , Model model) {
						HttpSession session = request.getSession();
						HashMap<String, Object> paramMap = new HashMap<String, Object>();
						String pass = request.getParameter("pass");
						int qseq = Integer.parseInt(request.getParameter("qseq"));
						paramMap.put("qseq", qseq);
						paramMap.put("ref_cursor", null);
						
						//qs.b_getpassChk(paramMap); 
						ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
						HashMap<String, Object> mvo = list.get(0);
						
						if(session.getAttribute("loginUser") == null) {	// 비로그인 상태
							return "redirect:/loginForm";
						}else {
							
							if(!mvo.get("pass").equals(pass)) {	// 비밀번호 불일치
								model.addAttribute("message", "비밀번호를 확인하세요");
								return "redirect:/passCheckForm?qseq=" + qseq;
							}else {	// 비밀번호 일치
								paramMap.put("qseq", qseq);
								return "redirect:/qnaView";
							}
						}
					}
		
		
		/*
		
		

		// 고객센터 qna pass검사
			@RequestMapping(value="/passChk", method=RequestMethod.POST)
			public ModelAndView passChk (HttpServletRequest request , Model model) {
				ModelAndView mav = new ModelAndView();
				HttpSession session = request.getSession();
				if(session.getAttribute("loginUser") == null) {	// 비로그인 상태
					mav.setViewName("redirect:/loginForm");
				}else {
					String pass = request.getParameter("pass");
					int qseq = Integer.parseInt(request.getParameter("qseq"));
					QnaVO qvo = qs.getpassChk(qseq); 
					if(!qvo.getPass().equals(pass)) {	// 비밀번호 불일치
						mav.addObject("message", "비밀번호가 일치하지 않습니다"); 
						mav.setViewName("redirect:/passCheckForm?qseq=" + qseq);
					}else {	// 비밀번호 일치
						mav.addObject("qseq", qseq);
						mav.setViewName("redirect:/qnaView");
					}
				}
				return mav;
			}
			
		
		// 고객센터 문의내용
		@RequestMapping(value="/qnaView")
		public ModelAndView qna_view(Model model, HttpServletRequest request) {
			ModelAndView mav = new ModelAndView();
			int qseq = Integer.parseInt(request.getParameter("qseq"));
			mav.addObject("qnaVO", qs.getQna(qseq));	//qseq로 해당게시물 보기
			mav.setViewName("ServiceCenter/qnaView");
			return mav;
		}
		 
		
		// 고객센터 qna 삭제
		@RequestMapping(value="qnaDelete" )
		public String qnaDelete( @RequestParam("delete") int [] qseqArr ) {
			for( int qseq : qseqArr)
				qs.deleteQna(qseq);
			return "redirect:/qnaForm";
		}*/
	
}
