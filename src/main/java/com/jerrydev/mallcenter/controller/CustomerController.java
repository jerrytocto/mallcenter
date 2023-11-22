package com.jerrydev.mallcenter.controller;

import com.jerrydev.mallcenter.dto.CustomerDTO;
import com.jerrydev.mallcenter.dto.OrderDTO;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.response.ApiResponse;
import com.jerrydev.mallcenter.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("customers")
    public ResponseEntity<?> showAll(){

        return new ResponseEntity<>(ApiResponse.builder()
                .code(1)
                .message("Listado de Customers")
                .object(customerService.findAll())
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        try{
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Customer encontrado satisfactoriamente")
                    .object(customerService.findById(id))
                    .build(),
                    HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("customer/local/{idLocal}")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDTO customerDTO,
                                            @PathVariable  int idLocal){
        try{
            CustomerDTO customerSaved = customerService.create(customerDTO, idLocal);
            if(customerSaved!=null){
                return new ResponseEntity<>(ApiResponse.builder()
                        .code(1)
                        .message("Customer Actualizado Correctamente")
                        .object(customerSaved)
                        .build(),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message("No se pudo actualizar el Customer")
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.NOT_FOUND);
        }

        catch (DatabaseOperationException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NumberFormatException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("customer/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerDTO customerDTO,
                                            @PathVariable(name = "id")  int id) {
        try{
            CustomerDTO customerDTO1 = customerService.update(customerDTO,id);
            if(customerDTO1!=null){
                return new ResponseEntity<>(ApiResponse.builder()
                        .code(1)
                        .message("Customer Actualizado Correctamente")
                        .object(customerDTO1)
                        .build(),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message("No se pudo actualizar el Customer")
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NumberFormatException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id")  int id) {
        try{
            customerService.delete(id);
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Customer Eliminado Correctamente")
                    .build(),
                    HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        catch (DatabaseOperationException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NumberFormatException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("customer/{id}/orders")
    public ResponseEntity<?> findOrdersByCustomerId(@PathVariable(name = "id")  int id) {
        try{
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Listado de Orders")
                    .object(customerService.findOrdersByCustomerId(id))
                    .build(),
                    HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("customer/{idCustomer}/order/{idOrder}")
    public ResponseEntity<?> findOrderById(@PathVariable int idCustomer,
                                           @PathVariable int idOrder) {
        try{
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Order encontrado satisfactoriamente")
                    .object(customerService.findOrderById(idCustomer,idOrder))
                    .build(),
                    HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("customer/{idCustomer}/order/{idOrder}")
    public ResponseEntity<?> updateOrder(@RequestBody @Valid OrderDTO orderDTO,
                                         @PathVariable(name = "idCustomer")  int idCustomer,
                                         @PathVariable(name = "idOrder")  int idOrder) {
        try{
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Order Actualizado Correctamente")
                    .object(customerService.updateOrder(orderDTO,idCustomer,idOrder))
                    .build(),
                    HttpStatus.OK);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NumberFormatException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("customer/{idCustomer}/order")
    public ResponseEntity<?> insertOrder(@RequestBody @Valid OrderDTO orderDTO,
                                         @PathVariable(name = "idCustomer")  int idCustomer) {
        try{
            customerService.insertOrder(orderDTO,idCustomer);
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Order Insertado Correctamente")
                    .build(),
                    HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.NOT_FOUND);
        } catch (DatabaseOperationException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NumberFormatException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message(ex.getMessage())
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
