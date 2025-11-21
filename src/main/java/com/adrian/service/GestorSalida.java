package com.adrian.service;

import java.time.Duration;
import java.time.LocalDateTime;

import com.adrian.model.Vehiculo;

public class GestorSalida {
    
    private IValidator vIValidator;

    public GestorSalida(IValidator vIValidator) {
        this.vIValidator = vIValidator;
    }

    public double calcularCosto(Vehiculo vehiculo) throws Exception {
        var hIngreso = vehiculo.getHoraIngreso();
        var hSalida = LocalDateTime.now();

        if (hSalida.isBefore(hIngreso)) {
            throw new Exception("Error: La hora de salida es anterior a la hora de ingreso");
        }

        Duration duracion = Duration.between(hIngreso, hSalida);

        long minutos = duracion.toMinutes(); //Toda la duración en minutos 125 minutos

        long horasACobrar = (long)Math.ceil(minutos / 60.0); // 125 /60 = 2,x

        if (horasACobrar == 0) {
            horasACobrar = 1;
        }

        double totalAPagar = vehiculo.getTipoVehiculo().getTarifaPorTiempo() * horasACobrar;

        mostarRecibo(vehiculo, duracion, horasACobrar, vehiculo.getTipoVehiculo().getTarifaPorTiempo() , totalAPagar);

        return totalAPagar;
    }

    private void mostarRecibo(Vehiculo vehiculo, Duration duration, long horasACobrar, double tarifaHora, double totalPagar) {
        System.out.println("--- RESUMEN DE SALIDA ---");
        System.out.println("Vehiculo: "+ vehiculo.getPlaca() +"");
        System.out.println("Tiempo total: " + formatDuration(duration));
        System.out.println("Horas cobradas: " + horasACobrar);
        System.out.println("Tarifa x Hora: $" + tarifaHora);
        System.out.println("Total a Pagar: $" + totalPagar);
        System.out.println("-------------------------");
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        return hours + " hrs " + minutes + " min";
    }

    public void procesarSalida(String placa) {
        //Tendria salir del parqueadero -> GestorIngreso -> eliminarlo
    }

    public boolean validarSalida(String placa) {
        return vIValidator.existePlaca(placa);
    }
}
