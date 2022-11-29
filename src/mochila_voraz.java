import java.io.*;
import java.nio.file.FileSystemException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PEC1
 * CURSO: UNED PREDA 2022/2023
 * JDK: Oracle OpenJDK version 19
 * @author Asier Rodríguez
 * @version 1.0
 */
public class mochila_voraz {

    static boolean existeFicheroEntrada = false;
    static String ficheroEntrada = "";
    static boolean existeFicheroSalida = false;
    static String ficheroSalida = "";
    static boolean trazasActivas = false;
    static Mochila mochila;

    public static void main(String[] args) {
        System.out.println("\nSYSTEM: INICIO DE PROGRAMA MOCHILA_VORAZ\n\n");
        Locale.setDefault(Locale.ENGLISH);

        try {
            //Validaciones de argumentos de entrada
            if (args.length > 4) {
                mostrarAyuda();
                throw new IllegalArgumentException("ERROR: ha introducido " + (args.length - 4) + " argumentos más de los permitidos.");
            }
            if (args.length > 0) {
                if (!sonArgumentosValidos(args)) throw new IllegalArgumentException("ERROR: argumentos de entrada no válidos.");
            }

            if (!existeFicheroEntrada)
                System.out.println("SYSTEM: No se ha especificado fichero de entrada...se solicitarán los datos por entrada de teclado.");
            if (!existeFicheroSalida)
                System.out.println("SYSTEM: No se ha especificado fichero de salida...se creará un fichero con nombre salida_mochila_voraz.txt.");

            //Validaciones de datos de mochila
            if (existeFicheroEntrada) {
                //Lectura y validación de la entrada
                sonValidosDatosFichero(leerFichero(ficheroEntrada));
            } else {
                //Se solicita al usuario que introduzca la entrada por teclado
                esEntradaPorTecladoValida();
            }

            //Se resuelve el problema de la mochila con objetos fraccionable
            Mochila.ResultadoMochila[] resultado = mochila.mochilaObjetosFraccionables(mochila);

            //Selección de objetos
            System.out.println(Arrays.toString(resultado));

            //Salida de datos
            String salida = "";
            for (Mochila.ResultadoMochila res : resultado)
                if(res.peso != 0) salida += res+"\n";

            escribirFichero(salida);


        } catch (Exception iae) {
            gestionarMensajeError(iae);

        }


        System.out.println("\nSYSTEM: FIN DE PROGRAMA MOCHILA_VORAZ\n");


    }

    static <Excep extends Exception> void gestionarMensajeError(Excep e){
        System.out.println(e.getMessage().startsWith("ERROR: ")?e.getMessage()+"\n":"ERROR: error inesperado => "+e.getMessage()+"\n");
    }

