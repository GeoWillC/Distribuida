package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona  {

    //C
    void crear(Persona persona);
    //R
    Persona findbyId(Integer id);
    List<Persona> allFind();
    //U
    void actualizar(Integer id);
    //D
    void eliminar(Integer id);
}
