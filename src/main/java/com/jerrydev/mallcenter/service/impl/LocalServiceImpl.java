package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.CustomerDTO;
import com.jerrydev.mallcenter.dto.LocalDTO;
import com.jerrydev.mallcenter.entity.Customer;
import com.jerrydev.mallcenter.entity.Local;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.maper.CustomerMapper;
import com.jerrydev.mallcenter.maper.LocalMapper;
import com.jerrydev.mallcenter.repository.CustomerRepository;
import com.jerrydev.mallcenter.repository.LocalRepository;
import com.jerrydev.mallcenter.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository ;

    @Autowired
    private LocalMapper localMapper ;

    @Autowired
    private CustomerMapper customerMapper ;

    @Autowired
    private CustomerRepository customerRepository ;

    @Override
    public List<LocalDTO> showAll() {

        List<LocalDTO> localDTOS = new ArrayList<>();
        List<Local> locals = localRepository.findAll() ;
        for (Local local:locals) localDTOS.add(localMapper.fromLocal(local));

        return localDTOS ;
    }

    @Override
    public LocalDTO findById(int id) {
        Local localFound = localRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Local","id",id));

        return localMapper.fromLocal(localFound);
    }

    @Override
    public LocalDTO createLocal(LocalDTO localDTO) {
        try{
            Local local = localMapper.fromLocalDTO(localDTO);

            Local localSaved = localRepository.save(local);

            return localMapper.fromLocal(localSaved);

        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Crear Local","Error al crear local",ex);
        }

    }

    @Override
    public LocalDTO updateLocal(LocalDTO localDTO, int id) {
        return null;
    }

    @Override
    public void deleteLocal(int id) {

    }

    @Override
    public int addCustomerToLocal(CustomerDTO customerDTO, int idLocal) {
        try{
            Local local = localRepository.findById(idLocal)
                    .orElseThrow(()->new ResourceNotFoundException("Local","id",idLocal));

            Customer customer = customerMapper.fromCustomerDTO(customerDTO);
            customer.setLocalList(List.of(local));
            customerRepository.save(customer);
            return 1;
        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Insertar un customer a un local", "Error al intentar agretar un customer a un locald",ex);
        }
    }
}
