import java.lang.reflect.Array;

/**
 * @author Asier Rodríguez
 * @version 1.0
 */
public class Monticulo <T extends Comparable>{

    /**
     * Variable auxiliar utilizada para poder instanciar montículos.
     */
    private T clase;//Se utiliza para instanciar array con genéricos.

    /**
     * Variable array que contendrá el montículo de tipo T.
     */
    private T[] vector;

    /**
     * Si no se dispone de un array se inicializa la clase y el montículo vacío.
     * @param clase instancia de la clase Comparable que se va a utilizar para crear el montículo.
     */
    public Monticulo(T clase){
        this.clase = clase;
        this.vector = crearMonticuloVacio();
    }

    /**
     * Transforma el array de entrada en un montículo.
     * El tamaño máximo no puede ser menor al del array de entrada.
     * @param clase instancia de la clase Comparable que se va a utilizar para crear el montículo.
     * @param vector vector a partir del cual se va a crear el montículo.
     */
    public Monticulo (T clase, T[] vector){
        if(vector.length < 1 ) throw new IllegalArgumentException("ERROR: el tamaño del montículo no puede ser menor a 1.");
        this.clase = clase;
        //Crea el montículo
        this.vector = creaMonticuloLineal(vector);
    }

    /**
     * @return montículo generado en el constructor.
     */
    public T[] getMonticulo(){
        return this.vector;
    }

    /**
     * Si no se indica tamaño, se creará de tamaño 1. Utilizar insertar para añadir nuevos elementos.
     * @return una nueva instancia de un montículo vacío.
     */
    public T[] crearMonticuloVacio(){
        return (T[]) Array.newInstance(clase.getClass(),1);
    }

    /**
     * @param tamano tamaño del montículo a crear. Si no se indica tamaño, se creará de tamaño 1.
     * @return una nueva instancia de un montículo de tamaño n.
     */
    public T[] crearMonticuloVacio(int tamano){
        return (T[]) Array.newInstance(clase.getClass(),tamano);
    }

    /**
     * El montículo está vacío si tiene un tamaño de 0 o no tiene elementos.
     * @return true si montículo vacío.
     */
    public boolean elMonticuloEstaVacio(T[] monticulo){
        if(monticulo.length == 0) return true;

        for(int i=0; i<monticulo.length; i++) if(monticulo[i] != null) return false;

        return true;
    }

    /**
     * Reubica el elemento i del vector en caso de que este sea mayor que el padre, hasta que esté correctamente
     * situado en el montículo y se haya restablecido la propiedad de montículo.
     * Se utiliza para la inserción de un elemento nuevo en el montículo.
     * @param monticulo montículo sobre el que se realizará la acción flotar.
     * @param elemento posición en el montículo del elemento sobre el que se realizará la acción flotar.
     */
    public void flotar(T[] monticulo, int elemento){
        int hijo  = elemento;
        int padre = elemento/2;
        //compareTO => negativo: menor    cero: igual    positivo: mayor
        while(hijo>0 && monticulo[padre].compareTo(monticulo[hijo])<0){
            T tempPadre = monticulo[padre];
            monticulo[padre] = monticulo[hijo]; //Intercambiar posición padre<->hijo
            monticulo[hijo]  = tempPadre;
            hijo = padre;
            padre = hijo/2;
        }
    }

    /**
     * Reubica el elemento i del vector en caso de que ést sea menor que alguno de sus hijos.
     * En tal caso, intercambia su valor por el del mayor de sus hijos.
     * @param monticulo montículo sobre el que se realizará la acción hundir.
     * @param elemento posición en el montículo del elemento sobre el que se realizará la acción hundir.
     */
    public void hundir(T[] monticulo, int elemento){
        int hijoIZQ;
        int hijoDRC;
        int padre = -1;
        int i = elemento;

        while(padre != i) {
            hijoIZQ = 2 * i;
            hijoDRC = (2 * i) + 1;
            padre = i;

            //compareTO => negativo: menor    cero: igual    positivo: mayor
            if ((hijoDRC < monticulo.length) && (monticulo[hijoDRC].compareTo(monticulo[i]) > 0))
                i = hijoDRC;

            if ((hijoIZQ < monticulo.length) && (monticulo[hijoIZQ].compareTo(monticulo[i]) > 0))
                i = hijoIZQ;

            intercambiar(padre, i, monticulo);
        }

    }

    /**
     * Intercambia de posición a dos elementos de un montículo.
     * @param a posición en el montículo del primer elemento.
     * @param b posición en el montículo del segundo elemento.
     * @param monticulo montículo sobre el que se realiza el intercambio.
     */
    private void intercambiar(int a, int b, T[] monticulo){
        T temp = monticulo[a];
        monticulo[a] = monticulo[b];
        monticulo[b] = temp;
    }

    /**
     * Inserta un elemento en el montículo y lo flota hasta restaurar la propiedad de montículo.
     * @param elemento elemento a insertar en montículo.
     * @param monticulo montículo sobre el que se realiza la acción insertar.
     * @return montículo de tamaño n+1 con el elemento nuevo.
     */
    public T[] insertar(T elemento, T[] monticulo){
        T[] vTemp = crearMonticuloVacio(monticulo.length+1);
        System.arraycopy(monticulo,0,vTemp,0,monticulo.length); //Coste temporal: O(n)
        vTemp[vTemp.length-1] = elemento;
        flotar(vTemp, vTemp.length-1);
        monticulo = vTemp;
        return monticulo;
    }

    /**
     * Devuelve la cima del montículo sin modificarlo.
     * @param monticulo montículo sobre el que se realizará la acción mostrarCima.
     * @return primer elemento del montículo.
     */
    public T mostrarCima(T[] monticulo){
        return monticulo[0];
    }

    /**
     * Devuelve la cima del montículo, la elimina y recompone la propiedad de montículo.
     * @param monticulo montículo sobre el que se realizará la acción obtenerCima.
     * @return cima del montículo.
     */
    public T obtenerCima(T[] monticulo){
        T cima = mostrarCima(monticulo); //Guardamos la cima
        //Se elimina cima, se pone el último elemento en cabeza y se recompone montículo con hundir.
        T[] vTemp = crearMonticuloVacio(monticulo.length-2);//Array de n-1 posiciones, coste temporal: O(n)
        monticulo[0] = monticulo[monticulo.length-1];
        System.arraycopy(monticulo,0,vTemp,0,monticulo.length-1);
        //Recomponer montículo mediante hundir.
        hundir(monticulo,0);
        return cima;
    }

    /**
     * Crea y devuelve un montículo a partir de un vector.
     * @param vector array sobre el que se realizará la acción creaMonticuloLineal.
     * @return montículo creado a partir de vector de entrada.
     */
    public T[] creaMonticuloLineal(T[] vector){
        T[] monticulo = crearMonticuloVacio(vector.length-1);
        for(int i=(vector.length/2); i<=0;i--)
            hundir(vector, i);

        return monticulo;
    }

    /**
     * Recibe un vector y lo devuelve con los elementos ordenados de mayor a menor.
     * @param vector vector sobre el que se realizará la acción heapShort.
     * @return vector ordenado de mayor a menor.
     */
    public T[] heapShort(T[] vector){



        return null;
    }
}
