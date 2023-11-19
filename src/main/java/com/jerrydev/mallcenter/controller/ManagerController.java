package com.jerrydev.mallcenter.controller;

import com.jerrydev.mallcenter.dto.ManagerDTO;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.NumberFormatException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.response.ApiResponse;
import com.jerrydev.mallcenter.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ManagerController {

    @Autowired
    private ManagerService managerService ;

    @GetMapping("managers")
    public ResponseEntity<?> showAll(){
        return new ResponseEntity<>(ApiResponse.builder()
                .code(1)
                .message("Listado de Managers")
                .object(managerService.listAll())
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("manager/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        try{
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Manager encontrado satisfactoriamente")
                    .object(managerService.findById(id))
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
    @PostMapping("manager")
    public ResponseEntity<?> createManager(@RequestBody @Valid ManagerDTO managerDTO){
        try{
            ManagerDTO managerSaved = managerService.saveManager(managerDTO);
            if(managerSaved!=null){
                return new ResponseEntity<>(ApiResponse.builder()
                        .code(1)
                        .message("Manager Actualizado Correctamente")
                        .object(managerSaved)
                        .build(),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message("El email ya existe")
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }catch (DatabaseOperationException ex){
            return  new ResponseEntity<>(ApiResponse.builder()
                    .code(-1)
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("manager/{id}")
    public ResponseEntity<?> updateManager (
            @RequestBody @Valid ManagerDTO managerDTO, @PathVariable(name = "id")  int id) {
        try{
            ManagerDTO managerDTO1 = managerService.updateManager(managerDTO,id);
            if(managerDTO1!=null){
                return new ResponseEntity<>(ApiResponse.builder()
                        .code(1)
                        .message("Manager Actualizado Correctamente")
                        .object(managerService.updateManager(managerDTO,id))
                        .build(),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .message("El email ya existe")
                    .build(),
                    HttpStatus.BAD_REQUEST);

        }catch (NumberFormatException ex) {
            // Capturamos la excepción específica y devolvemos un mensaje de error
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(-1)
                    .object("Error al procesar el ID. Asegúrese de que sea un número válido.")
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Error al actualizar un manager")
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.NOT_FOUND);

        } catch (DatabaseOperationException ex){
            return  new ResponseEntity<>(ApiResponse.builder()
                    .code(-1)
                    .object(ex.getMessage())
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("manager/{id}")
    public ResponseEntity<?> deleteManager(@PathVariable int id){
        try{
            managerService.deleteManager(id);
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Manager eliminado correctamente")
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
}
