package com.adrian.service;

import java.util.HashSet;
import java.util.Set;

public class GestorIngreso {
    private Set<String> placasRegistradas = new HashSet<>();

    public boolean registrarIngreso(String placa) {
        if(placasRegistradas.add(placa)) {
            System.out.println("Ingreso registrado: " + placa);
            return true;
        } else {
            System.out.println("Error: la placa "+ placa +" ya esta dentro del parqueadero");
            return false;
        }
    }
}
