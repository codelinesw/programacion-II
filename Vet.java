import java.util.ArrayList;
import java.util.Scanner;

public class Vet {
    public Cliente cliente;
    public static void main(String[] args) {
      Vet vet = new Vet();
      Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario
      ArrayList<Cliente> clientes = new ArrayList<Cliente>(); // Lista para almacenar los clientes
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
            vet.cliente = new Cliente(nombre, id, correo, telefono, direccion);


            String continuarMascotas;
            do {
                System.out.print("Nombre de la mascota: ");
                String nombreMascota = scanner.nextLine();

                System.out.print("Ingresa una especie, escoge una opción (1. Perro, 2. Gato, 3. Conejo): ");
                String especie = scanner.nextLine();

                System.out.print("Raza: ");
                String raza = scanner.nextLine();

                System.out.print("Edad de la mascota(en meses): ");
                int edad = Integer.parseInt(scanner.nextLine());

                // Valida que tipo de mascota se va ingresar segun la opción seleccionada
                if (especie.equals("1")) { // Si es perro
                    // Se instancia una clase de tipo Perro y se agrega a la lista de mascotas
                    Perro perro = new Perro(nombreMascota, edad, raza);
                    vet.cliente.agregarMascota(perro);
                }
                if (especie.equals("2")) { // Si es perro
                    // Se instancia una clase de tipo Perro y se agrega a la lista de mascotas
                    Gato gato = new Gato(nombreMascota, edad, raza);
                    vet.cliente.agregarMascota(gato);
                }
                if (especie.equals("3")) { // Si es perro
                    // Se instancia una clase de tipo Perro y se agrega a la lista de mascotas
                    Rabbit conejo = new Rabbit(nombreMascota, edad, raza);
                    vet.cliente.agregarMascota(conejo);
                }
                

                System.out.print("¿Desea agregar otra mascota para este cliente? (s/n): ");
                continuarMascotas = scanner.nextLine();
            } while(continuarMascotas.equalsIgnoreCase("s"));

            //Agregar el cliente a la lista
            clientes.add(vet.cliente);

            //Función para preguntar al usuario si desea ingresar mas clientes
            System.out.print("¿Desea agregar otro cliente? (s/n): ");
            continuar = scanner.nextLine();

        } while (continuar.equalsIgnoreCase("s")); // Si el usuario ingresa "s" continúa registrando

        // Mostrar todos los clientes registrados
        System.out.println("\n=== Lista de Clientes Registrados ===");
        for (Cliente c : clientes) {
            System.out.println(c);
        }

        // Mostramos todas las mascotas de Ana
        System.out.println("Mascotas:");
        for (Mascota pet : vet.cliente.obtenerMascotas()) {
            pet.showInfoMascota();
        }

        scanner.close();
    }
}