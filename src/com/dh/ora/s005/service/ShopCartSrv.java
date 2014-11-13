package com.dh.ora.s005.service;

import java.util.List;
import java.util.Map;

public interface ShopCartSrv {

	public List<Map<String,Object>> goodList(String userid) throws Exception;
}
