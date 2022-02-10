package com.ezen.burger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.AdminDAO;
import com.ezen.burger.dao.MemberDAO;
import com.ezen.burger.dao.OrderDAO;

import com.ezen.burger.dto.Paging;

import com.ezen.burger.service.AdminService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="AdminService")
public class AdminServiceimpl extends EgovAbstractServiceImpl implements AdminService{
	@Resource(name="AdminDAO")
	AdminDAO adao;
	
	@Resource(name="MemberDAO")
	MemberDAO mdao;
	
	@Resource(name="OrderDAO")
	OrderDAO odao;

	@Override
	public void b_adminCheck(HashMap<String, Object> paramMap) {
		adao.b_adminCheck(paramMap);
	}

	@Override
	public void b_getAllCountMem(HashMap<String, Object> paramMap) {
		adao.b_getAllCountMem(paramMap);
		
	}

	@Override
	public void b_listMember(HashMap<String, Object> paramMap) {
		adao.b_listMember(paramMap);
		
	}

	@Override
	public void b_deleteMember(HashMap<String, Object> paramMap1) {
		adao.b_deleteMember(paramMap1);
		
	}

	@Override
	public void b_getAllCountEvent(HashMap<String, Object> paramMap) {
		adao.b_getAllCountEvent(paramMap);
		
	}

	@Override
	public void b_listEvent(HashMap<String, Object> paramMap) {
		adao.b_listEvent(paramMap);
		
	}

	@Override
	public void b_getShortProductAllCount(HashMap<String, Object> paramMap) {
		adao.b_getShortProductAllCount(paramMap);
		
	}

	@Override
	public void b_listShortProduct(HashMap<String, Object> paramMap) {
		adao.b_listShortProduct(paramMap);
	}
	
	@Override
	public void b_insertEvent(HashMap<String, Object> paramMap) {
		adao.b_insertEvent(paramMap);

		
	}

	@Override
	public void b_getProductAllCount(HashMap<String, Object> paramMap) {
		adao.b_getProductAllCount(paramMap);
		
	}

	@Override
	public void b_listProduct(HashMap<String, Object> paramMap) {
		adao.b_listProduct(paramMap);
		
	}

	
	
	/*

	public ArrayList<MemberVO> listMember(Paging paging, String key) {
		return adao.listMember(paging, key);
	}

	public void deleteMember(int mseq) {
		// 해당 mseq의 회원 정보를 불러온다.
		MemberVO mvo = mdao.getMember_mseq(mseq);
		
		// 이후 해당 아이디의 oseq 값들을 조회하여
		// 해당 oseq 값을 가진 order_detail을 삭제한다.
		int[] oseq = odao.getOseqs(mvo.getId());
		for(int i = 0; i < oseq.length; i++) {
			mdao.deleteOrderDetail(oseq[i]);
		}
		// 이후 해당 아이디의 orders, cart, qna를 삭제한다.
		mdao.deleteOrders(mvo.getId());
		mdao.deleteCart(mvo.getId());
		mdao.deleteQna(mvo.getId());
		
		// 그 뒤 해당 mseq값을 가진 주소를 삭제하고 최종적으로 회원을 삭제한다.
		mdao.deleteMyaddress(mvo.getMseq());
		mdao.deleteMember(mvo.getMseq());
	}

	public ArrayList<QnaVO> listQna(Paging paging, String key) {
		return adao.listQna(paging, key);
	}

	public void deleteQna(int qseq) {
		adao.deleteQna(qseq);
		
	}

	public ArrayList<EventVO> listEvent(Paging paging, String key) {
		return adao.listEvent(paging, key);
		
	}

	public void deleteEvent(int eseq) {
		adao.deleteEvent(eseq);
		
	}

	public ArrayList<ProductVO> listShortProduct(Paging paging, String key) {
		return adao.listShortProduct(paging, key);
	}

	public ArrayList<ProductVO> listProduct(Paging paging, String key) {
		return adao.listProduct(paging, key);
	}

	public void deleteProduct(int pseq) {
		adao.deleteProduct(pseq);
		
	}

	public void insertProduct(ProductVO pvo) {
		adao.insertProduct(pvo);
		
	}

	public void insertEvent(EventVO evo) {
		adao.insertEvent(evo);
	}

	public void updateEvent(EventVO evo) {
		adao.updateEvent(evo);        
	}

	public int checkShortProductYN(String k1, String k2, String k3) {
		int result = 1;
		ArrayList<ProductVO> list1=adao.selectProduct1(k1);
		if(list1.size()==0) {
			result=2; return result;
		}
		
		ArrayList<ProductVO> list2=adao.selectProduct2(k1,k2);
		if(list2.size()==0) {
			result=3; return result;
		}
		if(k3.equals("4")) {
			result=4; return result;
		}
		return result;
	}

	public int checkShortProductYN2(String k1, String k2) {
		int result = 1;
		ArrayList<ProductVO> list2=adao.selectProduct2(k1,k2);
		if(list2.size()!=0) {
			result=2; return result;
		}
		return result;
	}

	public ProductVO productDetail(int pseq) {
		return adao.productDetail(pseq);
	}

	public void updateProduct(ProductVO pvo) {
		adao.updateProduct(pvo);
		
	}

	public ArrayList<orderVO> listOrder(Paging paging, String key, String kind) {
		ArrayList<orderVO> ovo = new ArrayList<orderVO>();
		if(kind.equals("1")) {
			ovo = adao.listOrder(paging, key);
		}else {
			ovo = adao.listOrder2(paging, key);
		}
		return ovo;
	}

	public void updateOrderResult(String odseq, String step) {
		adao.updateOrderResult(Integer.parseInt(odseq), step);
	}

	public String getResult(String odseq) {
		String result = adao.getResult(odseq);
		result = String.valueOf(Integer.parseInt(result) + 1);
		return result;
	}

	public int getShortProductAllCount(String key) {
		return adao.getShortProductAllCount(key);
	}
	
	public int getProductAllCount(String key) {
		return adao.getProductAllCount(key);
	}*/
}
