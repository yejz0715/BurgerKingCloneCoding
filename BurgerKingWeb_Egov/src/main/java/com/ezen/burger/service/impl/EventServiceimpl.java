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
	public void getAllEvents(HashMap<String, Object> paramMap) {
		edao.getAllEvents(paramMap);	
	}
	@Override
	public void getOngoingEvents(HashMap<String, Object> paramMap) {
		edao.getOngoingEvents(paramMap);
	}
	@Override
	public void getPastEvents(HashMap<String, Object> paramMap) {
		edao.getPastEvents(paramMap);	
	}
	@Override
	public void getDetailEvent(HashMap<String, Object> paramMap) {
		edao.getDetailEvent(paramMap);
		
	}
	
		
	}

	


