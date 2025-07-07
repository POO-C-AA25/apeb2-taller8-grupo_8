/**
 * Una red de monitoreo ambiental tiene como objetivo registrar, analizar y
 * reportar los impactos del cambio climático en diferentes regiones. En cada
 * ubicación se instalan dispositivos capaces de medir distintos indicadores
 * climáticos como temperatura, precipitación, calidad del aire, y humedad del
 * suelo. Dependiendo de la región (costa, sierra y oriente), los dispositivos
 * pueden variar en capacidades y protocolos de recolección.
 * 
 * Los datos recolectados deben almacenarse y analizarse periódicamente. Además,
 * ciertas ubicaciones requieren generar reportes personalizados que destaquen
 * riesgos ambientales como sequías, deslizamientos o contaminación del aire.
 * Algunos dispositivos pueden comportarse de forma especializada para detectar
 * únicamente ciertos tipos de indicadores dependiendo de la región (costa,
 * sierra y oriente).
 * 
 * Requisitos funcionales:
 * Representar diferentes tipos de dispositivos y sus especializaciones, para la
 * costa, sierra y oriente.
 * 
 * Implementar métodos polimórficos que permitan procesar los datos según los
 * tipos de dispositivos y sus especializaciones, para la costa, sierra y
 * oriente.
 * 
 * Generar reportes dinámicos en función del tipo de riesgo ambiental detectado
 * según la región
 * 
 * Note
 * 
 * Plantee una solución polimórfica dada una jerarquía de clases con ventajas de
 * herencia. Y para la generación de reportería, use los toString() base.
 * 
 * @author Daniel Irene
 */
public class Problema_4_Monitoreo {
    public static void main(String[] args) {
        // Crear dispositivos de monitoreo
        DispositivoCosta dispositivoCosta = new DispositivoCosta("Sensor Costa", "Modelo A", 25.5, 80.0);
        DispositivoSierra dispositivoSierra = new DispositivoSierra("Sensor Sierra", "Modelo B", 15.0, 60.0);
        DispositivoOriente dispositivoOriente = new DispositivoOriente("Sensor Oriente", "Modelo C", 20.0, 70.0);

        // Procesar datos y generar reportes
        System.out.println(dispositivoCosta.generarReporte());
        System.out.println(dispositivoSierra.generarReporte());
        System.out.println(dispositivoOriente.generarReporte());
    }
}

abstract class DispositivoMonitoreo {
    public String region;
    public double temperatura;
    public double precipitacion;
    public double calidadAire;
    public double humedadSuelo;
    public String modelo;
    public String nombre;

    public DispositivoMonitoreo(String nombre, String modelo, double temperatura, double precipitacion,
            double calidadAire, double humedadSuelo) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
        this.calidadAire = calidadAire;
        this.humedadSuelo = humedadSuelo;

    }

    public abstract String generarReporte();

    @Override
    public String toString() {
        return String.format(
                "Dispositivo: %s\nModelo: %s\nTemperatura: %.2f°C\nPrecipitación: %.2f mm\nCalidad del aire: %.2f AQI\nHumedad del suelo: %.2f%%",
                nombre, modelo, temperatura, precipitacion, calidadAire, humedadSuelo);
    }
}

class DispositivoCosta extends DispositivoMonitoreo {
    public DispositivoCosta(String nombre, String modelo, double temperatura, double precipitacion) {
        super(nombre, modelo, temperatura, precipitacion, 0, 0);
    }

    @Override
    public String generarReporte() {
        if (temperatura > 30.0) {
            return String.format("Alerta de calor extremo en la costa: %s", super.toString());
        } else if (precipitacion < 10.0) {
            return String.format("Riesgo de sequía en la costa: %s", super.toString());
        }
        return String.format("Reporte de monitoreo en la costa: %s", super.toString());
    }
}

class DispositivoSierra extends DispositivoMonitoreo {
    public DispositivoSierra(String nombre, String modelo, double temperatura, double precipitacion) {
        super(nombre, modelo, temperatura, precipitacion, 0, 0);
    }

    @Override
    public String generarReporte() {
        if (temperatura < 5.0) {
            return String.format("Alerta de frío extremo en la sierra: %s", super.toString());
        } else if (precipitacion > 100.0) {
            return String.format("Riesgo de deslizamientos en la sierra: %s", super.toString());
        }
        return String.format("Reporte de monitoreo en la sierra: %s", super.toString());
    }
}

class DispositivoOriente extends DispositivoMonitoreo {
    public DispositivoOriente(String nombre, String modelo, double temperatura, double precipitacion) {
        super(nombre, modelo, temperatura, precipitacion, 0, 0);
    }

    @Override
    public String generarReporte() {
        if (calidadAire < 50.0) {
            return String.format("Alerta de contaminación del aire en el oriente: %s", super.toString());
        } else if (humedadSuelo < 20.0) {
            return String.format("Riesgo de sequía en el oriente: %s", super.toString());
        }
        return String.format("Reporte de monitoreo en el oriente: %s", super.toString());
    }
}
