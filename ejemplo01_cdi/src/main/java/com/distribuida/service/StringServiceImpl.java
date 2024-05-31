package com.distribuida.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StringServiceImpl implements StringService{
    @Override
    public String convert(String valor) {
        return valor.toUpperCase();
    }
}