package com.ezen.burger.dao;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="EventDAO")
public interface EventDAO {

	void getAllEvents(HashMap<String, Object> paramMap);
//	ArrayList<EventVO> getAllEvents();
//
//	ArrayList<EventVO> getOngoingEvents();
//
//	ArrayList<EventVO> getPastEvents();
//
//	EventVO getDetailEvent(int eseq);
//	
////admin event
//	EventVO getEvent(int eseq);
//
//	void deleteEvent(int eseq);



	
}
