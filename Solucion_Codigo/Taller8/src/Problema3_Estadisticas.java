public class Problema3_Estadisticas {
    public static void main(String[] args) {
        jugadores[] equipo = new jugadores[3];

        equipo[0] = new portero("Manuel Neuer", 1);
        ((portero) equipo[0]).atajadas = 10;
        ((portero) equipo[0]).goles = 2;

        equipo[1] = new atacante("Robert Lewandowski", 9);
        ((atacante) equipo[1]).pases = 20;
        ((atacante) equipo[1]).recuperadas = 5;
        ((atacante) equipo[1]).goles = 8;

        equipo[2] = new defensor("Sergio Ramos", 4);
        ((defensor) equipo[2]).pases = 15;
        ((defensor) equipo[2]).recuperadas = 12;
        ((defensor) equipo[2]).goles = 3;

        for (jugadores j : equipo) {
            j.calcularEstadistica();
            System.out.println(j);
            System.out.println("------------------");
        }
    }
}

abstract class jugadores {
    public String nombre;
    public int numDorsal;

    public abstract void calcularEstadistica();

    public jugadores(String nombre, int numDorsal) {
        this.nombre = nombre;
        this.numDorsal = numDorsal;
    }

    public String toString() {
        return String.format("Jugador: %s \n Dorsal: %d\n", nombre, numDorsal);
    }
}

class portero extends jugadores {
    public int valor;
    public int atajadas;
    public int goles;

    public portero(String nombre, int numDorsal) {
        super(nombre, numDorsal);
        this.valor = valor;
        this.atajadas = atajadas;
        this.goles = goles;

    }

    public void calcularEstadistica() {
        valor = (atajadas * 5) + (goles * 30);
    }

    @Override
    public String toString() {
        return String.format("Atajadas: %5d\n Goles: %d\n Valor: %d", atajadas, goles, valor, super.toString());
    }
}

class atacante extends jugadores {
    public int valor;
    public int pases;
    public int recuperadas;
    public int goles;

    public atacante(String nombre, int numDorsal) {
        super(nombre, numDorsal);
        this.pases = pases;
        this.recuperadas = recuperadas;
        this.goles = goles;
        this.valor = valor;
    }

    public void calcularEstadistica() {
        valor = (recuperadas * 3) + (goles * 30) + (pases * 2);
    }

    @Override
    public String toString() {
        return String.format("Atajadas: %d\n Pases: %d \nGoles: %d\n Valor: %d", recuperadas, pases, goles, valor,
                super.toString());
    }
}

class defensor extends jugadores {
    public int valor;
    public int pases;
    public int recuperadas;
    public int goles;

    public defensor(String nombre, int numDorsal) {
        super(nombre, numDorsal);
        this.pases = pases;
        this.recuperadas = recuperadas;
        this.goles = goles;
        this.valor = valor;

    }

    public void calcularEstadistica() {
        valor = (recuperadas * 3) + (goles * 30) + (pases * 2);
    }

    @Override
    public String toString() {
        return String.format("Atajadas: %d\n Pases: %d \nGoles: %d\n Valor: %d", recuperadas, pases, goles, valor,
                super.toString());
    }
}