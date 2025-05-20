package core.model;

import java.util.ArrayList;
public class Rabbit extends Mascota
{
    // Constructor
    public Rabbit(String id, String tempNombre, int tempEdad, String tempRaza, String propetarioId)
    {
        super(id, tempNombre, tempEdad, tempRaza, propetarioId);
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
        System.out.println(" Especie -> Conejo, Nombre -> "+this.nombre+" Edad -> "+this.edad+" Raza -> "+this.raza);
    }
}