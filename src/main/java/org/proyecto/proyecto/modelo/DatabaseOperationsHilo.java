package org.proyecto.proyecto.modelo;

import java.util.List;

public interface DatabaseOperationsHilo {

    //Añadir id par asi poder modificar nombre
    // Métodoo para insertar un hilo
    boolean insertarHilo(Hilo hilo);

    // Métodoo para obtener todos los hilos
    List<Hilo> obtenerTodosHilos();

    //Métodoo para eliminar un hilo
    boolean eliminarHilo(String id);

    //Métodoo para actualizar el hilo
    boolean actualizarHilo(String id, String nombre, String nuevoPwd, String cantidad);
}
