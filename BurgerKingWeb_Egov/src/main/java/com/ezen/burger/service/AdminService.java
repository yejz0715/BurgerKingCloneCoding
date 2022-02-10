package com.ezen.burger.service;

import java.util.HashMap;

public interface AdminService {

	void b_adminCheck(HashMap<String, Object> paramMap);

	void b_getAllCountMem(HashMap<String, Object> paramMap);

	void b_listMember(HashMap<String, Object> paramMap);

	void b_deleteMember(HashMap<String, Object> paramMap1);

	void b_getAllCountEvent(HashMap<String, Object> paramMap);

	void b_listEvent(HashMap<String, Object> paramMap);

	void b_getShortProductAllCount(HashMap<String, Object> paramMap);

	void b_listShortProduct(HashMap<String, Object> paramMap);

	void b_insertEvent(HashMap<String, Object> paramMap);

	void b_updateEvent(HashMap<String, Object> paramMap);

	void b_getProductAllCount(HashMap<String, Object> paramMap);

	void b_listProduct(HashMap<String, Object> paramMap);

	void b_deleteProduct(HashMap<String, Object> paramMap);

	void b_productDetail(HashMap<String, Object> paramMap);

	void b_getAllCountQna(HashMap<String, Object> paramMap);

	void b_adminListQna(HashMap<String, Object> paramMap);

	void b_getAllCountOrderMem(HashMap<String, Object> paramMap);

	void b_getAllCountOrderNonmem(HashMap<String, Object> paramMap);

	void b_adminListOrder(HashMap<String, Object> paramMap);

	void b_adminListOrder2(HashMap<String, Object> paramMap);

}
