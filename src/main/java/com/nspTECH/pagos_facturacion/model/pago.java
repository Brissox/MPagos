package com.nspTECH.pagos_facturacion.model;


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

    @Column(name= "TIPO_METODO_DE_PAGO",nullable= false , length = 100)
    private String NOMBRE;

    @Column(name= "ESTADO_DEL_PAGO",nullable= false , length = 100)
    private String ESTADO_DEL_PAGO;

    @Column(name = "ID_PEDIDO",nullable= false , length = 10)
    private Long ID_PEDIDO;
}
