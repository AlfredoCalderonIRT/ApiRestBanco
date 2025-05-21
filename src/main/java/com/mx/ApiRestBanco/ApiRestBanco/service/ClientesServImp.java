package com.mx.ApiRestBanco.ApiRestBanco.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.ApiRestBanco.ApiRestBanco.dao.ClientesDao;
import com.mx.ApiRestBanco.ApiRestBanco.model.Clientes;
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
         * Desarrollo de la tabla
         * 1-- Obtener los registros de la tabla
         * 2-- Ciclos para recorrer la tabla
         * 3-- Condicionales
         * 4-- Banderas
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
    
    //Buscar registro
    @Transactional(readOnly = true)
    public Clientes buscarRegistro(Long id) {
    	Clientes clienteTem = clientesDao.findById(id).orElse(null);
    	return clienteTem;
    }

    //Validar id
    public boolean editarRegistro(Clientes cliente) {
    	//Instanciamos el objeto
    	Clientes clienteTem = clientesDao.findById(cliente.getIdCliente()).orElse(null);
    	
    	//Validar que existe el cliente
    	if (clienteTem!=null) {
			clientesDao.save(cliente);
			return true;
		} else {
			return false;
		}
    
    }





}
