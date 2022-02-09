package com.ezen.burger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.CartDAO;
import com.ezen.burger.service.CartService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="CartService")
public class CartServiceimpl extends EgovAbstractServiceImpl implements CartService{
	@Resource(name="CartDAO")
	CartDAO cdao;
/*
	public ArrayList<CartVO> selectCart(String id) {
		return cdao.selectCart(id);
	}

	public void insertCart(CartVO cvo) {
		cdao.insertCart(cvo);
	}

	public int getCseq() {
		return cdao.getCseq();
	}

	public void deleteCart(int cseq) {
		cdao.deleteCart(cseq);
	}

	public void minusQuantity(int cseq) {
		cdao.minusQuantity(cseq);
	}

	public int getQuantity(int cseq) {
		return cdao.getQuantity(cseq);
	}

	public void plusQuantity(int cseq) {
		cdao.plusQuantity(cseq);
	}

	public ArrayList<CartVO> getPseqCart(int pseq) {
		return cdao.getPseqCart(pseq);
	}*/

	@Override
	public void selectCart(HashMap<String, Object> paramMap3) {
		cdao.b_selectCart(paramMap3);
	}
}
