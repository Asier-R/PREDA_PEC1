import java.util.ArrayList;

public class Monticulo <Objeto extends Comparable<Objeto>>{

    private ArrayList<Objeto> vector;
    private final int tamanoMaximo;

    /*
     * Si no se dispone de un array se inicializa la clase y el montículo vacío.
     * Si el tamaño es menor de 1, el montículo no tendrá tamaño máximo.
     */
    public Monticulo(int tamanoMaximo){
        this.vector = crearMonticuloVacio();
        if(tamanoMaximo < 1) this.tamanoMaximo = 0;
        else this.tamanoMaximo = tamanoMaximo;
    }

    /*
     * Transforma el array de entrada en un montículo.
     * Si el tamaño es menor de 1, el montículo no tendrá tamaño máximo.
     */
    public  Monticulo (int tamanoMaximo, Objeto[] objetos){
        mochila_voraz.trazar("INICIO DE CONSTRUCTOR MONTICULO");

        if(tamanoMaximo < 1) this.tamanoMaximo = 0;
        else this.tamanoMaximo = tamanoMaximo;

        //Crea el montículo
        this.vector = new ArrayList<Objeto>(objetos.length);
        for (Objeto nodo: objetos) vector.add(nodo); //MODIFICAR ESTO POR LA FUNCIÓN OPTIMA

    }

    /*
     * Devuelve un montículo vacio.
     */
    public ArrayList<Objeto> crearMonticuloVacio(){
        return new ArrayList<>();
    }

    /*
     * Devuelve true si el montículo está vacío.
     */
    public boolean elMonticuloEstaVacio(){
        if(this.vector.size() == 0) return true;

        for(int i=0; i<this.vector.size(); i++) if(this.vector.get(i) != null) return false;

        return true;
    }

    /*
     * Reubica el elemento i del vector en caso de que ést sea mayor que el padre.
     */
    public void flotar(int elemento){

    }

    /*
     * Reubica el elemento i del vector en caso de que ést sea menor que alguno de sus hijos.
     * En tal caso, intercambia su valor por el del mayor de sus hijos.
     */
    public void hundir(int elemento){

    }

    /*
     * Inserta un elemento en el montículo y lo flota hasta restaurar la propiedad de montículo.
     */
    public void insertar(){

    }

    /*
     * Devuelve la cima del montículo sin modificarlo.
     */
    public Objeto mostrarCima(){
        return this.vector.get(0);
    }

    /*
     * Devuelve la cima del montículo, la elimina y recompone la propiedad de montículo.
     */
    public Objeto obtenerCima(){
        Objeto cima = mostrarCima();
        this.vector.remove(0);
        //Recomponer montículo


        return cima;
    }

}
