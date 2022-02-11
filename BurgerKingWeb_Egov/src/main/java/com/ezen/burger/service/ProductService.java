package com.ezen.burger.service;

import java.util.HashMap;

public interface ProductService {

	void getProductList(HashMap<String, Object> paramMap4);

	void selectSubProductOrder(HashMap<String, Object> paramMap);

	void selectSubProductOrder2(HashMap<String, Object> paramMap);

	void getDeliverydetail(HashMap<String, Object> paramMap);

	void getProductkind(HashMap<String, Object> paramMap2);

	void getProducts(HashMap<String, Object> paramMap);

	void getSubProduct(HashMap<String, Object> paramMap);

	void getSubProduct2(HashMap<String, Object> sublist);

	void insertSubProductOrder(HashMap<String, Object> spvo);

	void insertSubProductOrderByGseq(HashMap<String, Object> spvo);

	void b_getProduct(HashMap<String, Object> paramMap);

	void selectSubProductOrder3(HashMap<String, Object> paramMap);

	void getResult(HashMap<String, Object> temp);

	void selectSubProductOrder4(HashMap<String, Object> paramMap);

	void selectSubProductOrder5(HashMap<String, Object> paramMap1);

	void b_selectSubProductOrder6(HashMap<String, Object> paramMap1);

	void b_deleteSpo(HashMap<String, Object> paramMap);

}
