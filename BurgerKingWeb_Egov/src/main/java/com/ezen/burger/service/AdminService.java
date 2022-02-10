package com.ezen.burger.service;

import java.util.HashMap;

public interface AdminService {

	void b_adminCheck(HashMap<String, Object> paramMap);

	void b_getAllCountMem(HashMap<String, Object> paramMap);

	void b_listMember(HashMap<String, Object> paramMap);

	void b_deleteMember(HashMap<String, Object> paramMap1);

	void b_getAllCountEvent(HashMap<String, Object> paramMap);

	void b_listEvent(HashMap<String, Object> paramMap);

	void b_insertEvent(HashMap<String, Object> paramMap);

}
