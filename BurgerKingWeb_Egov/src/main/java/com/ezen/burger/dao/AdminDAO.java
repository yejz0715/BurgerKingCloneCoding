package com.ezen.burger.dao;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

import com.ezen.burger.dto.Paging;


@Mapper(value="AdminDAO")
public interface AdminDAO {

	AdminVO adminCheck(String id);

	int getAllCount(String tableName, String fieldName, String key);

	ArrayList<MemberVO> listMember(Paging paging, String key);

	void deleteMember(int mseq);
	
	ArrayList<EventVO> listEvent(Paging paging, String key);

	void deleteEvent(int eseq);

	ArrayList<QnaVO> listQna(Paging paging, String key);

	void deleteQna(int qseq);

	ArrayList<ProductVO> listShortProduct(Paging paging, String key);

	ArrayList<ProductVO> listProduct(Paging paging, String key);

	void deleteProduct(int pseq);

	void insertProduct(ProductVO pvo);

	void insertEvent(EventVO evo);

	void updateEvent(EventVO evo);

	ArrayList<ProductVO> selectProduct1(String k1);

	ArrayList<ProductVO> selectProduct2(String k1, String k2);

	ProductVO productDetail(int pseq);

	void updateProduct(ProductVO pvo);

	public ArrayList<orderVO> listOrder(Paging paging, String key);

	public ArrayList<orderVO> listOrder2(Paging paging, String key);

	public void updateOrderResult(int odseq, String step);

	public String getResult(String odseq);

	public int getShortProductAllCount(String key);
	
	public int getProductAllCount(String key);

}
