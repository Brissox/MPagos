package com.nspTECH.pagos_facturacion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class pedidoDTO {
    private long ID_PEDIDO;
    private String ANOTACIONES;
    private Long IVA;
    private Long VALOR_TOTAL;
}
