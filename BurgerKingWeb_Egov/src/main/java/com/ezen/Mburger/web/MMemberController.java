package com.ezen.Mburger.web;

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
public class MMemberController {
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
	@RequestMapping(value="/MloginForm.do")
	public String mloginForm() {
		return "mobile/member/MloginForm";
	}
	
	
	// 로그인
	@RequestMapping(value="Mlogin.do", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", request.getParameter("id"));
		paramMap.put("ref_cursor", null);
		String pwd = request.getParameter("pwd");
		
		// 입력받은 아이디를 가진 회원 검색
		ms.getMember(paramMap);
		
		// 검색한 아이디를 변수에 저장
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		if(list.size() == 0) { // 해당 ID를 가진 회원이 없을경우
			model.addAttribute("message", "ID가 없습니다.");
			return "mobile/member/MloginForm";
		}
		HashMap<String, Object> mvo = list.get(0);
		
		if(mvo.get("PWD") == null) { // 회원은 있지만 비밀번호에 문제가 있을 경우
			model.addAttribute("message", "관리자에게 문의하세요.");
			return "mobile/member/MloginForm";
		}else if(!mvo.get("PWD").equals(pwd)) { // 입력한 패스워드가 일치하지 않을 경우
			model.addAttribute("message", "비밀번호가 맞지 않습니다..");
			return "mobile/member/MloginForm";
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
			return "redirect:/mobilemain.do";
		}else { // 기타 원인을 알 수 없는 오류
			model.addAttribute("message", "알수없는 이유로 로그인 실패.");
			return "mobile/member/MloginForm";
		}
	}
	 
	
	// 로그아웃
	@RequestMapping(value="/Mlogout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/mobilemain.do";
	}
	
	
	
	// 아이디 찾기 페이지 이동
	@RequestMapping(value="/MfindIdForm.do")
	public String MfindIdForm() {
		return "mobile/member/MfindIdForm";
	}
	
