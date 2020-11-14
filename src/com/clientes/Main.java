package com.clientes;

import com.clases.Clientes;
import com.clases.Producto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.clases.Constantes.*;

public class Main {

    public static void main(String[] args) {

        String opMenu;
        String nombre;
        String cedula;
        String direccion;
        String telefono;
        String celular;
        int pos;
        String buscar;
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        ArrayList<Producto> listaProductos = new ArrayList<>();

        do {
            opMenu = JOptionPane.showInputDialog(null,MENU,TITULO_MENU,JOptionPane.INFORMATION_MESSAGE);
            switch (opMenu) {
                case "1":
                    nombre = JOptionPane.showInputDialog(NOMBRE);
                    cedula = JOptionPane.showInputDialog(CEDULA);
                    direccion = JOptionPane.showInputDialog(DIRECCION);
                    telefono = JOptionPane.showInputDialog(TELEFONO);
                    celular = JOptionPane.showInputDialog(CELULAR);
                    Clientes clientes = new Clientes(nombre, cedula, direccion, telefono, celular);
                    List opcPdcto = mostrarProductos(listaProductos);
                    if (!opcPdcto.isEmpty()) {
                        clientes.setProductos(opcPdcto);
                    } else {
                        JOptionPane.showMessageDialog(null, VALIDACION_PDCTOS,TITULO_PDCTO,
                                JOptionPane.WARNING_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(null, VALIDACION_CLIENTE,
                            TITULO_CLIENTE, JOptionPane.INFORMATION_MESSAGE);
                    listaClientes.add(clientes);

                    break;
                case "2":
                    buscar = JOptionPane.showInputDialog(null, CEDULA_CLIENTE,
                            TITULO_BUSCAR, JOptionPane.QUESTION_MESSAGE);
                    pos = searchClient(listaClientes, buscar);
                    if (pos != -1) {
                        listaClientes.get(pos).setNombre(JOptionPane.showInputDialog(NOMBRE_ACTUAL+
                                listaClientes.get(pos).getNombre() + "\n"+NOMBRE));
                        listaClientes.get(pos).setCedula(JOptionPane.showInputDialog(CEDULA_ACTUAL +
                                listaClientes.get(pos).getCedula() + "\n"+CEDULA));
                        listaClientes.get(pos).setDireccion(JOptionPane.showInputDialog(DIR_ACTUAL +
                                listaClientes.get(pos).getDireccion() + "\n"+DIRECCION));
                        listaClientes.get(pos).setTelefono(JOptionPane.showInputDialog(TEL_ACTUAL +
                                listaClientes.get(pos).getTelefono() + "\n"+TELEFONO));
                        listaClientes.get(pos).setCelular(JOptionPane.showInputDialog(CEL_ACTUAL +
                                listaClientes.get(pos).getCelular() + "\n"+CELULAR));

                        JOptionPane.showMessageDialog(null, EDICION_OK,
                                TITULO_EDICION, JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case "3":
                    buscar = JOptionPane.showInputDialog(null, CLIENTE_ELIMINAR,
                            TITULO_BUSCAR, JOptionPane.QUESTION_MESSAGE);
                    pos = searchClient(listaClientes, buscar);
                    if (pos != -1) {
                        int resp = JOptionPane.showConfirmDialog(null,
                                CONFIRMACION, TITULO_ELIMINAR, JOptionPane.WARNING_MESSAGE);
                        if (JOptionPane.OK_OPTION == resp) {
                            listaClientes.remove(pos);
                            JOptionPane.showMessageDialog(null, ELIMINADO_OK,
                                    TITULO_ELIMINADO, JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, CANCELAR,
                                    CANCELADO, JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break;
                case "4":
                    String nombreProducto = JOptionPane.showInputDialog(NOMBRE);
                    String idProducto = JOptionPane.showInputDialog(ID_PDCTO);
                    String condicion = JOptionPane.showInputDialog(CONDICION);
                    String caracteristica = JOptionPane.showInputDialog(CARACTERISTICA);
                    Producto producto = new Producto(idProducto, nombreProducto, caracteristica, condicion);
                    listaProductos.add(producto);
                    JOptionPane.showMessageDialog(null, PDCTO_AGREGADO,
                            TITULO_AGREGAR, JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "5":
                    String opcBusqueda;
                    String buscarAvanzado;
                    do {
                        opcBusqueda = JOptionPane.showInputDialog(METODO_BUSCAR);
                        if (!isNumeric(opcBusqueda) || Integer.valueOf(opcBusqueda) != 1 && Integer.valueOf(opcBusqueda) != 2) {
                            JOptionPane.showMessageDialog(null, OPCION_INVALIDA,TITULO_INVALIDA, JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!isNumeric(opcBusqueda) || Integer.valueOf(opcBusqueda) != 1 && Integer.valueOf(opcBusqueda) != 2);
                    buscar = JOptionPane.showInputDialog(null, BUSCAR_CLIENTE,
                            TITULO_BUSCAR, JOptionPane.QUESTION_MESSAGE);
                    if (Integer.valueOf(opcBusqueda) == 1) {
                        searchClient(listaClientes, buscar);
                    } else {
                        buscarAvanzado = JOptionPane.showInputDialog(null,
                                BUSCAR_NOMBRE, TITULO_BUSCAR, JOptionPane.QUESTION_MESSAGE);
                        searchClient(listaClientes, buscar, buscarAvanzado);
                    }
                    break;
                case "0":
                    JOptionPane.showMessageDialog(null, CERRAR_SESION, TITULO_CERRAR,
                            JOptionPane.CLOSED_OPTION);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, OPCION_INVALIDA,
                            TITULO_INVALIDA, JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } while (!opMenu.equals("0") || !isNumeric(opMenu));
    }

    public static boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int searchClient(List<Clientes> list, String cedula) {
        int x = 0;
        boolean encontrado = false;
        Clientes listaAux;
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, SIN_CLIENTES,
                    TITULO_SIN_CLIENTES, JOptionPane.WARNING_MESSAGE);
            x=-1;
        } else {
            for (x = 0; x < list.size(); x++) {
                if (list.get(x).getCedula().equals(cedula)) {
                    encontrado = true;
                    listaAux = list.get(x);
                    JOptionPane.showMessageDialog(null, CLIENTE_ENCONTRADO +
                                   NOMBRE_ENCONTRADO + listaAux.getNombre() +
                                    CEDULA_ENCONTRADA + listaAux.getCedula() +
                                    DIRECCION_ENCONTRADA + listaAux.getDireccion() +
                                    TELEFONO_ENCONTRADO + listaAux.getTelefono() +
                                    CELULAR_ENCONTRADO + listaAux.getCelular() +
                                    PRODUCTO + listaAux.getProductos(),
                            BUSQUEDA, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, NO_EXISTE,
                        TITULO_INEXISTENCIA, JOptionPane.WARNING_MESSAGE);
                x = -1;
            }
        }
        return x;
    }

    public static int searchClient(List<Clientes> list, String cedula, String nombre) {
        int x = 0;
        boolean encontrado = false;
        Clientes listaAux;
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, SIN_CLIENTES,
                    TITULO_SIN_CLIENTES, JOptionPane.WARNING_MESSAGE);
        } else {
            for (x = 0; x < list.size(); x++) {
                if (list.get(x).getCedula().equals(cedula) && list.get(x).getNombre().equalsIgnoreCase(nombre)) {
                    encontrado = true;
                    listaAux = list.get(x);
                    JOptionPane.showMessageDialog(null, CLIENTE_ENCONTRADO +
                                    NOMBRE_ENCONTRADO + listaAux.getNombre() +
                                    CEDULA_ENCONTRADA + listaAux.getCedula() +
                                    DIRECCION_ENCONTRADA + listaAux.getDireccion() +
                                    TELEFONO_ENCONTRADO + listaAux.getTelefono() +
                                    CELULAR_ENCONTRADO + listaAux.getCelular() +
                                    PRODUCTO + listaAux.getProductos()
                            , BUSQUEDA, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, NO_EXISTE,
                        TITULO_INEXISTENCIA, JOptionPane.WARNING_MESSAGE);
                x = -1;
            }
        }
        return x;
    }

    public static List mostrarProductos(List<Producto> listProducto) {
        String cadena = "";
        String pdctoAElegir;
        List<String> productoList = new ArrayList<>();
        int y;

        if (listProducto.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    SIN_PDCTOS, TITULO_PDCTOS, JOptionPane.WARNING_MESSAGE);
        } else {
            do {
                for (y = 0; y < listProducto.size(); y++) {
                    cadena += y + ". " + listProducto.get(y).getNombre() + "\n";
                }
                do {
                    pdctoAElegir = JOptionPane.showInputDialog(LISTA_PDCTO +
                            "\n" + cadena + "\n-2. Para salir");

                    if (!isNumeric(pdctoAElegir) || Integer.valueOf(pdctoAElegir) >= listProducto.size()) {
                        JOptionPane.showMessageDialog(null, OPCION_INVALIDA,
                                TITULO_INVALIDA, JOptionPane.WARNING_MESSAGE);
                    }
                } while (!isNumeric(pdctoAElegir) || Integer.valueOf(pdctoAElegir) >= listProducto.size());

                if (Integer.valueOf(pdctoAElegir) != -2) {
                    productoList.add(listProducto.get(Integer.valueOf(pdctoAElegir)).getNombre());
                    JOptionPane.showMessageDialog(null, PDCTO_AGREGADO,
                            TITULO_AGREGAR, JOptionPane.INFORMATION_MESSAGE);
                }
                cadena = "";
            } while (Integer.valueOf(pdctoAElegir) != -2);
        }
        return productoList;
    }
}
