
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
import java.util.ArrayList;
import java.util.Scanner;

public class Problema_4_Monitoreo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Region regionCosta = new Region("Costa");
        Region regionSierra = new Region("Sierra");
        Region regionOriente = new Region("Oriente");

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione la región para agregar un dispositivo:");
            System.out.println("1. Costa");
            System.out.println("2. Sierra");
            System.out.println("3. Oriente");
            System.out.println("0. Terminar e imprimir reportes");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del dispositivo: ");
                    String nombreCosta = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modeloCosta = sc.nextLine();
                    System.out.print("Temperatura: ");
                    double tempCosta = sc.nextDouble();
                    System.out.print("Precipitación: ");
                    double precCosta = sc.nextDouble();
                    System.out.print("Calidad del aire: ");
                    double aireCosta = sc.nextDouble();
                    System.out.print("Humedad del suelo: ");
                    double humCosta = sc.nextDouble();
                    regionCosta.agregarDispositivo(
                            new DispositivoCosta(nombreCosta, modeloCosta, tempCosta, precCosta, aireCosta, humCosta));
                    break;
                case 2:
                    System.out.print("Nombre del dispositivo: ");
                    String nombreSierra = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modeloSierra = sc.nextLine();
                    System.out.print("Temperatura: ");
                    double tempSierra = sc.nextDouble();
                    System.out.print("Precipitación: ");
                    double precSierra = sc.nextDouble();
                    System.out.print("Calidad del aire: ");
                    double aireSierra = sc.nextDouble();
                    System.out.print("Humedad del suelo: ");
                    double humSierra = sc.nextDouble();
                    regionSierra.agregarDispositivo(new DispositivoSierra(nombreSierra, modeloSierra, tempSierra,
                            precSierra, aireSierra, humSierra));
                    break;
                case 3:
                    System.out.print("Nombre del dispositivo: ");
                    String nombreOriente = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modeloOriente = sc.nextLine();
                    System.out.print("Temperatura: ");
                    double tempOriente = sc.nextDouble();
                    System.out.print("Precipitación: ");
                    double precOriente = sc.nextDouble();
                    System.out.print("Calidad del aire: ");
                    double aireOriente = sc.nextDouble();
                    System.out.print("Humedad del suelo: ");
                    double humOriente = sc.nextDouble();
                    regionOriente.agregarDispositivo(new DispositivoOriente(nombreOriente, modeloOriente, tempOriente,
                            precOriente, aireOriente, humOriente));
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        System.out.println("\n--- Reporte Región Costa ---");
        System.out.println(regionCosta.generarReporteRegion());
        System.out.println("\n--- Reporte Región Sierra ---");
        System.out.println(regionSierra.generarReporteRegion());
        System.out.println("\n--- Reporte Región Oriente ---");
        System.out.println(regionOriente.generarReporteRegion());
        sc.close();
    }
}

class Region {
    public String nombreRegion;
    public ArrayList<DispositivoMonitoreo> dispositivos;

    public Region(String nombreRegion) {
        this.nombreRegion = nombreRegion;
        this.dispositivos = new ArrayList<>();
    }

    public void agregarDispositivo(DispositivoMonitoreo dispositivo) {
        dispositivos.add(dispositivo);
    }

    public String generarReporteRegion() {
        if (dispositivos.isEmpty()) {
            return String.format("No hay dispositivos registrados en la región %s.\n", nombreRegion);
        } else {
            String reporte = "";
            for (DispositivoMonitoreo d : dispositivos) {
                reporte += String.format("%s\n", d.generarReporte());
            }
            return String.format("Reporte de la región %s:\n%s", nombreRegion, reporte);
        }
    }
}

