public class mochila_voraz {
    public static void main(String[] args) {
        System.out.println("System: INICIO DE PROGRAMA MOCHILA_VORAZ");

        if(args.length != 0)
            System.out.println("Primer argumento: "+args[0]);

        boolean existeFicheroEntrada = false;
        boolean demasiadosParametros = false;

        if(args.length == 0){
            System.out.println("System: No se ha especificado fichero de entrada...se solicitarán los datos por entrada de teclado.");
            System.out.println("System: No se ha especificado fichero de salida...se creará un fichero con nombre salida_mochila_voraz.txt.");
        }

        if(args.length > 3) {
            System.out.println("System: Ha introducido "+(args.length-3)+" más argumentos de los permitidos.");
            System.out.println("System: Los argumentos permitidos son los siguientes:");
            System.out.println("             -t: traza cada paso de manera que se describa la aplicación del algoritmo utilizado.");
            System.out.println("             -h: muestra una ayuda y la sintaxis del comando.");
            System.out.println("fichero_entrada: es el nombre del fichero del que se leen los datos de entrada.");
            System.out.println(" fichero_salida: es el nombre del fichero que se creará para almacenar la salida.\n");
        }

// ----------------------------------------
        if(args.length != 0){
            if (args.length < 4){
                for (String parametro:args) {
                    switch (parametro){
                        case "-t":
                    }
                }

            }else{

            }

        }else{
            System.out.println("System: No se ha especificado fichero de entrada...se solicitarán los datos por entrada de teclado.");
            System.out.println("System: No se ha especificado fichero de salida...se creará un fichero con nombre salida_mochila_voraz.txt.");
        }

    }
}