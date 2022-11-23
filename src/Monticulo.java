import java.util.ArrayList;

public class Monticulo <Objeto extends Comparable<Objeto>>{

    private Objeto[] vector;

    /*
     * Si no se dispone de un array se inicializa la clase y el montículo vacío.
     */
    public Monticulo(int tamano){
        if(tamano < 1) throw new IllegalArgumentException("ERROR: el tamaño del montículo no puede ser menor a 1.");
        this.vector = crearMonticuloVacio(tamano);
    }

    /*
     * Transforma el array de entrada en un montículo.
     * El tamaño máximo no puede ser menor al del array de entrada.
     */
    public Monticulo (Objeto[] vector){
        if(vector.length < 1 ) throw new IllegalArgumentException("ERROR: el tamaño del montículo no puede ser menor a 1.");
        //Crea el montículo
        this.vector = creaMonticuloLineal(vector);
    }

    /*
    * Devuelve el monticulo generado en el constructor
    */
    public Objeto[] getMonticulo(){
        return this.vector;
    }

    /*
     * Devuelve un montículo vacio.
     */
    public Objeto[] crearMonticuloVacio(int tamano){
        return (Objeto[]) new Comparable[tamano];
    }

    /*
     * Devuelve true si el montículo está vacío.
     * El montículo está vacío si tiene un tamaño de 0 o no tiene elementos
     */
    public boolean elMonticuloEstaVacio(Objeto[] monticulo){
        if(monticulo.length == 0) return true;

        for(int i=0; i<monticulo.length; i++) if(monticulo[i] != null) return false;

        return true;
    }

    /*
     * Reubica el elemento i del vector en caso de que este sea mayor que el padre, hasta que esté correctamente
     * situado en el montículo y se haya restablecido la propiedad de montículo.
     * Se utiliza para la inserción de un elemento nuevo en el montículo.
     */
    public Objeto[] flotar(Objeto[] monticulo, int elemento){
        Objeto[] mont = crearMonticuloVacio(monticulo.length-1);
        int hijo  = elemento;
        int padre = elemento/2;
        //compareTO => negativo: menor    cero: igual    positivo: mayor
        while(hijo>1 && mont[padre].compareTo(mont[hijo])<0){
            Objeto tempPadre = mont[padre];
            mont[padre] = mont[hijo]; //Intercambiar posición padre<->hijo
            mont[hijo] = tempPadre;
            hijo = padre;
        }
        return mont;
    }

    /*
     * Reubica el elemento i del vector en caso de que ést sea menor que alguno de sus hijos.
     * En tal caso, intercambia su valor por el del mayor de sus hijos.
     */
    public Objeto[] hundir(Objeto[] monticulo, int elemento){
        Objeto[] mont = crearMonticuloVacio(monticulo.length-1);
        boolean continuar = true;
        int hijoIZQ;
        int hijoDRC;
        int padre = -1;
        int i = elemento;

        while(padre != i) {
            hijoIZQ = 2 * i;
            hijoDRC = (2 * i) + 1;
            padre = i;

            //compareTO => negativo: menor    cero: igual    positivo: mayor
            if ((hijoDRC < mont.length) && (mont[hijoDRC].compareTo(monticulo[i]) > 0))
                i = hijoDRC;

            if ((hijoIZQ < mont.length) && (mont[hijoIZQ].compareTo(monticulo[i]) > 0))
                i = hijoIZQ;

            mont = intercambiar(padre, i, mont);
            //if(padre == i) continuar = false;
        }

        return mont;
    }

    private Objeto[] intercambiar(int a, int b, Objeto[] monticulo){
        Objeto[] mont = crearMonticuloVacio(monticulo.length-1);
        Objeto temp = mont[a];
        mont[a] = mont[b];
        mont[b] = temp;
        return mont;
    }

    /*
     * Inserta un elemento en el montículo y lo flota hasta restaurar la propiedad de montículo.
     * Devuelve un montículo de n+1 elementos.
     */
    public Objeto[] insertar(Objeto objeto, Objeto[] monticulo){
        Objeto[] vTemp = crearMonticuloVacio(monticulo.length);
        for(int i=0; i<monticulo.length; i++)
            vTemp[i] = monticulo[i];
        vTemp[vTemp.length-1] = objeto;
        return flotar(vTemp, vTemp.length-1);
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
        //Se elimina cima, se pone el último elemento en cabeza y se recompone montículo con hundir.
        this.vector[0] = this.vector[this.vector.length-1];
        //Se crea array nuevo para reducir el tamaño del montículo y se pasan todos los elementos menos el último.
        Objeto[] vTemp = crearMonticuloVacio(this.vector.length-1);
        for(int i=0; i<this.vector.length-1; i++)
            vTemp[i] = this.vector[i];
        //Recomponer montículo mediante hundir.
        this.vector =hundir(vTemp,0);
        return cima;
    }

    public Objeto[] creaMonticuloLineal(Objeto[] vector){
        Objeto[] monticulo = crearMonticuloVacio(this.vector.length-1);
        for(int i=(vector.length/2); i<=0;i--)
            monticulo = hundir(monticulo, i);

        return monticulo;
    }

}
