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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.ashokit.entity.Specialization;
import in.ashokit.exception.SpecializationNotFoundException;
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
		String message = "Reocord (" + id + ") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}

	/**
	 * 3. display all Specialization
	 */
	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}

	/**
	 * 4 Delete By ID
	 */
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("message", "Reocord (" + id + ") is removed");
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";

	}

	/**
	 * 5 Fetch data into edit page
	 * End user clicks on link,may enter is manually.
	 * if entered id is present in db
	 * >load raw as abject
	 * >Send to edit page
	 * >fill in form
	 * else
	 * >Redirect to all(data Page)
	 * >show Error message (not Found)
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, 
			Model model,
			RedirectAttributes attributes) 
	{
		String page=null;
		try {
		Specialization spec = service.getOneSpecialization(id);
		model.addAttribute("specialization", spec);
		page ="SpecializationEdit";
		}catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	/***
	 * 6 update Form data and redirect to all
	 */
	@PostMapping("/update")
	public String updateData(@ModelAttribute Specialization specialization, RedirectAttributes attributes) {
		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "Reocord (" + specialization.getId() + ") is updated");
		return "redirect:all";
	}

	/**
	 * 7 Read code check with service return message back to UI
	 */
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code) {
		String message = "";
		if (service.isSpecCodeExist(code)) {
			message = code + ", already exist";
		}

		return message; // this is not viewName(it is message)
	}
}
