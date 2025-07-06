/**
 * En un juego de rol, se desea implementar un sistema de combate en el que
 * participen diferentes tipos de personajes: guerreros, magos y arqueros.
 * Cada personaje tiene atributos y habilidades únicas, así como diferentes
 * métodos de ataque y defensa.
 *
 * El objetivo del juego es enfrentar a los personajes en batallas y determinar
 * el ganador en función de sus habilidades, estrategias y atributos.
 * Los guerreros se destacan por su fuerza y habilidades cuerpo a cuerpo,
 * los magos por sus hechizos y poderes mágicos, y los arqueros por su precisión
 * y habilidades a distancia.
 *
 * El sistema debe permitir crear nuevos personajes de cada tipo, asignarles
 * atributos iniciales, como puntos de vida y nivel de experiencia,
 * y permitirles subir de nivel a medida que ganan batallas.
 * Además, se debe implementar un algoritmo de combate que evalúe las
 * habilidades de cada personaje y determine el resultado de la batalla.
 *
 * Utilizando programación orientada a objetos, herencia y polimorfismo,
 * implementa el sistema de combate y las clases necesarias para representar
 * a los diferentes tipos de personajes. Asegúrate de que cada tipo de personaje
 * tenga sus propias habilidades y métodos de ataque y defensa, y que puedan
 * interactuar entre sí en las batallas.
 *
 * Note
 *
 * Para solucionar lo anterior se debe generar lo siguiente:
 *
 * Un diagrama exclusivo que involucren las funcionalidades principales del juego.
 * Una solución en lenguaje de programación Java. Usar Polimorfismo en la solución.
 * Clase de prueba/ejecutor, que demuestre la funcionalidad del juego.
 *
 * @author 
 */
abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int nivel;
    protected int experiencia;

    public Personaje(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = 1;
        this.experiencia = 0;
    }

    public abstract int atacar();
    public abstract int defender();

    public void recibirDanio(int danio) {
        vida -= danio;
        if (vida < 0) vida = 0;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void ganarExperiencia(int exp) {
        experiencia += exp;
        if (experiencia >= 100) {
            subirNivel();
            experiencia -= 100;
        }
    }

    public void subirNivel() {
        nivel++;
        vida += 20;
        System.out.println(nombre + " ha subido al nivel " + nivel + " y ahora tiene " + vida + " de vida.");
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }
}

class Guerrero extends Personaje {
    public Guerrero(String nombre) {
        super(nombre, 120);
    }

    @Override
    public int atacar() {
        return 25 + (nivel * 3);
    }

    @Override
    public int defender() {
        return 15 + (nivel * 2);
    }
}

class Mago extends Personaje {
    public Mago(String nombre) {
        super(nombre, 80);
    }

    @Override
    public int atacar() {
        return 35 + (nivel * 4);
    }

    @Override
    public int defender() {
        return 10 + (nivel * 2);
    }
}

class Arquero extends Personaje {
    public Arquero(String nombre) {
        super(nombre, 100);
    }

    @Override
    public int atacar() {
        return 20 + (nivel * 5);
    }

    @Override
    public int defender() {
        return 12 + (nivel * 2);
    }
}

class Combate {
    public static Personaje luchar(Personaje p1, Personaje p2) {
        System.out.println("¡Comienza la batalla entre " + p1.getNombre() + " y " + p2.getNombre() + "!");
        int ronda = 1;
        while (p1.estaVivo() && p2.estaVivo()) {
            System.out.println("\nRonda " + ronda);
            int danio1 = Math.max(0, p1.atacar() - p2.defender());
            int danio2 = Math.max(0, p2.atacar() - p1.defender());
            p2.recibirDanio(danio1);
            p1.recibirDanio(danio2);
            System.out.println(p1.getNombre() + " inflige " + danio1 + " de daño. Vida de " + p2.getNombre() + ": " + p2.getVida());
            System.out.println(p2.getNombre() + " inflige " + danio2 + " de daño. Vida de " + p1.getNombre() + ": " + p1.getVida());
            ronda++;
        }
        Personaje ganador = p1.estaVivo() ? p1 : p2;
        System.out.println("\n¡El ganador es " + ganador.getNombre() + "!");
        ganador.ganarExperiencia(120);
        return ganador;
    }
}

public class Problema_1_Juego {
    public static void main(String[] args) {
        Personaje guerrero = new Guerrero("Thor");
        Personaje mago = new Mago("Merlín");
        Personaje arquero = new Arquero("Legolas");

        Combate.luchar(guerrero, mago);
        Combate.luchar(guerrero, arquero);
        Combate.luchar(mago, arquero);
    }
}