package core.model;

import java.util.HashMap;
import java.util.Map;

public class Vacuna {
    public String id;
    public String mascotaId;
    private String nombre;
    private String fechaAplicacion;
    private String lote;
    private String veterinarioResponsable;

    // Constructor
    public Vacuna(String tempId, String tempMascotaId, String nombre, String fechaAplicacion, String lote, String veterinarioResponsable) {
        this.id = tempId;
        this.mascotaId = tempMascotaId;
        this.nombre = nombre;
        this.fechaAplicacion = fechaAplicacion;
        this.lote = lote;
        this.veterinarioResponsable = veterinarioResponsable;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getMascotaId() {
        return mascotaId;
    }
    public String getNombre() {
        return nombre;
    }
    public String getFechaAplicacion() {
        return fechaAplicacion;
    }
    public String getLote() {
        return lote;
    }
    public String getVeterinario() {
        return veterinarioResponsable;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public static Vacuna formattValue(String info)
    {
        Map<String, String> map = parseQueryString(info);
        String id = map.getOrDefault("id", "");
        String _mascotaId = map.getOrDefault("mascota_id", "");
        String name = map.getOrDefault("vacuna_name", "");
        String fechaApplicacion = map.getOrDefault("fecha_aplicacion", "");
        String lote = map.getOrDefault("lote", "");
        String veterinario = map.getOrDefault("veterinario", "");
        return new Vacuna(id, _mascotaId, name, fechaApplicacion, lote, veterinario);
    }

    public String _toString()
    {
        return "id="+this.getId()+"&mascota_id="+this.getMascotaId()+"&vacuna_name="+this.getNombre()+"&fecha_aplicacion="+this.getFechaAplicacion()+"&lote="+this.getLote()+"&veterinario="+this.getVeterinario();
    }
}