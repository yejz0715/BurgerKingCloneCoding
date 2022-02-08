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
		
		ArrayList< HashMap/String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor"); 
		es.getAllEvents(paramMap);
		model.addAttribute("eventList", list);
	   
	      return "event/eventList";
	   }
	/*
	//진행중인 이벤트목록
	@RequestMapping(value="/eventTab2")
	 public ModelAndView eventTab2(Model model) {
		ModelAndView mav=new ModelAndView();
		ArrayList<EventVO> list=es.getOngoingEvents();
		mav.addObject("eventList", list);
	    mav.setViewName("event/eventTab2");
	      return mav;
	   }
	
	//종료된 이벤트목록
	@RequestMapping(value="/eventTab3")
	 public ModelAndView eventTab3(Model model) {
		ModelAndView mav=new ModelAndView();
		ArrayList<EventVO> list=es.getPastEvents();
		mav.addObject("eventList", list);
	    mav.setViewName("event/eventTab3"); 
	    //System.out.printf("mav2", mav);
	      return mav;
	   }
	
	//이벤트 상세보기
	@RequestMapping(value="/eventDetailForm")
	 public ModelAndView eventDetailForm(@RequestParam("eseq")int eseq) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("EventVO", es.getDetailEvent(eseq));
	    mav.setViewName("event/eventDetail");  
	      return mav;
	   }
	*/
}
