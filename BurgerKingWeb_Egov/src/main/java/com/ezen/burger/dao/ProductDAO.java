package com.ezen.burger.dao;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="ProductDAO")
public interface ProductDAO {

	public ArrayList<ProductVO> getProduct(String kind1);

	public ArrayList<ProductVO> getProductdetail(int pseq);

	public ArrayList<ProductVO> getProductkind(String kind1, String kind2);

	public ArrayList<ProductVO> getProductList(String kind1);

	public ArrayList<subproductOrderVO> selectSubProductOrder(int mseq);

	public ProductVO getDeliveryDetail(int pseq);

	public ProductVO getProducts(int pseq);

	public ArrayList<subProductVO> getSubProduct();

	public ArrayList<subproductOrderVO> selectSubProductOrder2(int gseq);

	public subProductVO getSubProduct2(int spseq);

	public void insertSubProductOrder(int cseq, int mseq, subProductVO subProductVO);

	public void insertSubProductOrderByGseq(int cseq, int gseq, subProductVO subProductVO);

	public ArrayList<subproductOrderVO> selectSubProductOrder3(int mseq);

	public ArrayList<subproductOrderVO> selectSubProductOrder4(int gseq);

	public ArrayList<subproductOrderVO> selectSubProductOrder5(int oseq);

	public ArrayList<subproductOrderVO> selectSubProductOrder6(String odseq);

	public void deleteSpo(String sposeq);

	public int getResult(int odseq);

}
