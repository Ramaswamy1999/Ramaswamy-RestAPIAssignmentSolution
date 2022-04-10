package com.greatLearning.employeeManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatLearning.employeeManagement.entity.Employee;
import com.greatLearning.employeeManagement.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// add mapping for "/list"

	@RequestMapping("/list")
	public String listEmployee(Model theModel) {

		// get Employees from db
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("Employees", theEmployees);

		return "list-Employees";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("Employee", theEmployee);

		return "Employee-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

		// get the Employee from the service
		Employee theEmployee = employeeService.findById(theId);

		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Employee", theEmployee);

		// send over to our form
		return "Employee-form";
	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		System.out.println(id);
		Employee theEmployee;
		if (id != 0) {
			theEmployee = employeeService.findById(id);
			theEmployee.setFirstName(firstName);
			theEmployee.setLastName(lastName);
			theEmployee.setEmail(email);
		} else
			theEmployee = new Employee(firstName, lastName, email);
		// save the Book
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employee/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		// delete the Book
		employeeService.deleteById(theId);

		// redirect to /Books/list
		return "redirect:/employee/list";

	}

	@RequestMapping("/search")
	public String search(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			Model theModel) {

		// check names, if both are empty then just give list of all Books

		if (firstName.trim().isEmpty() && lastName.trim().isEmpty()) {
			return "redirect:/employee/list";
		} else {
			// else, search by first name and last name
			List<Employee> theEmployees = employeeService.searchBy(firstName, lastName);

			// add to the spring model
			theModel.addAttribute("Employees", theEmployees);

			// send to list-Books
			return "list-Employees";
		}

	}

	@RequestMapping(value = "/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView mv = new ModelAndView();
		if (user != null) {
			mv.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			mv.addObject("msg", "You do not have permission to access this page!");
		}
		mv.setViewName("403");
		return mv;
	}
}
