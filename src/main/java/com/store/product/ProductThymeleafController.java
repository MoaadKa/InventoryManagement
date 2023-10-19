package com.store.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v2/product")
public class ProductThymeleafController {

    private final ProductService productService;

    @Autowired
    public ProductThymeleafController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String homePage(){

        return "index";
    }

   @GetMapping(path = "/list")
    public String listProducts(Model model){
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product-list";
   }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "register-form";
    }

    @PostMapping("/add")
    public String submitProduct(@ModelAttribute("product") Product product){
        System.out.println(product);
        productService.addProduct(product);


        return "success-form";
    }
}
