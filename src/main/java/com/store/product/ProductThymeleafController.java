package com.store.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String listProducts(Model model){

        String keyword = null;
        return productPage(model,1,"id", "asc",keyword);
   }
   @GetMapping("list/page/{pageNumber}")
   public String productPage(
           Model model,
           @PathVariable("pageNumber") int currentPage,
           @RequestParam(name = "sortField",required = false) String sortField,
           @RequestParam(name = "sortDir", required = false) String sortDir,
           @RequestParam(name = "keyword", required = false) String keyword
   ){
       Page<Product> page = productService.getProducts(keyword,currentPage,sortField,sortDir);
       List<Product> products = page.getContent();
       long totalItems = page.getTotalElements();
       int totalPages = page.getTotalPages();
       model.addAttribute("products", products);
       model.addAttribute("totalItems", totalItems);
       model.addAttribute("totalPages", totalPages);
       model.addAttribute("currentPage", currentPage);
       model.addAttribute("sortField",sortField);
       model.addAttribute("sortDir", sortDir);
       String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
       model.addAttribute("reverseSortDir", reverseSortDir);
       model.addAttribute("keyword", keyword);
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
