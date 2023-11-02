package com.store.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProducts(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);

        return productRepository.findAll(pageable);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(Long productId){
        if (!productRepository.existsById(productId)){
            throw new IllegalStateException("Product with id: "+productId+" doesn't exist");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(
            Long productId,
            String name,
            String brand,
            Double price
    ){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(
                ()-> new IllegalStateException("not found"));
        if(name!=null && !name.isBlank()) product.setName(name);
        if(brand!=null) product.setBrand(brand);
        if(price!=null && price != 0) product.setPrice(price);

    }
}
