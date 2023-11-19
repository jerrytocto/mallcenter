package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.ProductDTO;
import com.jerrydev.mallcenter.entity.Local;
import com.jerrydev.mallcenter.entity.Product;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.maper.ProductMapper;
import com.jerrydev.mallcenter.repository.LocalRepository;
import com.jerrydev.mallcenter.repository.ProductRepository;
import com.jerrydev.mallcenter.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper ;

    @Autowired
    private LocalRepository localRepository ;

    @Override
    public List<ProductDTO> showAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product: productList) productDTOList.add(productMapper.fromProduct(product));

        return productDTOList;
    }

    @Override
    public ProductDTO findById(int id) {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product","id",id));

        return productMapper.fromProduct(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, int idLocal) {

        try{
            Local local = localRepository.findById(idLocal)
                    .orElseThrow(()-> new ResourceNotFoundException("Local","id",idLocal));

            Product product = productMapper.fromProductDTO(productDTO);

            product.setLocal(local);
            Product productSaved = productRepository.save(product);

            return productMapper.fromProduct(productSaved);

        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Insertar","Error al insertar un producto",ex);
        }
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO, int idLocal, int idProduct) {
        try{
            Product product = productRepository.findById(idProduct).orElseThrow(
                    ()-> new ResourceNotFoundException("Product","id",idProduct));

            Local local = localRepository.findById(idLocal)
                    .orElseThrow(()-> new ResourceNotFoundException("Local","id",idLocal));

            Optional<Product> existingProduct = local.getProductList()
                    .stream()
                    .filter(p -> p.getId() == product.getId())
                    .findFirst();

            if (existingProduct.isPresent()) {
                Product foundProduct = existingProduct.get();
                foundProduct.setName(productDTO.getName());
                foundProduct.setDescription(productDTO.getDescription());
                foundProduct.setPrice(productDTO.getPrice());
                foundProduct.setStock(productDTO.getStock());
                foundProduct.setUpdatedAt(new Date());
                return productMapper.fromProduct(productRepository.save(foundProduct));
            } else {
                throw new ResourceNotFoundException("El producto " + product.getName() + " no pertenece al local", "id", idLocal);
            }
        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Actualizar producto","Error al insertar un producto",ex);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(int idProduct, int idLocal) {
        try{
            Product product = productRepository.findById(idProduct)
                    .orElseThrow(()->new ResourceNotFoundException("Producto","id",idProduct));

            Local local = localRepository.findById(idLocal)
                    .orElseThrow(()-> new ResourceNotFoundException("Local","id",idLocal));

            if (local.getProductList().remove(product)) {
                productRepository.delete(product);
            } else {
                throw new ResourceNotFoundException("El producto " + product.getName() + " no pertenece al local", "id", idLocal);
            }
        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Actualizar producto","Error al insertar un producto",ex);
        }
    }
}
