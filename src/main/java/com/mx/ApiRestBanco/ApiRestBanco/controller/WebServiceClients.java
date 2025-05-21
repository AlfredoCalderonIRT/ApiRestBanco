package com.mx.ApiRestBanco.ApiRestBanco.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mx.ApiRestBanco.ApiRestBanco.model.Clientes;
import com.mx.ApiRestBanco.ApiRestBanco.service.ClientesServImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController // ? Nos permite marcar como controlador de solicitudes y se utiliza para crear
				// servicios restFull, apiRest o web services de tipo RES usando JSON.
@RequestMapping(path = "WebServiceClients") // ? Nos permite mapear nuestros recursos por medio de una URL
@CrossOrigin // ? Nos permite eludir la seguridad de nuestro navegador debido a que se
				// utiliza el protocolo http

public class WebServiceClients {
	// Inyeccion de dependencia

	@Autowired
	ClientesServImp clientesServImp;

	// Metodos GET
	//Obtener todos los registros de la base de datos
	@GetMapping("getClients")
	public List<Clientes> getClients() {
		return clientesServImp.obtenerRegistros();
	}
	
	//Buscar registro por id
	@PostMapping("idClient")
	public Clientes idClient(@RequestBody Clientes cliente) {
		//TODO: process POST request
		
		return clientesServImp.buscarRegistro(cliente.getIdCliente());
	}
	 
	
	// Metodo POST
	// Ingresar registros a la base de datos
	@PostMapping("saveClient")
	public ResponseEntity<?> postMethodName(@RequestBody Clientes cliente) {
		// TODO: process POST request
		String response = clientesServImp.guardarRegistro(cliente);
		try {
			if (response.equalsIgnoreCase("numClienteExiste")) {
				return new ResponseEntity<>("Ese número de cliente ya existe", HttpStatus.OK);
			} else if (response.equalsIgnoreCase("nomClienteExiste")) {
				return new ResponseEntity<>("Ese nombre de cliente ya existe", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(cliente, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error al guardar registro",HttpStatus.OK);
		}

	}
	
	//Metodos PUT
	//Editar registro por ID
	@PutMapping("modifyClient")
	public ResponseEntity<?> putMethodName(@RequestBody Clientes cliente) {
		//TODO: process PUT request
		//Guardamos la respuesta en una variable
		boolean response = clientesServImp.editarRegistro(cliente);
		
		if (response) {
			return new ResponseEntity<>(cliente,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("El registro a editar no existe",HttpStatus.OK);
		}
	}
	

	
	
	
	
	
	
	
	
}
