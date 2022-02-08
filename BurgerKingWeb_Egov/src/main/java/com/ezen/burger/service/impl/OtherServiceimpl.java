package com.ezen.burger.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.OtherDAO;

@Service
public class OtherServiceimpl {
	@Autowired
	OtherDAO odao;

}
