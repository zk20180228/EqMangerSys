package com.zkhv.user.action;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhv.constant.Constant;
import com.zkhv.user.entity.User;
import com.zkhv.user.service.UserService;
import com.zkhv.utils.LogUtil;
import com.zkhv.utils.PageUtil;

//用户控制其器
@RequestMapping("/user")
@Controller
public class UserAction {
	
	
	@Resource
	private UserService userService;
	
	//登录
	@RequestMapping("/login")
	public String login(User user,HttpSession session){
		
		//根据用户名密码查询用户
		if(user!=null&&StringUtils.isNotBlank(user.getUaccount())&&StringUtils.isNotBlank(user.getUpwd())){
			//根据用户名密码查询用户
			user=userService.findUserByUaccountAndPwd(user);
			if(user!=null){
				//将用户存入session
				session.setAttribute(Constant.LOGIN_USER, user);
				//记录当前登录的 用户
				LogUtil.getLog().info(new Date().toLocaleString()+"当前登录用户为："+user.getUaccount()+"=====================================================================================================================登录");
				//重定向到主页
				return "forward:/WEB-INF/index.jsp";
			}
		}
		//转发到登陆页面
		return "forward:/login.jsp";
	} 
	
	//检查该用户是否存在(异步)
	@RequestMapping("/checkLogin")
	@ResponseBody
	public String checkLogin(User user){
		
		String flag ="false";
		if(user!=null&&StringUtils.isNotBlank(user.getUaccount())&&StringUtils.isNotBlank(user.getUpwd())){
			//根据用户名密码查询用户
			user=userService.findUserByUaccountAndPwd(user);
			if(user!=null){
				//存在flag="true"
				flag="true";
			}
		}
		//不存在
		return flag;
	}
	
	//退出
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		
		//移除session中的信息
		session.removeAttribute(Constant.LOGIN_USER);
		return "redirect:/login.jsp";
	}
	
	
	//添加(页面)
	@RequestMapping("/userAddUI")
	public String userAddUI(){
		
		return "forward:/WEB-INF/userAddUI.jsp";
	}
	
	//保存添加
	@ResponseBody
	@RequestMapping(value="/userAdd",produces = "text/html;charset=UTF-8")
	public String userAdd(User user){
		String flag="false";	
			if(user!=null){
				//生成32位的UUID
				String uuidStr = UUID.randomUUID().toString().replace("-", "");
				user.setUid(uuidStr);
				userService.insert(user);
				flag="true";
			}
			return flag;
	}
	
	//校验账号是否重复
	@RequestMapping("/checkAccount")
	@ResponseBody
	public String checkAccount(User user){
		
		//默认该账号不存在
		String flag="flag";
		//添加时的校验
		if(user!=null&&user.getUid()==null){
			if(StringUtils.isNotBlank(user.getUaccount())){
				int i=userService.checkUserByAccount(user);
				if(i!=0){
					//说明存在该账号
					flag="true";
				}
			}
		}else{
			if(user!=null&&StringUtils.isNotBlank(user.getUaccount())&&StringUtils.isNotBlank(user.getUid())){
				int i=userService.checkUserByAccount(user);
				if(i!=0){
					//说明存在该账号
					flag="true";
				}
			}
		}
		
		return flag;
	}
	
	
	
	//编辑(页面)
	@RequestMapping("/userEditUI")
	public String userEditUI(String uid,HttpServletRequest request){
		if(uid!=null&&uid.length()>0){
			request.setAttribute("user", userService.selectByPrimaryKey(uid));
		}
		return "forward:/WEB-INF/userEditUI.jsp";
	}
	
	//保存编辑
	@ResponseBody
	@RequestMapping(value="/userEdit",produces ="text/html;charset=UTF-8")
	public String userEdit(User user){
		
		String flag="false";	
		if(user!=null&&StringUtils.isNotBlank(user.getUid())){
			userService.updateByPrimaryKey(user);
			flag="true";
		}
		return flag;
	}
	
	
	//删除
	@ResponseBody
	@RequestMapping(value="/deleteUser",produces ="text/html;charset=UTF-8")
	public String deleteUser(String[] uids){
		
		String msg="false";
		if(uids!=null&&uids.length>0){
			for(String id :uids){
				userService.deleteByPrimaryKey(id);
			}
			msg="true";
		}
		return msg;
	}
	
	//加载用户列表
	@ResponseBody
	@RequestMapping("/userList")
	public Object UserList(PageUtil<User> page,User user){
		page.setEntity(user);
		PageUtil<User> p = userService.selectPageByDyc(page);
		return p.getMap();
	}

}
