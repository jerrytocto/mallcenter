package com.jerrydev.mallcenter.controller;

import com.jerrydev.mallcenter.dto.CustomerDTO;
import com.jerrydev.mallcenter.dto.LocalDTO;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.response.ApiResponse;
import com.jerrydev.mallcenter.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LocalController {

    @Autowired
    private LocalService localService ;

    @GetMapping("/locales")
    public ResponseEntity<?> showAll(){

        return  new ResponseEntity<>(ApiResponse.builder()
                .code(1)
                .message("Listado de Locales")
                .object(localService.showAll())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/local/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        try{
            return  new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Listado de Locales")
                    .object(localService.findById(id))
                    .build(), HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(0)
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/local")
    public ResponseEntity<?> createLocal(@RequestBody LocalDTO localDTO){
        try{
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(1)
                            .message("Usuario encontrado")
                            .object(localService.createLocal(localDTO))
                            .build(),
                    HttpStatus.CREATED);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(-1)
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/local/{idLocal}")
    public ResponseEntity<?> addCustomer(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable int idLocal){

        try{
            return  new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Cliente creado con éxito")
                    .object(localService.addCustomerToLocal(customerDTO,idLocal))
                    .build(), HttpStatus.CREATED);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(0)
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.NOT_FOUND);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(-1)
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
