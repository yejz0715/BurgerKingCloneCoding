package com.ezen.burger.web;

import java.util.ArrayList;

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
	
	/*
	// 회원 마이페이지로 이동
	@RequestMapping(value="/deliveryMypageForm.do")
	public ModelAndView deliveryMypageForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				ArrayList<orderVO> list1 = os2.getOrderList(mvo.getId());
				ArrayList<CartVO> list2 = cs.selectCart( mvo.getId() );	

				mav.addObject("ovo", list1);
				mav.addObject("cvo", list2);
				mav.addObject("MemberVO", mvo);
				mav.setViewName("delivery/myPage");
			}else if(memberKind == 2){
				// 비회원일 경우에는 마이 페이지로 이동하지 않는다.
				mav.addObject("kind1", 1);
				mav.setViewName("redirect:/deliveryForm");
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	// 원산지표시 팝업
	@RequestMapping(value="/popup3.do")
	public String popup3() {
		return "product/popup3";
	}
	
	*/
}


