public class mochila_voraz {

    static boolean existeFicheroEntrada = false;
    static boolean trazasActivas = false;
    static boolean FINDEPROGAMA = false;

    public static void main(String[] args) {
        System.out.println("\nSYSTEM: INICIO DE PROGRAMA MOCHILA_VORAZ\n\n");

        if(args.length > 3) {
            System.out.println("SYSTEM: Ha introducido "+(args.length-4)+" argumentos más de los permitidos.");
            mostrarAyuda();
            FINDEPROGAMA = true;
        }

        if(args.length == 0){
            System.out.println("SYSTEM: No se ha especificado fichero de entrada...se solicitarán los datos por entrada de teclado.");
            System.out.println("SYSTEM: No se ha especificado fichero de salida...se creará un fichero con nombre salida_mochila_voraz.txt.");

        }else{
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
        if(args.length == 3){
            //Primer argumento
            switch (args[0]){
                case "-t":
                    System.out.println("SYSTEM: se han activado las trazas.");
                    trazasActivas = true;
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    System.out.println("ERROR: argumento no válido.");
                    mostrarAyuda();
                    FINDEPROGAMA = true;
                    return;
            }
            //Segundo argumento
            switch (args[1]){
                case "-t":
                    System.out.println("SYSTEM: se han activado las trazas.");
                    trazasActivas = true;
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    System.out.println("ERROR: argumento no válido.");
                    mostrarAyuda();
                    FINDEPROGAMA = true;
                    return;
            }
            //Tercer argumento
            if(args[2].equals("-t") || args[2].equals("-h")){
                System.out.println("ERROR: tercer argumento no válido.");
                mostrarAyuda();
                FINDEPROGAMA = true;
                return;
            }else{
                System.out.println("SYSTEM: se comprueba validez del fichero de entrada '"+args[3]+"'.");
                if(!validarFichero(args[2], true)) {
                    FINDEPROGAMA = true;
                    return;
                }
            }
            //Cuarto argumento
            if(args[3].equals("-t") || args[3].equals("-h")){
                System.out.println("ERROR: cuarto argumento no válido.");
                mostrarAyuda();
                FINDEPROGAMA = true;
                return;
            }else{
                System.out.println("SYSTEM: se comprueba validez del fichero de salida '"+args[3]+"'.");
                if(!validarFichero(args[3], false)) {
                    FINDEPROGAMA = true;
                    return;
                }
            }



        }else if(args.length == 2){

        }

    }

    static boolean validarFichero(String nombre_fichero, Boolean esEntrada){
        //incluir codigo de validacion de fichero
        return true;
    }

}