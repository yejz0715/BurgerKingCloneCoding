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

import com.ezen.burger.service.AddressService;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.MemberService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.ProductService;

@Controller
public class MemberController {
	@Resource(name="MemberService") 
	MemberService ms;
	@Resource(name="ProductService") 
	ProductService ps;
	@Resource(name="OrderService")
	OrderService os;
	@Resource(name="CartService")
	CartService cs;
	@Resource(name="AddressService")
	AddressService as;
	

	// 로그인 페이지로 이동
	@RequestMapping(value="/loginForm.do")
	public String loginForm() {
		return "member/loginForm";
	}
	
	// 로그인
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", request.getParameter("id"));
		paramMap.put("ref_cursor", null);
		String pwd = request.getParameter("pwd");
		
		// 입력받은 아이디를 가진 회원 검색
		ms.getMember(paramMap);
		
		// 검색한 아이디를 변수에 저장
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		HashMap<String, Object> mvo = list.get(0);
		
		if(mvo == null) { // 해당 ID를 가진 회원이 없을경우
			model.addAttribute("message", "ID가 없습니다.");
			return "member/loginForm";
		}else if(mvo.get("PWD") == null) { // 회원은 있지만 비밀번호에 문제가 있을 경우
			model.addAttribute("message", "관리자에게 문의하세요.");
			return "member/loginForm";
		}else if(!mvo.get("PWD").equals(pwd)) { // 입력한 패스워드가 일치하지 않을 경우
			model.addAttribute("message", "비밀번호가 맞지 않습니다..");
			return "member/loginForm";
		}else if(mvo.get("PWD").equals(pwd)){ // 정상 로그인
			HttpSession session = request.getSession();
			// 회원 로그인시 세션에 비회원카트정보가 있다면 제거
			if(session.getAttribute("guestCartList") != null) {
				session.removeAttribute("guestCartList");
			}
			
			// 마지막 로그인시간 변경
			HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("id", request.getParameter("id"));
			ms.lastDateUpdate(paramMap2);
			
			session.setAttribute("loginUser", mvo);
			session.setAttribute("memberkind", mvo.get("MEMBERKIND").toString());
			return "redirect:/index.do";
		}else { // 기타 원인을 알 수 없는 오류
			model.addAttribute("message", "알수없는 이유로 로그인 실패.");
			return "member/loginForm";
		}
	}
	
	// 로그아웃
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/index.do";
	}
	
	// 아이디 찾기 페이지 이동
	@RequestMapping(value="/findIdForm.do")
	public String findIdForm() {
		return "member/findIdForm";
	}
	//아이디 찾기
	@RequestMapping(value="/findId.do", method = RequestMethod.POST)
	public String findId(Model model, HttpServletRequest request) {
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		System.out.println(1);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name",name);
		paramMap.put("phone",phone);
		paramMap.put("ref_cursor",null);
		System.out.println(2);
		ms.b_findMember(paramMap);
		System.out.println(3);
		ArrayList<HashMap<String, Object>> list =
				(ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		HashMap<String, Object> resultMap = list.get(0);
		System.out.println(4);
		if(list.size() == 0) {
			model.addAttribute("message", "해당 정보를 가진 회원이 없습니다.");
			System.out.println(5);
			return "member/findIdForm";
		}else{
			model.addAttribute("memberVO", resultMap);
		}
		System.out.println(6);
		return "member/showIdForm";
	}
	/*
	// 비밀번호 찾기 페이지로 이동
	@RequestMapping(value="/findPwdForm")
	public String findPwdForm(@RequestParam(value="id", required = false) String id,
			@RequestParam(value="name", required = false) String name,
			HttpServletRequest request) {
		request.setAttribute("name", name);
		request.setAttribute("id", id);
		return "member/findPwdForm";
	}
	
	// 비밀번호 찾기
	@RequestMapping(value="/findPwd")
	public ModelAndView findPwd(@ModelAttribute("dto") @Valid MemberVO membervo, 
			BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) { 
			if(result.getFieldError("name") != null) {
				mav.addObject("message", result.getFieldError("name").getDefaultMessage());
				mav.setViewName("member/findPwdForm");
			}else if(result.getFieldError("id") != null) {
				mav.addObject("message", result.getFieldError("id").getDefaultMessage());
				mav.setViewName("member/findPwdForm");
			}
		}	
		MemberVO mvo = ms.findPwd(membervo.getName(), membervo.getId());
		if(mvo == null) {
			mav.addObject("message", "해당 정보를 가진 회원이 없습니다.");
			mav.setViewName("member/findIdForm");
		}else{
			mav.addObject("memberVO", mvo);
			mav.setViewName("member/sendPwdForm");
		}
		return mav;
	}
	
	// 비밀번호 찾기, 정보 일치 후 비밀번호 재설정
	@RequestMapping(value="updatePwd")
	public ModelAndView updatePwd(@RequestParam("pwd") String pwd,
			@RequestParam("mseq") String mseq) {
		ModelAndView mav = new ModelAndView();
		ms.updatePwd(mseq, pwd);
		mav.setViewName("redirect:/loginForm");
		return mav;
	}
	*/
	// 비회원 로그인 정보 입력화면 이동
	@RequestMapping(value="/guestLoginForm")
	public String guestLoginForm() {
		return "member/guestLoginForm";
	}
	
	// 비회원 로그인
	@RequestMapping(value="/guestLogin")
	public String guestLogin(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		paramMap.put("phone", phone);
		paramMap.put("pwd", pwd);

		HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("ref_cursor", null);
		
		ms.b_selectGseq(paramMap2);
		
		int gseq = Integer.parseInt(paramMap2.get("ref_cursor").toString());
		String id = "Non" + gseq;
		
		paramMap.put("gseq", gseq);
		paramMap.put("id", id);
		
		// 이후 게스트 주문내역을 위한 게스트 정보 저장
		ms.b_insertGuest(paramMap);
		
		HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
		paramMap3.put("ref_cursor", null);
		paramMap3.put("gseq", gseq);
		
		ms.b_getGuest(paramMap3);
		
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap3.get("ref_cursor");
		HashMap<String, Object> gvo = list.get(0);
		// 세션에 담아둘 게스트 카트정보 생성
		ArrayList<HashMap<String, Object>> guestCartList = new ArrayList<HashMap<String, Object>>();
		HttpSession session = request.getSession();
		
		session.setAttribute("loginUser", gvo);
		session.setAttribute("memberkind", gvo.get("MEMBERKIND").toString());
		session.setAttribute("guestCartList", guestCartList);
		return "redirect:/index.do";
	}
	
	// 로그인 이후 딜리버리 페이지로 이동
	@RequestMapping(value="/deliveryForm")
	public String deliveryForm(HttpServletRequest request, Model model) {
		String kind1 = request.getParameter("kind1");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("memberkind") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			// 회원 종류 검사 (1:회원, 2:비회원)
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				if(mvo == null) {
					return "redirect:/loginForm.do";
				}else {
					// 로그인한 회원의 주소지를 확인 후 없으면 주소 초기설정 페이지로 이동
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("mseq", mvo.get("MSEQ").toString());
					paramMap.put("ref_cursor", null);
					
					as.b_getMyAddress(paramMap);
					
					ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
					if(list.size() == 0) {
						HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
						paramMap2.put("id", mvo.get("ID").toString());
						paramMap2.put("ref_cursor", null);
						HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
						paramMap3.put("id", mvo.get("ID").toString());
						paramMap3.put("ref_cursor", null);
						
						os.getOrderList(paramMap2);
						cs.selectCart(paramMap3);
						
						ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
						ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>)paramMap3.get("ref_cursor");
						
						model.addAttribute("ovo", list1);
						model.addAttribute("cvo", list2);
						return "delivery/addressSet";
					}else {
						// 주소지가 있다면 주문 상품목록과 회원의 카트, 주문의 리스트를 가지고 딜리버리페이지로 이동
						HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
						paramMap2.put("id", mvo.get("ID").toString());
						paramMap2.put("ref_cursor", null);
						HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
						paramMap3.put("id", mvo.get("ID").toString());
						paramMap3.put("ref_cursor", null);
						HashMap<String, Object> paramMap4 = new HashMap<String, Object>();
						paramMap4.put("kind1", kind1);
						paramMap4.put("ref_cursor", null);
						
						os.getOrderList(paramMap2);
						cs.selectCart(paramMap3);
						ps.getProductList(paramMap4);
						
						ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
						ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>)paramMap3.get("ref_cursor");
						ArrayList<HashMap<String, Object>> list3 = (ArrayList<HashMap<String, Object>>)paramMap4.get("ref_cursor");
						model.addAttribute("ovo", list1);
						model.addAttribute("cvo", list2);
						model.addAttribute("productList", list3);
						model.addAttribute("kind1", kind1);
						return "delivery/delivery";
					}
				}
			}else if(memberKind == 2){
				HashMap<String, Object> gvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				if(gvo == null) {
					return "redirect:/loginForm.do";
				}else {
					if(gvo.get("ADDRESS") == null) {
						return "delivery/addressSet";
					}else {
						HashMap<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("kind1", kind1);
						paramMap.put("ref_cursor", null);
						ps.getProductList(paramMap);
						
						ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
						
						model.addAttribute("productList", list);
						model.addAttribute("kind1", kind1);
						return "delivery/delivery";
					}
				}
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
	
	// 회원 정보 변경 페이지로 이동
	@RequestMapping(value="/memberUpdateForm.do")
	public String memberUpdateForm(HttpServletRequest request, Model model) {
		String message = request.getParameter("message");
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				
				// order,cart view 조회
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("id", mvo.get("ID").toString());
				paramMap2.put("ref_cursor", null);
				HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
				paramMap3.put("id", mvo.get("ID").toString());
				paramMap3.put("ref_cursor", null);
				
				os.getOrderList(paramMap2);
				cs.selectCart(paramMap3);
				
				ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
				ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>)paramMap3.get("ref_cursor");
				
				// message 확인
				if(message !=null) {
					model.addAttribute("message", message);
				}
				
				model.addAttribute("ovo", list1);
				model.addAttribute("cvo", list2);
				model.addAttribute("MemberVO", mvo);
				return "member/updateForm";
			}else if(memberKind == 2){
				model.addAttribute("kind1", 1);
				return "redirect:/deliveryForm.do";
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
	
	// 회원정보 수정
	@RequestMapping(value="/updateMember")
	public String updateMember(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				mvo.put("ID", request.getParameter("id"));
				mvo.put("PWD", request.getParameter("pwd"));
				mvo.put("NAME", request.getParameter("name"));
				mvo.put("PHONE", request.getParameter("phone"));

				ms.updateMember(mvo);
				
				session.setAttribute("loginUser", mvo);
				session.setAttribute("memberkind", mvo.get("MEMBERKIND").toString());
				return "redirect:/deliveryMypageForm.do";
			}else if(memberKind == 2){
				model.addAttribute("kind1", 1);
				return "redirect:/deliveryForm.do";
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
	/*
	// 회원정보 삭제
	@RequestMapping(value="/memberDelete")
	public ModelAndView memberDelete(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				ArrayList<orderVO> list = os.getOrderListResult2(mvo.getId());
				if(list.size() > 0) {
					mav.addObject("message", "진행중인 주문이 있어서 회원탈퇴가 불가능합니다.");
					mav.setViewName("redirect:/memberUpdateForm");
					return mav;
				}
				int mseq = Integer.parseInt(request.getParameter("mseq"));
				ms.deleteMember(mseq);
				session.invalidate();
				mav.setViewName("redirect:/loginForm");
			}else if(memberKind == 2){
				mav.addObject("kind1", 1);
				mav.setViewName("redirect:/deliveryForm");
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}*/
	
	// 회원가입 페이지로 이동
	@RequestMapping(value="/joinForm.do")
	public String join_form(Model model, HttpServletRequest request) {
		return "member/join";
	}
	
	// 약관동의 페이지
	@RequestMapping(value="/contract.do")
	public String contractform(Model model, HttpServletRequest request) {
		return "member/contract";
	}
	
	// 약관동의 페이지 팝업1
	@RequestMapping(value="/popup1.do")
	public String popup1(Model model, HttpServletRequest request) {
		return "member/popup1";
	}
	
	// 약관동의 페이지 팝업2
	@RequestMapping(value="/popup2.do")
	public String popup2(Model model, HttpServletRequest request) {
		return "member/popup2";
	}
	
	// joinpage로 이동
	@RequestMapping(value="/joinpageForm.do", method=RequestMethod.POST)
	public String firstjoinpage(Model model, HttpServletRequest request) {
		return "member/joinpage";
	}
	
	// 회원가입
	@RequestMapping(value = "/joinpage.do", method=RequestMethod.POST)
	public String join(Model model, HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id", request.getParameter("id") );
		paramMap.put("pwd" , request.getParameter("pwd"));
		paramMap.put( "phone" , request.getParameter("phone"));
		paramMap.put( "name" ,  request.getParameter("name"));
		
		
		
		
		ms.b_insertMember(paramMap);
		
		
		return "member/complet";
	}
	
	
	// 아이디체크
	@RequestMapping("/idcheck.do")
	public String idcheck( Model model, HttpServletRequest request ) {
		String id = request.getParameter("id");
		 
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		paramMap.put("id", id);
		ms.b_getMember(paramMap);	 // 조회 
		
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		if(list.size() == 0) model.addAttribute("result", -1);
		else model.addAttribute("result", 1);
		model.addAttribute("id", id);
		return "member/idcheck";
	} 
	// 회원가입완료 페이지
	@RequestMapping(value="/complet")
	public String complet(Model model, HttpServletRequest request) {
		return "member/complet";
	}
	

}	