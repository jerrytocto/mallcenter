package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.AccountDTO;
import com.jerrydev.mallcenter.dto.ManagerDTO;
import com.jerrydev.mallcenter.entity.Account;
import com.jerrydev.mallcenter.entity.Manager;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.NumberFormatException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.maper.ManagerMapper;
import com.jerrydev.mallcenter.repository.AccountRepository;
import com.jerrydev.mallcenter.repository.ManagerRepository;
import com.jerrydev.mallcenter.service.ManagerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository ;

    @Autowired
    private AccountRepository accountRepository ;

    @Autowired
    private ManagerMapper managerMapper ;
    @Override
    public List<ManagerDTO> listAll() {
        List<ManagerDTO> managerDTOList = new ArrayList<>();
        List<Manager> managerList = managerRepository.findAll();

        for(Manager manager : managerList) managerDTOList.add(managerMapper.fromManagerDTO(manager));

        return managerDTOList;
    }

    @Override
    public ManagerDTO findById(int id) {
        Manager manager = managerRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Manager","id",id));

        return managerMapper.fromManagerDTO(manager);
    }

    @Transactional
    @Override
    public ManagerDTO saveManager(ManagerDTO managerDTO) {

        try{
            Set<String> accountEmails = managerDTO.getAccounts().stream()
                    .map(AccountDTO::getCorreo)
                    .collect(Collectors.toSet());

            List<Account> existingAccounts = accountRepository.findByEmailIn(accountEmails);
            if(existingAccounts.isEmpty()){
                Manager manager = managerRepository.save(managerMapper.fromManagerDTO(managerDTO));
                return managerMapper.fromManagerDTO(manager);
            }
            return null;

        }catch (DatabaseOperationException ex){
            throw  new DatabaseOperationException("Agregar Manager","Error al agregar un manager",ex);
        }
    }

    @Override
    @Transactional
    public ManagerDTO updateManager(ManagerDTO managerDTO, int id) {
        try {

            Manager manager = managerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Manager", "id", id));
            manager.setFirstName(managerDTO.getFirstName());
            manager.setLastName(managerDTO.getLastName());
            manager.setEnable(managerDTO.isEnable());
            manager.setUpdatedAt(new Date());

            return managerMapper.fromManagerDTO(managerRepository.save(manager));

        }catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Manager", "id", id);
        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Actualizar Manager", "Error al actualizar un manager", ex);
        }
    }

    @Override
    public void deleteManager(int id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Manager","id",id));

        managerRepository.delete(manager);

    }
}
