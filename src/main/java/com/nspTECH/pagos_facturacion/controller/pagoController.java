package com.nspTECH.pagos_facturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nspTECH.pagos_facturacion.Assembler.pagoModelAssembler;
import com.nspTECH.pagos_facturacion.DTO.pagoPedidoDTO;
import com.nspTECH.pagos_facturacion.DTO.pedidoDTO;
import com.nspTECH.pagos_facturacion.model.pago;
import com.nspTECH.pagos_facturacion.services.pagoServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/v1/Pagos")


public class pagoController {

    @Autowired

    private pagoServices pagoservice;

    @Autowired

    private pagoModelAssembler assembler;


        // ENDPOINT PARA BUSCAR TODOS LOS PAGOS
    @GetMapping
        @Operation(summary = "ENDPIONT PARA LISTAR LOS PAGOS", description = "Operacion que lista todos los pagos")
        @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los pagos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pago.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun pago", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })
    public ResponseEntity<?> listarPagos(){
        List<pago> pagos = pagoservice.BuscarTodoPago();
        if (pagos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran dato");
        } else {
            return ResponseEntity.ok(assembler.toCollectionModel(pagos));
        }
    }

    // ENDPOINT PARA BUSCAR UN PAGO
    @GetMapping("/{ID_PAGO}")
    @Operation(summary = "ENDPOINT PARA BUSCAR UN PAGO", description = "Operacion que lista un pago")
    @Parameters (value = {
        @Parameter (name="ID_PAGO", description= "ID del pago que se buscara", in = ParameterIn.PATH, required= true)

    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el pago ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pago.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun pago", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })

    public ResponseEntity<?> BuscarPago(@PathVariable Long ID_PAGO){
        try {
            pago pagoBuscado = pagoservice.BuscarUnPago(ID_PAGO);
            return ResponseEntity.ok(assembler.toModel(pagoBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran el pago");
        }
        
    }

    



@GetMapping("/PagoPedido/{ID_PAGO}")
        @Operation(summary = "DTO PAGO PEDIDO", description = "Operacion que lista un dtoPagoPedido")
        @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listo correctamente un dtoPagoPedido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pagoPedidoDTO.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun dtoPagoPedido", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    }) 


    public ResponseEntity<?> pagoCliente(@PathVariable Long ID_PAGO){
        try {
            pago pagoBuscado = pagoservice.BuscarUnPago(ID_PAGO);
            pedidoDTO pedido = pagoservice.buscarpedido(ID_PAGO);
            pagoPedidoDTO pagopedido = new pagoPedidoDTO();
            pagopedido.setVALOR_TOTAL(pedido.getVALOR_TOTAL());
            pagopedido.setANOTACIONES(pedido.getANOTACIONES());
            pagopedido.setIVA(pedido.getIVA());
            pagopedido.setID_PAGO(pagoBuscado.getID_PAGO());
            pagopedido.setID_PEDIDO(pedido.getID_PEDIDO());
            return ResponseEntity.ok(pagopedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el pago");
        }
        
    }


    // ENDPOINT PARA REGISTRAR UN PAGO
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN PAGO", description = "ENDPOINT QUE REGISTRA UN PAGO",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="PAGO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = pago.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el pago", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pago.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el pago", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el pago")))
    })

    public ResponseEntity<?> GuardarPago(@RequestBody pago pagoGuardar){
    try {
            pago pagoRegistrar = pagoservice.GuardarPago(pagoGuardar);
            return ResponseEntity.ok(assembler.toModel(pagoRegistrar));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Pago");
    }
    }
    
    


    // ENDPOINT PARA ELIMINAR UN PAGO
    @DeleteMapping("/{ID_PAGO}")
    @Operation(summary = "ENDPOINT QUE ELIMINA UN PAGO", description = "Operacion que elimina un pago")
        @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se elimino correctamente el pago", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pago.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun pago", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })
        public ResponseEntity<String> EliminarPago(@PathVariable Long ID_PAGO){
            try {
                pago pagooBuscado = pagoservice.BuscarUnPago(ID_PAGO);
                pagoservice.EliminarPago(ID_PAGO);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina pago");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("pago no esta registrado");
            }
        }




    // ENDPOINT PARA EDITAR UN PAGO
    @PutMapping("/{ID_PAGO}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS
    @Operation(summary = "ENDPOINT QUE EDITA UN PAGO", description = "ENDPOINT QUE EDITA UN PAGO", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="PAGO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = pago.class))))
    @Parameters (value = {
        @Parameter (name="ID_PAGO", description= "ID del pago que se editara", in = ParameterIn.PATH, required= true)})

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente el pago", content = @Content(mediaType = "application/json", schema = @Schema(implementation = pago.class))),
        @ApiResponse(responseCode = "500", description = "Pago no esta registrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el pago")))
    })
    public ResponseEntity<?> ActualizarPago(@PathVariable Long ID_PAGO, @RequestBody pago pagoActualizar){
        try {
            pago pagoActualizado = pagoservice.BuscarUnPago(ID_PAGO);
            pagoActualizado.setMetodo(pagoActualizar.getMetodo());
            pagoActualizado.setEstado_pago(pagoActualizar.getEstado_pago());
            pagoActualizado.setFecha_pago(pagoActualizar.getFecha_pago());
            pagoActualizado.setTipo(pagoActualizar.getTipo());
            pagoActualizado.setDescuentos(pagoActualizar.getDescuentos());
            pagoActualizado.setIva(pagoActualizar.getIva());
            pagoActualizado.setTotal(pagoActualizar.getTotal());
            pagoActualizado.setID_PEDIDO(pagoActualizar.getID_PEDIDO());
            pagoservice.GuardarPago(pagoActualizar);
            return ResponseEntity.ok(assembler.toModel(pagoActualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pago no esta registrado");
        }
    }
    





}
