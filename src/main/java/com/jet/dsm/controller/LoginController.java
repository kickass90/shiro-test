package com.jet.dsm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.jet.common.utils.MD5;
import com.jet.dsm.mysql.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jet.dsm.common.Constants;
import com.jet.dsm.mysql.entity.SysParameter;
import com.jet.dsm.mysql.repository.SysParameterRepo;
import com.jet.dsm.pojo.SysParameterPojo;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Copyright 2016 济中节能 All rights reserved.
 * 
 * @ClassName: LoginController.java
 * @Author: LiLei
 * @CreateTime: 2016年12月2日 下午2:22:36
 * @Description: 获取系统参数
 */

@Controller
public class LoginController {
	
	@Resource(name = "SysParameterRepo")
	private SysParameterRepo sysParameterRepo;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value="/login",method= RequestMethod.GET)
	public String loginForm(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		List<SysParameter> sysParameterList = sysParameterRepo.findAll();
		Map<String, SysParameterPojo> mapResult = new HashMap<String, SysParameterPojo>();
		String platFormName = null;
		String picPath = null;
		String platformCopyright = null;
		for(SysParameter i:sysParameterList){
			mapResult.put(i.getParameterKey(), new SysParameterPojo(i));
			if("PlatformName".equals(i.getParameterKey())){
				platFormName = i.getParameterValue();
			}
			if("PlatformLogo".equals(i.getParameterKey())){
				picPath = i.getParameterValue();
			}
			if("PlatformCopyright".equals(i.getParameterKey())){
				platformCopyright = i.getParameterValue();
			}
		}
		session.setAttribute(Constants.SESSION_SYS_PARAMETER, mapResult);
		model.addAttribute("platFormName", platFormName);
		model.addAttribute("picPath", picPath);
		model.addAttribute("platformCopyright", platformCopyright);
		return "login";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@Valid SysUser user, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		if(bindingResult.hasErrors()){
			return "login";
		}

		String username = user.getUserId();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserId(), MD5.computeToHexStr(user.getUserPwd()));
		//获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			//在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			//每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			//所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
		}catch(UnknownAccountException uae){
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addFlashAttribute("message", "未知账户");
		}catch(IncorrectCredentialsException ice){
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		}catch(LockedAccountException lae){
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		}catch(ExcessiveAttemptsException eae){
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
		}catch(AuthenticationException ae){
			//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}
		//验证是否登录成功
		if(currentUser.isAuthenticated()){
			logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			return "redirect:/main";
		}else{
			token.clear();
			return "redirect:/login";
		}
	}

	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(RedirectAttributes redirectAttributes ){
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/login";
	}

	@RequestMapping("/403")
	public String unauthorizedRole(){
		logger.info("------没有权限-------");
		return "403";
	}

}
