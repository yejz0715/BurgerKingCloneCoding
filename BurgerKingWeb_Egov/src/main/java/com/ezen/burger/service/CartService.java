package com.ezen.burger.service;

import java.util.HashMap;

public interface CartService {

	void selectCart(HashMap<String, Object> paramMap3);

	void insertCart(HashMap<String, Object> paramMap2);

	void getCseq(HashMap<String, Object> paramMap2);

	void getPseqCart(HashMap<String, Object> paramMap2);

}
