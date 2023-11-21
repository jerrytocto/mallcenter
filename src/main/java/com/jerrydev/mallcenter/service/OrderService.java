package com.jerrydev.mallcenter.service;

import com.jerrydev.mallcenter.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    //Método para listar todos los pedidos
    List<OrderDTO> findAll();

    //Método para buscar un pedido por su id
    OrderDTO findById(int id);

    //Método para crear un pedido
    OrderDTO create(OrderDTO orderDTO, int userId);

    //Método para actualizar un pedido
    OrderDTO update(OrderDTO orderDTO, int id);

    //Método para eliminar un pedido
    void delete(int id, int userId);

    //Método para buscar un pedido por su id de usuario
    List<OrderDTO> findByUserId(int userId);

    //Método para buscar un pedido por su id de usuario y su id de pedido
    OrderDTO findByUserIdAndOrderId(int userId, int orderId);


}
