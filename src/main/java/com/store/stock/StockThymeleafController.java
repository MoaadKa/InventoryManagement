package com.store.stock;

import com.store.product.Product;
import com.store.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/v2/stock")
public class StockThymeleafController {

    private final StockService stockService;
    private final ProductService productService;
    private final StockRepository stockRepository;

    @Autowired
    public StockThymeleafController(StockService stockService, ProductService productService, StockRepository stockRepository) {
        this.stockService = stockService;
        this.productService = productService;
        this.stockRepository = stockRepository;
    }

    @GetMapping("list")
    public String listStocks(Model model){
        List<Stock> stocks = stockService.getStocks();
        model.addAttribute("stocks", stocks);
        return "stock-list";
    }
    @GetMapping("add")
    public String createStock(Model model){
        List<Product> products = productService.getProducts();
        model.addAttribute("stock", new Stock());
        model.addAttribute("products", products);
        return "stock-form";
    }
    @PostMapping("add")
    public String saveStock(Stock stock){
        stockService.addStock(stock);
        return "redirect:/api/v2/stock/list";
    }

    @GetMapping("edit/{id}")
    public String editStock(
            @PathVariable("id") Long id,
            Model model
    ){
        Stock stock = stockRepository.findById(id).get();
        model.addAttribute("stock", stock);
        List<Product> products = productService.getProducts();
        model.addAttribute("products",products);
        return "stock-form";
    }

    @GetMapping("delete/{id}")
    public String deleteStock(@PathVariable("id") Long id){
        stockService.deleteStock(id);
        return "redirect:/api/v2/stock/list";
    }
}
