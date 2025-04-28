import java.util.ArrayList;
import java.util.Scanner;

//Clase principal (cliente)
public class Cliente {
    private String nombreCompleto;
    private String identificacion;
    private String correo;
    private String telefono;
    private String direccion;
    private ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

    // Constructor para inicializar un cliente con sus datos
    public Cliente(String nombreCompleto, String identificacion, String correo, String telefono, String direccion) {
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

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

    public ArrayList<Mascota> obtenerMascotas()
    {
       return this.mascotas;
    }

    // Método para mostrar los datos del cliente como un texto
    public String toString() {
        return "\nCliente{" +
                "Nombre='" + this.nombreCompleto + '\'' +
                ", ID='" + this.identificacion + '\'' +
                ", Correo='" + this.correo + '\'' +
                ", Teléfono='" + this.telefono + '\'' +
                ", Dirección='" + this.direccion + '\'' +
                '}';
    }
}
