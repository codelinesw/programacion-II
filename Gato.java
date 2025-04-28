import java.util.ArrayList;
public class Gato extends Mascota
{
    // Constructor
    public Gato(String tempNombre, int tempEdad, String tempRaza) 
    {
        super(tempNombre, tempEdad, tempRaza);
    }

    // Método para agregar vacunas
    public void agregarVacuna(Vacuna vacuna) {
        this.vacunasAplicadas.add(vacuna);
    }

    // Método para listar vacunas
    public ArrayList<Vacuna> getVacunasAplicadas() {
        return this.vacunasAplicadas;
    }

    public void showInfoMascota(){
        System.out.println(" Especie -> Gato, Nombre -> "+this.nombre+" Edad -> "+this.edad+" Raza -> "+this.raza);
    }
}