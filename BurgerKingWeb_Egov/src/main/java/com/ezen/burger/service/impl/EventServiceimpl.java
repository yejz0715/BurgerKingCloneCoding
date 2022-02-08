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
	public ArrayList<EventVO> getAllEvents() {
		return edao.getAllEvents();
	}

	public ArrayList<EventVO> getOngoingEvents() {
		return edao.getOngoingEvents();
	}

	public ArrayList<EventVO> getPastEvents() {
		return edao.getPastEvents();
	}

	public EventVO getDetailEvent(int eseq) {
		return edao.getDetailEvent(eseq);
	}
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
	
		
	}

	


