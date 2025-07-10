package com.nspTECH.pagos_facturacion.model;


import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name="PAGOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Todos los pagos registrados en la empresa")


public class pago {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "ID_PAGO")
    @Schema(description = "Identificador único del pago", example = "1001")
    private long ID_PAGO;

    @Column(name= "metodo",nullable= false , length = 20)
    @Schema(description = "Método de pago utilizado", example = "Tarjeta de Crédito")
    private String metodo;

    @Column(name= "estado_pago",nullable= false , length = 20)
    @Schema(description = "Estado actual del pago", example = "Pagado")
    private String estado_pago;

    @Column(name= "fecha_pago",nullable= false)
    @Schema(description = "Fecha en que se realizó el pago", example = "2025-07-09")
    private Date fecha_pago;

    @Column(name= "tipo",nullable= false , length = 20)
    @Schema(description = "Tipo de pago", example = "")
    private String tipo;

    @Column(name= "descuentos",nullable= true , precision= 10)
    @Schema(description = "Descuentos aplicados al pago, si existen", example = "500")
    private int descuentos;

    @Column(name= "iva",nullable= false , precision = 10)
    @Schema(description = "Monto correspondiente al IVA", example = "1900")
    private int  iva;

    @Column(name= "total",nullable= false , precision = 10)
    @Schema(description = "Monto total pagado", example = "11900")
    private int total;

    @Column(name = "ID_PEDIDO",nullable= false , precision = 10)
    @Schema(description = "ID del pedido asociado al pago", example = "2001")
    private Long ID_PEDIDO;





}
