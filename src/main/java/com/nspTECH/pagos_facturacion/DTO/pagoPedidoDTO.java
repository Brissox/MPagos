package com.nspTECH.pagos_facturacion.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class pagoPedidoDTO {

    private long ID_PEDIDO;
    private String ANOTACIONES;
    private Long IVA;
    private Long VALOR_TOTAL;
    private long ID_PAGO;
    private long MONTO_PAGO;

}
