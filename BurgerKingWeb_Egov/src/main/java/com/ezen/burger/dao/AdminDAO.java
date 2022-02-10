package com.ezen.burger.dao;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

import com.ezen.burger.dto.Paging;


@Mapper(value="AdminDAO")
public interface AdminDAO {

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

	/* 
	 * void deleteQna(int qseq);
	 * 
	 * ArrayList<ProductVO> listShortProduct(Paging paging, String key);
	 * 
	 * ArrayList<ProductVO> listProduct(Paging paging, String key);
	 * 
	 * void deleteProduct(int pseq);
	 * 
	 * void insertProduct(ProductVO pvo);
	 * 
	 * ArrayList<ProductVO> selectProduct1(String k1);
	 * 
	 * ArrayList<ProductVO> selectProduct2(String k1, String k2);
	 * 
	 * ProductVO productDetail(int pseq);
	 * 
	 * void updateProduct(ProductVO pvo);
	 * 
	 * public ArrayList<orderVO> listOrder(Paging paging, String key);
	 * 
	 * public ArrayList<orderVO> listOrder2(Paging paging, String key);
	 * 
	 * public void updateOrderResult(int odseq, String step);
	 * 
	 * public String getResult(String odseq);
	 * 
	 * public int getShortProductAllCount(String key);
	 * 
	 * public int getProductAllCount(String key);
	 */

}
