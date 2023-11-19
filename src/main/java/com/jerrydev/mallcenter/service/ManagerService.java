package com.jerrydev.mallcenter.service;

import com.jerrydev.mallcenter.dto.ManagerDTO;

import java.util.List;

public interface ManagerService {

    List<ManagerDTO> listAll() ;

    ManagerDTO findById(int id);

    ManagerDTO saveManager(ManagerDTO managerDTO);

    ManagerDTO updateManager(ManagerDTO managerDTO, int id);

    void deleteManager(int id);
}
