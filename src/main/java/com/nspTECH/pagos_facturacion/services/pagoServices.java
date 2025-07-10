package com.nspTECH.pagos_facturacion.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nspTECH.pagos_facturacion.DTO.pedidoDTO;
import com.nspTECH.pagos_facturacion.model.pago;
import com.nspTECH.pagos_facturacion.repository.pagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional


public class pagoServices {

    private final WebClient webClient;

    public pagoServices(WebClient webClient){
        this.webClient = webClient;
    }



    public pedidoDTO buscarpedido(long ID_PAGO){
    pedidoDTO pedidodto = webClient.get()
                                                .uri("/{ID_PEDIDO}",ID_PAGO)
                                                    .retrieve()
                                                    .bodyToMono(pedidoDTO.class)
                                                    .block();
    return pedidodto;
    }

    @Autowired
    private pagoRepository pagorepository;


    public List<pago> BuscarTodoPago(){
        return pagorepository.findAll();
    }

    public pago BuscarUnPago(Long ID_PAGO){
        return pagorepository.findById(ID_PAGO).get();

    }

    public pago GuardarPago(pago pago){
        return pagorepository.save(pago);

    }

    public void EliminarPago(Long ID_PAGO){
        pagorepository.deleteById(ID_PAGO);
    }

}
