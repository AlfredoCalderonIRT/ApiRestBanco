package com.mx.ApiRestBanco.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mx.ApiRestBanco.model.Clientes;

//Es un repositorio que conecta la base de datos con el programa

public interface ClientesDao extends JpaRepository<Clientes, Long> {
	// Buscar por numero de cliente
	// Optional es un contenedor que puede o no contener un valor nulo
	Optional<Clientes> findByNumCliente(Integer numCliente);

	// Buscar por fecha de nacimiento
	List<Clientes> findByFechaNacim(Date fechaNacim);

	// Eliminar por numero de cliente
	Optional<Clientes> deleteByNumCliente(Integer numCliente);

	// Buiscar por correo electronico
	@Query(value = "SELECT * FROM CLIENTES WHERE CORREO=:CORREO", nativeQuery = true)
	public Clientes buscarXcorreo(@Param("CORREO") String correo);
}
