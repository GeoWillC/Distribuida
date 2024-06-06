package com.distribuida;

import com.google.gson.Gson;
import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.*;

public class Main {

    static SeContainer container;

    static List<Persona> listarPersonas(Request rq, Response rs){
        rs.type("application/json");
        var service = container.select(ServicioPersona.class).get();
        return service.allFind();
    }

    static Object eliminar(Request rq, Response rs){
        rs.type("application/json");
        String _id = rq.params(":id");
        var service = container.select(ServicioPersona.class).get();
        try {
            service.eliminar(Integer.parseInt(_id));
            rs.status(204); // 204 No Content
        } catch (Exception e) {
            halt(404, "Persona no encontrada");
        }
        return "";
    }

    static Persona buscarPersona(Request rq, Response rs){
        rs.type("application/json");
        String _id = rq.params(":id");
        var service = container.select(ServicioPersona.class).get();
        var person = service.findbyId(Integer.valueOf(_id));

        if (person == null){
            halt(404, "Persona no encontrada");
        }
        return person;
    }

    public static void main(String[] args) {
        //INICIANDO CONTENEDOR
        container = SeContainerInitializer
                .newInstance()
                .initialize();

        ServicioPersona servicio = container.select(ServicioPersona.class).get();

        servicio.allFind().stream().map(Persona::getNombre).forEach(System.out::println);

        //puerto
        port(8080);
//        get("/hello", (req, res) -> "Hello World");


        //gson: for formato json
        Gson gson = new Gson();

        //METHOD GET
        get("/personas", Main::listarPersonas, gson::toJson);
        get("/personas/:id", Main::buscarPersona, gson::toJson);
        delete("/personas/:id",Main::eliminar, gson::toJson);
    }
}