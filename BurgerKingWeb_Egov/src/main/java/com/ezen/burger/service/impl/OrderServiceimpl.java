package com.ezen.burger.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.CartDAO;
import com.ezen.burger.dao.OrderDAO;
import com.ezen.burger.service.OrderService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="OrderService")
public class OrderServiceimpl extends EgovAbstractServiceImpl implements OrderService{
	@Resource(name="OrderDAO")
	OrderDAO odao;
	
	@Resource(name="CartDAO")
	CartDAO cdao;
	/*

	public int getOseq(String odseq) {
		return odao.getOseq(odseq);
	}

	public void deleteOrder(String odseq, int oseq) {
		odao.deleteOrderDetail(odseq);
		// oseq 값을 기반으로 남은 odseq 즉 주문상세가 남아있는지 확인을 한다.
		ArrayList<orderVO> list = odao.getOrderDetailByOseq(oseq);
		odao.deleteSpo(odseq);
		// 해당 oseq값에 해당하는 detail이 없으면 orders 테이블의 데이터도 삭제
		if(list.size() == 0) {
			odao.deleteOrders(oseq);
		}
	}
	
	public void deleteOrder2(String odseq) {
		int oseq = odao.getOseq(odseq);
		odao.deleteOrderDetail(odseq);
		// oseq 값을 기반으로 남은 odseq 즉 주문상세가 남아있는지 확인을 한다.
		ArrayList<orderVO> list = odao.getOrderDetailByOseq(oseq);
		odao.deleteSpo(odseq);
		// 해당 oseq값에 해당하는 detail이 없으면 orders 테이블의 데이터도 삭제
		if(list.size() == 0) {
			odao.deleteOrders(oseq);
		}
	}

	public orderVO getOrder_view(String odseq) {
		return odao.getOrder_view(odseq);
	}
	
	public orderVO getOrder_view2(String odseq) {
		return odao.getOrder_view2(odseq);
	}

	public String getOrderDetail(String odseq) {
		return odao.getOrderDetail(odseq);
	}
	*/

	@Override
	public void getOrderList(HashMap<String, Object> paramMap2) {
		odao.b_getOrderList(paramMap2);
	}

	@Override
	public void b_getOrderListResult2(HashMap<String, Object> paramMap) {
		odao.getOrderListResult2(paramMap);
		
	}

	@Override
	public void getOrderListByGuest(HashMap<String, Object> paramMap2) {
		odao.b_getOrderListByGuest(paramMap2);
	}

	@Override
	public void insertOrder(HashMap<String, Object> paramMap2) {
		odao.b_insertOrder(paramMap2);
	}

	@Override
	public void insertOrderDetail(HashMap<String, Object> paramMap2) {
		odao.b_insertOrderDetail(paramMap2);
	}

	@Override
	public void getOrderDetail(HashMap<String, Object> paramMap) {
		odao.b_getOrderDetail(paramMap);
	}

	@Override
	public void deleteOrder2(HashMap<String, Object> paramMap) {
		odao.b_deleteOrder2(paramMap);
	}

	@Override
	public void getOrderByOseq(HashMap<String, Object> paramMap) {
		odao.b_getOrderByOseq(paramMap);
	}
}
