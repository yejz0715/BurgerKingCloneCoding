package com.ezen.burger.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.QnaDAO;
import com.ezen.burger.dto.QnaVO;
import com.ezen.burger.service.QnaService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="QnaService")
public class QnaServiceimpl extends EgovAbstractServiceImpl implements QnaService{
	@Resource(name="QnaDAO")
	QnaDAO qdao;

	public ArrayList<QnaVO>  listQna(String id) {
		return qdao.listQna(id);
	}
	
	public void insertQna( QnaVO qnavo) {
		qdao.insertQna(qnavo);
	}

	public QnaVO  getQna(int qseq) {
		return qdao.getQna(qseq);
	}

	public void deleteQna(int qseq) {
		qdao.deleteQna(qseq);
	}

	public QnaVO getpassChk(int qseq) {
		return qdao.getpassChk(qseq);
	}

	public void updateQna(int qseq, String reply) {
		qdao.updateQna(qseq, reply);		
	}





}
