package com.ezen.burger.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.ProductDAO;
import com.ezen.burger.service.ProductService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="ProductService")
public class ProductServiceimpl extends EgovAbstractServiceImpl implements ProductService{
	@Resource(name="ProductDAO")
	ProductDAO pdao;
/*
	public ProductVO getDeliverydetail(int pseq) {
		return pdao.getDeliveryDetail(pseq);
	}

	public ArrayList<subproductOrderVO> selectSubProductOrder5(int oseq) {
		return pdao.selectSubProductOrder5(oseq);
	}

	public ArrayList<subproductOrderVO> selectSubProductOrder6(String odseq) {
		return pdao.selectSubProductOrder6(odseq);
	}

	public void deleteSpo(String sposeq) {
		pdao.deleteSpo(sposeq);
	}
*/

	@Override
	public void getProductList(HashMap<String, Object> paramMap4) {
		pdao.b_getProductList(paramMap4);
	}

	@Override
	public void selectSubProductOrder(HashMap<String, Object> paramMap) {
		pdao.b_selectSubProductOrder(paramMap);
	}
	
	@Override
	public void selectSubProductOrder2(HashMap<String, Object> paramMap) {
		pdao.b_selectSubProductOrder2(paramMap);
	}

	@Override
	public void getDeliverydetail(HashMap<String, Object> paramMap) {
		pdao.b_getDeliverydetail(paramMap);
	}

	@Override
	public void getProductkind(HashMap<String, Object> paramMap2) {
		pdao.b_getProductkind(paramMap2);
	}

	@Override
	public void getProducts(HashMap<String, Object> paramMap) {
		pdao.b_getProducts(paramMap);
	}

	@Override
	public void getSubProduct(HashMap<String, Object> paramMap) {
		pdao.b_getSubProduct(paramMap);
	}

	@Override
	public void getSubProduct2(HashMap<String, Object> paramMap) {
		pdao.b_getSubProduct2(paramMap);
	}

	@Override
	public void insertSubProductOrder(HashMap<String, Object> spvo) {
		pdao.b_insertSubProductOrder(spvo);
	}

	@Override
	public void insertSubProductOrderByGseq(HashMap<String, Object> spvo) {
		pdao.b_insertSubProductOrderByGseq(spvo);
	}

	@Override
	public void b_getProduct(HashMap<String, Object> paramMap) {
		pdao.b_getProduct(paramMap);
	}

	public void selectSubProductOrder3(HashMap<String, Object> paramMap) {
		pdao.b_selectSubProductOrder3(paramMap);
	}

	@Override
	public void getResult(HashMap<String, Object> temp) {
		pdao.b_getResult(temp);
	}

	@Override
	public void selectSubProductOrder4(HashMap<String, Object> paramMap) {
		pdao.b_selectSubProductOrder4(paramMap);
	}
}