	//아이디 찾기
	@RequestMapping(value="/MfindId.do", method = RequestMethod.POST)
	public String MfindId(Model model, HttpServletRequest request) {
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name",name);
		paramMap.put("phone",phone);
		paramMap.put("ref_cursor",null);
		
		ms.b_findMember(paramMap);
		
		ArrayList<HashMap<String, Object>> list =
				(ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		if(list.size() == 0) {
			model.addAttribute("message", "해당 정보를 가진 회원이 없습니다.");
			return "mobile/member/MfindIdForm";
		}else{
			model.addAttribute("memberVO", list.get(0));
		}
		return "mobile/member/MshowIdForm";
	}
	
	@RequestMapping(value="/MfindPwdForm.do")
	public String findPwdForm(Model model,HttpServletRequest request) {
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		
		model.addAttribute("name", name);
		model.addAttribute("id", id);
		
		return "mobile/member/MfindPwdForm";
	}
	
	// 비밀번호 찾기
	@RequestMapping(value="/MfindPwd.do" , method = RequestMethod.POST)
	public String findPwd(Model model, HttpServletRequest request) {
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name",name);
		paramMap.put("id",id);
		paramMap.put("ref_cursor",null);
		
		ms.b_findPwd(paramMap);
		
		ArrayList<HashMap<String, Object>> list =
				(ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		
		if(list.size() == 0) {
			
			model.addAttribute("message", "해당 정보를 가진 회원이 없습니다.");
			
			return "mobile/member/MfindPwdForm";
		}else{
			
			model.addAttribute("memberVO", list.get(0));
			
		}
		return "mobile/member/MsendPwdForm";
	}
	
	// 비밀번호 찾기, 정보 일치 후 비밀번호 재설정
	@RequestMapping(value="MupdatePwd.do")
	public String updatePwd(Model model, HttpServletRequest request) {
	String pwd=request.getParameter("pwd");
	String mseq=request.getParameter("mseq");
	
	HashMap<String,Object> paramMap=new HashMap<String,Object>();
	paramMap.put("pwd",pwd);
	paramMap.put("mseq",mseq);	
	ms.b_updatePwd(paramMap);

	model.addAttribute("paramMap", paramMap);
		return "redirect:/MloginForm.do";
	}

	// 비회원 로그인 정보 입력화면 이동
	@RequestMapping(value="/MguestLoginForm.do")
	public String MguestLoginForm() {
		return "mobile/member/MguestLoginForm";
	}
		
	// 비회원 로그인
	@RequestMapping(value="/MguestLogin.do")
	public String MguestLogin(HttpServletRequest request, Model model) {
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
		return "redirect:/start.do";
	}

	// 로그인 이후 딜리버리 페이지로 이동
	@RequestMapping(value="/MdeliveryForm.do")
	public String deliveryForm(HttpServletRequest request, Model model) {
		String kind1 = request.getParameter("kind1");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("memberkind") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			// 회원 종류 검사 (1:회원, 2:비회원)
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				if(mvo == null) {
					return "redirect:/MloginForm.do";
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
						return "mobile/delivery/MaddressSet";
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
						return "mobile/delivery/Mdelivery";
					}
				}
			}else if(memberKind == 2){
				HashMap<String, Object> gvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				if(gvo == null) {
					return "redirect:/loginForm.do";
				}else {
					if(gvo.get("ADDRESS") == null) {
						return "mobile/delivery/MaddressSet";
					}else {
						HashMap<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("kind1", kind1);
						paramMap.put("ref_cursor", null);
						ps.getProductList(paramMap);
						
						ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
						
						model.addAttribute("productList", list);
						model.addAttribute("kind1", kind1);
						return "mobile/delivery/Mdelivery";
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
	@RequestMapping(value="/MmemberUpdateForm.do")
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
				return "mobile/member/MupdateForm";
			}else if(memberKind == 2){
				model.addAttribute("kind1", 1);
				return "redirect:/MdeliveryForm.do";
			}else {
				return "redirect:/MloginForm.do";
			}
		}else {
			return "redirect:/MloginForm.do";
		}
	} 
	
	
	
	// 회원정보 수정
	@RequestMapping(value="/MupdateMember.do")
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
				return "redirect:/MdeliveryMypageForm.do";
			}else if(memberKind == 2){
				model.addAttribute("kind1", 1);
				return "redirect:/MdeliveryForm.do";
			}else {
				return "redirect:/MloginForm.do";
			}
		}else {
			return "redirect:/MloginForm.do";
		}
	}
	
	
	
	// 회원정보 삭제
	@RequestMapping(value="/MmemberDelete.do")
	public String memberDelete(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("id", mvo.get("ID").toString());
				paramMap2.put("ref_cursor", null);
				
				os.getOrderList(paramMap2);
				
				ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
				// 진행중인 주문이 있으면 회원탈퇴 거절
				if(list.size() > 0) {
					model.addAttribute("message", "진행중인 주문이 있어서 회원탈퇴가 불가능합니다.");
					return "redirect:/MmemberUpdateForm.do";
				}
				
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("mseq", Integer.parseInt(request.getParameter("mseq").toString()));
				ms.deleteMember(paramMap);
				
				session.invalidate();
				return "redirect:/MloginForm.do";
			}else if(memberKind == 2){
				model.addAttribute("kind1", 1);
				return "redirect:/MdeliveryForm.do";
			}else {
				return "redirect:/MloginForm.do";
			}
		}else {
			return "redirect:/MloginForm.do";
		}
	}
	
	
	// 회원가입 페이지로 이동
	@RequestMapping(value="/MjoinForm.do")
	public String MjoinForm(Model model, HttpServletRequest request) {
		return "mobile/member/Mjoin";
	}
	
	
	// 약관동의 페이지
	@RequestMapping(value="/Mcontract.do")
	public String Mcontract(Model model, HttpServletRequest request) {
		return "mobile/member/Mcontract";
	}
	

	// 약관동의 페이지 팝업1
	@RequestMapping(value="/Mpopup1.do")
	public String Mpopup1(Model model, HttpServletRequest request) {
		return "mobile/member/Mpopup1";
	}
	
	// 약관동의 페이지 팝업2
	@RequestMapping(value="/Mpopup2.do")
	public String Mpopup2(Model model, HttpServletRequest request) {
		return "mobile/member/Mpopup2";
	}
	
	
	
	
	// joinpage로 이동
	@RequestMapping(value="/MjoinpageForm.do", method=RequestMethod.POST)
	public String MjoinpageForm(Model model, HttpServletRequest request) {
		return "mobile/member/Mjoinpage";
	}
	
	// 회원가입
	@RequestMapping(value = "/Mjoinpage.do", method=RequestMethod.POST)
	public String Mjoinpage(Model model, HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id", request.getParameter("id") );
		paramMap.put("pwd" , request.getParameter("pwd"));
		paramMap.put( "phone" , request.getParameter("phone"));
		paramMap.put( "name" ,  request.getParameter("name"));
		
		
		
		
		ms.b_insertMember(paramMap);
		
		
		return "mobile/member/Mcomplet";
	}
	
	
	
	
	
	// 아이디체크
	@RequestMapping("/Midcheck.do")
	public String Midcheck( Model model, HttpServletRequest request ) {
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
		return "mobile/member/Midcheck";
	} 
	
	
	// 회원가입완료 페이지
	@RequestMapping(value="/Mcomplet")
	public String Mcomplet(Model model, HttpServletRequest request) {
		return "mobile/member/Mcomplet";
	}
	

}	