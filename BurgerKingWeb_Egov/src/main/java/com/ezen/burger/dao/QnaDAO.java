package com.ezen.burger.dao;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="QnaDAO")
public interface QnaDAO {

	void b_listQna(HashMap<String, Object> paramMap);

//	void insertQna(QnaVO qnavo);
//
//	ArrayList<QnaVO> listQna(String id);
//
//	QnaVO getQna(int qseq);
//
//	void deleteQna(int qseq);
//
//	QnaVO getpassChk(int qseq);
//
//	void updateQna(int qseq, String reply);
//	
}
