import java.io.File;

public class mochila_voraz {

    static boolean existeFicheroEntrada = false; //Se modifica en validarFichero
    static boolean trazasActivas = false;
    static boolean FINDEPROGAMA = false;

    public static void main(String[] args) {
        System.out.println("\nSYSTEM: INICIO DE PROGRAMA MOCHILA_VORAZ\n\n");

        if(args.length > 4) {
            System.out.println("SYSTEM: Ha introducido "+(args.length-4)+" argumentos más de los permitidos.");
            mostrarAyuda();
            FINDEPROGAMA = true;
        }

        if(args.length == 0){
            System.out.println("SYSTEM: No se ha especificado fichero de entrada...se solicitarán los datos por entrada de teclado.");
            System.out.println("SYSTEM: No se ha especificado fichero de salida...se creará un fichero con nombre salida_mochila_voraz.txt.");

        }
        else{
            comprobarArgumentos(args);
        }

        if (FINDEPROGAMA){
            System.out.println("SYSTEM: FIN DE PROGRAMA MOCHILA_VORAZ\n");
            return;
        }
    }

    static void mostrarAyuda(){
        System.out.println("SINTAXIS: mochila_voraz [-t] [-h] [fichero_entrada] [fichero_salida]\n");
        System.out.println("             -t: traza cada paso de manera que se describa la aplicación del algoritmo utilizado.");
        System.out.println("             -h: muestra una ayuda y la sintaxis del comando.");
        System.out.println("fichero_entrada: es el nombre del fichero del que se leen los datos de entrada.");
        System.out.println(" fichero_salida: es el nombre del fichero que se creará para almacenar la salida.\n\n");
    }

    static void comprobarArgumentos(String[] args){
        //Se utilizan 4 argumentos
        if(args.length == 4){
            //Primer argumento
            if(!esValidoArgumento(args[0])){
                FINDEPROGAMA = true;
                return;
            }
            //Segundo argumento
            if(!esValidoArgumento(args[1])){
                FINDEPROGAMA = true;
                return;
            }
            //Tercer argumento
            if(!esValidoArgumentoFichero(args[2], true)){
                FINDEPROGAMA = true;
                return;
            }
            //Cuarto argumento
            if(!esValidoArgumentoFichero(args[3], false)){
                FINDEPROGAMA = true;
                return;
            }
        }
        //Se utilizan 3 argumentos
        else if(args.length == 3){
            //Primer argumento
            if(!esValidoArgumento(args[0])){
                FINDEPROGAMA = true;
                return;
            }
            //Segundo argumento
            boolean esEntrada = false;
            switch (args[1]){
                case "-t":
                    System.out.println("SYSTEM: se han activado las trazas.");
                    trazasActivas = true;
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    System.out.println("SYSTEM: se comprueba validez del fichero '"+args[1]+"'.");
                    if(!validarFichero(args[1], true)){
                        FINDEPROGAMA = true;
                        return;
                    }
                    esEntrada = true; //Para indicar si el tercer argumento es fichero de salida
                    break;
            }
            //Tercer argumento
            if(!esValidoArgumentoFichero(args[2], !esEntrada)){
                FINDEPROGAMA = true;
                return;
            }
        }
        //Se utilizan 2 argumentos
        else if(args.length == 2){
            //Primer argumento
            boolean esEntrada = false;
            switch (args[0]){
                case "-t":
                    System.out.println("SYSTEM: se han activado las trazas.");
                    trazasActivas = true;
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    System.out.println("SYSTEM: se comprueba validez del fichero '"+args[0]+"'.");
                    if(!validarFichero(args[0], true)){
                        FINDEPROGAMA = true;
                        return;
                    }
                    esEntrada = true; //Para indicar si el segundo argumento es fichero de salida
                    break;
            }
            //Segundo argumento
            if(!esValidoArgumentoFichero(args[2], !esEntrada)){
                FINDEPROGAMA = true;
                return;
            }
        }
        //Se utilizan 2 argumentos
        else if(args.length == 1){
            switch (args[0]){
                case "-t":
                    System.out.println("SYSTEM: se han activado las trazas.");
                    trazasActivas = true;
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    System.out.println("SYSTEM: se comprueba validez del fichero '"+args[0]+"'.");
                    if(!validarFichero(args[0], true)){
                        FINDEPROGAMA = true;
                        return;
                    }
                    break;
            }
        }

    }

    static boolean esValidoArgumento(String arg){
        switch (arg){
            case "-t":
                System.out.println("SYSTEM: se han activado las trazas.");
                trazasActivas = true;
                return true;
            case "-h":
                mostrarAyuda();
                return true;
            default:
                System.out.println("ERROR: argumento no válido.");
                mostrarAyuda();
                return false;
        }
    }

    static boolean esValidoArgumentoFichero(String arg, Boolean esEntrada){
        if(arg.equals("-t") || arg.equals("-h")){
            System.out.println("ERROR: argumento "+arg+" no válido.");
            mostrarAyuda();
            return false;
        }else{
            System.out.println("SYSTEM: se comprueba validez del fichero '"+arg+"'.");
            if(!validarFichero(arg, esEntrada)) {
                return false;
            }
        }
        return true;
    }

    static boolean validarFichero(String nombre_fichero, Boolean esEntrada){
        File fichero = new File(nombre_fichero);
        return fichero.isFile();


    }

}