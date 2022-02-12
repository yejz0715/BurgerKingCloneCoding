package com.ezen.Mburger.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.service.EventService;

@Controller
public class MEventController {
	@Resource(name="EventService")
	EventService es;
	
	
	//전체 이벤트목록
	@RequestMapping(value="/MeventListForm.do")
	   public String MeventListForm(Model model) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		es.b_getAllEvents(paramMap);
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		model.addAttribute("eventList", list);
		return "mobile/mevent/MeventList";
	   }
	
	//진행중인 이벤트목록
	@RequestMapping(value="/MeventTab2.do")
	 public String MeventTab2(Model model) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		es.b_getOngoingEvents(paramMap);
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		model.addAttribute("eventList", list);
	      return "mobile/mevent/MeventTab2";
	   }
	
	//종료된 이벤트목록
	@RequestMapping(value="/MeventTab3.do")
	 public String MeventTab3(Model model) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		es.b_getPastEvents(paramMap);
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		model.addAttribute("eventList", list);
	      return "mobile/mevent/MeventTab3";
	   }
	
	//이벤트 상세보기
	@RequestMapping(value="/MeventDetailForm.do")
	 public String MeventDetailForm(Model model, @RequestParam("eseq")int eseq) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("eseq", eseq);
		paramMap.put( "ref_cursor", null );
		es.b_getDetailEvent(paramMap);
		ArrayList< HashMap<String,Object> > list 
		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		HashMap<String, Object> resultMap = list.get(0);
		model.addAttribute("EventVO" , resultMap);
	      return "mobile/mevent/MeventDetail";
	   }
	
}
