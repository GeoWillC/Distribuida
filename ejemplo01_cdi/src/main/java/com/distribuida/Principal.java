package com.distribuida;

import com.distribuida.service.StringService;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.jboss.weld.bean.builtin.InstanceBean;

public class Principal {
    public static void main(String[] args) {
        SeContainer container= SeContainerInitializer.newInstance().initialize();
        String valor=container.select(StringService.class).get().convert("holamundo");
        System.out.println(valor);
    }
}
