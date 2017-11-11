package com.skilldistillery.characters.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.characters.data.Characters;
import com.skilldistillery.characters.data.CharactersDAO;

@SessionAttributes("selectCharacter")
@Controller
public class CharacterController {
	// Autowiring is where it's tying all the beans together, which allows line 41
	// and
	// similar lines to work.
	@Autowired
	CharactersDAO dao;

	// This should return the characters that are already in the list and display
	// them.
	// it is not currently
	@ModelAttribute("selectCharacter")
	public Characters newCharacter() {
		return new Characters();
	}

	@RequestMapping(path="getCharacter.do", method=RequestMethod.POST)
	public ModelAndView addCharacter(Characters characters) {
		dao.addCharacter(characters);
		ModelAndView mv = new ModelAndView();
		mv.addObject("selectCharacter", characters);
		mv.addObject("characterList", dao.getAllCharacters());
		mv.setViewName("intro");
		return mv;
	}

	@RequestMapping(path="home.do", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("characterList", dao.getAllCharacters());
		mv.setViewName("intro");

		return mv;
	}

	@RequestMapping("delete.do")
	public ModelAndView delete(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("deleted"); // different view
		Characters change = dao.getCharacterById(id);
		mv.addObject("id", change.getId());
		dao.deleteCharacter(change);
		return mv;
	}

	@RequestMapping(path="update.do", params="id")
	public ModelAndView update(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("update"); // different view
		Characters change = dao.getCharacterById(id);
//		dao.updateCharacter(change);
		mv.addObject("character", change);
//		mv.setViewName("intro");
//		mv.addObject("id", change.getId());
		return mv;
	}

	@RequestMapping(path="updateCharacter.do", method=RequestMethod.POST)
	public ModelAndView doUpdate(Characters characters) {
		ModelAndView mv = new ModelAndView();
		
		dao.updateCharacter(characters);
//		mv.setViewName("update"); // displays a different page
		
		
		mv.setViewName("intro"); // internal resource resolver exists, so no ".jsp"
		return mv;
	}
	// @RequestMapping(path = "home.do", method = RequestMethod.GET)
	// public ModelAndView homeWithValidation(Model m) {
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("index2");
	// GiraffeIdForm f = new GiraffeIdForm();
	// mv.addObject("idForm", f);
	//
	// List<Giraffe> allGiraffes = dao.getAllGiraffes();
	// mv.addObject("list", allGiraffes);
	// return mv;
	// }

	// @RequestMapping(path = "getGiraffe.do", method = RequestMethod.POST)
	// public ModelAndView getGiraffeByIdForm(@Valid @ModelAttribute("idForm")
	// GiraffeIdForm f, Errors e) {
	// ModelAndView mv = new ModelAndView("index2");
	// if (e.hasErrors()) {
	// // goto the same or Error page
	// mv.setViewName("index2");
	// return mv;
	// }
	//
	// mv.setViewName("info"); // new view of newly added giraffe
	//
	// Giraffe best = dao.getGiraffeById(f.getId()); // from form
	// mv.addObject("giraffe", best);
	// return mv;
	// }

	@RequestMapping(path = "add.do", method = RequestMethod.GET)
	public String addGiraffe(Model model) {
		Characters c = new Characters();
		model.addAttribute("characters", c);
		return ("add");
	}

	// @RequestMapping(path = "addGiraffe.do", method = RequestMethod.POST)
	// public ModelAndView doAdd(@Valid Giraffe giraffe, Errors e) {
	// ModelAndView mv = new ModelAndView();
	// if (e.hasErrors()) {
	// mv.setViewName("add"); // displays a different page
	// return mv;
	// }
	// dao.addGiraffe(giraffe);
	//
	// mv.setViewName("added"); // internal resource resolver exists, so no ".jsp"
	// return mv;
	// }


}
