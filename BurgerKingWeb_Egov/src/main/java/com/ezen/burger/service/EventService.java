package com.ezen.burger.service;

import java.util.HashMap;

public interface EventService {

	void getAllEvents(HashMap<String, Object> paramMap);

	void getOngoingEvents(HashMap<String, Object> paramMap);

	void getPastEvents(HashMap<String, Object> paramMap);

	void getDetailEvent(HashMap<String, Object> paramMap);

}
