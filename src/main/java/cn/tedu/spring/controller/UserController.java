package cn.tedu.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 处理用户数据的控制类
 * @author admin
 *
 */
@Controller
@RequestMapping("users")
public class UserController {
	String url=null;
	/**
	 * 用户注册
	 * @param username 用户名
	 * @param password 用户密码
	 */
	@RequestMapping("reg")
	public void reg(String username, String password) {
		return ;
	}
}
