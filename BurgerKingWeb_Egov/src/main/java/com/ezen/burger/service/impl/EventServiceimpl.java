package com.ezen.burger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.EventDAO;
import com.ezen.burger.service.EventService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="EventService")
public class EventServiceimpl extends EgovAbstractServiceImpl implements EventService{
	@Resource(name="EventDAO")
	EventDAO edao;
	
/*
//admin event
	public EventVO getEvent(int eseq) {
		return edao.getEvent(eseq);
	}

	public void deleteEvent(int eseq) {
		edao.deleteEvent(eseq);	
	}
*/

	@Override
	public void b_getAllEvents(HashMap<String, Object> paramMap) {
		edao.b_getAllEvents(paramMap);	
	}
	@Override
	public void b_getOngoingEvents(HashMap<String, Object> paramMap) {
		edao.b_getOngoingEvents(paramMap);
	}
	@Override
	public void b_getPastEvents(HashMap<String, Object> paramMap) {
		edao.b_getPastEvents(paramMap);	
	}
	@Override
	public void b_getDetailEvent(HashMap<String, Object> paramMap) {
		edao.b_getDetailEvent(paramMap);
		
	}
	@Override
	public void b_getEvent(HashMap<String, Object> paramMap) {
		edao.b_getEvent(paramMap);
		
	}
	
		
	}

	


