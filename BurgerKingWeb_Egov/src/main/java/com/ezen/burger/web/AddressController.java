package com.ezen.burger.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.burger.service.AddressService;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.ProductService;

@Controller
public class AddressController {
	@Resource(name="AddressService")
	AddressService as;
	@Resource(name="ProductService")
	ProductService ps;
	@Resource(name="OrderService")
	OrderService os;
	@Resource(name="CartService")
	CartService cs;
	
	
	// 주소찾기 팝업창 띄우기
	@RequestMapping(value="/findZipNum.do")
	public String findZipNum(HttpServletRequest request, Model model) {
		String dong = request.getParameter("dong");
		if( dong != null) {
			if( dong.equals("") == false ) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("dong", dong);
				paramMap.put("ref_cursor", null);
				
				as.selectAddressByDong(paramMap);
				
				ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
				
				model.addAttribute("addressList", list);
			}
		}else {
			// 별일없이 다음 페이지로 이동
		}
		return "delivery/findZipNum";
	}
	
	// 입력한 주소 저장
	@RequestMapping(value="/myAddress.do")
	public String myAddress(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			String address = request.getParameter("addr1") + " " + request.getParameter("addr2");
			String zip_num = request.getParameter("zip_num");
			// 회원 종류 검사 (1:회원, 2:비회원)
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				if(mvo == null) {
					return "redirect:/loginForm.do";
				}else {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("zip_num", zip_num);
					paramMap.put("address", address);
					paramMap.put("mseq", Integer.parseInt(mvo.get("MSEQ").toString()));
					
					as.setUserAddress(paramMap);
					
					model.addAttribute("kind1", 1);
					return "redirect:/deliveryForm.do";
				}
			}else if(memberKind == 2){
				HashMap<String, Object> gvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				if(gvo == null) {
					return "redirect:/loginForm.do";
				}else {
					gvo.put("ZIP_NUM", zip_num);
					gvo.put("ADDRESS", address);
					
					as.setGuestAddress(gvo);
					
					session.setAttribute("loginUser", gvo);
					session.setAttribute("memberkind", gvo.get("MEMBERKIND"));
					model.addAttribute("kind1", 1);
					return "redirect:/deliveryForm.do";
				}
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
	/*
	// 회원 주소지 페이지로 이동
	@RequestMapping(value="/myAddressForm")
	public ModelAndView myAddressForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			if((int)session.getAttribute("memberkind") == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				MyAddressVO mavo = as.getMyAddress(mvo.getMseq());
				ArrayList<orderVO> list1 = os.getOrderList(mvo.getId());
				ArrayList<CartVO> list2 = cs.selectCart( mvo.getId() );
				String addr = mavo.getAddress();
				String[] addrs= addr.split(" ");
				String addr1="";
				for(int i=0; i<3; i++) {
					addr1 += addrs[i] + " ";
				}
				String addr2="";
				for(int i=3; i<addrs.length; i++) {
					addr2 += addrs[i] + " ";
				}			 
				mav.addObject("addr1", addr1);
				mav.addObject("addr2", addr2);
				mav.addObject("zip_num", mavo.getZip_num());
				request.setAttribute("ovo", list1);
				request.setAttribute("cvo", list2);
				mav.setViewName("delivery/myaddress");
			}else if((int)session.getAttribute("memberkind") == 2) {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	// 회원 주소지  변경
	@RequestMapping(value="/updateAddress")
	public ModelAndView updateAddress(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			String address = request.getParameter("addr1") + " " + request.getParameter("addr2");
			String zip_num = request.getParameter("zip_num");
			// 회원 종류 검사 (1:회원, 2:비회원)
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				if(mvo == null) {
					mav.setViewName("redirect:/loginForm");
				}else {
					MyAddressVO mavo = new MyAddressVO();
					mavo.setZip_num(zip_num);
					mavo.setAddress(address);
					mavo.setMseq(mvo.getMseq());
					as.updateUserAddress(mavo);
					mav.setViewName("redirect:/deliveryMypageForm");
				}
			} else{
				mav.setViewName("redirect:/loginForm");
			}
		}
		return mav;
	}*/
}
