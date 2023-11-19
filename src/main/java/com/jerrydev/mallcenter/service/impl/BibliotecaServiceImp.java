package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.BibliotecaDTO;
import com.jerrydev.mallcenter.entity.Biblioteca;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.maper.BibliotecaMapper;
import com.jerrydev.mallcenter.repository.BibliotecaRepository;
import com.jerrydev.mallcenter.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaServiceImp implements BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private BibliotecaMapper bibliotecaMapper ;

    @Override
    public List<BibliotecaDTO> showAll() {
        List<BibliotecaDTO> bibliotecaDTOList = new ArrayList<>( );
        List<Biblioteca> bibliotecas =bibliotecaRepository.findAll();

        for(Biblioteca biblioteca:bibliotecas) bibliotecaDTOList.add(bibliotecaMapper.fromBiblioteca(biblioteca));

        return bibliotecaDTOList;
    }

    @Override
    public BibliotecaDTO findById(int id) {
        Biblioteca biblioteca = bibliotecaRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Biblioteca","id",id));
        return bibliotecaMapper.fromBiblioteca(biblioteca);
    }

    @Override
    public BibliotecaDTO create(BibliotecaDTO bibliotecaDTO) {
        try{
            Biblioteca biblioteca = bibliotecaMapper.fromBibliotecaDTO(bibliotecaDTO);
            return bibliotecaMapper.fromBiblioteca(bibliotecaRepository.save(biblioteca));

        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Insertar Biblioteca","Error al insertar una nueva biblioteca", ex);
        }

    }

    @Override
    public Optional<BibliotecaDTO> update(BibliotecaDTO bibliotecaDTO, int id) {
        try{
            Biblioteca biblioteca  = bibliotecaRepository.findById(id)
                            .orElseThrow(()->new ResourceNotFoundException("Biblioteca","id",id));
            biblioteca.setName(bibliotecaDTO.getName());
            return Optional.of(bibliotecaMapper.fromBiblioteca(bibliotecaRepository.save(biblioteca)));

        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Acutalización de Biblioteca","Error al actualizar la biblioteca",ex);
        }
    }

    @Override
    public void delete(int id) {
        Biblioteca biblioteca  = bibliotecaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Biblioteca", "id", id) );

        bibliotecaRepository.deleteById(id);
    }
}
