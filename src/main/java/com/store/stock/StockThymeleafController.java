package com.store.stock;

import com.store.product.Product;
import com.store.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/v2/stock")
public class StockThymeleafController {

    private final StockService stockService;
    private final ProductService productService;

    public StockThymeleafController(StockService stockService, ProductService productService) {
        this.stockService = stockService;
        this.productService = productService;
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
}
