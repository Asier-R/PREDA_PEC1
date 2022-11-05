import java.io.File;

public class MainPruebas {
    public static void main(String[] args) {

        pruebas_de_ficheros();


    }

    static void pruebas_de_ficheros(){
        File fichero = new File(System.getProperty("user.dir")+"\\FICHEROS\\"+"ficheroPruebas.txt");
        System.out.println("Path => "+fichero.getPath());
        System.out.println("Existe fichero => "+fichero.exists());
        System.out.println("Es fichero => "+fichero.isFile());
        System.out.println("........................................................................................");

        File fichero2 = new File(System.getProperty("user.dir")+"\\FICHEROS\\"+"ficheroVacio.txt");
        System.out.println("Path => "+fichero2.getPath());
        System.out.println("Existe fichero => "+fichero2.exists());
        System.out.println("Es fichero => "+fichero2.isFile());
        System.out.println("........................................................................................");

        File fichero3 = new File(System.getProperty("user.dir")+"\\FICHEROS\\"+"JG1.jpg");
        System.out.println("Path => "+fichero3.getPath());
        System.out.println("Existe fichero => "+fichero3.exists());
        System.out.println("Es fichero => "+fichero3.isFile());
        System.out.println("........................................................................................");

        File fichero4 = new File(System.getProperty("user.dir")+"\\FICHEROS\\"+"carpetaFichero");
        System.out.println("Path => "+fichero4.getPath());
        System.out.println("Existe fichero => "+fichero4.exists());
        System.out.println("Es fichero => "+fichero4.isFile());
        System.out.println("........................................................................................");

        File fichero5 = new File(System.getProperty("user.dir")+"\\FICHEROS\\"+"Nuevo Pres.odp");
        System.out.println("Path => "+fichero5.getPath());
        System.out.println("Existe fichero => "+fichero5.exists());
        System.out.println("Es fichero => "+fichero5.isFile());
        System.out.println("........................................................................................");

        File fichero6 = new File(System.getProperty("user.dir")+"\\FICHEROS\\"+"Nuevo Texto.odt");
        System.out.println("Path => "+fichero6.getPath());
        System.out.println("Existe fichero => "+fichero6.exists());
        System.out.println("Es fichero => "+fichero6.isFile());
        System.out.println("........................................................................................");
    }
}
