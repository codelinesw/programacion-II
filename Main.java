import java.util.ArrayList;
import java.util.Scanner;

//Clase principal (cliente)
class Cliente {
    private String nombreCompleto;
    private String identificacion;
    private String correo;
    private String telefono;
    private String direccion;

    // Constructor para inicializar un cliente con sus datos
    public Cliente(String nombreCompleto, String identificacion, String correo, String telefono, String direccion) {
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Método para mostrar los datos del cliente como un texto
    public String toString() {
        return "\nCliente{" +
                "Nombre='" + nombreCompleto + '\'' +
                ", ID='" + identificacion + '\'' +
                ", Correo='" + correo + '\'' +
                ", Teléfono='" + telefono + '\'' +
                ", Dirección='" + direccion + '\'' +
                '}';
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario
        ArrayList<Cliente> clientes = new ArrayList<>(); // Lista para almacenar los clientes
        String continuar; // Variable para controlar si el usuario quiere seguir registrando clientes

        System.out.println("=== Registro de Clientes para Veterinaria ===");

        do {
            // Solicitar los datos del cliente
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();

            System.out.print("Número de identificación: ");
            String id = scanner.nextLine();

            System.out.print("Correo electrónico: ");
            String correo = scanner.nextLine();

            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();

            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();

            // Crear un nuevo cliente con los datos ingresados
            Cliente cliente = new Cliente(nombre, id, correo, telefono, direccion);

            //Agregar el cliente a la lista
            clientes.add(cliente);

            //Función para preguntar al usuario si desea ingresar mas clientes
            System.out.print("¿Desea agregar otro cliente? (s/n): ");
            continuar = scanner.nextLine();

        } while (continuar.equalsIgnoreCase("s")); // Si el usuario ingresa "s" continúa registrando

        // Mostrar todos los clientes registrados
        System.out.println("\n=== Lista de Clientes Registrados ===");
        for (Cliente c : clientes) {
            System.out.println(c);
        }

        scanner.close();
    }
}
