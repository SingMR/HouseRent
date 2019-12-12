package com.house.wym.service;

import com.house.wym.entity.House;
import com.house.wym.entity.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface IHouserService {
	/**
	 * 首页信息展示
	 * @return
	 */
	public List<House> findHomeInfo();
	/**
	 * 通过id查询房屋详情
	 * @param id
	 * @return
	 */
	public House findHouseDetailsById(int id);
	/**
	 * 添加房源信息
	 * @param house
	 * @return
	 */
	public int addNewHouse(House house);

	public List<House> findHouseByUser(Page page);
	/**
	 *  删除用户发布的房源信息
	 * @param hID
	 * @return
	 */
	public int deleteUserHouse(int hID);

	public int updateHouse(House house);
	/**
	 * 条件查询
	 * @param keywords
	 * @return
	 */
	public List<House> findHouseByLike(String keywords);
	/**
	 * 降序查询
	 * @return
	 */
	public List<House> findHouseOrderByDesc();
	/**
	 * 升序序查询
	 * @return
	 */
	public List<House> findHouseOrderByAsc();
}
