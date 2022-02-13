package com.ezen.Mburger.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.burger.service.AddressService;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.ProductService;

@Controller
public class MOrderCotroller {
	@Resource(name="OrderService")
	OrderService os;
	
	@Resource(name="CartService")
	CartService cs;
	
	@Resource(name="ProductService")
	ProductService ps;
	
	@Resource(name="AddressService")
	AddressService as;
	
	// 주문 페이지로 이동
	@RequestMapping(value="/MdeliveryOrderList.do")
	public String MdeliveryOrderList(HttpServletRequest request, Model model,
			@RequestParam(value="message", required = false) String message) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
				//해당 접속 회원의 주문 목록과 카트 목록 가져오기
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
				
				// 가져온 카트 목록에서 가격 총합 계산 
				int totalPrice = 0; 
				
				for(HashMap<String, Object> ovo : list1) { 
					totalPrice += 
					Integer.parseInt(ovo.get("PRICE1").toString()) * 
					Integer.parseInt(ovo.get("QUANTITY").toString());
				}
				
				// 해당 접속 회원의 추가 재료의 목록을 가져오기
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("mseq", mvo.get("MSEQ").toString());
				paramMap.put("ref_cursor", null);
				
				ps.selectSubProductOrder3(paramMap);
				
				ArrayList<HashMap<String, Object>> spovo = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
				
				// 추가 재료의 가격까지 총 가격으로 계산
				for(int i = 0; i < spovo.size(); i++) {
					HashMap<String, Object> temp = new HashMap<String, Object>();
					temp.put("odseq", spovo.get(i).get("ODSEQ"));
					temp.put("ref_cursor", null);
					ps.getResult(temp);
					ArrayList<HashMap<String, Object>> results = (ArrayList<HashMap<String, Object>>)temp.get("ref_cursor");
					for(HashMap<String, Object> result : results ) {
						if(Integer.parseInt(result.get("RESULT").toString()) == 1 || 
							Integer.parseInt(result.get("RESULT").toString()) == 2 || 
							Integer.parseInt(result.get("RESULT").toString()) == 3) {
							totalPrice += Integer.parseInt(spovo.get(i).get("ADDPRICE").toString());
						}
					}
				}

				// 로그인 회원의 주소 정보 호출
				HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
				paramMap1.put("mseq", mvo.get("MSEQ"));
				paramMap1.put("ref_cursor", null);
				
				as.b_getMyAddress(paramMap1);
				
				ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap1.get("ref_cursor");
				HashMap<String, Object> mavo = list.get(0);
		
