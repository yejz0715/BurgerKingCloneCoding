package com.ezen.Mburger.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.burger.service.AddressService;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.MemberService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.QnaService;

@Controller
public class MOtherController {
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
	
	@RequestMapping(value="/mobilemain.do")
	public String mobilemain() { 
		return "mobile/main";
	}
	//브랜드스토리
	@RequestMapping(value="/MbrandStroyForm.do")
	public String MbrandStroyForm() {
		return "mobile/brand/MbrandStory";
	}

}
