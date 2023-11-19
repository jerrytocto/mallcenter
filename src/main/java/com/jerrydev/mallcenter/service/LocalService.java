package com.jerrydev.mallcenter.service;

import com.jerrydev.mallcenter.dto.CustomerDTO;
import com.jerrydev.mallcenter.dto.LocalDTO;

import java.util.List;

public interface LocalService {

    List<LocalDTO> showAll();

    LocalDTO findById(int id);

    LocalDTO createLocal(LocalDTO localDTO);

    LocalDTO updateLocal(LocalDTO localDTO, int id);

    void deleteLocal(int id) ;

    int addCustomerToLocal(CustomerDTO customerDTO, int idLocal);


}
