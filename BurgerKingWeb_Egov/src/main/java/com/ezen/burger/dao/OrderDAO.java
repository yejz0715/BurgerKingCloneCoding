package com.ezen.burger.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="OrderDAO")
public interface OrderDAO {

	void b_getOrderList(HashMap<String, Object> paramMap2);
	
	void b_getOrderListResult2(HashMap<String, Object> paramMap);

	void b_getOrderListByGuest(HashMap<String, Object> paramMap2);

	void b_insertOrder(HashMap<String, Object> paramMap2);

	void b_insertOrderDetail(HashMap<String, Object> paramMap2);

	void b_getOrderDetail(HashMap<String, Object> paramMap);

	void b_deleteOrder2(HashMap<String, Object> paramMap);

	void b_getOrderByOseq(HashMap<String, Object> paramMap);

	void b_getOseq(HashMap<String, Object> paramMap1);

	void b_deleteOrderDetail(HashMap<String, Object> paramMap1);

	void b_deleteSpo(HashMap<String, Object> paramMap1);

	void b_getOrderDetailByOseq(HashMap<String, Object> paramMap2);

	void b_deleteOrders(HashMap<String, Object> paramMap2);

	void b_getOrder_view(HashMap<String, Object> paramMap1);

	void b_getOrder_view2(HashMap<String, Object> paramMap1);

	
}
