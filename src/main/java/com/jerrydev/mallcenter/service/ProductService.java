package com.jerrydev.mallcenter.service;

import com.jerrydev.mallcenter.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> showAllProducts();

    ProductDTO findById(int id);

    ProductDTO createProduct(ProductDTO productDTO, int idLocal);

    ProductDTO updateProduct(ProductDTO productDTO , int idLocal, int idProduct);

    void deleteProduct(int idProduct, int idLocal);
}
