package com.adrian.service;

import java.util.HashSet;
import java.util.Set;

public class GestorIngreso implements IValidator {
    private Set<String> placasRegistradas = new HashSet<>();

    public boolean registrarIngreso(String placa) {
        return placasRegistradas.add(placa);
    }

    @Override
    public boolean existePlaca(String placa) {
        return placasRegistradas.contains(placa);
    }
}
