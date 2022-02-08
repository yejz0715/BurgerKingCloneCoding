package com.ezen.burger.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ezen.burger.dao.AddressDAO;
import com.ezen.burger.dto.AddressVO;
import com.ezen.burger.dto.MyAddressVO;
import com.ezen.burger.service.AddressService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="AddressService")
public class AddressServiceimpl extends EgovAbstractServiceImpl implements AddressService{
	@Resource(name="AddressDAO")
	AddressDAO adao;

	public MyAddressVO getMyAddress(int mseq) {
		return adao.getMyAddress(mseq);
	}

	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		return adao.selectAddressByDong(dong);
	}

	public void setUserAddress(MyAddressVO mavo) {
		adao.setUserAddress(mavo);
	}

	public void updateUserAddress(MyAddressVO mavo) {
		adao.updateUserAddress(mavo);
	}

	public void setGuestAddress(String address, int gseq, String zip_num) {
		adao.setGuestAddress(address, gseq, zip_num);
	}
}
