package org.proyecto.proyecto.modelo;

import java.util.List;

public interface DatabaseOperationsProyecto {

    boolean insertarProyecto(Proyecto proyecto);

    List<Proyecto> obtenerTodosProyectos();

    boolean eliminaProyecto(String id);

    boolean actualizarProyecto(Proyecto proyecto);

    boolean actualizarProgreso(String id, float progreso, String estado);
}
