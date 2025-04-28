import java.util.ArrayList;

public class Mascota {
    public String nombre;
    public int edad;
    public String raza;
    public ArrayList<Vacuna> vacunasAplicadas = new ArrayList<Vacuna>();

    // Constructor
    public Mascota(String tempNombre, int tempEdad, String tempRaza) {
        this.nombre = tempNombre;
        this.edad = tempEdad;
        this.raza = tempRaza;
    }

    // Método para agregar vacunas
    public void agregarVacuna(Vacuna vacuna) {
        this.vacunasAplicadas.add(vacuna);
    }

    // Método para listar vacunas
    public ArrayList<Vacuna> getVacunasAplicadas() {
        return this.vacunasAplicadas;
    }

    public void showInfoMascota() {
        System.out.println(" Nombre -> "+ this.nombre + " edad -> " + this.edad + " raza -> " + this.raza);
    }
}