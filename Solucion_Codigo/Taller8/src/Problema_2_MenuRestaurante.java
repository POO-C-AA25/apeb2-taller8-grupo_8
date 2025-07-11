
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
 * 
 * @author Daniel Irene
 **/

import java.util.ArrayList;
import java.util.Scanner;

public class Problema_2_MenuRestaurante {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente("Juan Pérez");

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione el tipo de menú a agregar:");
            System.out.println("1. Menú a la carta");
            System.out.println("2. Menú del día");
            System.out.println("3. Menú de niños");
            System.out.println("4. Menú económico");
            System.out.println("0. Terminar pedido");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del plato: ");
                    String nombreCarta = sc.nextLine();
                    System.out.print("Valor del menú: ");
                    double valorMenuCarta = sc.nextDouble();
                    System.out.print("Valor inicial del menú: ");
                    double valorInicialCarta = sc.nextDouble();
                    System.out.print("Valor porción guarnición: ");
                    double valorGuarnicion = sc.nextDouble();
                    System.out.print("Valor bebida: ");
                    double valorBebidaCarta = sc.nextDouble();
                    System.out.print("Porcentaje servicio: ");
                    int porcentajeServicio = sc.nextInt();
                    cliente.agregarMenu(new MenuCarta(nombreCarta, valorMenuCarta, valorInicialCarta, valorGuarnicion,
                            valorBebidaCarta, porcentajeServicio));
                    break;
                case 2:
                    System.out.print("Nombre del plato: ");
                    String nombreDia = sc.nextLine();
                    System.out.print("Valor del menú: ");
                    double valorMenuDia = sc.nextDouble();
                    System.out.print("Valor inicial del menú: ");
                    double valorInicialDia = sc.nextDouble();
                    System.out.print("Valor postre: ");
                    double valorPostre = sc.nextDouble();
                    System.out.print("Valor bebida: ");
                    double valorBebidaDia = sc.nextDouble();
                    cliente.agregarMenu(
                            new MenuDia(nombreDia, valorMenuDia, valorInicialDia, valorPostre, valorBebidaDia));
                    break;
                case 3:
                    System.out.print("Nombre del plato: ");
                    String nombreNinos = sc.nextLine();
                    System.out.print("Valor del menú: ");
                    double valorMenuNinos = sc.nextDouble();
                    System.out.print("Valor inicial del menú: ");
                    double valorInicialNinos = sc.nextDouble();
                    System.out.print("Valor porción helado: ");
                    double valorHelado = sc.nextDouble();
                    System.out.print("Valor porción pastel: ");
                    double valorPastel = sc.nextDouble();
                    cliente.agregarMenu(
                            new MenuNinos(nombreNinos, valorMenuNinos, valorInicialNinos, valorHelado, valorPastel));
                    break;
                case 4:
                    System.out.print("Nombre del plato: ");
                    String nombreEco = sc.nextLine();
                    System.out.print("Valor del menú: ");
                    double valorMenuEco = sc.nextDouble();
                    System.out.print("Valor inicial del menú: ");
                    double valorInicialEco = sc.nextDouble();
                    System.out.print("Porcentaje descuento: ");
                    double porcentajeDescuento = sc.nextDouble();
                    cliente.agregarMenu(
                            new MenuEconomico(nombreEco, valorMenuEco, valorInicialEco, porcentajeDescuento));
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        double subtotal = cliente.calcularSubtotal();
        double iva = subtotal * 0.15;
        double total = subtotal + iva;

        System.out.println(cliente);
        System.out.println(String.format("Subtotal: %.2f", subtotal));
        System.out.println(String.format("IVA: %.2f", iva));
        System.out.println(String.format("Total a cancelar: %.2f", total));
        System.out.println(String.format("%s", "Gracias por su visita al restaurante!"));
        sc.close();
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
        return String.format("Nombre del Plato: %s, Valor del Menú: %.2f, Valor Inicial del Menú: %.2f",
                nombrePlato, valorMenu, valorInicialMenu);
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
        return String.format(
                "%s, Valor por Porción de Guarnición: %.2f, Valor de bebida: %.2f, Porcentaje de servicio: %d%%",
                super.toString(), valorPorcionGuarnicion, valorBebida, (int) porcentajeServicio);
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
        return String.format("%s, Valor del Postre: %.2f, Valor de la Bebida: %.2f",
                super.toString(), valorPostre, valorBebida);
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
        return String.format("%s, Valor de Porción de Helado: %.2f, Valor de Porción de Pastel: %.2f",
                super.toString(), valorPorcionHelado, valorPorcionPastel);
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
        return String.format("%s, Porcentaje de Descuento: %.2f%%",
                super.toString(), porcentajeDescuento);
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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Cuenta{\nnombreCliente= %s\n", nombreCliente));
        sb.append("menus=\n");
        for (Menu m : menus) {
            sb.append(String.format("  %s\n", m.toString()));
        }
        sb.append(String.format("subtotal= %.2f\n", subtotal));
        sb.append(String.format("iva= %.2f\n", iva));
        sb.append(String.format("total= %.2f\n}", total));
        return sb.toString();
    }
}