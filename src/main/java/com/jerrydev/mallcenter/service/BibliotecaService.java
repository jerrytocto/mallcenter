package com.jerrydev.mallcenter.service;

import com.jerrydev.mallcenter.dto.BibliotecaDTO;

import java.util.List;
import java.util.Optional;

public interface BibliotecaService {

    List<BibliotecaDTO> showAll();

    BibliotecaDTO findById(int id);

    BibliotecaDTO create (BibliotecaDTO bibliotecaDTO);

    Optional<BibliotecaDTO> update (BibliotecaDTO bibliotecaDTO, int id);

    void delete(int id);
}
