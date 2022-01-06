package in.ashokit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Specialization;
import in.ashokit.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	@Autowired
	private ISpecializationService service;

	/**
	 * 1. show Register page
	 */
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}

	/**
	 * 2.on submit form data
	 */
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization specialization, Model model) {
		Long id = service.saveSpecialization(specialization);
		String message = "Reocord ("+id+") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}

	/**
	 * 3. display all Specialization
	 */
	@GetMapping("/all")
	public String viewAll(Model model) {
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		return "SpecializationData";
	}
	/**
	 * Delete By ID
	 */
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id) {
		service.removeSpecialization(id);
		return "redirect:all";
	}
}
