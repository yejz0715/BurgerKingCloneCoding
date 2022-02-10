package com.ezen.burger.service;

import java.util.HashMap;

public interface EventService {

	void b_getAllEvents(HashMap<String, Object> paramMap);

	void b_getOngoingEvents(HashMap<String, Object> paramMap);

	void b_getPastEvents(HashMap<String, Object> paramMap);

	void b_getDetailEvent(HashMap<String, Object> paramMap);

	void b_getEvent(HashMap<String, Object> paramMap);


}
