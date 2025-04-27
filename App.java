public class Vacuna {
    private String nombre;
    private String fechaAplicacion;
    private String lote;
    private String veterinarioResponsable;

    // Constructor
    public Vacuna(String nombre, String fechaAplicacion, String lote, String veterinarioResponsable) {
        this.nombre = nombre;
        this.fechaAplicacion = fechaAplicacion;
        this.lote = lote;
        this.veterinarioResponsable = veterinarioResponsable;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ... (otros getters y setters)
}

import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private String nombre;
    private String tipoAnimal; // Ej: "Perro", "Gato", etc.
    private List<Vacuna> vacunasAplicadas;

    // Constructor
    public Mascota(String nombre, String tipoAnimal) {
        this.nombre = nombre;
        this.tipoAnimal = tipoAnimal;
        this.vacunasAplicadas = new ArrayList<>();
    }

    // Método para agregar vacunas
    public void agregarVacuna(Vacuna vacuna) {
        vacunasAplicadas.add(vacuna);
    }

    // Método para listar vacunas
    public List<Vacuna> getVacunasAplicadas() {
        return vacunasAplicadas;
    }
}

public class SistemaVacunas {
    public static void main(String[] args) {
        // Ejemplo de uso
        Mascota mascota = new Mascota("Max", "Perro");
        
        // Crear vacunas
        Vacuna vacuna1 = new Vacuna("Rabia", "2023-10-15", "L123", "Dr. Pérez");
        Vacuna vacuna2 = new Vacuna("Moquillo", "2023-09-20", "L456", "Dr. Gómez");

        // Asociar vacunas a la mascota
        mascota.agregarVacuna(vacuna1);
        mascota.agregarVacuna(vacuna2);

        // Mostrar vacunas aplicadas
        System.out.println("Vacunas de " + mascota.getNombre() + ":");
        for (Vacuna vacuna : mascota.getVacunasAplicadas()) {
            System.out.println("- " + vacuna.getNombre() + " (Fecha: " + vacuna.getFechaAplicacion() + ")");
        }
    }
}