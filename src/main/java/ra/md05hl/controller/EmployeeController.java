package ra.md05hl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.md05hl.model.entity.Employee;
import ra.md05hl.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

    @GetMapping("/add")
    public String addEmployee() {
        return "employees/add";
    }
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        Employee emp = employeeService.findById(id);
        model.addAttribute("employee",emp);
        return  "employees/edit";
    }
}
