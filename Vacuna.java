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
}