package com.clases;

import java.util.List;

public class Clientes extends Personas{

    public Clientes (String nombre,String cedula,String direccion,String telefono,String celular) {
        this.setNombre(nombre);
        this.setCedula(cedula);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setCelular(celular);
    }

    private List<Producto> productos;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
