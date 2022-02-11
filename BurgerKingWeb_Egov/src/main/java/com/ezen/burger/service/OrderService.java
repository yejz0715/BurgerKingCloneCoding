package com.ezen.burger.service;

import java.util.HashMap;

public interface OrderService {

	void getOrderList(HashMap<String, Object> paramMap2);

	void b_getOrderListResult2(HashMap<String, Object> paramMap);

	void getOrderListByGuest(HashMap<String, Object> paramMap2);

	void insertOrder(HashMap<String, Object> paramMap2);

	void insertOrderDetail(HashMap<String, Object> paramMap2);

	void getOrderDetail(HashMap<String, Object> paramMap);

	void deleteOrder2(HashMap<String, Object> paramMap);

	void getOrderByOseq(HashMap<String, Object> paramMap);

	void b_getOseq(HashMap<String, Object> paramMap1);

	void b_deleteOrderDetail(HashMap<String, Object> paramMap1);

	void b_deleteSpo(HashMap<String, Object> paramMap1);

	void b_getOrderDetailByOseq(HashMap<String, Object> paramMap2);

	void b_deleteOrders(HashMap<String, Object> paramMap2);

	void b_getOrder_view(HashMap<String, Object> paramMap1);

	void b_getOrder_view2(HashMap<String, Object> paramMap1);

}
