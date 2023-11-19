package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.LocalDTO;
import com.jerrydev.mallcenter.dto.ManagerDTO;
import com.jerrydev.mallcenter.dto.ProductDTO;
import com.jerrydev.mallcenter.entity.Local;
import com.jerrydev.mallcenter.entity.Manager;
import com.jerrydev.mallcenter.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalMapper {

    @Autowired
    private ManagerMapper managerMapper ;

    @Autowired
    private ProductMapper productMapper ;

    public Local fromLocalDTO(LocalDTO localDTO){
        List<Manager> managers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<ManagerDTO> managerDTOS = localDTO.getManagers();
        List<ProductDTO> productDTOS = localDTO.getProductDTOS();

        for(ProductDTO productDTO: productDTOS) products.add(productMapper.fromProductDTO(productDTO));


        for(ManagerDTO managerDTO1: managerDTOS){
            managers.add(managerMapper.fromManagerDTO(managerDTO1));
        }

        Local local =  Local.builder()
                .name(localDTO.getName())
                .floor(localDTO.getFloor())
                .enable(localDTO.isEnable())
                .build();
        local.setManagerList(managers);
        local.setProductList(products);

        return  local;
    }

    public LocalDTO fromLocal(Local local){

        List<ManagerDTO>  managerDTOS =new ArrayList<>();
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = local.getProductList() ;

        for(Product product: products) productDTOS.add(productMapper.fromProduct(product));

        for(Manager manager1:local.getManagerList()){
            managerDTOS.add(managerMapper.fromManagerDTO(manager1));
        }

        LocalDTO localDTO = LocalDTO.builder()
                .id(local.getId())
                .name(local.getName())
                .floor(local.getFloor())
                .enable(local.isEnable())
                .managers(managerDTOS)
                .productDTOS(productDTOS)
                .build();

        return  localDTO ;

    }

}
