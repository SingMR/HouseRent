package com.house.wym.service.impl;

import java.util.List;

import com.house.wym.dao.HouseMapper;
import com.house.wym.entity.House;
import com.house.wym.entity.Page;
import com.house.wym.service.IHouserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HouserServiceImpl implements IHouserService {

	@Autowired
	private HouseMapper dao;
	
	@Override
	public List<House> findHomeInfo() {
		return dao.findHomeInfo();
	}

	@Override
	public House findHouseDetailsById(int id) {
		return dao.findHouseDetailsById(id);
	}

	@Override
	public int addNewHouse(House house) {
		return dao.addNewHouse(house);
	}

	@Override
	public List<House> findHouseByUser(Page page) {
		return dao.findHouseByUser(page);
	}
	
	@Override
	public int deleteUserHouse(int hID) {
		return dao.deleteUserHouse(hID);
	}
	@Override
	public int updateHouse(House house) {
		return dao.updateHouse(house);
	}
	@Override
	public List<House> findHouseByLike(String keywords) {
		return dao.findHouseByLike(keywords);
	}
	@Override
	public List<House> findHouseOrderByAsc() {
		return dao.findHouseOrderByAsc();
	}
	@Override
	public List<House> findHouseOrderByDesc() {
		return dao.findHouseOrderByDesc();
	}
}
