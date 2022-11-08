import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PEC1
 * AUTOR: Asier Rodríguez
 * CURSO: UNED PREDA 2022/2023
 * JDK: Oracle OpenJDK version 19
 */
public class mochila_voraz {

    static boolean existeFicheroEntrada = false; //Se modifica en validarFichero
    static boolean existeFicheroSalida = false; //Se modifica en validarFichero
    static boolean trazasActivas = false;
    static boolean FINDEPROGAMA = false;

    public static void main(String[] args) {
        System.out.println("\nSYSTEM: INICIO DE PROGRAMA MOCHILA_VORAZ\n\n");

        if(args.length > 4) {
            System.out.println("SYSTEM: Ha introducido "+(args.length-4)+" argumentos más de los permitidos.");
            mostrarAyuda();
            FINDEPROGAMA = true;
        }

        if(args.length > 0){
            if(!comprobarArgumentos(args)) FINDEPROGAMA = true;
        }

        if(!existeFicheroEntrada && !FINDEPROGAMA) System.out.println("SYSTEM: No se ha especificado fichero de entrada...se solicitarán los datos por entrada de teclado.");
        if(!existeFicheroSalida && !FINDEPROGAMA) System.out.println("SYSTEM: No se ha especificado fichero de salida...se creará un fichero con nombre salida_mochila_voraz.txt.");

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

    static boolean comprobarArgumentos(String[] args){
        //Se utilizan 4 argumentos
        if(args.length == 4){
            //Primer argumento
            if(!esValidoArgumento(args[0])) return false;
            //Segundo argumento
            if(!esValidoArgumento(args[1])) return false;
            //Tercer argumento
            if(!esValidoArgumentoFichero(args[2], true)) return false;
            //Cuarto argumento
            return esValidoArgumentoFichero(args[3], false);
        }
        //Se utilizan 3 argumentos
        else if(args.length == 3){
            //Primer argumento
            if(!esValidoArgumento(args[0])) return false;
            //Segundo argumento
            boolean esEntrada = false;
            switch (args[1]){
                case "-t":
                    trazasActivadas();
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    if(!validarFichero(args[1], true)) return false;
                    esEntrada = true; //Para indicar si el tercer argumento es fichero de salida
                    break;
            }
            //Tercer argumento
            return esValidoArgumentoFichero(args[2], !esEntrada);
        }
        //Se utilizan 2 argumentos
        else if(args.length == 2){
            //Primer argumento
            boolean esEntrada = false;
            switch (args[0]){
                case "-t":
                    trazasActivadas();
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    if(!validarFichero(args[0], true)) return false;
                    esEntrada = true; //Para indicar si el segundo argumento es fichero de salida
                    break;
            }
            //Segundo argumento
            if(args[1].equals("-t")) trazasActivadas();
            else if(args[1].equals("-h")) mostrarAyuda();
            else return esValidoArgumentoFichero(args[1], !esEntrada);

            return true;
        }
        //Se utilizan 1 argumento
        else if(args.length == 1){
            switch (args[0]){
                case "-t":
                    trazasActivadas();
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    return validarFichero(args[0], true);
            }
            return true;
        }
        else{
            return false;
        }
    }

    static void trazasActivadas(){
        System.out.println("SYSTEM: se han activado las trazas.");
        trazasActivas = true;
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
            FINDEPROGAMA = true;
            return false;
        }else{
            System.out.println("SYSTEM: se comprueba validez del fichero '"+arg+"'.");
            return validarFichero(arg, esEntrada);
        }
    }

    static boolean validarFichero(String nombre_fichero, Boolean esEntrada){
        System.out.println("SYSTEM: inicio de validación de fichero "+nombre_fichero+".");
        File fichero = new File(nombre_fichero);
        //¿Existe fichero?
        if(!fichero.exists()) {
            System.out.println("ERROR: el fichero no existe.");
            FINDEPROGAMA = true;
            return false;
        }
        //¿Es fichero válido?
        if(!fichero.isFile()) {
            System.out.println("ERROR: no es un fichero válido.");
            FINDEPROGAMA = true;
            return false;
        }
        //¿Se puede leer?
        if(!fichero.canRead()) {
            System.out.println("ERROR: el fichero no se puede leer.");
            FINDEPROGAMA = true;
            return false;
        }
        if(esEntrada) existeFicheroEntrada = true;
        else existeFicheroSalida = true;
        //Fichero válido
        return true;
    }

    static void validarDatos(String[] arrayDatos){
        System.out.println("SYSTEM: inicio de la validación de los datos.");

        //Número de objetos
        String regex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(arrayDatos[0]);

        if(matcher.find())
            System.out.println("SYSTEM: el número de objetos es "+arrayDatos[0]+".");
        else
            System.out.println("ERROR: no se ha introducido el número de objetos.");

        //Capacidad de la mochila
        regex = "^[0-9]+$";
        pattern = Pattern.compile(regex, Pattern.MULTILINE);
        matcher = pattern.matcher(arrayDatos[arrayDatos.length-1]);

        if(matcher.find())
            System.out.println("SYSTEM: la capacidad de la mochila es "+arrayDatos[arrayDatos.length-1]+".");
        else
            System.out.println("ERROR: no se ha introducido la capacidad de la mochila.");

        //El número de objetos coincide con lo indicado en la primera línea
        if(arrayDatos.length-2 == Integer.parseInt(arrayDatos[0]))
            System.out.println("SYSTEM: el número de objetos es coherente con lo indicado.");
        else
            System.out.println("ERROR: el número de objetos no cuadra con lo indicado.");

        //Comprobar validez de objetos
        regex = "^[0-9]+ +[0-9]+$";
        pattern = Pattern.compile(regex, Pattern.MULTILINE);

        for(int i=1; i< arrayDatos.length-2; i++){
            matcher = pattern.matcher(arrayDatos[i]);
            if(!matcher.find())
                System.out.println("ERROR: uno de los objetos no tiene el formato correcto.");
        }

        System.out.println("SYSTEM: fin de validación de los datos.");
    }

    static String leerFichero(String path){
        //Obtenemos el fichero
        String archivo = System.getProperty(path);
        File fichero = new File(archivo);

        String datos = "";
        try {
            Scanner lector = new Scanner(fichero);

            while(lector.hasNext())
                datos+=lector.nextLine()+"\n";

            System.out.println("LECTURA: \n"+datos);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return datos;
    }

}