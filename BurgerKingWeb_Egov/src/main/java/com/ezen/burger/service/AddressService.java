package com.ezen.burger.service;

import java.util.HashMap;

public interface AddressService {

	void b_getMyAddress(HashMap<String, Object> paramMap);

	void setUserAddress(HashMap<String, Object> paramMap);

	void setGuestAddress(HashMap<String, Object> gvo);

	void selectAddressByDong(HashMap<String, Object> paramMap);

}
