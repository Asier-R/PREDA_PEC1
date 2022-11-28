import com.sun.security.jgss.GSSUtil;

import java.io.File;
import java.io.FileNotFoundException;
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

        /*String fichero = "C:\\Users\\Monst\\Desktop\\UNED\\Asignaturas\\2022-2023\\Programacion y Estructuras de Datos Avanzadas\\PRACTICA 1\\PREDA_PEC1\\FICHEROS\\ficheroPruebas.txt";

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
        });*/

        //mochila_voraz.entradaPorTeclado();

        //String[] sp = ("10\n10 43\n2 2.3\n20").split("\n");
        //for(int i=0; i<sp.length; i++)
        //    System.out.println(sp[i]);

        //float p = Float.parseFloat("8.2");
        //System.out.println(p);

        try {
            String fichero = "C:\\Users\\Monst\\Desktop\\UNED\\Asignaturas\\2022-2023\\Programacion y Estructuras de Datos Avanzadas\\PRACTICA 1\\PREDA_PEC1\\FICHEROS\\ficheroPruebas.txt";
            mochila_voraz.main(new String[] {"-t",fichero});
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }



        /*float[] pesos      = new float[]{2,2,4,5,1};
        float[] beneficios = new float[]{2,2,4,5,1};
        int cantidad       = 5;
        float capacidad    = 20;

        Mochila mochi = new Mochila(cantidad, pesos, beneficios, capacidad);
        Monticulo<Mochila.PesoBeneficio> monte = new Monticulo(mochi.getPesosBeneficios()[0]);
        System.out.println(Arrays.toString(mochi.getPesosBeneficios()));
        monte.heapShort(mochi.getPesosBeneficios());
        System.out.println(Arrays.toString(mochi.getPesosBeneficios()));*/



    }

}