				// 해당 값을 전송
				if(message !=null) {
					model.addAttribute("message", message);
				}
				model.addAttribute("totalPrice", totalPrice);
				model.addAttribute("spseqAm", spovo);
				model.addAttribute("userPhone", mvo.get("PHONE").toString());
				model.addAttribute("Myaddress", mavo);
				model.addAttribute("ovo", list1);
				model.addAttribute("cvo", list2);
				return "mobile/delivery/MorderList";
			}else if(memberKind == 2) {
				HashMap<String, Object> gvo = (HashMap<String, Object>)session.getAttribute("loginUser");
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("id", gvo.get("ID").toString());
				paramMap2.put("ref_cursor", null);
				
				os.getOrderListByGuest(paramMap2);
				
				ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
				
				// 가져온 카트 목록에서 가격 총합 계산 
				int totalPrice = 0; 
				for(HashMap<String, Object> ovo : list1) { 
					totalPrice += 
					Integer.parseInt(ovo.get("PRICE1").toString()) * 
					Integer.parseInt(ovo.get("QUANTITY").toString());
				}
				
				// 해당 접속 회원의 추가 재료의 목록을 가져오기
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("gseq", gvo.get("GSEQ").toString());
				paramMap.put("ref_cursor", null);
				
				ps.selectSubProductOrder4(paramMap);
				
				ArrayList<HashMap<String, Object>> spovo = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
				
				// 추가 재료의 가격까지 총 가격으로 계산
				for(int i = 0; i < spovo.size(); i++) {
					HashMap<String, Object> temp = new HashMap<String, Object>();
					temp.put("odseq", spovo.get(i).get("ODSEQ"));
					temp.put("ref_cursor", null);
					ps.getResult(temp);
					ArrayList<HashMap<String, Object>> results = (ArrayList<HashMap<String, Object>>)temp.get("ref_cursor");
					for(HashMap<String, Object> result : results ) {
						if(Integer.parseInt(result.get("RESULT").toString()) == 1 || 
							Integer.parseInt(result.get("RESULT").toString()) == 2 || 
							Integer.parseInt(result.get("RESULT").toString()) == 3) {
							totalPrice += Integer.parseInt(spovo.get(i).get("ADDPRICE").toString());
						}
					}
				}
				
				// 주문 페이지에 띄울 로그인한 유저의 주소지 
				HashMap<String, Object> mavo = new HashMap<String, Object>();
				mavo.put("ADDRESS", gvo.get("ADDRESS"));
				
				// 해당 값을 전송
				if(message !=null) {
					model.addAttribute("message", message);
				}
				model.addAttribute("totalPrice", totalPrice);
				model.addAttribute("spseqAm", spovo);
				model.addAttribute("userPhone", gvo.get("PHONE"));
				model.addAttribute("Myaddress", mavo);
				model.addAttribute("ovo", list1);
				model.addAttribute("mkind", session.getAttribute("memberkind"));
				return "mobile/delivery/MorderList";
			}else {
				return "redirect:/MloginForm.do";
			}
		}else {
			return "redirect:/MloginForm.do";
		}
	}
	
	// 카트 목록 주문하기
	@RequestMapping(value="/MdeliveryCartOrder.do")
	public String MdeliveryCartOrder(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			int memberKind = Integer.parseInt(session.getAttribute("memberkind").toString());
			if(memberKind == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ref_cursor", null);
				paramMap.put("mseq", mvo.get("MSEQ"));
				
				as.b_getMyAddress(paramMap);
				
				ArrayList<HashMap<String, Object>> temp = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor"); 
				HashMap<String, Object> mavo = temp.get(0);
				// 주소가 없으면 주소 설정 창으로 이동
				if(mavo.get("ADDRESS") == null || mavo.get("ADDRESS").equals("")) {
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
					// 주문자 아이디로 검색한 카트 목록(지금 주문 처리할) 목록을 먼저 조회합니다
					HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
					paramMap3.put("id", mvo.get("ID").toString());
					paramMap3.put("ref_cursor", null);
					
					cs.selectCart(paramMap3);
					
					ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>)paramMap3.get("ref_cursor");
					
					// 카트 목록의 총가격을 계산하여 12000원 미만이면 주문을 실행하지 않는다.
					int totalPrice = 0;
					for(HashMap<String, Object> cvo : list2) { 
						totalPrice += 
						Integer.parseInt(cvo.get("PRICE1").toString()) * 
						Integer.parseInt(cvo.get("QUANTITY").toString());
					}
					
					// 해당 접속 회원의 추가 재료의 목록을 가져오기
					HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
					paramMap1.put("mseq", Integer.parseInt(mvo.get("MSEQ").toString()));
					paramMap1.put("ref_cursor", null);
					
					ps.selectSubProductOrder(paramMap1);
					
					ArrayList<HashMap<String, Object>> spovo = (ArrayList<HashMap<String, Object>>)paramMap1.get("ref_cursor");
					
					// 추가 재료의 가격까지 총 가격으로 계산
					for(int j = 0; j < list2.size(); j++) {
						for(int i = 0; i < spovo.size(); i++) {
							if(list2.get(j).get("CSEQ") == spovo.get(i).get("CSEQ")) {
								totalPrice += Integer.parseInt(spovo.get(i).get("ADDPRICE").toString());
							}
						}
					}
					
					if(totalPrice < 12000) {
						model.addAttribute("message", "가격이 12000원 이상이어야 주문 가능합니다.");
						return "redirect:/MdeliveryCartForm.do";
					}
					
					// 추출한 list 와 주문자의 아디를 갖고 OrderDao 에 가서 오더 와 오더 디테일에 데이터를 추가합니다.
					HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
					paramMap2.put("id", mvo.get("ID"));
					paramMap2.put("memberkind", Integer.parseInt(mvo.get("MEMBERKIND").toString()));
					paramMap2.put("oseq", 0);
					os.insertOrder(paramMap2);
					
					for(HashMap<String, Object> cvo : list2) {
						paramMap2.put("pseq", Integer.parseInt(cvo.get("PSEQ").toString()));
						paramMap2.put("quantity", Integer.parseInt(cvo.get("QUANTITY").toString()));
						paramMap2.put("cseq", cvo.get("CSEQ"));
						os.insertOrderDetail(paramMap2);
					}
					
					return "redirect:/MdeliveryOrderList.do";
				}
			}else if(memberKind == 2) {
				HashMap<String, Object> gvo = (HashMap<String, Object>)session.getAttribute("loginUser");
				// 주소가 없으면 주소 설정 창으로 이동
				if(gvo.get("ADDRESS") == null || gvo.get("ADDRESS").equals("")) {
					return "mobile/delivery/MaddressSet";
				}else {
					// 주문자 아이디로 검색한 카트 목록(지금 주문 처리할) 목록을 먼저 조회합니다
					ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)session.getAttribute("guestCartList");
					
					// 카트 목록의 총가격을 계산하여 12000원 미만이면 주문을 실행하지 않는다.
					int totalPrice = 0;
					for(HashMap<String, Object> cvo : list) {
						totalPrice += 
						Integer.parseInt(cvo.get("PRICE1").toString()) * 
						Integer.parseInt(cvo.get("QUANTITY").toString());
					}
					
					// 해당 접속 회원의 추가 재료의 목록을 가져오기
					HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
					paramMap1.put("gseq", Integer.parseInt(gvo.get("GSEQ").toString()));
					paramMap1.put("ref_cursor", null);
					
					ps.selectSubProductOrder2(paramMap1);
					
					ArrayList<HashMap<String, Object>> spovo = (ArrayList<HashMap<String, Object>>)paramMap1.get("ref_cursor");
					
					// 추가 재료의 가격까지 총 가격으로 계산
					for(int j = 0; j < list.size(); j++) {
						for(int i = 0; i < spovo.size(); i++) {
							if(Integer.parseInt(list.get(j).get("CSEQ").toString()) == Integer.parseInt(spovo.get(i).get("CSEQ").toString())) {
								totalPrice += Integer.parseInt(spovo.get(i).get("ADDPRICE").toString());
							}
						}
					}
					
					if(totalPrice < 12000) {
						model.addAttribute("message", "가격이 12000원 이상이어야 주문 가능합니다.");
						return "redirect:/MdeliveryCartForm.do";
					}
					
					
					// 추출한 list 와 주문자의 아디를 갖고 OrderDao 에 가서 오더 와 오더 디테일에 데이터를 추가합니다.
					HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
					paramMap2.put("id", gvo.get("ID"));
					paramMap2.put("memberkind", Integer.parseInt(gvo.get("MEMBERKIND").toString()));
					paramMap2.put("oseq", 0);
					os.insertOrder(paramMap2);
					
					for(HashMap<String, Object> cvo : list) {
						paramMap2.put("pseq", cvo.get("PSEQ").toString());
						paramMap2.put("quantity", Integer.parseInt(cvo.get("QUANTITY").toString()));
						paramMap2.put("cseq", cvo.get("CSEQ"));
						os.insertOrderDetail(paramMap2);
					}
					
					// 비회원의 카트세션을 초기화한다.
					list.clear();
					session.setAttribute("guestCartList", list);
					return "redirect:/MdeliveryOrderList.do";
				}
			}else {
				return "redirect:/MloginForm.do";
			}
		}else {
			return "redirect:/MloginForm.do";
		}
	}
	
	// 비회원 주문 내역창 불러오기
	@RequestMapping(value="/MnonOrderList.do")
	public String MnonOrderList(HttpServletRequest request, Model model){
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		int pwd = Integer.parseInt(request.getParameter("pwd2"));
		
		if(request.getParameter("oseq")==null || request.getParameter("pwd2")==null) {
			model.addAttribute("message2", "조회하기 위한 정보를 정확하게 입력해주세요.");
			return "redirect:/loginForm.do";
		}		
		// 입력받은 주문번호의 내역을 가져오기
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("oseq", oseq);
		paramMap.put("ref_cursor", null);
		
		os.getOrderByOseq(paramMap);
		
		ArrayList<HashMap<String, Object>> ovo = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		
		// 해당 주문이 없거나, 비번이 없을 때,
		if(ovo.size() == 0) {
			model.addAttribute("message2", "해당 주문이 없습니다.");
			return "mobile/member/MloginForm";
		}else if(Integer.parseInt(ovo.get(0).get("PWD").toString()) != pwd) {
			model.addAttribute("message2", "비밀번호가 다릅니다.");
			return "mobile/member/MloginForm";
		}else if(Integer.parseInt(ovo.get(0).get("PWD").toString()) == pwd) {
			int totalPrice = 0; 
			for(HashMap<String, Object> order : ovo) {
				totalPrice += Integer.parseInt(order.get("PRICE1").toString()) * 
						Integer.parseInt(order.get("QUANTITY").toString());
			}
			// 해당 접속 회원의 추가 재료의 목록을 가져오기
			HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("oseq", oseq);
			paramMap1.put("ref_cursor", null);
			
			ps.selectSubProductOrder5(paramMap1);
			
			ArrayList<HashMap<String, Object>> spovo = (ArrayList<HashMap<String, Object>>)paramMap1.get("ref_cursor"); 
			
			for(int i = 0; i < spovo.size(); i++) {
				HashMap<String, Object> temp = new HashMap<String, Object>();
				temp.put("odseq", spovo.get(i).get("ODSEQ"));
				temp.put("ref_cursor", null);
				ps.getResult(temp);
				ArrayList<HashMap<String, Object>> results = (ArrayList<HashMap<String, Object>>)temp.get("ref_cursor");
				for(HashMap<String, Object> result : results ) {
					if(Integer.parseInt(result.get("RESULT").toString()) == 1 || 
						Integer.parseInt(result.get("RESULT").toString()) == 2 || 
						Integer.parseInt(result.get("RESULT").toString()) == 3) {
						totalPrice += Integer.parseInt(spovo.get(i).get("ADDPRICE").toString());
					}
				}
			}
			
			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("spseqAm", spovo);
			model.addAttribute("address", ovo.get(0).get("ADDRESS"));
			model.addAttribute("userPhone", ovo.get(0).get("PHONE"));
			model.addAttribute("result", ovo.get(0).get("RESULT"));
			model.addAttribute("ovo", ovo);
			return "mobile/delivery/MguestOrderList";
		}else {
			model.addAttribute("message2", "기타 오류로 조회가 불가능합니다. 관리자에게 문의하세요.");
			return "mobile/member/MloginForm";
		}
	}
	
	// 주문 삭제
	@RequestMapping(value="/MorderDelete.do")
	public String MorderDelete(HttpServletRequest request, Model model,
			@RequestParam("odseq") String odseq) {
		HttpSession session = request.getSession();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("odseq", odseq);
		paramMap.put("result", 0);
		
		os.getOrderDetail(paramMap);
		
		if(Integer.parseInt(paramMap.get("result").toString()) > 3) {
			model.addAttribute("message", "삭제하려는 주문 중 진행중인 배달이 있습니다.");
			return "redirect:/MdeliveryOrderList.do";
		}
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			os.deleteOrder2(paramMap);
			return "redirect:/MdeliveryOrderList.do";
		}else {
			return "redirect:/MloginForm.do";
		}
	}
}
