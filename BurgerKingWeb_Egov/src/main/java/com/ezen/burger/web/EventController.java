package com.ezen.burger.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.service.EventService;

@Controller
public class EventController {
	@Resource(name="EventService")
	EventService es;
	
	
	//전체 이벤트목록
	@RequestMapping(value="/eventListForm.do")
	   public String eventListForm(Model model) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put( "ref_cursor", null );
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		es.getAllEvents(paramMap);
		model.addAttribute("eventList", list);
		return "event/eventList";
	   }
	
	//진행중인 이벤트목록
	@RequestMapping(value="/eventTab2.do")
	 public String eventTab2(Model model) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		es.getOngoingEvents(paramMap);
		model.addAttribute("eventList", list);
	      return "event/eventTab2";
	   }
	
	//종료된 이벤트목록
	@RequestMapping(value="/eventTab3.do")
	 public String eventTab3(Model model) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		es.getPastEvents(paramMap);
		model.addAttribute("eventList", list);
	      return "event/eventTab3";
	   }
	
	//이벤트 상세보기
	@RequestMapping(value="/eventDetailForm.do")
	 public String eventDetailForm(Model model, @RequestParam("eseq")int eseq) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("eseq", eseq);
		paramMap.put( "ref_cursor", null );
		es.getDetailEvent(paramMap);
		ArrayList< HashMap<String,Object> > list 
		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		HashMap<String, Object> resultMap = list.get(0);
		model.addAttribute("EventVO" , resultMap);
	      return "event/eventDetail";
	   }
	
}
