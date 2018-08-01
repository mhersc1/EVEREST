package pe.com.everest.adtprogram;

import java.util.Arrays;

/**
 * Created by MHER_ on 08/04/2018.
 */
public class ADTHeapHandler extends GenericADT {
    private Persona[] personas;
    private int size;

    public ADTHeapHandler() {
        //we use two terms for binary heaps
        //one of them is capacity: length
        //and another is people's amount named size
        personas = new Persona[8];
        size = 0;
    }

    @Override
    void ordenar(ORDEREnum OREnum) {

    }

    @Override
    void agregar(Persona persona) {
        if (size == this.personas.length)
            increaseCapacity();

        int curr = size;
        int parent;
        personas[size] = persona;
        size++;

        //We compare trough age between parent and child
        while (curr > 0) {
            parent = (curr - 1) / 2;
            if (Integer.compare(personas[parent].getEdad(), persona.getEdad()) <= 0)
                break;
            personas[curr] = personas[parent];
            curr = parent;
        }
        personas[curr] = persona;
    }

    @Override
    void actualizar(Persona persona) {
        if (size == 0)
            System.out.println("El binary heap esta vacio!!");
        size --;

    }

    @Override
    void eliminar(Persona persona) {

    }

    @Override
    void busqueda(int dni) {

    }

    @Override
    void mostrar() {

    }

    private void increaseCapacity() {
        int capacity = this.personas.length * 2;
        this.personas = Arrays.copyOf(this.personas, capacity);
    }
}
