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
		ModelAndView mav = new ModelAndView();
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
				mav.addObject("totalPrice", totalPrice);
				mav.addObject("spseqAm", spovo);
				mav.addObject("ovo", list1);
				mav.addObject("cvo", list2);
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
		return "redirect:/deliveryCartForm";
	}
	/*
	// 카트 삭제
	@RequestMapping(value="/cartDelete")
	public ModelAndView cartDelete(HttpServletRequest request,
			@RequestParam("cseq") int cseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if((int)session.getAttribute("memberkind") == 1) {
			cs.deleteCart(cseq);
			mav.setViewName("redirect:/deliveryCartForm");
		}else if((int)session.getAttribute("memberkind") == 2) {
			// 비회원 카트 삭제시 세션에서 카트 정보를 불러온 뒤
			ArrayList<CartVO> guestCartList = (ArrayList<CartVO>)session.getAttribute("guestCartList");
			int index = 0;
			// 해당 cseq 값을 가진 CartVO를 삭제한다.
			for(CartVO cvo : guestCartList) {
				if(cvo.getCseq() == cseq) {
					guestCartList.remove(index++);
					break;
				}
			}
			session.setAttribute("guestCartList", guestCartList);
			mav.setViewName("redirect:/deliveryCartForm");
		}
		return mav;
	}
	
	// 카트 여러개 삭제, 전체 삭제
	@RequestMapping(value="/deliveryCartDelete")
	public ModelAndView deliveryCartDelete(HttpServletRequest request,
			@RequestParam("menu") int[] cseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if((int)session.getAttribute("memberkind") == 1) {
			for(int cq : cseq) {
				cs.deleteCart(cq);
			}
			mav.setViewName("redirect:/deliveryCartForm");
		}else if((int)session.getAttribute("memberkind") == 2) {
			ArrayList<CartVO> guestCartList = (ArrayList<CartVO>)session.getAttribute("guestCartList");
			for(int cq : cseq) {
				int index = 0;
				for(CartVO cvo : guestCartList) {
					if(cvo.getCseq() == cq) {
						guestCartList.remove(index);
						break;
					}
					index++;
				}
			}
			session.setAttribute("guestCartList", guestCartList);
			mav.setViewName("redirect:/deliveryCartForm");
		}
		return mav;
	}
	
	// 카트 상품 수량 감소
	@RequestMapping(value="/minusQuantity")
	public ModelAndView minusQuantity(HttpServletRequest request,
			@RequestParam("cseq") int cseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind")!=null && session.getAttribute("loginUser")!=null) {
			if((int)session.getAttribute("memberkind") == 1) {
				// 수량이 1개보다 작으면 더이상 감소가 되지않음
				int quantity = cs.getQuantity(cseq);
				if(quantity <= 1) {
					mav.setViewName("redirect:/deliveryCartForm");
				}else {
					cs.minusQuantity(cseq);
					mav.setViewName("redirect:/deliveryCartForm");
				}
			}else if((int)session.getAttribute("memberkind") == 2) {
				ArrayList<CartVO> guestCartList = (ArrayList<CartVO>)session.getAttribute("guestCartList");
				int index = 0;
				for(CartVO cvo : guestCartList) {
					if(cvo.getCseq() == cseq) {
						if(cvo.getQuantity() <= 1) {
							break;
						}else {
							guestCartList.remove(index);
							cvo.setQuantity(cvo.getQuantity() - 1);
							guestCartList.add(index, cvo);
							break;
						}
					}
					index++;
				}
				session.setAttribute("guestCartList", guestCartList);
				mav.setViewName("redirect:/deliveryCartForm");
			}
		}
		return mav;
	}
	
	// 카트 상품 수량 증가
	@RequestMapping(value="/plusQuantity")
	public ModelAndView plusQuantity(HttpServletRequest request,
			@RequestParam("cseq") int cseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind")!=null && session.getAttribute("loginUser")!=null) {
			if((int)session.getAttribute("memberkind") == 1) {
				// 수량이 99개보다 크면 더이상 증가가 되지않음
				int quantity = cs.getQuantity(cseq);
				if(quantity >= 99) {
					mav.setViewName("redirect:/deliveryCartForm");
				}else {
					cs.plusQuantity(cseq);
					mav.setViewName("redirect:/deliveryCartForm");
				}
			}else if((int)session.getAttribute("memberkind") == 2) {
				ArrayList<CartVO> guestCartList = (ArrayList<CartVO>)session.getAttribute("guestCartList");
				int index = 0;
				for(CartVO cvo : guestCartList) {
					if(cvo.getCseq() == cseq) {
						if(cvo.getQuantity() >= 99) {
							break;
						}else {
							guestCartList.remove(index);
							cvo.setQuantity(cvo.getQuantity() + 1);
							guestCartList.add(index, cvo);
							break;
						}
					}
					index++;
				}
				session.setAttribute("guestCartList", guestCartList);
				mav.setViewName("redirect:/deliveryCartForm");
			}
		}
		return mav;
	}
	
	// 재료 추가 카트 저장
	@RequestMapping(value="/insertAddMeterial")
	public ModelAndView insertAddMeterial(HttpServletRequest request,
			@RequestParam("addM") int[] m) { // m의 0번째 인덱스에는 상품의 pseq값, 1부터는 추가 재료의spseq값이 들어있다.
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind")!=null && session.getAttribute("loginUser")!=null) {
			if((int)session.getAttribute("memberkind") == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				ArrayList<subProductVO> sublist = null;
				if(m.length != 0) { // meterial값이 있다면
					if(m.length == 1) { // am 배열의 길이가 1이라면 pseq값만 온 것이므로 선택한 메뉴가 없다. 즉 그냥 pass
						mav.addObject("spseqAm", null);
						mav.setViewName("redirect:/deliveryCartForm");
					}else {
						// 넘어온 spseq 값이 있다면 해당 sub_productVO 정보를 list로 저장한다.
						sublist = new ArrayList<subProductVO>();
						for(int i = 1; i < m.length; i++)
						{
							sublist.add(ps.getSubProduct2(m[i]));
						}
						
						// 이후 해당 주문의 cart를 생성
						CartVO cvo = new CartVO();
						cvo.setId( mvo.getId() );   // 아이디 저장
						cvo.setPseq(m[0]);  // 상품번호저장
						cs.insertCart(cvo);
						
						// 방금 들어간 pseq값을 가진 카트 중 젤 최근에 들어온것을 가져온다.
						ArrayList<CartVO> Am_cvo = cs.getPseqCart(m[0]);
						CartVO cvo1 = Am_cvo.get(0);
						// 해당 카트 번호와 추가 메뉴vo, 회원의 mseq값을 가지고 추가재료 order를 생성
						for(int i = 0; i < sublist.size(); i++) {
							ps.insertSubProductOrder(cvo1.getCseq(), sublist.get(i), mvo.getMseq());
						}
						
						mav.setViewName("redirect:/deliveryCartForm");
					}
				}
			}else if((int)session.getAttribute("memberkind") == 2) {
				GuestVO gvo = (GuestVO)session.getAttribute("loginUser");
				ArrayList<subProductVO> sublist = null;
				if(m.length != 0) { // meterial값이 있다면
					if(m.length == 1) { // am 배열의 길이가 1이라면 pseq값만 온 것이므로 선택한 메뉴가 없다. 즉 그냥 pass
						mav.addObject("spseqAm", null);
						mav.setViewName("redirect:/deliveryCartForm");
					}else {
						// 넘어온 spseq 값이 있다면 해당 sub_productVO 정보를 list로 저장한다.
						sublist = new ArrayList<subProductVO>();
						for(int i = 1; i < m.length; i++)
						{
							sublist.add(ps.getSubProduct2(m[i]));
						}
						
						ProductVO pvo = ps.getProducts(m[0]);
						ArrayList<CartVO> guestCartList = (ArrayList<CartVO>) session.getAttribute("guestCartList");
						
						// 이후 해당 주문의 cart를 생성
						CartVO cvo = new CartVO();
						cvo.setCseq(cs.getCseq());
						cvo.setId(gvo.getId());   // 아이디 저장
						cvo.setPseq(m[0]);  // 상품번호저장
						cvo.setQuantity(1);
						cvo.setResult("1");
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						cvo.setDate(ts);
						cvo.setPname(pvo.getPname());
						cvo.setMname(gvo.getName());
						cvo.setImage(pvo.getImage());
						cvo.setPrice1(pvo.getPrice1());
						cvo.setKind1(pvo.getKind1());
						cvo.setKind3(pvo.getKind3());
						cvo.setPhone(gvo.getPhone());
						cvo.setMemberkind(gvo.getMemberkind());
						guestCartList.add(cvo);
						session.setAttribute("guestCartList", guestCartList);
						
						// 해당 카트 번호와 추가 메뉴vo, 비회원의 gseq값을 가지고 추가재료 order를 생성
						for(int i = 0; i < sublist.size(); i++) {
							ps.insertSubProductOrderByGseq(cvo.getCseq(), sublist.get(i), gvo.getGseq());
						}// 비회원은 주문을 하지않고 나갈경우 추가재료에 대한 주문 데이터가 남는 상황이 발생한다.
						// 이후 관리자가 주문리스트에서 검색을 할 때 indate가 1시간~2시간이 지난데이터는 삭제하고 공개하는
						// 코드를 작성할 필요가 있다.
						
						mav.setViewName("redirect:/deliveryCartForm");
					}
				}
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	*/
}
