package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.QnaVO;

@Mapper
public interface QnaDAO {

	void insertQna(QnaVO qnavo);

	ArrayList<QnaVO> listQna(String id);

	QnaVO getQna(int qseq);

	void deleteQna(int qseq);

	QnaVO getpassChk(int qseq);

	void updateQna(int qseq, String reply);


	
}
