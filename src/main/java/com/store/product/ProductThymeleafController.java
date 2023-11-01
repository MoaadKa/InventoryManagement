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
    private final ProductRepository productRepository;

    @Autowired
    public ProductThymeleafController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String homePage(){

        return "index";
    }

   @GetMapping(path = "/list")
    public String listProducts(Model model, @RequestParam(required = false) String keyword){

        List<Product> products = productService.getProducts(keyword);
       int totalProducts = products.size();
       model.addAttribute("products", products);
       model.addAttribute("totalProducts", totalProducts);
        return "product-list";
   }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "product-form";
    }

    @PostMapping("/add")
    public String submitProduct(@ModelAttribute("product") Product product){
        System.out.println(product);
        productService.addProduct(product);


        return "success-form";
    }

    @GetMapping("edit/{id}")
    public String editProduct(
            @PathVariable("id") Long id,
            Model model
            ){
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "product-form";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(
            @PathVariable("id") Long id,
            Model model
    ){
        productService.deleteProduct(id);

        return "redirect:/api/v2/product/list";
    }

}
