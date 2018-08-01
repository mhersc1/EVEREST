package pe.com.everest.adtprogram;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Created by MHER_ on 17/03/2018.
 */
public class ADTListHandler extends GenericADT {
    private List<Persona> personaList;

    public ADTListHandler() {
        this.personaList = new ArrayList<>();
        this.personaList.add(new Persona(12345, "Maycol Espinoza"));
        this.personaList.add(new Persona(12347, "Melissa Castro"));
        this.personaList.add(new Persona(12346, "Gonzalo Rodriguez"));
        this.personaList.add(new Persona(12348, "Andrea Cabrera"));
    }

    @Override
    void ordenar(ORDEREnum OREnum) {
        Comparator<Persona> ascendente = Comparator.comparing(Persona::getDni);
        Comparator<Persona> descendente = Comparator.comparing(Persona::getDni).reversed();
        switch (OREnum) {
            case ASC:
                personaList.sort(ascendente);
                System.out.println("Se ordenó ascendentemente el listado de personas");
                break;
            case DESC:
                personaList.sort(descendente);
                System.out.println("Se ordenó descendentemente el listado de personas");
                break;
        }
    }

    @Override
    void agregar(Persona persona) {
        personaList.add(persona);
        System.out.println("Se agregó satisfactoriamente a la persona");
    }

    @Override
    void actualizar(Persona persona) {
        int dni = persona.getDni();
        //Se obtiene el indice de la persona con el dni especificado
        OptionalInt indexOpt = IntStream.range(0, personaList.size())
                .filter(i -> dni == (personaList.get(i).getDni()))
                .findFirst();

        if (indexOpt.isPresent()) {
            personaList.set(indexOpt.getAsInt(), persona);
            System.out.println("Se actualizó satisfactoriamente a la persona");
        } else
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");

    }

    @Override
    void eliminar(Persona persona) {
        int dni = persona.getDni();
        //Se obtiene el indice de la persona a eliminar
        OptionalInt indexOpt = IntStream.range(0, personaList.size())
                .filter(i -> dni == (personaList.get(i).getDni()))
                .findFirst();

        if (indexOpt.isPresent()) {
            personaList.remove(indexOpt.getAsInt());
            System.out.println("Se eliminó satisfactoriamente a la persona");
        } else
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");
    }

    @Override
    void busqueda(int dni) {
        Predicate<Persona> predicate = p -> p.getDni() == (dni);
        Optional<Persona> personaOpt = personaList.stream().filter(predicate).findFirst();
        mostrarPersonaIfPresent(personaOpt, dni);
    }

    @Override
    void mostrar() {
        mostrarPersonaList(this.personaList);
    }
}
