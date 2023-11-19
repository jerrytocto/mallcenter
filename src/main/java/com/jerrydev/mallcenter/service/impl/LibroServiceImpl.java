package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.LibroDTO;
import com.jerrydev.mallcenter.entity.Libro;
import com.jerrydev.mallcenter.maper.BibliotecaMapper;
import com.jerrydev.mallcenter.repository.LibroRepository;
import com.jerrydev.mallcenter.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private BibliotecaMapper bibliotecaMapper ;

    @Override
    public List<LibroDTO> showAll() {

        List<LibroDTO> libroDTOS = new ArrayList<>();
        List<Libro> libros = libroRepository.findAll();

        for (Libro libro:libros) libroDTOS.add(bibliotecaMapper.fromLibro(libro));

        return libroDTOS;
    }

    @Override
    public Optional<LibroDTO> findById(int id) {

        Libro libroFound = libroRepository.findById(id).orElse(null);

        return Optional.of(bibliotecaMapper.fromLibro(libroFound));
    }

    @Override
    public Optional<LibroDTO> createLibro(LibroDTO libroDTO) {

        return Optional.of(bibliotecaMapper.fromLibro(libroRepository.save(bibliotecaMapper.fromLibroDTO(libroDTO))));

    }

    @Override
    public Optional<LibroDTO> updateLibro(LibroDTO libroDTO, int idLibro) {

        Libro libroFound = libroRepository.findById(idLibro).orElse(null);
        if (libroFound != null){
            libroFound.setName(libroDTO.getName());
            return Optional.of(bibliotecaMapper.fromLibro(libroRepository.save(libroFound)));
        }
        return Optional.empty();
    }

    @Override
    public void deleteLibro(int id) {

        Libro libroFound = libroRepository.findById(id).orElse(null);

        if(libroFound != null){
            libroRepository.delete(libroFound);
        }
    }
}
