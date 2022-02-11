package com.ezen.burger.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.service.CartService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.ProductService;

@Controller
public class CartController {
	@Resource(name="CartService")
	CartService cs;

	@Resource(name="OrderService")
	OrderService os;
	
	@Resource(name="ProductService")
	ProductService ps;
	
	// 카트 리스트 페이지로 이동
	@RequestMapping(value="/deliveryCartForm.do")
	public String deliveryCartForm(HttpServletRequest request, Model model) {
		String message = request.getParameter("message");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("memberkind") != null) {
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
				for(HashMap<String, Object> cvo : list2) { 
					totalPrice += 
					Integer.parseInt(cvo.get("PRICE1").toString()) * 
					Integer.parseInt(cvo.get("QUANTITY").toString());
				}
				// 해당 접속 회원의 추가 재료의 목록을 가져오기
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("mseq", mvo.get("MSEQ").toString());
				paramMap.put("ref_cursor", null);
				
				ps.selectSubProductOrder(paramMap);
				
				ArrayList<HashMap<String, Object>> spovo = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
				
				// 추가 재료의 가격까지 총 가격으로 계산
				for(int i = 0; i < spovo.size(); i++) {
					totalPrice += Integer.parseInt(spovo.get(i).get("ADDPRICE").toString());
				}
				if(totalPrice<=12000) {
				// 해당 값을 전송
					if(message !=null) {
						model.addAttribute("message", message);
					}
				}
				model.addAttribute("totalPrice", totalPrice);
				model.addAttribute("spseqAm", spovo);
				model.addAttribute("ovo", list1);
				model.addAttribute("cvo", list2);
				return "delivery/cart";
			}else if(memberKind == 2) {
				HashMap<String, Object> gvo = (HashMap<String, Object>)session.getAttribute("loginUser");
				//해당 접속 회원의 주문 목록과 카트 목록 가져오기
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("id", gvo.get("ID").toString());
				paramMap2.put("ref_cursor", null);
				os.getOrderList(paramMap2);
				
				ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
				ArrayList<HashMap<String, Object>> list2 = (ArrayList<HashMap<String, Object>>)session.getAttribute("guestCartList");
				// 가져온 카트 목록에서 가격 총합 계산 
				int totalPrice = 0; 
				for(HashMap<String, Object> cvo : list2) { 
					totalPrice += 
					Integer.parseInt(cvo.get("PRICE1").toString()) * 
					Integer.parseInt(cvo.get("QUANTITY").toString());
				}
				
				// 해당 접속 회원의 추가 재료의 목록을 가져오기
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("gseq", gvo.get("GSEQ").toString());
				paramMap.put("ref_cursor", null);
				
				ps.selectSubProductOrder2(paramMap);
				
				ArrayList<HashMap<String, Object>> spovo = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
				
				// 추가 재료의 가격까지 총 가격으로 계산
				for(int i = 0; i < spovo.size(); i++) {
					totalPrice += Integer.parseInt(spovo.get(i).get("ADDPRICE").toString());
				}
		
				// 해당 값을 전송
				if(totalPrice<=12000) {
					if(message !=null) {
						model.addAttribute("message", message);
					}
				}
				model.addAttribute("totalPrice", totalPrice);
				model.addAttribute("spseqAm", spovo);
				model.addAttribute("ovo", list1);
				model.addAttribute("cvo", list2);
				return "delivery/cart";
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
	
	// 재료 추가 없이 카트 저장
	@RequestMapping(value="noMeterialCart.do")
	public String noMeterialCart(@RequestParam("pseq") int pseq, HttpServletRequest request,
			Model model) { 
		HttpSession session = request.getSession();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pseq", pseq);
		paramMap.put("ref_cursor", null);
		
		ps.getProducts(paramMap);
		
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		HashMap<String, Object> pvo = list.get(0);
		
		//로그인이 되어 있다면 로그인 정보에스 id 를 추출하고  상품번호와 아이디를  CartVO 에 담아서
		if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 1) {
			HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
			HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("id", mvo.get("ID"));
			paramMap2.put("PSEQ", pseq);
			
			cs.insertCart(paramMap2);
		}else if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 2) {
			HashMap<String, Object> gvo = (HashMap<String, Object>)session.getAttribute("loginUser");
			
			// 비회원 카트 리스트 호출
			ArrayList<HashMap<String, Object>> guestCartList = (ArrayList<HashMap<String, Object>>) session.getAttribute("guestCartList");
			HashMap<String, Object> cvo = new HashMap<String, Object>();
			cvo.put("ID", gvo.get("ID"));   // 아이디 저장
			
			HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("ref_cursor", null);
			cs.getCseq(paramMap2);
			cvo.put("CSEQ", Integer.parseInt(paramMap2.get("ref_cursor").toString()));
			
			cvo.put("PSEQ", pseq);
			cvo.put("QUANTITY", 1);
			cvo.put("RESULT", "1");

			Timestamp ts = new Timestamp(System.currentTimeMillis());
			cvo.put("DATE", ts);
			cvo.put("PNAME", pvo.get("PNAME"));
			cvo.put("NAME", pvo.get("NAME"));
			cvo.put("IMAGE", pvo.get("IMAGE"));
			cvo.put("PRICE1", pvo.get("PRICE1"));
			cvo.put("KIND1", pvo.get("KIND1"));
			cvo.put("KIND3", pvo.get("KIND3"));
			cvo.put("PHONE", pvo.get("PHONE"));
			cvo.put("MEMBERKIND", gvo.get("MEMBERKIND"));
			guestCartList.add(cvo);
			session.setAttribute("guestCartList", guestCartList);
		}
		return "redirect:/deliveryCartForm.do";
	}
	
	// 카트 삭제
	@RequestMapping(value="/cartDelete.do")
	public String cartDelete(HttpServletRequest request, Model model,
			@RequestParam("cseq") int cseq) {
		HttpSession session = request.getSession();
		if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 1) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cseq", cseq);
			cs.deleteCart(paramMap);
			return "redirect:/deliveryCartForm.do";
		}else if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 2) {
			// 비회원 카트 삭제시 세션에서 카트 정보를 불러온 뒤
			ArrayList<HashMap<String, Object>> guestCartList = (ArrayList<HashMap<String, Object>>)session.getAttribute("guestCartList");
			int index = 0;
			// 해당 cseq 값을 가진 CartVO를 삭제한다.
			for(HashMap<String, Object> cvo : guestCartList) {
				if(Integer.parseInt(cvo.get("CSEQ").toString()) == cseq) {
					guestCartList.remove(index++);
					break;
				}
			}
			session.setAttribute("guestCartList", guestCartList);
			return "redirect:/deliveryCartForm.do";
		}else {
			return "redirect:/loginForm.do";
		}
	}
	
	// 카트 여러개 삭제, 전체 삭제
	@RequestMapping(value="/deliveryCartDelete")
	public String deliveryCartDelete(HttpServletRequest request, Model model,
			@RequestParam("menu") String c) {
		HttpSession session = request.getSession();
		String[] cseq = c.split(",");
		if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 1) {
			for(String cq : cseq) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("cseq", cq);
				cs.deleteCart(paramMap);
			}
			return "redirect:/deliveryCartForm.do";
		}else if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 2) {
			ArrayList<HashMap<String, Object>> guestCartList = (ArrayList<HashMap<String, Object>>)session.getAttribute("guestCartList");
			for(String cq : cseq) {
				int index = 0;
				for(HashMap<String, Object> cvo : guestCartList) {
					if(cvo.get("CSEQ").toString().equals(cq)) {
						guestCartList.remove(index);
						break;
					}
					index++;
				}
			}
			session.setAttribute("guestCartList", guestCartList);
			return "redirect:/deliveryCartForm.do";
		}else {
			return "redirect:/loginForm.do";
		}
	}
	// 카트 상품 수량 감소
	@RequestMapping(value="/minusQuantity.do")
	public String minusQuantity(HttpServletRequest request, Model model,
			@RequestParam("cseq") int cseq) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind")!=null && session.getAttribute("loginUser")!=null) {
			if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 1) {
				// 수량이 1개보다 작으면 더이상 감소가 되지않음
				HashMap<String ,Object> paramMap = new HashMap<String ,Object>();
				paramMap.put("cseq", cseq);
				paramMap.put("ref_cursor", null);
				
				cs.getQuantity(paramMap);
				
				ArrayList<HashMap<String ,Object>> list = (ArrayList<HashMap<String ,Object>>)paramMap.get("ref_cursor");
				int quantity=Integer.parseInt(list.get(0).get("QUANTITY").toString()); 
						
				if(quantity <= 1) {
					return "redirect:/deliveryCartForm.do";
				}else {
					HashMap<String ,Object> paramMap1 = new HashMap<String ,Object>();
					paramMap1.put("cseq", cseq);
					cs.minusQuantity(paramMap1);
					return "redirect:/deliveryCartForm.do";
				}
			}else if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 2) {
				ArrayList<HashMap<String ,Object>> guestCartList = (ArrayList<HashMap<String ,Object>>)session.getAttribute("guestCartList");
				int index = 0;
				for(HashMap<String ,Object> cvo : guestCartList) {
					if(Integer.parseInt(cvo.get("CSEQ").toString()) == cseq) {
						if(Integer.parseInt(cvo.get("QUANTITY").toString()) <= 1) {
							break;
						}else {
							guestCartList.remove(index);
							cvo.put("QUANTITY", (int)cvo.get("QUANTITY") - 1);
							guestCartList.add(index, cvo);
							break;
						}
					}
					index++;
				}
				session.setAttribute("guestCartList", guestCartList);
				return "redirect:/deliveryCartForm.do";
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
	
	// 카트 상품 수량 증가
	@RequestMapping(value="/plusQuantity.do")
	public String plusQuantity(HttpServletRequest request, Model model,
			@RequestParam("cseq") int cseq) {
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind")!=null && session.getAttribute("loginUser")!=null) {
			if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 1) {
				// 수량이 99개보다 크면 더이상 증가가 되지않음
				HashMap<String ,Object> paramMap = new HashMap<String ,Object>();
				paramMap.put("cseq", cseq);
				paramMap.put("ref_cursor", null);
				
				cs.getQuantity(paramMap);
				
				ArrayList<HashMap<String ,Object>> list = (ArrayList<HashMap<String ,Object>>)paramMap.get("ref_cursor");
				int quantity=Integer.parseInt(list.get(0).get("QUANTITY").toString()); 
				
				if(quantity >= 99) {
					return "redirect:/deliveryCartForm.do";
				}else {
					HashMap<String ,Object> paramMap1 = new HashMap<String ,Object>();
					paramMap1.put("cseq", cseq);
					cs.plusQuantity(paramMap1);
					return "redirect:/deliveryCartForm.do";
				}
			}else if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 2) {
				ArrayList<HashMap<String ,Object>> guestCartList = (ArrayList<HashMap<String ,Object>>)session.getAttribute("guestCartList");
				int index = 0;
				for(HashMap<String ,Object> cvo : guestCartList) {
					if(Integer.parseInt(cvo.get("CSEQ").toString()) == cseq) {
						if(Integer.parseInt(cvo.get("QUANTITY").toString()) >= 99) {
							break;
						}else {
							guestCartList.remove(index);
							cvo.put("QUANTITY", (int)cvo.get("QUANTITY") + 1);
							guestCartList.add(index, cvo);
							break;
						}
					}
					index++;
				}
				
				session.setAttribute("guestCartList", guestCartList);
				return "redirect:/deliveryCartForm.do";
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
	
	// 재료 추가 카트 저장
	@RequestMapping(value="/insertAddMeterial.do")
	public String insertAddMeterial(HttpServletRequest request, Model model,
			@RequestParam("addM") String addm) { // m의 0번째 인덱스에는 상품의 pseq값, 1부터는 추가 재료의spseq값이 들어있다.
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind")!=null && session.getAttribute("loginUser")!=null) {
			String[] m = addm.split(",");
			if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 1) {
				HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
				HashMap<String, Object> sublist = null;
				if(m.length != 0) { // meterial값이 있다면
					if(m.length == 1) { // am 배열의 길이가 1이라면 pseq값만 온 것이므로 선택한 메뉴가 없다. 즉 그냥 pass
						model.addAttribute("spseqAm", null);
						System.out.println(m[0]);
						return "redirect:/deliveryCartForm.do";
					}else {
						// 넘어온 spseq 값이 있다면 해당 sub_productVO 정보를 list로 저장한다.
						System.out.println(m[0]);
						sublist = new HashMap<String, Object>();
						sublist.put("ref_cursor", null);
						ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
						for(int i = 1; i < m.length; i++)
						{
							sublist.put("spseq", Integer.parseInt(m[i]));
							ps.getSubProduct2(sublist);
							ArrayList<HashMap<String, Object>> temp = (ArrayList<HashMap<String, Object>>) sublist.get("ref_cursor");
							HashMap<String, Object> tmp = temp.get(0);
							list.add(tmp);
						}
						
						// 이후 해당 주문의 cart를 생성
						HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
						paramMap1.put("id", mvo.get("ID").toString());
						paramMap1.put("PSEQ", Integer.parseInt(m[0]));
						
						cs.insertCart(paramMap1);
						
						// 방금 들어간 pseq값을 가진 카트 중 젤 최근에 들어온것을 가져온다.
						HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
						paramMap2.put("ref_cursor", null);
						paramMap2.put("PSEQ", Integer.parseInt(m[0]));
						
						cs.getPseqCart(paramMap2);
						
						ArrayList<HashMap<String, Object>> Am_cvo = (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor"); 
						
						HashMap<String, Object> cvo1 = Am_cvo.get(0);
						// 해당 카트 번호와 추가 메뉴vo, 회원의 mseq값을 가지고 추가재료 order를 생성
						HashMap<String, Object> spvo = new HashMap<String, Object>();
						spvo.put("CSEQ", Integer.parseInt(cvo1.get("CSEQ").toString()));
						spvo.put("MSEQ", Integer.parseInt(mvo.get("MSEQ").toString()));
						for(int i = 0; i < list.size(); i++) {
							spvo.put("SPSEQ", Integer.parseInt(list.get(i).get("SPSEQ").toString()));
							spvo.put("SNAME", list.get(i).get("SNAME").toString());
							spvo.put("ADDPRICE", list.get(i).get("ADDPRICE").toString());
							ps.insertSubProductOrder(spvo);
						}
						
						return "redirect:/deliveryCartForm.do";
					}
				}else {
					return "redirect:/index.do";
				}
			}else if(Integer.parseInt(session.getAttribute("memberkind").toString()) == 2) {
				HashMap<String, Object> gvo = (HashMap<String, Object>)session.getAttribute("loginUser");
				HashMap<String, Object> sublist = null;
				if(m.length != 0) { // meterial값이 있다면
					if(m.length == 1) { // am 배열의 길이가 1이라면 pseq값만 온 것이므로 선택한 메뉴가 없다. 즉 그냥 pass
						model.addAttribute("spseqAm", null);
						return "redirect:/deliveryCartForm.do";
					}else {
						// 넘어온 spseq 값이 있다면 해당 sub_productVO 정보를 list로 저장한다.
						sublist = new HashMap<String, Object>();
						sublist.put("ref_cursor", null);
						ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
						for(int i = 1; i < m.length; i++)
						{
							sublist.put("spseq", Integer.parseInt(m[i]));
							ps.getSubProduct2(sublist);
							ArrayList<HashMap<String, Object>> temp = (ArrayList<HashMap<String, Object>>) sublist.get("ref_cursor");
							HashMap<String, Object> tmp = temp.get(0);
							list.add(tmp);
						}
						
						HashMap<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("pseq", Integer.parseInt(m[0]));
						paramMap.put("ref_cursor", null);
						
						ps.getProducts(paramMap);
						
						ArrayList<HashMap<String, Object>> list1 = (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
						HashMap<String, Object> pvo = list1.get(0);
						
						ArrayList<HashMap<String, Object>> guestCartList = (ArrayList<HashMap<String, Object>>) session.getAttribute("guestCartList");
						
						// 이후 해당 주문의 cart를 생성
						HashMap<String, Object> cvo = new HashMap<String, Object>();
						HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
						paramMap1.put("ref_cursor", null);
						cs.getCseq(paramMap1);
						int cseq = Integer.parseInt(paramMap1.get("ref_cursor").toString());
						cvo.put("CSEQ", cseq);
						cvo.put("ID", gvo.get("ID"));   // 아이디 저장
						cvo.put("PSEQ", Integer.parseInt(m[0]));  // 상품번호저장
						cvo.put("QUANTITY", 1);
						cvo.put("RESULT", "1");
						
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						cvo.put("DATE", ts);
						cvo.put("PNAME", pvo.get("PNAME"));
						cvo.put("MNAME",gvo.get("NAME"));
						cvo.put("IMAGE", pvo.get("IMAGE"));
						cvo.put("PRICE1", pvo.get("PRICE1"));
						cvo.put("KIND1", pvo.get("KIND1"));
						cvo.put("KIND3", pvo.get("KIND3"));
						cvo.put("PHONE", gvo.get("PHONE"));
						cvo.put("MEMBERKIND", gvo.get("MEMBERKIND"));
						guestCartList.add(cvo);
						session.setAttribute("guestCartList", guestCartList);
						
						
						HashMap<String, Object> spvo = new HashMap<String, Object>();
						spvo.put("CSEQ", Integer.parseInt(cvo.get("CSEQ").toString()));
						spvo.put("GSEQ", Integer.parseInt(gvo.get("GSEQ").toString()));
						for(int i = 0; i < list.size(); i++) {
							spvo.put("SPSEQ", Integer.parseInt(list.get(i).get("SPSEQ").toString()));
							spvo.put("SNAME", list.get(i).get("SNAME").toString());
							spvo.put("ADDPRICE", list.get(i).get("ADDPRICE").toString());
							ps.insertSubProductOrderByGseq(spvo);
						}
						// 해당 카트 번호와 추가 메뉴vo, 비회원의 gseq값을 가지고 추가재료 order를 생성
						// 비회원은 주문을 하지않고 나갈경우 추가재료에 대한 주문 데이터가 남는 상황이 발생한다.
						// 이후 관리자가 주문리스트에서 검색을 할 때 indate가 1시간~2시간이 지난데이터는 삭제하고 공개하는
						// 코드를 작성할 필요가 있다.
						
						return "redirect:/deliveryCartForm.do";
					}
				}else {
					return "redirect:/index.do";
				}
			}else {
				return "redirect:/loginForm.do";
			}
		}else {
			return "redirect:/loginForm.do";
		}
	}
}
