package com.jerrydev.mallcenter.service;

import com.jerrydev.mallcenter.dto.LibroDTO;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<LibroDTO> showAll();

    Optional<LibroDTO> findById(int id);

    Optional<LibroDTO> createLibro(LibroDTO libroDTO);

    Optional<LibroDTO> updateLibro(LibroDTO libroDTO, int idLibro);

    void deleteLibro(int id);
}
