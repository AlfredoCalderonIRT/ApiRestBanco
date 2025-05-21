package com.mx.ApiRestBanco.ApiRestBanco.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.ApiRestBanco.ApiRestBanco.model.Clientes;
/* 
? Se requieren olos repositorios que vienen de spring data
? 1.----JpaRepository tiene la paginacion
? 2.----CrudRepository
*/

public interface ClientesDao extends JpaRepository <Clientes, Long> {
    


}
