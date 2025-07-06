import java.util.ArrayList;
import java.util.List;

public class Problema5_Emprendimientos {
    public static void main(String[] args) {
        List<Emprendimiento> emprendimientos = new ArrayList<>();
        
        Emprendimiento tecno = new EmprendimientoTecnologico("TechLoja", "Innovación tecnológica", "0991234567", "Java");
        tecno.agregarProductoServicio("Desarrollo de apps");
        tecno.agregarMentor(new Mentor("Carlos Méndez", "Desarrollo Software"));
        
        Emprendimiento artesanal = new EmprendimientoArtesanal("ArteLojano", "Preservar tradiciones", "072345678", "Madera");
        artesanal.agregarProductoServicio("Tallado en madera");
        
        emprendimientos.add(tecno);
        emprendimientos.add(artesanal);
        
        for(Emprendimiento emp : emprendimientos) {
            System.out.println(emp.toString());
            emp.participarEnFeria("Tecnológica");
            emp.evolucionar();
            System.out.println("---------------------");
        }
    }
}

abstract class Emprendimiento {
    protected String nombre;
    protected String mision;
    protected String contacto;
    protected List<String> productosServicios;
    protected List<Mentor> mentores;
    protected boolean necesitaAcompanamiento;
    public abstract void participarEnFeria(String tipoFeria);
    public abstract void evolucionar();
    public Emprendimiento(String nombre, String mision, String contacto) {
        this.nombre = nombre;
        this.mision = mision;
        this.contacto = contacto;
        this.productosServicios = new ArrayList<>();
        this.mentores = new ArrayList<>();
    }
    public void agregarProductoServicio(String item) {
        productosServicios.add(item);
    }
    
    public void agregarMentor(Mentor mentor) {
        mentores.add(mentor);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n")
          .append("Misión: ").append(mision).append("\n")
          .append("Contacto: ").append(contacto).append("\n")
          .append("Productos/Servicios: ").append(productosServicios).append("\n")
          .append("Mentores: ");
        
        for(Mentor m : mentores) {
            sb.append(m.toString()).append(", ");
        }
        
        return sb.toString();
    }
}

class Mentor {
    private String nombre;
    private String especialidad;
    
    public Mentor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    
    @Override
    public String toString() {
        return nombre + " (" + especialidad + ")";
    }
}

class EmprendimientoTecnologico extends Emprendimiento {
    private String tecnologiaPrincipal;
    
    public EmprendimientoTecnologico(String nombre, String mision, String contacto, String tecnologiaPrincipal) {
        super(nombre, mision, contacto);
        this.tecnologiaPrincipal = tecnologiaPrincipal;
    }
    
    @Override
    public void participarEnFeria(String tipoFeria) {
        if(tipoFeria.equals("Tecnológica")) {
            System.out.println(nombre + " participará con demostraciones interactivas");
        } else {
            System.out.println(nombre + " participará con stand informativo");
        }
    }
    
    @Override
    public void evolucionar() {
        agregarProductoServicio("Nueva versión de software");
        System.out.println(nombre + " ha lanzado una nueva versión de su producto");
    }
    
    @Override
    public String toString() {
        return super.toString() + "Tecnología principal: " + tecnologiaPrincipal + "\n";
    }
}

class EmprendimientoArtesanal extends Emprendimiento {
    private String materialPrincipal;
    
    public EmprendimientoArtesanal(String nombre, String mision, String contacto, String materialPrincipal) {
        super(nombre, mision, contacto);
        this.materialPrincipal = materialPrincipal;
    }
    
    @Override
    public void participarEnFeria(String tipoFeria) {
        if(tipoFeria.equals("Artesanal")) {
            System.out.println(nombre + " participará con taller en vivo");
        } else {
            System.out.println(nombre + " participará con exhibición de productos");
        }
    }
    
    @Override
    public void evolucionar() {
        agregarProductoServicio("Línea premium artesanal");
        System.out.println(nombre + " ha creado una línea premium de productos");
    }
    
    @Override
    public String toString() {
        return super.toString() + "Material principal: " + materialPrincipal + "\n";
    }
}

