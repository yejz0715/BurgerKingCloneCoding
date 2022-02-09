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
	public ArrayList<ProductVO> getProduct(String kind1) {
		return pdao.getProduct(kind1);
	}

	public ArrayList<ProductVO> getProductdetail(int pseq) {
		return pdao.getProductdetail(pseq);
	}

	public ArrayList<ProductVO> getProductkind(String kind1, String kind2) {
		return pdao.getProductkind(kind1, kind2);
	}

	public ArrayList<ProductVO> getProductList(String kind1) {
		return pdao.getProductList(kind1);
	}

	public ArrayList<subproductOrderVO> selectSubProductOrder(int mseq) {
		return pdao.selectSubProductOrder(mseq);
	}

	public ProductVO getDeliverydetail(int pseq) {
		return pdao.getDeliveryDetail(pseq);
	}

	public ProductVO getProducts(int pseq) {
		return pdao.getProducts(pseq);
	}

	public ArrayList<subProductVO> getSubProduct() {
		return pdao.getSubProduct();
	}

	public ArrayList<subproductOrderVO> selectSubProductOrder2(int gseq) {
		return pdao.selectSubProductOrder2(gseq);
	}

	public subProductVO getSubProduct2(int spseq) {
		return pdao.getSubProduct2(spseq);
	}

	public void insertSubProductOrder(int cseq, subProductVO subProductVO, int mseq) {
		pdao.insertSubProductOrder(cseq, mseq, subProductVO);
	}

	public void insertSubProductOrderByGseq(int cseq, subProductVO subProductVO, int gseq) {
		pdao.insertSubProductOrderByGseq(cseq, gseq, subProductVO);
	}

	public ArrayList<subproductOrderVO> selectSubProductOrder3(int mseq) {
		return pdao.selectSubProductOrder3(mseq);
	}

	public ArrayList<subproductOrderVO> selectSubProductOrder4(int gseq) {
		return pdao.selectSubProductOrder4(gseq);
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

	public int getResult(int odseq) {
		return pdao.getResult(odseq);
	}*/

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
}
