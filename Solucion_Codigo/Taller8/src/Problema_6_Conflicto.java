
/**
 * La ONU le solicita desarrollar un simulador de conflictos bélicos mundiales
 * en el lenguaje de alto nivel orientado a objetos Java, considerando sus
 * cuatro pilares fundamentales: abstracción, encapsulamiento, herencia y
 * polimorfismo, cumpliendo con los siguientes lineamientos:
 * 
 * Requisitos Funcionales
 * De manera general, cada nación debe ser representada con la siguiente
 * información: Nombre de la nación, Número de habitantes de la nación, Cantidad
 * de recursos económicos disponibles, Nivel de poder militar (valor entre 1 y
 * 100), su estado de conflicto que indica si la nación está actualmente en
 * conflicto o no, y cualquier otra información que usted considere necesaria.
 * No olvide implementar los métodos y/o constructores básicos para procesar
 * esta información dados todos los requerimientos.
 * 
 * A su vez se requiere la información de las naciones desarrolladas con alta
 * tecnología militar, como: Si la nación dispone de tecnología avanzada. Para
 * estas naciones avanzadas, implementar el cálculo del impacto, el cual
 * considera un bono de tecnología para el incremento de su poder militar (no
 * olvide que para este último la restricción es de 1-100, y en el caso de sobre
 * pasar, asigne directamente 100).
 * 
 * De igual forma se necesita conocer de las naciones en vías de desarrollo su
 * nivel de recursos limitados (recursos económicos y poder militar por cada N
 * habitantes), así como la implementación del cálculo del impacto, el cual
 * reduce el impacto en el conflicto debido a sus recursos limitados. Queda a su
 * criterio matemático y/o estadístico el planteamiento del modelo matemático
 * (con las variables/parámetros que tenga a bien) para calcular este factor de
 * impacto.
 * 
 * Para las naciones desarrolladas o en vías de desarrollo, considere sus
 * naciones aliadas, lo cual es decisivo para incrementar o decrementar su nivel
 * de impacto directamente a su poder militar, pero solo si tiene aliados
 * disponibles.
 * 
 * El programa debe permitir declarar conflictos entre dos naciones
 * seleccionadas con un proceso aleatorio/randomico.
 * Calcular las consecuencias del conflicto utilizando polimorfismo y la
 * implementación de cálculo de impacto.
 * 
 * Consecuencias del conflicto:
 * Reducción del 5% de población por cada diferencia en los niveles de poder
 * militar.
 * Reducción del 10% de recursos de la nación derrotada.
 * Si las naciones tienen el mismo nivel de poder militar, ambas pierden el 5%
 * de recursos.
 * Al finalizar el programa, debe mostrar un reporte con el estado actual de
 * cada nación (población, recursos y estado de conflicto, etc), así como el
 * total de conflictos que se simularon entre N naciones.
 * Note
 * 
 * Reporte Final
 * 
 * Al finalizar la simulación, debe generarse un reporte general que contenga:
 * Estado actual de cada nación (población, recursos, estado de conflicto, etc.)
 * Total de conflictos simulados entre N naciones.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class Problema_6_Conflicto {
    public static void main(String[] args) {
        SimuladorConflictos simulador = new SimuladorConflictos();
        simulador.agregarNacion(new NacionDesarrollada("USAA", 330_000_000, 2_000_000_000_000.0, 95, true,
                Arrays.asList("Ukrania", "Francia")));
        simulador.agregarNacion(new NacionDesarrollada("Germany", 83_000_000, 800_000_000_000.0, 85, true,
                Arrays.asList("Francia", "USA")));
        simulador.agregarNacion(
                new NacionEnDesarrollo("Brazil", 210_000_000, 300_000_000_000.0, 60,
                        Arrays.asList("Argentina", "Chile")));
        simulador.agregarNacion(
                new NacionEnDesarrollo("India", 1_400_000_000, 500_000_000_000.0, 70, Arrays.asList("Russia")));
        simulador.agregarNacion(new NacionEnDesarrollo("Nigeria", 200_000_000, 100_000_000_000.0, 40, Arrays.asList()));

        simulador.simularConflictos(3);
        simulador.reporteFinal();
    }
}

class SimuladorConflictos {
    public List<Nacion> naciones;
    public int conflictosSimulados;

    public SimuladorConflictos() {
        naciones = new ArrayList<>();
        conflictosSimulados = 0;
    }

    public void agregarNacion(Nacion nacion) {
        naciones.add(nacion);
    }

    public void simularConflictos(int cantidad) {
        Random rand = new Random();
        for (int i = 0; i < cantidad; i++) {
            int idx1 = rand.nextInt(naciones.size());
            int idx2;
            do {
                idx2 = rand.nextInt(naciones.size());
            } while (idx1 == idx2);

            Nacion n1 = naciones.get(idx1);
            Nacion n2 = naciones.get(idx2);

            System.out.println(String.format("\nConflicto #%d: %s vs %s\n", i + 1, n1.nombre, n2.nombre));
            simularConflicto(n1, n2);
            conflictosSimulados++;
        }
    }

    public void simularConflicto(Nacion n1, Nacion n2) {
        n1.estadoConflicto = true;
        n2.estadoConflicto = true;

        double impacto1 = n1.calcularImpacto();
        double impacto2 = n2.calcularImpacto();

        System.out.println(
                String.format("Impacto %s: %.2f | Impacto %s: %.2f", n1.nombre, impacto1, n2.nombre, impacto2));

        if (impacto1 > impacto2) {
            double diferencia = impacto1 - impacto2;
            double reduccionPob = n2.habitantes * 0.05 * diferencia;
            n2.habitantes -= (long) reduccionPob;
            n2.recursos *= 0.90;
            System.out.println(String.format("%s gana el conflicto. %s pierde %.0f habitantes y 10%% de recursos.",
                    n1.nombre, n2.nombre, reduccionPob));
        } else if (impacto2 > impacto1) {
            double diferencia = impacto2 - impacto1;
            double reduccionPob = n1.habitantes * 0.05 * diferencia;
            n1.habitantes -= (long) reduccionPob;
            n1.recursos *= 0.90;
            System.out.println(String.format("%s gana el conflicto. %s pierde %.0f habitantes y 10%% de recursos.",
                    n2.nombre, n1.nombre, reduccionPob));
        } else {
            n1.recursos *= 0.95;
            n2.recursos *= 0.95;
            System.out.println("Empate. Ambas naciones pierden 5% de recursos.");
        }

        n1.estadoConflicto = false;
        n2.estadoConflicto = false;
    }

    public void reporteFinal() {
        System.out.println("\n--- REPORTE FINAL ---");
        for (Nacion n : naciones) {
            System.out.println(n);
        }
        System.out.println(String.format("Total de conflictos simulados: %d\n", conflictosSimulados));
    }

}

abstract class Nacion {
    public String nombre;
    public long habitantes;
    public double recursos;
    public int poderMilitar;
    public boolean estadoConflicto;
    public List<String> aliados;

    public Nacion(String nombre, long habitantes, double recursos, int poderMilitar, List<String> aliados) {
        this.nombre = nombre;
        this.habitantes = habitantes;
        this.recursos = recursos;
        this.poderMilitar = Math.max(1, Math.min(poderMilitar, 100));
        this.estadoConflicto = false;
        this.aliados = aliados;
    }

    public abstract double calcularImpacto();

    @Override
    public String toString() {
        return String.format(
                "Nación: %s\nHabitantes: %d\nRecursos: %.2f\nPoder Militar: %d\nEn Conflicto: %s\nAliados: %s\n",
                nombre, habitantes, recursos, poderMilitar, estadoConflicto ? "Sí" : "No", aliados);
    }
}

class NacionDesarrollada extends Nacion {
    public boolean tecnologiaAvanzada;

    public NacionDesarrollada(String nombre, long habitantes, double recursos, int poderMilitar,
            boolean tecnologiaAvanzada, List<String> aliados) {
        super(nombre, habitantes, recursos, poderMilitar, aliados);
        this.tecnologiaAvanzada = tecnologiaAvanzada;
    }

    @Override
    public double calcularImpacto() {
        int bono = tecnologiaAvanzada ? 15 : 0;
        int aliadosBono = aliados != null && !aliados.isEmpty() ? 10 : 0;
        int impacto = poderMilitar + bono + aliadosBono;
        return Math.min(impacto, 100);
    }

    @Override
    public String toString() {
        return String.format("%s Tecnología avanzada: %s", super.toString(), tecnologiaAvanzada ? "Sí" : "No");
    }
}

class NacionEnDesarrollo extends Nacion {
    public NacionEnDesarrollo(String nombre, long habitantes, double recursos, int poderMilitar, List<String> aliados) {
        super(nombre, habitantes, recursos, poderMilitar, aliados);
    }

    @Override
    public double calcularImpacto() {
        double recursosPorHabitante = recursos / (habitantes == 0 ? 1 : habitantes);
        int aliadosBono = aliados != null && !aliados.isEmpty() ? 5 : 0;
        int impacto = (int) (poderMilitar * recursosPorHabitante * 1000) + aliadosBono;
        impacto = Math.max(1, Math.min(impacto, 100));
        return impacto;
    }

    @Override
    public String toString() {
        return String.format("%s Recursos limitados: %.2f por habitante", super.toString(),
                recursos / (habitantes == 0 ? 1 : habitantes));
    }
}