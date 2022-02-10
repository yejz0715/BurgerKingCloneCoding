package com.ezen.burger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.MemberDAO;
import com.ezen.burger.dao.OrderDAO;
import com.ezen.burger.service.MemberService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="MemberService")
public class MermberServiceimpl extends EgovAbstractServiceImpl implements MemberService{
	@Resource(name="MemberDAO")
	MemberDAO mdao;
	
	@Resource(name="OrderDAO")
	OrderDAO odao;
/*
	public MemberVO getMember_mseq(int mseq) {
		return mdao.getMember_mseq(mseq);
	}
*/

	@Override
	public void getMember(HashMap<String, Object> paramMap) {
		mdao.getMember(paramMap);
	}

	@Override
	public void lastDateUpdate(HashMap<String, Object> paramMap) {
		mdao.b_lastDateUpdate(paramMap);
	}

	@Override
	public void b_insertMember(HashMap<String, Object> paramMap) {
		mdao.b_insertMember(paramMap);
	}

	@Override
	public void b_getMember(HashMap<String, Object> paramMap) {
		mdao.b_getMember(paramMap);
	}

	@Override
	public void b_selectGseq(HashMap<String, Object> paramMap) {
		mdao.b_selectGseq(paramMap);
	}

	@Override
	public void b_insertGuest(HashMap<String, Object> paramMap) {
		mdao.b_insertGuest(paramMap);
	}

	@Override
	public void b_getGuest(HashMap<String, Object> paramMap3) {
		mdao.b_getGuest(paramMap3);
	}

	@Override
	public void updateMember(HashMap<String, Object> mvo) {
		mdao.b_updateMember(mvo);
	}

	@Override
	public void b_findMember(HashMap<String, Object> paramMap) {
		mdao.b_findMember(paramMap);
	}
	
	@Override	
	public void deleteMember(HashMap<String, Object> paramMap) {
		mdao.b_deleteMember(paramMap);
	}

	@Override
	public void b_findPwd(HashMap<String, Object> paramMap) {
		mdao.b_findPwd(paramMap);
		
	}

	@Override
	public void b_updatePwd(HashMap<String, Object> paramMap) {
		mdao.b_updatePwd(paramMap);
		
	}

	@Override
	public void b_getMember2(HashMap<String, Object> paramMap) {
		mdao.b_getMember2(paramMap);
		
	}

	@Override
	public void b_adminUpdateMember(HashMap<String, Object> mvo) {
		mdao.b_adminUpdateMember(mvo);
		
	}

}
