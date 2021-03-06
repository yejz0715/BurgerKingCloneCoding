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

	void b_getProduct(HashMap<String, Object> paramMap);

	void b_selectSubProductOrder3(HashMap<String, Object> paramMap);

	void b_getResult(HashMap<String, Object> temp);

	void b_selectSubProductOrder4(HashMap<String, Object> paramMap);

	void b_selectSubProductOrder5(HashMap<String, Object> paramMap1);
//
//	public void deleteSpo(String sposeq);

	void b_selectSubProductOrder6(HashMap<String, Object> paramMap1);

	void b_deleteSpo(HashMap<String, Object> paramMap);

}
