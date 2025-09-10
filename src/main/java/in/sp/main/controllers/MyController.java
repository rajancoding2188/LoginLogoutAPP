package in.sp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import in.sp.main.entities.User;
import in.sp.main.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController
   { @Autowired
	private UserService userService;
	@GetMapping("/regPage")
      public String openRegPage(Model model)
      {     model.addAttribute("user", new User());

    	  return "register";
      }
   @PostMapping("/regForm")
   public String submitRegForm(@ModelAttribute("user") User user ,Model model)
   {
	   boolean status=userService.registerUser(user);
	   if(status)
	   {
		model.addAttribute("sucessMsg","user Registration sucessfully");   
	   }
	   else
	   {
		   model.addAttribute("errorMsg","user not Registered ");  
	   }
       return "register";
   }
   @GetMapping("/LoginPage")
   public String openLoginPage(Model model) {
	  model.addAttribute("user",new User());
	   
	   return"Login";
   }
   @PostMapping("/LoginForm")
   public String submitLoginForm(@ModelAttribute("user") User user,Model model) {
	 User validUser=userService.LoginUser(user.getEmail(),user.getPassword());
	 if(validUser!=null)
	 {
		 model.addAttribute("modelName",validUser.getName());
		 return "profile";
	 }
	 else {
		 model.addAttribute("errorMsg","Email id & Password didn't Matched ");
		return "Login";
	}
   }
   @GetMapping("/Logout")
   public String logout(HttpServletRequest request)
   { 
	   HttpSession session=request.getSession(false);
	   if(session!=null)
	   {
		   session.invalidate();
	   }
	   return "redirect:/LoginPage";
   }
}