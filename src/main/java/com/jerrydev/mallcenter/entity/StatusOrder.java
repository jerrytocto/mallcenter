package com.jerrydev.mallcenter.entity;

public enum StatusOrder {
    //Pendiente: El ciente hace la orden pero no la paga
    //Enviado: El cliente paga la orden y se envia al local
    //Entregado: El local entrega la orden al cliente
    //Cancelado: El cliente paga la orden
    PENDIENTE,ENVIADO,ENTREGADO,CANCELADO
}
