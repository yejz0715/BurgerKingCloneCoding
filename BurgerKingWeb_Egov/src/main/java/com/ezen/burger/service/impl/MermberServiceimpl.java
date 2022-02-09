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

	public MemberVO findMember(String name, String phone) {
		return mdao.findMember(name, phone);
	}

	public MemberVO findPwd(String name, String id) {
		return mdao.findPwd(name, id);
	}

	public void updatePwd(String mseq, String pwd) {
		mdao.updatePwd(mseq, pwd);
	}

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
	public void deleteMember(HashMap<String, Object> paramMap) {
		mdao.b_deleteMember(paramMap);
		/*
		mdao.getMember_mseq(paramMap1);
		odao.getOseqs(paramMap1);
		ArrayList<Integer> list = (ArrayList<Integer>) paramMap1.get("OSEQ");
		for(int i = 0; i < list.size(); i++) {
			HashMap<String, Object>
			mdao.deleteOrderDetail(list.get(i));
		}
		mdao.deleteOrders(mvo.getId());
		mdao.deleteCart(mvo.getId());
		mdao.deleteQna(mvo.getId());
		mdao.deleteMyaddress(mvo.getMseq());
		mdao.deleteMember(mvo.getMseq());*/
	}
}
