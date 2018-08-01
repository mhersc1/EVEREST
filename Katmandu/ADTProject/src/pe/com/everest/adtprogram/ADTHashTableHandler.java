package pe.com.everest.adtprogram;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * Created by MHER_ on 17/03/2018.
 */
public class ADTHashTableHandler extends GenericADT {
    private Map<Integer, Persona> personaMap;

    public ADTHashTableHandler() {
        this.personaMap.put(12345, new Persona(12345, "Maycol Espinoza"));
        this.personaMap.put(12347, new Persona(12347, "Melissa Castro"));
        this.personaMap.put(12346, new Persona(12346, "Gonzalo Rodriguez"));
        this.personaMap.put(12348, new Persona(12348, "Andrea Cabrera"));
    }

    @Override
    void ordenar(ORDEREnum OREnum) {
        switch (OREnum) {
            case ASC:
                this.personaMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey());
                break;
            case DESC:
                this.personaMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()));
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
        int index = 1;
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("                Listado de Personas                     ");
        for (Persona p : this.personaMap.values()) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Persona [" + index + "] ");
            mostrarPersona(p);
            index++;
        }
        System.out.println("********************************************************");
        System.out.println("********************************************************");
    }
}
