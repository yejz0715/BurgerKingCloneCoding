package com.ezen.burger.dao;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="EventDAO")
public interface EventDAO {

//	EventVO getDetailEvent(int eseq);
//	
////admin event
//	EventVO getEvent(int eseq);
//
//	void deleteEvent(int eseq);
	
	void b_getAllEvents(HashMap<String, Object> paramMap);

	void b_getOngoingEvents(HashMap<String, Object> paramMap);

	void b_getPastEvents(HashMap<String, Object> paramMap);

	void b_getDetailEvent(HashMap<String, Object> paramMap);

	void b_getEvent(HashMap<String, Object> paramMap);



	
}
