package com.fdmgroup.Controllers;

import java.lang.annotation.Annotation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.method.annotation.SessionAttributesHandler;

import com.fdmgroup.DAO.UserDAO;
import com.fdmgroup.Entities.User;
import com.fdmgroup.Enum.Type;

@Controller
@RequestMapping(value = "/logout")
public class LogoutController {



		@Resource(name = "userDAOBean")
		private UserDAO uDao;
		

		@RequestMapping(method = RequestMethod.GET)
		public String Logout(HttpSession session) {
			session.invalidate();
			return "logout";
			    
			
		}


}

