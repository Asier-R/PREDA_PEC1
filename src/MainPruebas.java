import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainPruebas {
    public static void main(String[] args) {
        /*System.out.println(System.getProperty("user.home"));
        String archivo = System.getProperty("user.dir")+"\\FICHEROS\\"+"ficheroPruebas.txt";
        File fichero = new File(archivo);

        pruebas_de_ficheros(fichero);
        validarDatos(leerFichero(fichero));*/


        String fichero = "C:\\Users\\Monst\\Desktop\\UNED\\Asignaturas\\2022-2023\\Programacion y Estructuras de Datos Avanzadas\\PRACTICA 1\\PREDA_PEC1\\FICHEROS\\ficheroPruebas.txt";

        ArrayList<String[]> casosPrueba = new ArrayList<>(100);

        casosPrueba.add(new String[] {fichero,fichero,"-t","-t"});
        casosPrueba.add(new String[] {fichero,fichero,fichero,"-t"});
        casosPrueba.add(new String[] {fichero,fichero,"-h",fichero});
        casosPrueba.add(new String[] {fichero,fichero,fichero,fichero});

        casosPrueba.add(new String[] {"-t",fichero,"-t","-t"});
        casosPrueba.add(new String[] {fichero,"-h","-t","-t"});
        casosPrueba.add(new String[] {"-t","-h","-t","-t"});




        casosPrueba.forEach(caso -> {
            System.out.println("CASO: "+ Arrays.stream(caso).toList().toString());
            mochila_voraz.main(caso);
            mochila_voraz.existeFicheroEntrada = false;
            mochila_voraz.existeFicheroSalida = false;
            mochila_voraz.trazasActivas = false;
            mochila_voraz.FINDEPROGAMA = false;
            System.out.println("-----------------------------------------------------------------------------------");
        });

    }

    static void validarDatos(String datos){
        System.out.println("SYSTEM: inicio de la validación de los datos.");
        String[] arrayDatos = datos.split("\n");

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
        matcher = pattern.matcher(arrayDatos[arrayDatos.length-1]);

        for(int i=1; i< arrayDatos.length-2; i++){
            matcher = pattern.matcher(arrayDatos[i]);
            if(!matcher.find())
                System.out.println("ERROR: uno de los objetos no tiene el formato correcto.");
        }

        System.out.println("SYSTEM: fin de validación de los datos.");
    }

    static String leerFichero(File fichero){
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

    static void pruebas_de_ficheros(File fichero){

        System.out.println("Path => "+fichero.getPath());
        System.out.println("Existe fichero => "+fichero.exists());
        System.out.println("Es fichero => "+fichero.isFile());

        System.out.println("File name: " + fichero.getName());
        System.out.println("Absolute path: " + fichero.getAbsolutePath());
        System.out.println("Writeable: " + fichero.canWrite());
        System.out.println("Readable " + fichero.canRead());
        System.out.println("File size in bytes " + fichero.length());
    }
}
