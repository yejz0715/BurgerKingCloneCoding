package com.ezen.burger.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.service.AddressService;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.MemberService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.QnaService;

@Controller
public class OtherController {
	@Resource(name="MemberService") 
	MemberService ms;
	
	@Resource(name="QnaService")
	QnaService qs;	
	
	@Resource(name="OrderService")
	OrderService os2;
	
	@Resource(name="CartService")
	CartService cs;
	
	@Resource(name="AddressService")
	AddressService as;
	
	@RequestMapping(value="/index.do")
	// 페이지 시작 리턴 값에 main, admin을 입력하여 사용자, 관리자 페이지로 이동한다.
	public String index() {
		return "redirect:/main.do";
	}  
	
	// 메인페이지로 이동
	@RequestMapping(value="/main.do")
	public String main() { 
		return "main/main";
	}
	
	// 관리자페이지로 이동
	@RequestMapping(value="/admin.do")
	public String admin() { 
		return "admin/adminLogin";
	}
	
	// faq 페이지로 이동
	@RequestMapping(value="/faqList1.do")
	public String faqList1() {
		return "ServiceCenter/faqList1";
	}
	
	
	// 고객센터 FAQ
	@RequestMapping(value="faqListForm.do")
	public ModelAndView  faqListForm(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView ();
		String fnum = request.getParameter("fnum");	// faq페이지의 fnum을 이용하여 해당 페이지로 이동
		mav.setViewName("ServiceCenter/faqList" + fnum);
		return mav;
	}
	
	
	// 고객센터 앱이용안내
	@RequestMapping(value="appGuideForm.do")
	public String appGuideForm(Model model, HttpServletRequest request) {
		return "ServiceCenter/appGuide";
	}
	
	
	
	@RequestMapping(value="/brandStroyForm.do")
	public String brandStroyForm() {
		return "brand/brandStory";
	}

	@RequestMapping(value="/terms.do")
	public String terms() {
		return "footer/terms";
	}
	
	@RequestMapping(value="/privacy.do")
	public String privacy() {
		return "footer/privacy";
	}
	
	@RequestMapping(value="/videoPolicy.do")
	public String videoPolicy() {
		return "footer/videoPolicy";
	}
	
	@RequestMapping(value="/legal.do")
	public String legal() {
		return "footer/legal";
	} 
	
	@RequestMapping(value="/deliveryUseForm.do")
	public String deliveryUseForm() {
		return "ServiceCenter/deliveryuse";
	}
	
	// 회원 마이페이지로 이동
	@RequestMapping(value="/deliveryMypageForm.do")
	public String deliveryMypageForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("id", mvo.get("ID").toString());
				paramMap2.put("ref_cursor", null);
				HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
				paramMap3.put("id", mvo.get("ID").toString());
				paramMap3.put("ref_cursor", null);
				
				os2.getOrderList(paramMap2);
				cs.selectCart(paramMap3);
				
				ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
				ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>)paramMap3.get("ref_cursor");
				
				model.addAttribute("ovo", list1);
				model.addAttribute("cvo", list2);
				model.addAttribute("MemberVO", mvo);
				return "delivery/myPage";
			}else if(memberKind == 2){
				// 비회원일 경우에는 마이 페이지로 이동하지 않는다.
				model.addAttribute("kind1", 1);
				return "redirect:/deliveryForm.do";
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
	
	// 원산지표시 팝업
	@RequestMapping(value="/popup3.do")
	public String popup3() {
		return "product/popup3";
	}
	
	
	// 모바일메인페이지로 이동
		@RequestMapping(value="/start.do")
		public String start(HttpServletRequest request, Model model) { 
			return "start";
		}
		
}


