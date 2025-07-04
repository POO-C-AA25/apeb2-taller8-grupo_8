
/**
 * En un restaurant se tiene diferentes tipos de menú para ofrecer a los
 * clientes. Una cuenta por pagar está compuesta por características como:
 * nombre del cliente, listado de todos las cartas(menú) solicitados por el
 * cliente, valor a cancelar total, subtotal, Iva.
 * 
 * Los tipos de menú del restaurant son:
 * 
 * Menú a la carta
 * 
 * nombre del plato
 * valor del menú
 * valor inicial del menú
 * valor de porción de guarnición
 * valor de bebida
 * porcentaje adicional por servicio en relación del valor inicial del menú
 * 
 * Menú del día
 * 
 * nombre del plato
 * valor del menú
 * valor inicial del menú
 * valor de postre
 * valor de bebida
 * 
 * Menú de niños
 * 
 * nombre del plato
 * valor del menú
 * valor inicial del menú
 * valor de porción de helado
 * valor de porción de pastel
 * 
 * Menú económico
 * 
 * nombre del plato
 * valor del menú
 * valor inicial del menú
 * porcentaje de descuento, en referencia al valor inicial del menú
 * 
 * Note
 * 
 * Para solucionar lo anterior se debe generar lo siguiente:
 * 
 * Un diagrama exclusivo que involucren las clases de tipo Menú (usar
 * polimorfismo)
 * Una solución en lenguaje de programación Java. Usar Polimorfismo en la
 * solución. Hacer uso del método toString() para presentar toda la información
 * posible del objeto (nombre del cliente, subtotal, iva, listado de todos los
 * menú, valor a cancelar a total.
 **/
import java.util.ArrayList;

public class Problema_2_MenuRestaurante {
    public static void main(String[] args) {
        // Crear menús
        Menu menuCarta = new MenuCarta("Plato a la carta", 20.0, 15.0, 3.0, 2.0, 10);
        Menu menuDia = new MenuDia("Plato del día", 15.0, 12.0, 2.0, 1.5);
        Menu menuNinos = new MenuNinos("Plato para niños", 10.0, 8.0, 1.5, 1.0);
        Menu menuEconomico = new MenuEconomico("Plato económico", 12.0, 10.0, 5);

        // Crear cliente y agregar menús a su pedido
        Cliente cliente = new Cliente("Juan Pérez");
        cliente.agregarMenu(menuCarta);
        cliente.agregarMenu(menuDia);
        cliente.agregarMenu(menuNinos);
        cliente.agregarMenu(menuEconomico);

        // Calcular totales
        double subtotal = cliente.calcularSubtotal();
        double iva = subtotal * 0.15;
        double total = subtotal + iva;

        // Mostrar información
        System.out.println(cliente);
        System.out.println("Subtotal: " + subtotal);
        System.out.println("IVA: " + iva);
        System.out.println("Total a cancelar: " + total);
        System.out.println("Gracias por su visita al restaurante!");
    }
}

abstract class Menu {
    public String nombrePlato;
    public double valorMenu;
    public double valorInicialMenu;

    public Menu(String nombrePlato, double valorMenu, double valorInicialMenu) {
        this.nombrePlato = nombrePlato;
        this.valorMenu = valorMenu;
        this.valorInicialMenu = valorInicialMenu;
    }

    public abstract double calcularTotal();

    @Override
    public String toString() {
        return "Nombre del Plato: " + nombrePlato + ", Valor del Menú: " + valorMenu
                + ", Valor Inicial del Menú: " + valorInicialMenu;
    }
}

class MenuCarta extends Menu {
    public double valorPorcionGuarnicion;
    public double valorBebida;
    public double porcentajeServicio;

    public MenuCarta(String nombrePlato, double valorMenu, double valorInicialMenu,
            double valorPorcionGuarnicion, double valorBebida, int porcentajeServicio) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPorcionGuarnicion = valorPorcionGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeServicio = porcentajeServicio;
    }

    @Override
    public double calcularTotal() {
        return valorMenu + valorPorcionGuarnicion + valorBebida
                + (valorInicialMenu * porcentajeServicio / 100);
    }

    @Override
    public String toString() {
        return super.toString() + ", Valor por Porción de Guarnición: " + valorPorcionGuarnicion
                + ", Valor de bebida:  " + this.valorBebida + ", Porcentaje de servicio: " + this.porcentajeServicio
                + "%";
    }
}

class MenuDia extends Menu {
    public double valorPostre;
    public double valorBebida;

    public MenuDia(String nombrePlato, double valorMenu, double valorInicialMenu,
            double valorPostre, double valorBebida) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }

    @Override
    public double calcularTotal() {
        return this.valorMenu + this.valorPostre + this.valorBebida;
    }

    @Override
    public String toString() {
        return super.toString() + ", Valor del Postre: " + this.valorPostre + ", Valor de la Bebida: "
                + this.valorBebida;
    }
}

class MenuNinos extends Menu {
    public double valorPorcionHelado;
    public double valorPorcionPastel;

    public MenuNinos(String nombrePlato, double valorMenu, double valorInicialMenu,
            double valorPorcionHelado, double valorPorcionPastel) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPorcionHelado = valorPorcionHelado;
        this.valorPorcionPastel = valorPorcionPastel;
    }

    @Override
    public double calcularTotal() {
        return this.valorMenu + this.valorPorcionHelado + this.valorPorcionPastel;
    }

    @Override
    public String toString() {
        return super.toString() + ", Valor de Porción de Helado: " + this.valorPorcionHelado
                + ", Valor de Porción de Pastel: " + this.valorPorcionPastel;
    }
}

class MenuEconomico extends Menu {
    public double porcentajeDescuento;

    public MenuEconomico(String nombrePlato, double valorMenu, double valorInicialMenu, double porcentajeDescuento) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularTotal() {
        return this.valorMenu - (this.valorInicialMenu * this.porcentajeDescuento / 100);
    }

    @Override
    public String toString() {
        return super.toString() + ", Porcentaje de Descuento: " + this.porcentajeDescuento + "%";
    }
}

class Cliente {
    public String nombreCliente;
    public ArrayList<Menu> menus;
    public double subtotal;
    public double iva;
    public double total;

    public Cliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.menus = new ArrayList<>();
        this.subtotal = 0.0;
        this.iva = 0.0;
        this.total = 0.0;
    }

    public void agregarMenu(Menu menu) {
        this.menus.add(menu);
        this.subtotal += menu.calcularTotal();
    }

    public void calcularIva() {
        this.iva = this.subtotal * 0.15; // 15% de IVA
    }

    public void calcularTotal() {
        this.total = this.subtotal + this.iva;
    }

    public double calcularSubtotal() {
        return this.subtotal;
    }

    @Override
    public String toString() { 
        return String.format("Cuenta{nombreCliente= %s\n, menus= %s\n , subtotal= %s\n , iva= %s\n , total= %s\n}",nombreCliente,menus,subtotal,iva,total);
    }
}
