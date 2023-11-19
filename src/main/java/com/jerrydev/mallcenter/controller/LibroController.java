package com.jerrydev.mallcenter.controller;

import com.jerrydev.mallcenter.dto.LibroDTO;
import com.jerrydev.mallcenter.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("libros")
    public List<LibroDTO> showAll(){
        return libroService.showAll();
    }

    @GetMapping("libro/{id}")
    public Optional<LibroDTO> findById(@PathVariable int id ){
        return Optional.of(libroService.findById(id).get());
    }

    @PostMapping("libro")
    public Optional<LibroDTO> create(@RequestBody LibroDTO libroDTO){
        return libroService.createLibro(libroDTO);
    }

    @PutMapping("libro/{id}")
    public Optional<LibroDTO> update(@RequestBody LibroDTO libroDTO,@PathVariable int id){
        return libroService.updateLibro(libroDTO, id);
    }

    @DeleteMapping("libro/{id}")
    public void delete(@PathVariable int id){
         libroService.deleteLibro(id);
    }
}
