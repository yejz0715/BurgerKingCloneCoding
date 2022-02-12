package com.ezen.Mburger.web;

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
public class MAddressController {
	@Resource(name="AddressService")
	AddressService as;
	@Resource(name="ProductService")
	ProductService ps;
	@Resource(name="OrderService")
	OrderService os;
	@Resource(name="CartService")
	CartService cs;
	
	
	// 주소찾기 팝업창 띄우기
		@RequestMapping(value="/MfindZipNum.do")
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
			return "mobile/delivery/MfindZipNum";
		}
	
	
	
	// 입력한 주소 저장
	@RequestMapping(value="/MmyAddress.do")
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
					return "redirect:/MloginForm.do";
				}else {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("zip_num", zip_num);
					paramMap.put("address", address);
					paramMap.put("mseq", Integer.parseInt(mvo.get("MSEQ").toString()));
					
					as.setUserAddress(paramMap);
					
					model.addAttribute("kind1", 1);
					return "redirect:/MdeliveryForm.do";
				}
			}else if(memberKind == 2){
				HashMap<String, Object> gvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				if(gvo == null) {
					return "redirect:/MloginForm.do";
				}else {
					gvo.put("ZIP_NUM", zip_num);
					gvo.put("ADDRESS", address);
					
					as.setGuestAddress(gvo);
					
					session.setAttribute("loginUser", gvo);
					session.setAttribute("memberkind", gvo.get("MEMBERKIND"));
					model.addAttribute("kind1", 1);
					return "redirect:/MdeliveryForm.do";
				}
			}else {
				return "redirect:/MloginForm.do";
			}
		}else {
			return "redirect:/MloginForm.do";
		}
	}
	
	
	// 회원 주소지 페이지로 이동
	@RequestMapping(value="/MmyAddressForm.do")
	public String myAddressForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("mseq", mvo.get("MSEQ").toString());
				paramMap.put("ref_cursor", null);
				
				as.b_getMyAddress(paramMap);
				
				ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
				if(list.size() == 0) {
					return "mobile/delivery/MaddressSet";
				}else {
					HashMap<String, Object> mavo = list.get(0);
					String addr = mavo.get("ADDRESS").toString();
					String[] addrs= addr.split(" ");
					String addr1="";
					for(int i=0; i<3; i++) {
						addr1 += addrs[i] + " ";
					}
					String addr2="";
					for(int i=3; i<addrs.length; i++) {
						addr2 += addrs[i] + " ";
					}			 
					
					// order, cart View 조회
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
					model.addAttribute("addr1", addr1);
					model.addAttribute("addr2", addr2);
					model.addAttribute("zip_num", mavo.get("ZIP_NUM").toString());
					return "mobile/delivery/Mmyaddress";
				}
			}else if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 2) {
				return "redirect:/MloginForm.do";
			}else {
				return "redirect:/MloginForm.do";
			}
		}else {
			return "redirect:/MloginForm.do";
		}
	}
	
	// 회원 주소지  변경
	@RequestMapping(value="/MupdateAddress")
	public String updateAddress(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			String address = request.getParameter("addr1") + " " + request.getParameter("addr2");
			String zip_num = request.getParameter("zip_num");
			// 회원 종류 검사 (1:회원, 2:비회원)
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>) session.getAttribute("loginUser");
				if(mvo == null) {
					return "redirect:/MloginForm.do";
				}else {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("zip_num", zip_num);
					paramMap.put("address", address);
					paramMap.put("mseq", Integer.parseInt(mvo.get("MSEQ").toString()));
					
					as.updateUserAddress(paramMap);
					
					model.addAttribute("kind1", 1);
					return "redirect:/MdeliveryMypageForm.do";
				}
			} else{
				return "redirect:/MloginForm.do";
			}
		}else {
			return "redirect:/MloginForm.do";
		}
	}
	
	
}
