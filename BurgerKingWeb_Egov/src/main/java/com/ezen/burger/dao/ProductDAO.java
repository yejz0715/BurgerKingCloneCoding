package com.ezen.burger.dao;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="ProductDAO")
public interface ProductDAO {

	void b_getProductList(HashMap<String, Object> paramMap4);

	void b_selectSubProductOrder(HashMap<String, Object> paramMap);

	void b_selectSubProductOrder2(HashMap<String, Object> paramMap);

	void b_getDeliverydetail(HashMap<String, Object> paramMap);

	void b_getProductkind(HashMap<String, Object> paramMap2);

	void b_getProducts(HashMap<String, Object> paramMap);

	void b_getSubProduct(HashMap<String, Object> paramMap);

	void b_getSubProduct2(HashMap<String, Object> paramMap);

	void b_insertSubProductOrder(HashMap<String, Object> spvo);

	void b_insertSubProductOrderByGseq(HashMap<String, Object> spvo);

//	public ArrayList<ProductVO> getProduct(String kind1);
//
//	public ProductVO getDeliveryDetail(int pseq);
//
//	public subProductVO getSubProduct2(int spseq);
//
//	public void insertSubProductOrder(int cseq, int mseq, subProductVO subProductVO);
//
//	public void insertSubProductOrderByGseq(int cseq, int gseq, subProductVO subProductVO);
//
//	public ArrayList<subproductOrderVO> selectSubProductOrder3(int mseq);
//
//	public ArrayList<subproductOrderVO> selectSubProductOrder4(int gseq);
//
//	public ArrayList<subproductOrderVO> selectSubProductOrder5(int oseq);
//
//	public ArrayList<subproductOrderVO> selectSubProductOrder6(String odseq);
//
//	public void deleteSpo(String sposeq);
//
//	public int getResult(int odseq);

}
