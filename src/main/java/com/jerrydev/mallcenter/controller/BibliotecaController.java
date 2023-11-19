package com.jerrydev.mallcenter.controller;

import com.jerrydev.mallcenter.dto.BibliotecaDTO;
import com.jerrydev.mallcenter.entity.Biblioteca;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.GlobalExceptionHandler;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.response.ApiResponse;
import com.jerrydev.mallcenter.service.BibliotecaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService ;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @GetMapping("bibliotecas")
    public ResponseEntity<?> showAll(){

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .code(1)
                        .message("Listado de bibliotecas")
                        .object(bibliotecaService.showAll())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("biblioteca/{id}")
    public ResponseEntity findById(@PathVariable int id){
        try{
            BibliotecaDTO bibliotecaDTOFound = bibliotecaService.findById(id);
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(1)
                            .message("Biblioteca encontrado")
                            .object(bibliotecaDTOFound)
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

    @PostMapping("biblioteca")
    public ResponseEntity<?> createBiblioteca(@RequestBody @Valid BibliotecaDTO bibliotecaDTO){

        try{
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(1)
                            .message("Usuario encontrado")
                            .object(bibliotecaService.create(bibliotecaDTO))
                            .build(),
                    HttpStatus.CREATED);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(0)
                            .message("")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("biblioteca/{id}")
    public ResponseEntity<?> updateBiblioteca(@RequestBody @Valid BibliotecaDTO bibliotecaDTO,@PathVariable int id){

        try{
            BibliotecaDTO bibliotecaUpdated =bibliotecaService.update(bibliotecaDTO,id).get();

            return new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Biblioteca Actualizada exitosamente")
                    .object(bibliotecaUpdated)
                    .build(), HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .object(ex.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }catch (DatabaseOperationException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(-1)
                    .object(ex.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("biblioteca/{id}")
    public ResponseEntity<?> deleteBiblioteca(@PathVariable int id){
        try{
            bibliotecaService.delete(id);

            return  new ResponseEntity<>(ApiResponse.builder()
                    .code(1)
                    .message("Biblioteca eliminada correctamente")
                    .build(),
                    HttpStatus.OK);

        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ApiResponse.builder()
                    .code(0)
                    .object(ex.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }catch (DatabaseOperationException ex) {
            return new ResponseEntity(
                    ApiResponse.builder()
                            .code(0)
                            .message("Error al eliminar la biblioteca")
                            .object(ex.getMessage())
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
