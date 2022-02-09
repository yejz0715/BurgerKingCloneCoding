package com.ezen.burger.dao;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="CartDAO")
public interface CartDAO {

	void b_selectCart(HashMap<String, Object> paramMap3);
//	public ArrayList<CartVO> selectCart(String id);
//	public void insertCart(CartVO cvo);
//	public int getCseq();
//	public void deleteCart(int cseq);
//	public void minusQuantity(int cseq);
//	public int getQuantity(int cseq);
//	public void plusQuantity(int cseq);
//	public ArrayList<CartVO> getPseqCart(int pseq);

	void b_insertCart(HashMap<String, Object> paramMap2);

	void b_getCseq(HashMap<String, Object> paramMap2);
}
