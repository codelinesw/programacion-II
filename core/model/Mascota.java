package core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mascota {
    public String id;
    public String nombre;
    public int edad;
    public String raza;
    public String propietarioId;
    public ArrayList<Vacuna> vacunasAplicadas = new ArrayList<Vacuna>();

    // Constructor
    public Mascota(String id, String tempNombre, int tempEdad, String tempRaza, String tempPropietarioId) {
        this.id = id;
        this.nombre = tempNombre;
        this.edad = tempEdad;
        this.raza = tempRaza;
        this.propietarioId = tempPropietarioId;
    }

    //Getters
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    public String getRaza() {
        return raza;
    }
    // Método para listar vacunas
    public ArrayList<Vacuna> getVacunasAplicadas() {
        return this.vacunasAplicadas;
    }

    // Método para agregar vacunas
    public void agregarVacuna(Vacuna vacuna) {
        this.vacunasAplicadas.add(vacuna);
    }

    public void showInfoMascota() {
        System.out.println(" Nombre -> "+ this.nombre + " edad -> " + this.edad + " raza -> " + this.raza);
    }


    public static Map<String, String> parseQueryString(String input) {
        String[] parameters = input.split("&");
        Map<String, String> mapa = new HashMap<>();
        for (String param : parameters) {
            String[] keyValue = param.split("=", 2);
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";
            mapa.put(key, value);
        }
        return mapa;
    }

    public static Mascota formattValue(String info)
    {
        Map<String, String> map = parseQueryString(info);
        String id = map.getOrDefault("id", "");
        String _propietarioId = map.getOrDefault("propietario_id", "");
        String name = map.getOrDefault("mascota_name", "");
        int edad = 0;
        try {
            edad = Integer.parseInt(map.getOrDefault("mascota_edad", "0"));
        } catch (NumberFormatException e) {
            System.out.println("Edad inválida, se usará 0");
        }
        String raza = map.getOrDefault("mascota_raza", "");
        return new Mascota(id, name, edad, raza, _propietarioId);
    }

    public String _toString()
    {
        return "id="+this.getId()+"&propietario_id="+this.propietarioId+"&mascota_name="+this.getNombre()+"&mascota_edad="+this.getEdad()+"&mascota_raza="+this.getRaza();
    }
}