    static boolean sonArgumentosValidos(String[] args){
        //Se utilizan 4 argumentos
        if(args.length == 4){
            //Primer argumento
            if(!esValidoArgumentoFichero(args[0], !existeFicheroEntrada)) return false;
            //Segundo argumento
            if(!esValidoArgumentoFichero(args[1], !existeFicheroEntrada)) return false;
            //Tercer argumento
            if(!esValidoArgumentoFichero(args[2], !existeFicheroEntrada)) return false;
            //Cuarto argumento
            return esValidoArgumentoFichero(args[3], !existeFicheroEntrada);
        }
        //Se utilizan 3 argumentos
        else if(args.length == 3){
            //Primer argumento
            if(!esValidoArgumento(args[0])) return false;
            //Segundo argumento
            switch (args[1]){
                case "-t":
                    trazasActivadas();
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    if(!esValidoArgumentoFichero(args[1], !existeFicheroEntrada)) return false;
                    break;
            }
            //Tercer argumento
            return esValidoArgumentoFichero(args[2], !existeFicheroEntrada);
        }
        //Se utilizan 2 argumentos
        else if(args.length == 2){
            //Primer argumento
            switch (args[0]){
                case "-t":
                    trazasActivadas();
                    break;
                case "-h":
                    mostrarAyuda();
                    break;
                default:
                    if(!esValidoArgumentoFichero(args[0], true)) return false;
                    break;
            }
            //Segundo argumento
            if(args[1].equals("-t")) trazasActivadas();
            else if(args[1].equals("-h")) mostrarAyuda();
            else return esValidoArgumentoFichero(args[1], !existeFicheroEntrada);

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

    static void mostrarAyuda(){
        System.out.println("SINTAXIS: mochila_voraz [-t] [-h] [fichero_entrada] [fichero_salida]\n");
        System.out.println("             -t: traza cada paso de manera que se describa la aplicación del algoritmo utilizado.");
        System.out.println("             -h: muestra una ayuda y la sintaxis del comando.");
        System.out.println("fichero_entrada: es el nombre del fichero del que se leen los datos de entrada.");
        System.out.println(" fichero_salida: es el nombre del fichero que se creará para almacenar la salida.\n");
    }

    static boolean esValidoArgumento(String arg){
        switch (arg){
            case "-t":
                trazasActivadas();
                return true;
            case "-h":
                mostrarAyuda();
                return true;
            default:
                return esValidoArgumentoFichero(arg, true);
        }
    }

    static boolean esValidoArgumentoFichero(String arg, Boolean esEntrada){
        if(arg.equals("-t")){
            trazasActivadas();
        }
        else if(arg.equals("-h")){
            mostrarAyuda();
        }
        else{
            return validarFichero(arg, esEntrada);
        }
        return true;
    }

    static boolean validarFichero(String nombre_fichero, Boolean esEntrada){
        System.out.println("SYSTEM: se comprueba fichero "+(esEntrada?"de entrada":"de salida")+" "+nombre_fichero+".");
        File fichero = new File(nombre_fichero);
        //¿Existe fichero?
        if(!fichero.exists()) {
            System.out.println("ERROR: el fichero no existe.");
            return false;
        }
        //¿Es fichero válido?
        if(!fichero.isFile()) {
            System.out.println("ERROR: no es un fichero válido.");
            return false;
        }
        //¿Se puede leer?
        if(esEntrada && !fichero.canRead()) {
            System.out.println("ERROR: el fichero no se puede leer.");
            return false;
        }
        //¿Se puede escribir?
        if(!esEntrada && !fichero.canWrite()) {
            System.out.println("ERROR: no se puede escribir en el fichero.");
            return false;
        }
        if(esEntrada) {
            existeFicheroEntrada = true;
            ficheroEntrada = nombre_fichero;

        }
        else{
            existeFicheroSalida = true;
            ficheroSalida = nombre_fichero;
        }
        //Fichero válido
        System.out.println("SYSTEM: el fichero "+(esEntrada?"de entrada":"de salida")+" se puede procesar.\n");
        return true;
    }

    static void sonValidosDatosFichero(String datos) throws FileSystemException {
        System.out.println("SYSTEM: inicio de la validación de los datos.");

        //Estructura del fichero
        Pattern pattern = Pattern.compile("^([0-9]+)\\s+((([0-9]+(\\.[0-9]+)?) ([0-9]+(?:\\.[0-9]+)?))\\s+)+([0-9]+(\\.[0-9]+)?)$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(datos);

        if(matcher.find())
            System.out.println("SYSTEM: el fichero está correctamente estructurado.");
        else
            throw new FileSystemException("""
                    ERROR: el fichero no está correctamente estructurado.
                    El número de objetos del conjunto debe estar en la primera linea del fichero y ser un número entero.
                    La capacidad de la mochila debe estar en la última linea del fichero.
                    Los objetos deben indicarse a partir de la primer linea del fichero. Cada linea tendrá el peso, un espacio y el beneficio.
                    El separador decimal debe ser un punto.
                    """);

        String[] arrayDatos = datos.split("\n");

        //Número de objetos
        pattern = Pattern.compile("^[0-9]+$", Pattern.MULTILINE);
        matcher = pattern.matcher(arrayDatos[0]);

        if(matcher.find())
            System.out.println("SYSTEM: el número de objetos es "+arrayDatos[0]+".");
        else
            throw new FileSystemException("ERROR: no se ha introducido correctamente el número de objetos => "+arrayDatos[0]);

        //Capacidad de la mochila
        pattern = Pattern.compile("^[0-9]+(?:\\.[0-9]+)?$", Pattern.MULTILINE);
        matcher = pattern.matcher(arrayDatos[arrayDatos.length-1]);

        if(matcher.find())
            System.out.println("SYSTEM: la capacidad de la mochila es "+arrayDatos[arrayDatos.length-1]+".");
        else
            throw new FileSystemException("ERROR: no se ha introducido correctamente la capacidad de la mochila => "+arrayDatos[arrayDatos.length-1]);

        //El número de objetos coincide con lo indicado en la primera línea
        if(arrayDatos.length-2 == Integer.parseInt(arrayDatos[0]))
            System.out.println("SYSTEM: el número de objetos es coherente con lo indicado.");
        else
            throw new FileSystemException("ERROR: el número de objetos no cuadra con lo indicado => num:"+arrayDatos[0]+" <> cap:"+(arrayDatos.length-2));

        //Datos mochila
        int cantidadObjetos    = arrayDatos.length-2;
        float capacidadMochila = Float.parseFloat(arrayDatos[arrayDatos.length-1]);
        float[] pesos          = new float[cantidadObjetos];
        float[] beneficios     = new float[cantidadObjetos];

        //Comprobar validez de objetos
        pattern = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?) ([0-9]+(?:\\.[0-9]+)?)$", Pattern.MULTILINE);

        for(int i=1; i<=cantidadObjetos; i++){
            matcher = pattern.matcher(arrayDatos[i]);
            if(!matcher.find()) {
                throw new FileSystemException("ERROR: el objeto "+(arrayDatos[i])+" no tiene el formato correcto.");
            }
            else {
                String[] grupo = matcher.group().split(" ");
                pesos[i-1]      = Float.parseFloat(grupo[0]);
                beneficios[i-1] = Float.parseFloat(grupo[1]);
            }
        }

        //Inicializar mochila
        mochila = new Mochila(cantidadObjetos, pesos, beneficios, capacidadMochila);

        System.out.println("SYSTEM: datos correctos. Fin de validación de los datos.\n");
    }

    static String leerFichero(String path) throws FileNotFoundException{
        //Obtenemos el fichero
        File fichero = new File(path);

        Scanner lector = new Scanner(fichero);
        StringBuilder datos = new StringBuilder();

        while(lector.hasNext())
            datos.append(lector.nextLine()).append("\n");

        System.out.println("SYSTEM: lectura de fichero de entrada => \n"+datos+"\n");

        return datos.toString();
    }

    static void escribirFichero(String salida) throws IOException {
        String path;
        FileOutputStream fos;
        if(existeFicheroSalida) {
            path = ficheroSalida;
            fos = new FileOutputStream(path);
        }
        else{
            path = System.getProperty("user.dir")+"\\"+"salida_mochila_voraz.txt";
            fos = new FileOutputStream(path,true);
        }

        StringBuilder datos = new StringBuilder();

        datos.append("\n.................... ");
        datos.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        datos.append(" ....................\n");
        datos.append("Salida producto de la ejecución de mochila_voraz:\n");
        datos.append(salida);

        fos.write(datos.toString().getBytes());
        fos.close();
    }

    static void esEntradaPorTecladoValida() throws IOException{

        Scanner entrada = new Scanner(System.in);

        System.out.println("\nSYSTEM: inicio entrada por teclado...");

        boolean entradaErronea = true;
        int cantidad    = 0;
        float capacidad = 0;
        float[] pesos;
        float[] beneficios;

        //Cantidad de tipo de objetos
        while(entradaErronea) {
            try {
                System.out.println("SYSTEM: introduzca la cantidad de objetos.");
                cantidad = entrada.nextInt();
                if(cantidad <= 0) throw new Exception("ERROR: no ha introducido un número entero mayor a cero.");
                entradaErronea = false;
                System.out.println("SYSTEM: cantidad de objetos => "+cantidad);
                entrada.nextLine();
            } catch (Exception e) {
                decidirSiFinalizarEjecucion(entrada, e);
            }
        }

        pesos      = new float[cantidad];
        beneficios = new float[cantidad];

        int i = 0;

        //Objetos posibles
        while(i < cantidad){
            try {
                System.out.println(("SYSTEM: introduzca el peso del objeto ("+(i+1)+"/"+cantidad+")"));
                float peso = entrada.nextFloat();
                if(peso <= 0) throw new Exception("ERROR: peso menor o igual a cero.");
                pesos[i] = peso;
                System.out.println(("SYSTEM: introduzca el beneficio del objeto ("+(i+1)+"/"+cantidad+")"));
                float beneficio = entrada.nextFloat();
                if(beneficio < 0) throw new Exception("ERROR: beneficio menor a cero.");
                beneficios[i] = beneficio;
                System.out.println("SYSTEM: se ha introducido un objeto con peso "+peso+" y beneficio "+beneficio+" ("+(i+1)+"/"+cantidad+")");
                i++;
                entrada.nextLine();
            } catch (Exception e) {
                decidirSiFinalizarEjecucion(entrada, e);
            }
        }

        entradaErronea = true;

        //Capacidad mochila
        while(entradaErronea){
            try {
                System.out.println("SYSTEM: introduce la capacidad de la mochila.");
                float cap = entrada.nextFloat();
                if(cap <= 0) throw new Exception("ERROR: no ha introducido un número entero mayor a cero.");
                capacidad= cap;
                entradaErronea = false;
                System.out.println("SYSTEM: la capacidad de la mochila es => "+capacidad);
            } catch (Exception e) {
                decidirSiFinalizarEjecucion(entrada, e);
            }
        }

        //Inicializamos la mochila
        mochila = new Mochila(cantidad, pesos, beneficios, capacidad);

        System.out.println("SYSTEM: los datos de la mochila son => ");
        System.out.println("objetos: "+mochila.getCantidadObjetos());
        for (int e=0; e<mochila.getCantidadObjetos(); e++)
            System.out.println("peso: "+mochila.getPesosBeneficios()[e].peso+" beneficio: "+mochila.getPesosBeneficios()[e].beneficio);
        System.out.println("capacidad: "+mochila.getCapacidad());

        System.out.println("SYSTEM: fin de entrada por teclado.\n");
    }

    static void decidirSiFinalizarEjecucion(Scanner entrada, Exception e) throws IOException {
        entrada.nextLine();
        System.out.println((e.getMessage() == null || e.getMessage().contains("null"))?"ERROR: no ha introducido un número. Para introducir un decimal use el punto.":e.getMessage()+"\n");
        System.out.println("SYSTEM: si desea finalizar el programa escriba SI.");
        String opcion = entrada.nextLine();
        if(opcion.equalsIgnoreCase("SI")){
            entrada.close();
            throw new IOException("ERROR: se ha interrumpido la entrada de datos, se finaliza la ejecución del programa.\n");
        }
        entrada.nextLine();
    }

    static void trazar(String traza){
        if(trazasActivas) System.out.println("TRAZA: "+traza+".");
    }

}

class Mochila {

    private final int cantidadObjetos;
    private PesoBeneficio[] pesosBeneficios;
    private final float capacidad;

    public Mochila(int cantidad_de_objetos, float[] pesos_de_objetos, float[] beneficios_de_objetos, float capacidad_de_mochila) {
        this.cantidadObjetos = cantidad_de_objetos;
        this.capacidad       = capacidad_de_mochila;

        this.pesosBeneficios = new PesoBeneficio[cantidad_de_objetos];

        for(int i=0; i<pesos_de_objetos.length; i++)
            this.pesosBeneficios[i] = new PesoBeneficio(pesos_de_objetos[i], beneficios_de_objetos[i]);

    }

    public ResultadoMochila[] mochilaObjetosFraccionables(Mochila mochila){

            mochila_voraz.trazar("inicio del algoritmo");

            PesoBeneficio[] mchPB = mochila.getPesosBeneficios();
            PesoBeneficio pb;
            int contador    = 0;
            float peso      = 0;
            float capacidad = mochila.getCapacidad();
            float beneficioTotal = 0;

            ResultadoMochila[] res = new ResultadoMochila[this.cantidadObjetos];

            // Se inicia montículo con una referencia al tipo de datos.
            mochila_voraz.trazar("se inicia montículo");
            Monticulo<Mochila.PesoBeneficio> mont = new Monticulo(mchPB[0]);

            // Se ordenan los objetos en orden decreciente
            mochila_voraz.trazar("se ordenan los objetos introducidos por "+(mochila_voraz.existeFicheroSalida?"fichero":"teclado"));
            mont.heapShort(mchPB);

            // Se inicia a ceros el resultado
            mochila_voraz.trazar("se inicia a ceros el resultado");
            for(int i=0; i<res.length; i++)
                res[i] = new ResultadoMochila(0,0,0);

            // Bucle principal
            mochila_voraz.trazar("inicio de bucle principal");
            while(peso < capacidad){
                pb = mont.obtenerCima(mchPB);
                mochila_voraz.trazar("iteración "+contador+" -> se obtiene de la cima del montículo el objeto de peso => "+pb.peso);
                if( (peso + pb.peso) < capacidad ){
                    mochila_voraz.trazar("máximo de capacidad no alcanzado, objeto no fraccionado");
                    res[contador] = new ResultadoMochila(pb.peso, 1, pb.beneficio);
                    peso += pb.peso;
                    beneficioTotal += pb.beneficio;
                }
                else{
                    mochila_voraz.trazar("máximo de capacidad alcanzado, objeto fraccionado");
                    res[contador] = new ResultadoMochila(pb.peso, ((capacidad-peso)/pb.peso), (((capacidad-peso)/pb.peso))*pb.beneficio);
                    peso = capacidad;
                    beneficioTotal += res[contador].beneficio;
                }

                mochila_voraz.trazar("se incorpora a la solución el objeto => peso:"+res[contador].peso+"  fracción:"+res[contador].fraccion+"  beneficio:"+res[contador].beneficio);
                mochila_voraz.trazar("peso actualizado a => "+peso+"    beneficio total => "+beneficioTotal);

                contador++;
            }

        return res;
    }

    class ResultadoMochila{
        float peso;
        float fraccion;
        float beneficio;

        public ResultadoMochila(float peso, float fraccion, float beneficio){
            this.peso      = peso;
            this.fraccion  = fraccion;
            this.beneficio = beneficio;
        }

        @Override
        public String toString(){
            return this.peso+" "+this.fraccion+" "+this.beneficio;
        }

    }

    class PesoBeneficio implements Comparable{
        float peso;
        float beneficio;
        float ratio;

        public PesoBeneficio(float peso, float beneficio){
            this.peso      = peso;
            this.beneficio = beneficio;
            this.ratio     = beneficio/peso;
        }

        @Override
        public int compareTo(Object o) {
            PesoBeneficio pb = (PesoBeneficio) o;
            float ratio = pb.beneficio/pb.peso;

            if(this.ratio > ratio) return 1;
            else if(this.ratio == ratio) return 0;
            else return -1;
        }

        @Override
        public String toString(){
            return this.peso+" "+this.beneficio;
        }
    }

    public PesoBeneficio[] getPesosBeneficios(){
        return this.pesosBeneficios;
    }

    public float getCapacidad(){
        return this.capacidad;
    }

    public int getCantidadObjetos(){
        return cantidadObjetos;
    }

}