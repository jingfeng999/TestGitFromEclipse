package cn.tedu.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * �����û����ݵĿ�����
 * @author admin
 *
 */
@Controller
@RequestMapping("users")
public class UserController {
	
	@RequestMapping("reg")
	public void reg(String username, String password) {
		return ;
	}
}
