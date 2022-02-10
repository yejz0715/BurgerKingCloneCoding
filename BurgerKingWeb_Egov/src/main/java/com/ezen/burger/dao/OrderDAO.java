package com.ezen.burger.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="OrderDAO")
public interface OrderDAO {

	void b_getOrderList(HashMap<String, Object> paramMap2);
	
	void getOrderListResult2(HashMap<String, Object> paramMap);
//	public void insertOrder(String id);
//
//	public void insertOrderDetail(CartVO list, int oseq);
//
//	public int selectOseq(String id);
//
//	public int selectOdseq(int oseq);
//
//	public void insertseq(int cseq, int oseq, int odseq);
//
//	public ArrayList<orderVO> getOrderByOseq(int oseq);
//
//	public int getOseq(String odseq);
//
//	public void deleteOrderDetail(String odseq);
//
//	public ArrayList<orderVO> getOrderDetailByOseq(int oseq);
//
//	public void deleteOrders(int oseq);
//
//	public orderVO getOrder_view(String odseq);
//	
//	public orderVO getOrder_view2(String odseq);
//
//	public void deleteSpo(String odseq);
//
//	public String getOrderDetail(String odseq);
//
//	public int[] getOseqs(String id);

	void b_getOrderListByGuest(HashMap<String, Object> paramMap2);

	void b_insertOrder(HashMap<String, Object> paramMap2);

	void b_insertOrderDetail(HashMap<String, Object> paramMap2);

	
}
