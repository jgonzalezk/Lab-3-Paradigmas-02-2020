package Stack.views;
import Stack.controllers.StackController;

import java.util.Scanner;

/**
 * Una clase que representa al menu de un foro
 * con una sesion de usuario iniciada.
 */
public class LoginMenu {

    private Scanner lector;
    private StackController controller;

    /**
     * Crea el menu con una sesion iniciada.
     * @param controller Controlador del stack.
     */
    public LoginMenu(StackController controller){
        lector = new Scanner(System.in);
        this.controller = controller;
    }

    /**
     * Menu interactivo por consola de opciones.
     */
    public void run2(){
        boolean salir = false;
        int opcion;

        while (!salir){
            System.out.println("------------------MENU LOGIN------------------");
            System.out.println("SESION INICIADA COMO: " + controller.usuarioActivoStr());
            System.out.println("CANTIDAD DE PUNTOS: " + controller.puntosActivoStr() + " Punto(s).");
            System.out.println("Opciones:");
            System.out.println("1. Agregar nueva pregunta");
            System.out.println("2. Responder pregunta");
            System.out.println("3. Dar recompensa");
            System.out.println("4. Aceptar respuesta");
            System.out.println("5. Agregar voto pregunta/respuesta");
            System.out.println("6. Cerrar sesion"); //Necesita cerrar sesion para volver al MainMenu y salir de programa
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
                case 4:
                    opcion4();
                    break;
                case 5:
                    opcion5();
                    break;
                case 6:
                    opcion6();
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }

    /**
     * Metodo que solicita los datos necesarios para crear una pregunta
     * y si es posible la crea.
     */
    private void opcion1(){
        System.out.println("Ingrese el titulo de la pregunta:");
        String titulo = lector.nextLine();
        System.out.println("Ingrese el contenido de la pregunta:");
        String contenido = lector.nextLine();
        System.out.println("Ingrese las etiquetas de la pregunta separadas por coma (Si no desea agregar oprima ENTER):");
        String etiquetas = lector.nextLine();

        int verificador = controller.ask(titulo, contenido, etiquetas);

        if(verificador == 1){
            System.out.println("Pregunta creada exitosamente.");
        }
        else{
            System.out.println("Una de las etiquetas ingresadas no existe.");
        }
    }

    /** Metodo que solicita los datos necesarios para responder a una pregunta
     *  y si es posible se crea la respuesta.
     */
    private void opcion2(){
        String strPreguntas = controller.stringPreguntas();
        System.out.println("PREGUNTAS Y RESPUESTAS:\n"+strPreguntas);

        System.out.println("Ingrese el id de la pregunta a la cual desea responder:");
        String idPregunta = lector.nextLine();
        System.out.println("Ingrese el contenido de la respuesta:");
        String contenido  = lector.nextLine();

        int verificador = controller.answer(idPregunta, contenido);

        if(verificador == 1){
            System.out.println("Respuesta agregada exitosamente.");
        }
        else{
            System.out.println("La pregunta a la cual desea responder no existe.");
        }
    }

    /**
     *  Metodo que solicita los datos necesarios para agregar recompensa a
     *  una pregunta, si es posible se agrega la recompensa.
     */
    private void opcion3(){
        String strPreguntas = controller.stringPreguntas();
        System.out.println("PREGUNTAS Y RESPUESTAS:\n"+strPreguntas);

        System.out.println("Ingrese el id de la pregunta a la cual desea agregar recompensa:");
        String idPregunta = lector.nextLine();
        System.out.println("Ingrese la cantidad de recompensa a agregar:");
        String recompensa  = lector.nextLine();

        int verificador = controller.reward(idPregunta, recompensa);

        if(verificador == 1){
            System.out.println("Recompensa agregada correctamente.");
        }
        else if(verificador == -1){
            System.out.println("No posee esa cantidad de puntos para otorgar o la cantidad es negativa.");
        }
        else{
            System.out.println("La pregunta a la cual desea agregar recompensa no existe o ya esta resuelta.");
        }

    }

    /**
     *  Metodo que solicita los datos necesarios para aceptar una respuesta,
     *  si es posible se acepta la respuesta.
     */
    private void opcion4(){
        String strPreguntas = controller.stringPregAutor();
        System.out.println("PREGUNTAS QUE PUEDE ACEPTAR:\n"+strPreguntas);

        System.out.println("Ingrese el id de la pregunta para aceptar una respuesta:");
        String idPregunta = lector.nextLine();
        System.out.println("Ingrese el id de la respuesta que sera aceptada:");
        String idRespuesta  = lector.nextLine();

        int verificador = controller.accept(idPregunta, idRespuesta);

        if(verificador==0){
            System.out.println("El id de la pregunta y/o respuesta a la cual desea aceptar no es correcto.");
        }

        else{
            System.out.println("Respuesta aceptada correctamente, estado de la pregunta acualizado.");
        }

    }

    /**
     *  Metodo que solicita los datos necesarios para agregar un voto a
     *  una pregunto o respuesta, si es posible se agrega el voto.
     */
    private void opcion5(){
        String strPreguntas = controller.stringPregAutorVote();
        System.out.println("PREGUNTAS Y RESPUESTAS QUE OUEDE VOTAR:\n"+strPreguntas);

        System.out.println("Ingrese los ID de pregunta y respuesta separados por una coma");
        System.out.println("EN CASO DE VOTAR A UNA PREGUNTA SOLO INGRESAR UN ID, por ejemplo 5,2 (pregunta 5 y respuesta 2) o 5 (pregunta 5): ");
        String ids = lector.nextLine();

        System.out.println("Ingrese \"+\" si el voto es positivo y \"-\" si el voto es negativo (SIN LAS COMILLAS):");
        String voto = lector.nextLine();

        int verificador = controller.vote(ids, voto);

        if(verificador==0){
            System.out.println("El id de la pregunta y/o respuesta a la cual desea votar no es correcto.");
        }

        else if(verificador == -1){
            System.out.println("El tipo de voto no es valido.");
        }

        else{
            System.out.println("El voto a sido agregado correctamente.");
        }

    }

    /**
     * Opcion para cerrar sesion.
     */
    private void opcion6(){
        controller.logoutUsuario();
        System.out.println("Cerrando sesion...");
    }

}
