package com.blog.safety.controlles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.safety.model.Posts;
import com.blog.safety.service.SafetyService;

@Controller
public class SafetyController {

		@Autowired
		SafetyService safetyService;
		
		@RequestMapping(value = {"/","/posts"}, method = RequestMethod.GET)
		public ModelAndView getPost() {
			ModelAndView mv = new ModelAndView("posts");
			List<Posts> posts = safetyService.fidAll();
			Collections.reverse(posts);
			mv.addObject("posts",posts);
			return mv;
		}
		
		
		@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
		public ModelAndView getPostDataile(@PathVariable("id") long id) {
				ModelAndView mv = new ModelAndView("postDetails");
				Posts post = safetyService.findById(id);
				mv.addObject("post",post);
				return mv;
		}
		
		
		//Funções do ADMIN
		
		@RequestMapping(value = "/admin", method = RequestMethod.GET)
		public ModelAndView getAdmin() {
			ModelAndView mv = new ModelAndView("admin");
			List<Posts> posts = safetyService.fidAll();
			Collections.reverse(posts);
			mv.addObject("admin",posts);
			return mv;
		}
		
		@RequestMapping(value = "/newpost", method = RequestMethod.GET)
		public String getPostFormat() {
			return "postFormat";
		}
		
		@RequestMapping(value = "/newpost", method = RequestMethod.POST)
		public String savePost(@Valid Posts post, BindingResult result, RedirectAttributes attributes) {
			if(result.hasErrors()) {
				attributes.addFlashAttribute("mensagem","verifique se os campos obrigatorios foram preenchidos");
				return "redirect:/newpost";
			}
			post.setData(LocalDate.now());
			safetyService.save(post);
			return "redirect:/admin";
		}
		
		@RequestMapping(value = "/editapost/{id}", method = RequestMethod.GET)
		public ModelAndView getPostEdita(@PathVariable("id") long id) {
			ModelAndView mv = new ModelAndView("PostEdita");
			Posts posts = safetyService.getReferenceById(id);
			mv.addObject("adminposts",posts);
			return mv;
		}
		
		@RequestMapping(value = "/editapost", method = RequestMethod.POST)
		public ModelAndView editaPost(@Valid Posts post, BindingResult result, RedirectAttributes attributes) {
			ModelAndView mv = new ModelAndView();
			if(result.hasErrors()) {
				attributes.addFlashAttribute("mensagem","verifique se os campos obrigatorios foram preenchidos");
				mv.setViewName("redirect:/editapost");
				return mv;
			}
			post.setData(LocalDate.now());
			safetyService.save(post);
			mv.setViewName("redirect:/admin");
			return mv;
		}
		
		
		@RequestMapping(value = "/deletpost/{id}", method = RequestMethod.GET)
		public String deletePost(@PathVariable("id") long id) {
			safetyService.deleteById(id);
			return "redirect:/posts";
		}

	}
