package com.nspTECH.pagos_facturacion.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.nspTECH.pagos_facturacion.controller.pagoController;
import com.nspTECH.pagos_facturacion.model.pago;

@Component
public class pagoModelAssembler implements  RepresentationModelAssembler<pago, EntityModel<pago>>{

    @Override
    public EntityModel<pago> toModel(pago p) {
        return EntityModel.of(
            p,
            linkTo(methodOn(pagoController.class).BuscarPago(p.getID_PAGO())).withRel("LINKS"),
            linkTo(methodOn(pagoController.class).listarPagos()).withRel("todos-los-pagos"),
            linkTo(methodOn(pagoController.class).ActualizarPago(p.getID_PAGO(), p)).withRel("actualiza-un-pago"),
            linkTo(methodOn(pagoController.class).EliminarPago(p.getID_PAGO())).withRel("elimina-un-pago")
        );
    }

}
