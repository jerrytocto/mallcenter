package com.jerrydev.mallcenter.controller;

import com.jerrydev.mallcenter.dto.BibliotecaDTO;
import com.jerrydev.mallcenter.dto.ProductDTO;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.response.ApiResponse;
import com.jerrydev.mallcenter.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService ;

    @GetMapping("/products")
    public ResponseEntity<?> showAll(){
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .code(1)
                        .message("Listado de productos")
                        .object(productService.showAllProducts())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/product/{idProduct}")
    public ResponseEntity findById(@PathVariable int idProduct){
        try{
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(1)
                            .message("Biblioteca encontrado")
                            .object(productService.findById(idProduct))
                            .build(),
                    HttpStatus.OK);

        }catch (ResourceNotFoundException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(0)
                            .message("")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product/local/{idLocal}")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable int idLocal){
        try{
            return  new ResponseEntity<>(ApiResponse
                    .builder()
                    .code(1)
                    .message("Producto creado con éxito")
                    .object(productService.createProduct(productDTO,idLocal))
                    .build(),
                    HttpStatus.CREATED);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(0)
                            .message("")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.NOT_FOUND);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(-1)
                            .message("")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{idProduct}/local/{idLocal}")
    public ResponseEntity<?> updateProduct(
            @RequestBody ProductDTO productDTO, @PathVariable int idProduct, @PathVariable int idLocal){

        try{
            return  new ResponseEntity<>(ApiResponse
                    .builder()
                    .code(1)
                    .message("Producto actualizado con éxito")
                    .object(productService.updateProduct(productDTO,idLocal,idProduct))
                    .build(),
                    HttpStatus.CREATED);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(0)
                            .message("")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.NOT_FOUND);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(-1)
                            .message("")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/product/{idProduct}/local/{idLocal}")
    public ResponseEntity<?> deleteProduct(@PathVariable int idLocal,@PathVariable int idProduct){
        try{
            productService.deleteProduct(idProduct,idLocal);
            return  new ResponseEntity<>(ApiResponse
                    .builder()
                    .code(1)
                    .message("Producto elimando  con éxito")
                    .build(),
                    HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(0)
                            .message("")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.NOT_FOUND);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(-1)
                            .message("")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