abstract class DispositivoMonitoreo {
    public String modelo;
    public String nombre;
    public double temperatura;
    public double precipitacion;
    public double calidadAire;
    public double humedadSuelo;

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
    public DispositivoCosta(String nombre, String modelo, double temperatura, double precipitacion, double calidadAire,
            double humedadSuelo) {
        super(nombre, modelo, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public String generarReporte() {
        String mensajes = "";
        if (temperatura > 30.0) {
            mensajes += "Alerta de calor extremo en la costa.\n";
        }
        if (precipitacion < 10.0) {
            mensajes += "Riesgo de sequía en la costa.\n";
        }
        if (calidadAire < 40.0) {
            mensajes += "Alerta de contaminación del aire en la costa.\n";
        }
        if (humedadSuelo < 15.0) {
            mensajes += "Riesgo de sequía en la costa.\n";
        }
        if (precipitacion > 100.0) {
            mensajes += "Riesgo de inundaciones en la costa.\n";
        }
        if (temperatura < 10.0) {
            mensajes += "Alerta de frío extremo en la costa.\n";
        }
        String reporteBase = String.format("Reporte de monitoreo en la costa:\n%s\n", super.toString());
        if (mensajes.isEmpty()) {
            return reporteBase;
        } else {
            return mensajes + "\n" + reporteBase;
        }
    }

    @Override
    public String toString() {
        return String.format("Dispositivo Costa: %s\n%s", nombre, super.toString());
    }
}

class DispositivoSierra extends DispositivoMonitoreo {
    public DispositivoSierra(String nombre, String modelo, double temperatura, double precipitacion, double calidadAire,
            double humedadSuelo) {
        super(nombre, modelo, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public String generarReporte() {
        String mensajes = "";
        if (temperatura < 5.0) {
            mensajes += "Alerta de frío extremo en la sierra.\n";
        }
        if (precipitacion > 100.0) {
            mensajes += "Riesgo de deslizamientos en la sierra.\n";
        }
        if (temperatura > 25.0) {
            mensajes += "Alerta de calor extremo en la sierra.\n";
        }
        if (precipitacion < 20.0) {
            mensajes += "Riesgo de sequía en la sierra.\n";
        }
        if (calidadAire < 30.0) {
            mensajes += "Alerta de contaminación del aire en la sierra.\n";
        }
        if (humedadSuelo < 10.0) {
            mensajes += "Riesgo de sequía en la sierra.\n";
        }
        String reporteBase = String.format("Reporte de monitoreo en la sierra:\n%s\n", super.toString());
        if (mensajes.isEmpty()) {
            return reporteBase;
        } else {
            return mensajes + "\n" + reporteBase;
        }
    }

    @Override
    public String toString() {
        return String.format("Dispositivo Sierra: %s\n%s", nombre, super.toString());
    }
}

class DispositivoOriente extends DispositivoMonitoreo {
    public DispositivoOriente(String nombre, String modelo, double temperatura, double precipitacion,
            double calidadAire, double humedadSuelo) {
        super(nombre, modelo, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public String generarReporte() {
        String mensajes = "";
        if (calidadAire < 50.0) {
            mensajes += "Alerta de contaminación del aire en el oriente.\n";
        }
        if (humedadSuelo < 20.0) {
            mensajes += "Riesgo de sequía en el oriente.\n";
        }
        if (precipitacion > 150.0) {
            mensajes += "Riesgo de inundaciones en el oriente.\n";
        }
        if (temperatura > 35.0) {
            mensajes += "Alerta de calor extremo en el oriente.\n";
        }
        if (temperatura < 10.0) {
            mensajes += "Alerta de frío extremo en el oriente.\n";
        }
        if (precipitacion < 30.0) {
            mensajes += "Riesgo de sequía en el oriente.\n";
        }
        if (calidadAire < 40.0) {
            mensajes += "Alerta de contaminación del aire en el oriente.\n";
        }
        String reporteBase = String.format("Reporte de monitoreo en el oriente:\n%s\n", super.toString());
        if (mensajes.isEmpty()) {
            return reporteBase;
        } else {
            return mensajes + "\n" + reporteBase;
        }
    }

    @Override
    public String toString() {
        return String.format("Dispositivo Oriente: %s\n%s", nombre, super.toString());
    }
}
