package com.ezen.burger.dao;

import java.util.HashMap;

import javax.validation.Valid;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="MemberDAO")
public interface MemberDAO {

	void getMember(HashMap<String, Object> paramMap);
//	public MemberVO getMember(String id);
//	public MemberVO findMember(String name, String phone);
//	public MemberVO findPwd(String name, String id);
//	public void updatePwd(String mseq, String pwd);
//	public MemberVO getMember_mseq(int mseq);
//	public void updateMember(@Valid MemberVO mvo);
//	public int selectGseq();
//	public void deleteMember(int mseq);
//	public void deleteMyaddress(int mseq);
//	public void insertMember(MemberVO membervo);
//	public MemberVO joinMember(String id, String name, String phone, String pwd);
//	public void insertGuest(GuestVO gvo);
//	public void lastDateUpdate(int mseq);
//	public void deleteOrders(String id);
//	public void deleteCart(String id);
//	public void deleteQna(String id);
//	public void deleteOrderDetail(int i);

	void b_lastDateUpdate(HashMap<String, Object> paramMap);

	void b_insertMember(HashMap<String, Object> paramMap);

	void b_getMember(HashMap<String, Object> paramMap);

	void b_selectGseq(HashMap<String, Object> paramMap);

	void b_insertGuest(HashMap<String, Object> paramMap);

	void b_getGuest(HashMap<String, Object> paramMap3);

	void b_updateMember(HashMap<String, Object> mvo);

	void b_deleteMember(HashMap<String, Object> paramMap);
}
