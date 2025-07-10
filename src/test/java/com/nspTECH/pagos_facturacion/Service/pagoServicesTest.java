package com.nspTECH.pagos_facturacion.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.nspTECH.pagos_facturacion.model.pago;
import com.nspTECH.pagos_facturacion.repository.pagoRepository;
import com.nspTECH.pagos_facturacion.services.pagoServices;


///Para que funcione es necesario comertar WEBCLIENT EN pagoSERVICES
/// ********************************************

public class pagoServicesTest {
    @Mock
    private pagoRepository pagorepository;
    
    @InjectMocks
    private pagoServices pagoservices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBt(){
    java.util.List<pago> lista = new  ArrayList<>();

    pago pago1 = new pago();
    pago pago2 = new pago();

    pago1.setID_PAGO(11L);
    pago1.setMetodo("Debito");
    pago1.setEstado_pago("pagado");
    pago1.setFecha_pago(new Date(0));
    pago1.setTipo("1600L");
    pago1.setDescuentos(0);
    pago1.setIva(110);
    pago1.setTotal(24100);
    pago1.setID_PEDIDO(103L);


    pago2.setID_PAGO(12L);
    pago2.setMetodo("Credito");
    pago2.setEstado_pago("Pendiente");
    pago2.setFecha_pago(new Date(0));
    pago2.setTipo("1600L");
    pago2.setDescuentos(0);
    pago2.setIva(110);
    pago2.setTotal(24100);
    pago2.setID_PEDIDO(102L);

    lista.add(pago1);
    lista.add(pago2);

    when(pagorepository.findAll()).thenReturn(lista);

    java.util.List<pago> resultadoBusqueda = pagoservices.BuscarTodoPago();

    assertEquals(2,resultadoBusqueda.size());
    verify(pagorepository, times(1)).findAll();

}

    @Test
    public void testBuscarUnpago(){
    pago pag = new pago();

    pag.setID_PAGO(11L);
    pag.setMetodo("Debito");
    pag.setEstado_pago("pagado");
    pag.setFecha_pago(new Date(0));
    pag.setTipo("1600L");
    pag.setDescuentos(0);
    pag.setIva(110);
    pag.setTotal(24100);
    pag.setID_PEDIDO(103L);


    when(pagorepository.findById(11L)).thenReturn(Optional.of(pag));

    pago prodBuscado = pagoservices.BuscarUnPago(11L);
    assertEquals(11L,prodBuscado.getID_PAGO());
    verify(pagorepository, times(1)).findById(11L);

    }



    @Test
    public void testGuardarProducto(){
        pago pag = new pago();

        pag.setID_PAGO(11L);
        pag.setMetodo("Debito");
        pag.setEstado_pago("pagado");
        pag.setFecha_pago(new Date(0));
        pag.setTipo("1600L");
        pag.setDescuentos(0);
        pag.setIva(110);
        pag.setTotal(24100);
        pag.setID_PEDIDO(103L);

            
        when(pagorepository.save(any())).thenReturn(pag);

        pago prodGuardados = pagoservices.GuardarPago(pag);
        assertEquals(11L, prodGuardados.getID_PAGO());
        verify(pagorepository, times(1)).save(pag);

    }

    @Test
    public void testEditarProducto(){

        pago pago0 = new pago();
        pago0.setID_PAGO(11L);
        pago0.setMetodo("12321aasd");
        pago0.setIva(112);

        pago pagoE = new pago();
        pagoE.setID_PAGO(11L);
        pagoE.setMetodo("31231231231");
        pagoE.setIva(500);

        when(pagorepository.save(any(pago.class))).thenReturn(pagoE);
        when(pagorepository.existsById(11L)).thenReturn(true);
        pago resultado = pagoservices.GuardarPago(pagoE);

        assertNotNull(resultado);
        assertEquals(11L, resultado.getID_PAGO());
        assertEquals("31231231231", resultado.getMetodo());
        assertEquals(500, resultado.getIva());

        verify(pagorepository, times(1)).save(pagoE);
    }

    @Test
    public void testEliminarPago(){
        Long id = 11L;
        doNothing().when(pagorepository).deleteById(id);

        pagoservices.EliminarPago(11L);

        verify(pagorepository, times(1)).deleteById(id);

    }


}
