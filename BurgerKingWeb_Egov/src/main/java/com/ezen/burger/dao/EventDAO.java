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
	
	void getAllEvents(HashMap<String, Object> paramMap);

	void getOngoingEvents(HashMap<String, Object> paramMap);

	void getPastEvents(HashMap<String, Object> paramMap);

	void getDetailEvent(HashMap<String, Object> paramMap);



	
}
