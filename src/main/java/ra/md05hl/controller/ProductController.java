package ra.md05hl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.md05hl.model.entity.Product;
import ra.md05hl.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/product";
    }

    @GetMapping("/add")
    public String addProduct() {
        return "products/add";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/product";
    }
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/product";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        Product pro = productService.findById(id);
        model.addAttribute("product",pro);
        return  "products/edit";
    }
}
