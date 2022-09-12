package Stack.views;
import Stack.controllers.StackController;
import java.util.Scanner;

/**
 * Una clase que representa al menu de un foro
 * en la seccion principal.
 */
public class MainMenu {

    private Scanner lector;

    private StackController controller;

    /**
     * Crea el menu principal.
     * @param controller Controlador del stack.
     */
    public MainMenu(StackController controller){
        lector = new Scanner(System.in);
        this.controller = controller;
    }

    /**
     * Menu interactivo por consola de opciones.
     */
    public void run(){
        boolean salir = false;
        int opcion;

        while (!salir){
            System.out.println("------------------MENU PRINCIPAL------------------");
            System.out.println("Opciones:");
            System.out.println("1. Registrar");
            System.out.println("2. Crear etiqueta");
            System.out.println("3. Iniciar sesion");
            System.out.println("0. Salir del programa");
            System.out.println("INGRESE SU OPCION: ");
            String lineaLeida = lector.nextLine();
            try{
                opcion = Integer.parseInt(lineaLeida.trim());
            }
            catch (NumberFormatException e){
                System.out.println("No ingreso un numero");
                opcion = -1;
            }

            switch (opcion){
                case 1:
                    opcion1();
                    break;
                case 2:
                    opcion2();
                    break;
                case 3:
                    opcion3();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }

    /**
     * Metodo que solicita los datos necesarios para registrar a un usuario,
     * si es posible lo registra.
     */
    private void opcion1(){

        System.out.println("Ingrese el nombre de usuario:");
        String username = lector.nextLine();

        System.out.println("Ingrese la constrasenia:");
        String password = lector.nextLine();

        int verificador = controller.registrarUsuario(username, password);

        if (verificador == 1){
            System.out.println("El usuario se registro exitosamente.");
        }
        else{
            System.out.println("El usuario ya existe.");
        }
    }

    /**
     * Metodo que solicita los datos necesarios para agregar una etiqueta,
     * si es posible se agrega.
     */
    private void opcion2(){
        System.out.println("Ingrese el nombre de la etiqueta:");
        String tagName = lector.nextLine();
        System.out.println("Ingrese la descripcion de la etiqueta:");
        String descripcion = lector.nextLine();

        int verificador = controller.crearEtiqueta(tagName, descripcion);

        if (verificador == 1){
            System.out.println("La etiqueta se ha creado correctamente.");
        }
        else{
            System.out.println("La etiqueta ya existe.");
        }
    }

    /**
     * Metodo que solicita los datos necesarios para iniciar sesion,
     * si es posible inicia sesion.
     */
    private void opcion3(){
        System.out.println("Ingrese su usuario:");
        String username = lector.nextLine();

        System.out.println("Ingrese su constrasenia:");
        String password = lector.nextLine();

        int verificador = controller.loginUsuario(username, password);
        if (verificador == 1){
            System.out.println("Inicio de sesion correcto...");
            //Mostrar el loginmenu
            LoginMenu loginMenu = new LoginMenu(controller);
            loginMenu.run2();
        }
        else{
            System.out.println("El usuario o contrasenia son incorrectos.");
        }


    }
}
