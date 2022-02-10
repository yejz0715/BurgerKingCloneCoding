package com.ezen.burger.service;

import java.util.HashMap;

public interface QnaService {

	void listQna(HashMap<String, Object> paramMap);

	void b_insertQna(HashMap<String, Object> paramMap);

	void b_getpassChk(HashMap<String, Object> paramMap);

	void b_getQna(HashMap<String, Object> paramMap);

	void b_deleteQna(HashMap<String, Object> paramMap);

	

}
