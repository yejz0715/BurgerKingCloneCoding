package com.ezen.burger.service;

import java.util.HashMap;

public interface ProductService {

	void getProductList(HashMap<String, Object> paramMap4);

	void selectSubProductOrder(HashMap<String, Object> paramMap);

	void selectSubProductOrder2(HashMap<String, Object> paramMap);

	void getDeliverydetail(HashMap<String, Object> paramMap);

	void getProductkind(HashMap<String, Object> paramMap2);

	void getProducts(HashMap<String, Object> paramMap);

}
