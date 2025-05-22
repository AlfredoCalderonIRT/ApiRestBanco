package com.mx.ApiRestBanco.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ApiRestBanco.dao.ClientesDao;
import com.mx.ApiRestBanco.model.Clientes;

import org.springframework.transaction.annotation.Transactional;

// ? @Service es para indicar que se realizara un servicio
@Service
public class ClientesServImp {
	// ? inyeccion de dependencias
	// ? @Autowired es para inyectar dependencias, permite tener un mejor control de
	// los objetos que se inicializaran
	@Autowired
	ClientesDao clientesDao;

	// ! Insertar registro
	@Transactional
	public String guardarRegistro(Clientes cliente) {
		// Validar que el num de cliente y el nombre compleno no se repita
		/*
		 * Desarrollo de la tabla 1-- Obtener los registros de la tabla 2-- Ciclos para
		 * recorrer la tabla 3-- Condicionales 4-- Banderas
		 */
		String respuesta = "guardado";
		boolean bandera = false;
		for (Clientes c : obtenerRegistros()) {
			if (c.getNumCliente().equals(cliente.getNumCliente())) {
				respuesta = "numClienteExiste";
				bandera = true;
				break;
			} else if (c.getNombre().equalsIgnoreCase(cliente.getNombre())
					&& c.getApp().equalsIgnoreCase(cliente.getApp()) && c.getApm().equalsIgnoreCase(cliente.getApm())) {
				respuesta = "NomClienteExiste";
				bandera = true;
				break;
			}
		}
		if (!bandera) {
			clientesDao.save(cliente);
		}
		return respuesta;

	}

	// ! Se obtienen todos los registros de la tabla
	@Transactional(readOnly = true) // ? Se usa este estereotipo para poder definir si es un metodo de solo lectura
	public List<Clientes> obtenerRegistros() {
		List<Clientes> registrosDB = clientesDao.findAll();
		return registrosDB;
	};

	// Buscar registro
	@Transactional(readOnly = true)
	public Clientes buscarRegistro(Long id) {
		Clientes clienteTem = clientesDao.findById(id).orElse(null);
		return clienteTem;
	}

	// Editar Registro
	@Transactional(readOnly = false)
	public boolean editarRegistro(Clientes cliente) {
		// Instanciamos el objeto
		Clientes clienteTem = clientesDao.findById(cliente.getIdCliente()).orElse(null);

		// Validar que existe el cliente
		if (clienteTem != null) {
			clientesDao.save(cliente);
			return true;
		} else {
			return false;
		}

	}

	// Eliminar Registro
	@Transactional(readOnly = false)
	public boolean eliminarRegistro(Long id) {
		// Validar Registro
		Clientes clienteTemp = clientesDao.findById(id).orElse(null);
		// Eliminacion de registro
		if (clienteTemp != null) {
			clientesDao.deleteById(id);
			return true;
		} else {
			return false;

		}

	}

	// Buscar por numero de cuenta
	@Transactional(readOnly = true)
	public Clientes buscarXnumClient(Integer numCliente) {

		Clientes clienteTem = clientesDao.findByNumCliente(numCliente).orElse(null);

		return clienteTem;
	}

	// Buscar por fecha de nacimiento
	@Transactional(readOnly = true)
	public List<Clientes> buscarXFechaNacm(Date fechaNacim) {
		List<Clientes> registrosDB = clientesDao.findByFechaNacim(fechaNacim);
		return registrosDB;
	}

	// Eliminar por numero de cliente
	@Transactional(readOnly = false)
	public boolean eliminarXnumCliente(Integer numCliente) {
		// Validar Registro
		Clientes clienteTemp = clientesDao.findByNumCliente(numCliente).orElse(null);
		// Eliminacion de registro
		if (clienteTemp != null) {
			clientesDao.deleteByNumCliente(numCliente);
			return true;
		} else {
			return false;

		}

	}
	
	//Buscar por correo electronico
	@Transactional(readOnly = true)
	public Clientes buscarXcorreo(String correo) {
		Clientes clienteTemp = clientesDao.buscarXcorreo(correo);
		return clienteTemp;
	}

}
