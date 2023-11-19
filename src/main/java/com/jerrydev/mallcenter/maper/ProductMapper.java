package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.ProductDTO;
import com.jerrydev.mallcenter.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product fromProductDTO(ProductDTO productDTO){
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();
    }

    public ProductDTO fromProduct(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
