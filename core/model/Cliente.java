package core.model;

import java.util.ArrayList;

//Clase principal (cliente)
public class Cliente {

    public String Id;
    private String nombreCompleto;
    private String identificacion;
    private String correo;
    private String telefono;
    private String direccion;
    private ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

    // Constructor para inicializar un cliente con sus datos
    public Cliente(String Id, String nombreCompleto, String identificacion, String correo, String telefono, String direccion) {
        this.Id = Id;
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    //Getters
    public String getId()
    {
        return this.Id;
    }

    public String getNombreCompleto() { return this.nombreCompleto; }

    public String getIdentificacion()
    {
        return this.identificacion;
    }

    public String getCorreo() { return this.correo; }

    public String getTelefono() { return this.telefono; }

    public String getDireccion()
    {
        return this.direccion;
    }

    public ArrayList<Mascota> obtenerMascotas()
    {
        return this.mascotas;
    }

    //Setters

    public void setNombreCompleto(String tempName)
    {
        this.nombreCompleto = tempName;
    }

    public void setIdentificacion(String tempIdentificacion)
    {
        this.identificacion = tempIdentificacion;
    }

    public void setCorreo(String tempCorreo)
    {
        this.correo = tempCorreo;
    }

    public void setTelefono(String tempTelefono)
    {
        this.telefono = tempTelefono;
    }

    public void setDireccion(String tempDireccion)
    {
        this.direccion = tempDireccion;
    }

    public void agregarMascota(Mascota tempMascota)
    {
       this.mascotas.add(tempMascota);
    }

    public static Cliente formattValue(String info)
    {
        String[] partes = info.split("\\|");
        if (partes.length == 6) {
            return new Cliente(
                    partes[0].trim(),
                    partes[1].trim(),
                    partes[2].trim(),
                    partes[3].trim(),
                    partes[4].trim(),
                    partes[5].trim()
            );
        }
        return null;
    }

    public String _toString()
    {
        return this.getId()+"|"+this.getNombreCompleto()+"|"+this.getIdentificacion()+"|"+this.getCorreo()+"|"+this.getTelefono()+"|"+this.getDireccion();
    }

}
