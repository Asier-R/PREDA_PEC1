public class Mochila {

    private int cantidadObjetos;
    private float[] pesos;
    private float[] beneficios;
    private float capacidad;

    public Mochila(int cantidad_de_objetos){
        this.cantidadObjetos = cantidad_de_objetos;
        this.pesos = new float[cantidad_de_objetos];
        this.beneficios = new float[cantidad_de_objetos];
    }

    public float[] getPesos(){
        return this.pesos;
    }

    public float[] getBeneficios(){
        return this.beneficios;
    }

    public void setCapacidad(float volumen_mochila){
        this.capacidad = volumen_mochila;
    }

    public float getCapacidad(){
        return this.capacidad;
    }

}
