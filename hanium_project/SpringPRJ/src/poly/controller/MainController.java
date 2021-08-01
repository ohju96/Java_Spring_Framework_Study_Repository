package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass());
		
		return "/index";
	}
		@RequestMapping(value="welcome")
		public String welcome() {
			log.info(this.getClass());
			
			return "/welcome";
	}
		
		@RequestMapping(value="about")
		public String about() {
			log.info(this.getClass());
			
			return "/about";
	}
		
		@RequestMapping(value="contact")
		public String contact() {
			log.info(this.getClass());
			
			return "/contact";
	}
		
		@RequestMapping(value="resume")
		public String resume() {
			log.info(this.getClass());
			
			return "/resume";
	}
		
		@RequestMapping(value="services")
		public String services() {
			log.info(this.getClass());
			
			return "/services";
	}
		
		@RequestMapping(value="testimonials")
		public String testimonials() {
			log.info(this.getClass());
			
			return "/testimonials";
	}
		
		@RequestMapping(value="works")
		public String works() {
			log.info(this.getClass());
			
			return "/works";
	}
		
			
}
