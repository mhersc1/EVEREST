package pe.com.everest.adtprogram;

import java.util.*;

/**
 * Created by MHER_ on 17/03/2018.
 */
public class ADTMapHandler extends GenericADT {
    private Map<Integer, Persona> personaMap;

    public ADTMapHandler() {
        this.personaMap = new Hashtable<>();
        this.personaMap.put(12345, new Persona(12345, "Maycol Espinoza"));
        this.personaMap.put(12347, new Persona(12347, "Melissa Castro"));
        this.personaMap.put(12346, new Persona(12346, "Gonzalo Rodriguez"));
        this.personaMap.put(12348, new Persona(12348, "Andrea Cabrera"));
    }

    @Override
    void ordenar(ORDEREnum OREnum) {
        switch (OREnum) {
            case ASC:
                this.personaMap = new TreeMap<>(this.personaMap);
                break;
            case DESC:
                Map<Integer, Persona> tempMapDesc = new TreeMap<>(Collections.reverseOrder());
                tempMapDesc.putAll(this.personaMap);
                this.personaMap = tempMapDesc;
                break;
        }

    }

    @Override
    void agregar(Persona persona) {
        personaMap.put(persona.getDni(), persona);
        System.out.println("Se agregó satisfactoriamente a la persona");
    }

    @Override
    void actualizar(Persona persona) {
        personaMap.put(persona.getDni(), persona);
        System.out.println("Se actualizó satisfactoriamente a la persona");
    }

    @Override
    void eliminar(Persona persona) {
        personaMap.remove(persona.getDni());
        System.out.println("Se eliminó satisfactoriamente a la persona");
    }

    @Override
    void busqueda(int dni) {
        Optional<Persona> personaOpt = Optional.ofNullable(personaMap.get(dni));
        mostrarPersonaIfPresent(personaOpt, dni);
    }

    @Override
    void mostrar() {
        System.out.println("********************************************************");
        System.out.printf("%-15s", "Personas");
        System.out.printf("%-15s", "Dni");
        System.out.printf("%-50s", "Nombres");
        System.out.print("\n");
        int i = 0;
        for (Persona persona : this.personaMap.values()) {
            System.out.printf("%-15s", "Persona [" + i + "]");
            System.out.printf("%-15d", persona.getDni());
            System.out.printf("%-50s", persona.getNombres());
            System.out.print("\n");
            i++;
        }
    }
}
