package com.ezen.burger.service;

import java.util.HashMap;

public interface AdminService {

	void b_adminCheck(HashMap<String, Object> paramMap);

	void getAllCountMem(HashMap<String, Object> paramMap);

	void listMember(HashMap<String, Object> paramMap);

	void deleteMember(HashMap<String, Object> paramMap1);

	void getAllCountEvent(HashMap<String, Object> paramMap);

	void listEvent(HashMap<String, Object> paramMap);

	

}
