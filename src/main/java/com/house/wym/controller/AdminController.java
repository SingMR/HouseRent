package com.house.wym.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.house.wym.entity.*;
import com.house.wym.service.IAdminService;
import com.house.wym.service.IHouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class AdminController {

	@Autowired
	private IAdminService service;

	@Autowired
	private IHouserService dao;

	/**
	 * 跳转到登录页
	 * 
	 * @return
	 */
	@RequestMapping("/toAdminLogin")
	public String toAdminLogin() {
		return "admin";
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param userpwd
	 * @param req
	 * @return
	 */
	@RequestMapping("/adminAccess")
	@ResponseBody
	public String adminAccess(String username, String userpwd, HttpServletRequest req) {
		Admin admin = new Admin(username, userpwd);
		Admin adminAccess = service.adminAccess(admin);
		req.getSession().setAttribute("Admin", adminAccess);
		if (adminAccess != null)
			return "OK";
		return "FAIL";
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminSingnout")
	public String signout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "admin";
	}

	@RequestMapping("/toAdminHomePage")
	public String toAdminHomePage() {
		return "adminhome";
	}

	@RequestMapping("/toAllUserPage")
	public String toAllUserPage() {
		return "alluser";
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@RequestMapping("/AllUsers")
	@ResponseBody
	public UserData findAllUser() {
		List<Users> findAllUser = service.findAllUser();
		UserData u = new UserData();
		u.setCode(0);
		u.setCount(findAllUser.size());
		u.setData(findAllUser);
		u.setMsg("OK");
		return u;
	}

	@RequestMapping("/toAllHousePage")
	public String toAllHousePage() {
		return "allhouse";
	}

	/**
	 * 查询所有房源
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/findAllHouse")
	@ResponseBody
	public UserHouseData findAllHouse(int page, int limit) {
		Page p = new Page();
		p.setLimit(limit);
		p.setPage((page - 1) * limit);
		List<House> findAllHouse = service.findAllHouse(p);
		UserHouseData data = new UserHouseData();
		data.setCode(0);
		data.setCount(findAllHouse.size());
		data.setData(findAllHouse);
		data.setMsg("OK");
		return data;
	}

	/**
	 * 删除房源
	 * 
	 * @param hID
	 * @return
	 */
	@RequestMapping("/deleteHouse")
	@ResponseBody
	public String deleteHouse(int hID) {
		int deleteHouse = service.deleteHouse(hID);
		if (deleteHouse > 0) {
			return "OK";
		}
		return "FAIL";
	}

	/**
	 * 跳转到管理员更新房源界面
	 * 
	 * @param hID
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAdminUpdateHousePage")
	public String toUpdatePage(int hID, HttpServletRequest request) {
		House house = dao.findHouseDetailsById(hID);
		request.getSession().setAttribute("House", house);
		return "updatehouse";
	}

	/**
	 * 传入id,跳转到修改用户界面
	 * 
	 * @return
	 */
	@RequestMapping("/toEditUserPage")
	public String toEditUserPage(int uID, HttpServletRequest req) {
		Users findUserById = service.findUserById(uID);
		req.getSession().setAttribute("User", findUserById);
		return "editUser";
	}

	/**
	 * 更新用户信息
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping("/editUser")
	@ResponseBody
	public String editUser(Users users) {
		int n = service.updateUser(users);
		if (n > 0)
			return "OK";
		return "FAIL";
	}

	/**
	 * 管理员删除用户
	 * 
	 * @param uID
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(int uID) {
		int n = service.deleteUser(uID);
		if (n > 0) {
			return "OK";
		}
		return "FAIL";
	}

	@RequestMapping("/toUpdateAdminPwdPage")
	public String toUpdateAdminPwdPage() {
		return "updateAdminPwd";
	}
	@RequestMapping("/welcome01")
	public String toWelcomePage() {
		return "welcome01";
	}

	@RequestMapping("/updateAdminPwd")
	@ResponseBody
	public String updateAdminPwd(HttpServletRequest request,String oldpwd, String newpwd, String newpwdagain) {
		Admin admin = new Admin();
		Admin adminSession = (Admin) request.getSession().getAttribute("Admin");
		admin.setId(adminSession.getId());
		admin.setUserpwd(oldpwd);
		Admin checkAdminPwd = service.checkAdminPwd(admin);
		if (checkAdminPwd == null) {
			return "ERROR";
		}
		if (!newpwd.equals(newpwdagain)) {
			return "FAIL";
		}
		Admin a = new Admin();
		a.setId(adminSession.getId());
		a.setUserpwd(newpwd);
		int n = service.updateAdminPwd(a);
		if (n > 0)
			return "OK";
		return "FAIL";
	}
}
