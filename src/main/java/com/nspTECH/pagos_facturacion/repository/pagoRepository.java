package com.nspTECH.pagos_facturacion.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nspTECH.pagos_facturacion.model.pago;


public interface pagoRepository extends JpaRepository<pago, Long> {

}
