package com.nspTECH.pagos_facturacion.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="PAGO")
@Data
@AllArgsConstructor
@NoArgsConstructor



public class pago {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "ID_PAGO")
    private long ID_PAGO;

    @Column(name= "metodo",nullable= false , length = 20)
    private String metodo;

    @Column(name= "estado_pago",nullable= false , length = 20)
    private String estado_pago;

    @Column(name= "fecha_pago",nullable= false)
    private Date fecha_pago;

    @Column(name= "tipo",nullable= false , length = 20)
    private String tipo;

    @Column(name= "descuentos",nullable= false , length = 10)
    private String descuentos;

    @Column(name= "iva",nullable= false , length = 10)
    private int  iva;

    @Column(name= "total",nullable= false , length = 10)
    private int total;

    @Column(name = "ID_PEDIDO",nullable= false , length = 10)
    private Long ID_PEDIDO;





}
