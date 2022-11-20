import java.util.ArrayList;

public class Monticulo <Objeto extends Comparable<Objeto>>{

    private Objeto[] vector;
    private final int tamanoMaximo;

    /*
     * Si no se dispone de un array se inicializa la clase y el montículo vacío.
     */
    public Monticulo(int tamanoMaximo){
        if(tamanoMaximo < 1) throw new IllegalArgumentException("ERROR: el tamaño del montículo no puede ser menor a 1.");
        else this.tamanoMaximo = tamanoMaximo;
        crearMonticuloVacio();
    }

    /*
     * Transforma el array de entrada en un montículo.
     * El tamaño máximo no puede ser menor al del array de entrada.
     */
    public Monticulo (int tamanoMaximo, Objeto[] objetos){
        mochila_voraz.trazar("PRUEBAS BORRAR ESTO"); //BORRAR SOLO ES PARA PRUEBAS

        if(tamanoMaximo < objetos.length) throw new IllegalArgumentException("ERROR: el tamaño del montículo no puede ser menor a 1.");
        else this.tamanoMaximo = tamanoMaximo;

        //Crea el montículo
        //for (Objeto nodo: objetos) vector.add(nodo); //MODIFICAR ESTO POR LA FUNCIÓN OPTIMA

    }

    /*
     * Devuelve un montículo vacio.
     */
    public void crearMonticuloVacio(){
        this.vector = (Objeto[]) new Comparable[0];
    }

    /*
     * Devuelve true si el montículo está vacío.
     * El montículo está vacío si tiene un tamaño de 0 o no tiene elementos
     */
    public boolean elMonticuloEstaVacio(){
        if(this.vector.length == 0) return true;

        for(int i=0; i<this.vector.length; i++) if(this.vector[i] != null) return false;

        return true;
    }

    /*
     * Reubica el elemento i del vector en caso de que este sea mayor que el padre, hasta que esté correctamente
     * situado en el montículo y se haya restablecido la propiedad de montículo.
     * Se utiliza para la inserción de un elemento nuevo en el montículo.
     */
    public void flotar(int elemento){
        int hijo  = elemento;
        int padre = elemento/2;
        //compareTO => negativo: menor    cero: igual    positivo: mayor
        while(hijo>1 && this.vector[padre].compareTo(this.vector[hijo])<0){
            Objeto tempPadre = this.vector[padre];
            this.vector[padre] = this.vector[hijo]; //Intercambiar posición padre<->hijo
            this.vector[hijo] = tempPadre;
            hijo = padre;
        }
    }

    /*
     * Reubica el elemento i del vector en caso de que ést sea menor que alguno de sus hijos.
     * En tal caso, intercambia su valor por el del mayor de sus hijos.
     */
    public void hundir(int elemento){
        //Tener en cuenta que se puede haber usado obtenerCima()
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
        return this.vector[0];
    }

    /*
     * Devuelve la cima del montículo, la elimina y recompone la propiedad de montículo.
     */
    public Objeto obtenerCima(){
        Objeto cima = mostrarCima();
        //Se elimina cima y se recompone montículo con hundir, teniendo en cuenta que puede haber nulo.
        this.vector[0] = null;
        //Recomponer montículo mediante hundir.
        hundir(0);
        return cima;
    }

}
