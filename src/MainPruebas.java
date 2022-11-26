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

        /*try {
            String fichero = "C:\\Users\\izask\\Desktop\\UNED\\Asignaturas\\2022-2023\\Programacion y Estructuras de Datos Avanzadas\\PRACTICA 1\\PREDA_PEC1\\FICHEROS\\ficheroPruebas.txt";
            mochila_voraz.main(new String[] {"-t","-h",fichero});
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }*/

        /*Integer aa = 1;
        Integer bb = 2;
        Integer cc = null;
        //compareTO => negativo: menor    cero: igual    positivo: mayor
        boolean buu = aa.compareTo(bb)>0;
        System.out.println("aa > bb: "+buu);
        buu = aa.compareTo(cc)>0;
        System.out.println("aa > bb: "+buu);

        String[] bobo = new String[0];
        System.out.println(bobo.length);
        System.out.println(bobo[0]);
        */

        /*
        Integer[] bbb = new Integer[]{2,3,4,5};
        Integer[] ccc = new Integer[10];
        System.arraycopy(bbb,0,ccc,1,bbb.length);
        System.out.println();
        */

        Integer[] bbb = new Integer[]{2,3,4,5};
        Integer[] ccc;
        Integer[] ddd;
        Integer a = 0;

        Monticulo<Integer> monte = new Monticulo(a);
        /*System.out.println("INI  "+Arrays.toString(bbb));
        monte.flotar(bbb,3);
        System.out.println("Flotar 3: "+Arrays.toString(bbb));
        ccc = monte.insertar(15,bbb);
        System.out.println("Insertar 15: "+Arrays.toString(ccc));
        ddd = monte.insertar(1,ccc);
        System.out.println("Insertar 1: "+Arrays.toString(ddd));
        System.out.println("FIN  "+Arrays.toString(ddd));
        monte.mostrarCima(ddd);
        System.out.println(monte.mostrarCima(ddd));*/

        bbb = new Integer[]{null, 2,3,4,5};
        //bbb = new Integer[]{4,6,4,5,1,3,2};
        //monte.hundir(bbb,0);
        //System.out.println(Arrays.toString(bbb));
        // monte.hundir(bbb,0);
        // System.out.println(Arrays.toString(bbb));
        // monte.hundir(bbb,0);
        //System.out.println(Arrays.toString(bbb));
        //System.out.println("INI "+Arrays.toString(bbb));
        //monte.creaMonticuloLineal(bbb);
        //System.out.println("FIN  "+Arrays.toString(bbb));
        //monte.obtenerCima(bbb);
        //System.out.println(Arrays.toString(bbb));

        monte.heapShort(bbb);
        System.out.println(Arrays.toString(bbb));



    }

}
