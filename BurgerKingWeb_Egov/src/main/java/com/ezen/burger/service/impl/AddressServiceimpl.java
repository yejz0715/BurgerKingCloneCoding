package com.ezen.burger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.AddressDAO;
import com.ezen.burger.service.AddressService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="AddressService")
public class AddressServiceimpl extends EgovAbstractServiceImpl implements AddressService{
	@Resource(name="AddressDAO")
	AddressDAO adao;

	@Override
	public void b_getMyAddress(HashMap<String, Object> paramMap) {
		adao.b_getMyAddress(paramMap);
	}

	@Override
	public void setUserAddress(HashMap<String, Object> paramMap) {
		adao.b_setUserAddress(paramMap);
	}
//	public void updateUserAddress(MyAddressVO mavo) {
//		adao.updateUserAddress(mavo);
//	}

	@Override
	public void setGuestAddress(HashMap<String, Object> gvo) {
		adao.b_setGuestAddress(gvo);
	}

	@Override
	public void selectAddressByDong(HashMap<String, Object> paramMap) {
		adao.b_selectAddressByDong(paramMap);
	}
}
