package pe.com.everest.adtprogram;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by MHER_ on 21/03/2018.
 */
public class ADTSetHandler extends GenericADT {
    private Set<Persona> personaSet;

    public ADTSetHandler() {
        this.personaSet = new TreeSet<>();
        this.personaSet.add(new Persona(12345, "Maycol Espinoza"));
        this.personaSet.add(new Persona(12347, "Melissa Castro"));
        this.personaSet.add(new Persona(12346, "Gonzalo Rodriguez"));
        this.personaSet.add(new Persona(12348, "Andrea Cabrera"));
    }

    @Override
    void ordenar(ORDEREnum OREnum) {
        switch (OREnum) {
            case ASC:
                TreeSet<Persona> personasTemp = new TreeSet<>();
                personasTemp.addAll(this.personaSet);

                this.personaSet = new TreeSet<>();
                this.personaSet.addAll(personasTemp);
                System.out.println("Por defecto el orden de los elementos de un TreeSet es ascendente.");
                break;
            case DESC:
                Set<Persona> personaTemp = new TreeSet<>();
                personaTemp.addAll(this.personaSet);

                this.personaSet = new TreeSet<>(Collections.reverseOrder());
                this.personaSet.addAll(personaTemp);
                System.out.println("Se ordenó descendentemente el listado de personas");
                break;
        }
    }

    @Override
    void agregar(Persona persona) {
        personaSet.add(persona);
        System.out.println("Se agregó satisfactoriamente a la persona");
    }

    @Override
    void actualizar(Persona persona) {
        int dni = persona.getDni();
        Predicate<Persona> predicate = p -> persona.getDni() == p.getDni();
        Optional<Persona> optionalPerson = personaSet.stream().filter(predicate).findFirst();

        if (optionalPerson.isPresent()) {
            this.personaSet.remove(optionalPerson.get());
            this.personaSet.add(persona);
            System.out.println("Se actualizó satisfactoriamente a la persona");
        } else
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");
    }

    @Override
    void eliminar(Persona persona) {
        int dni = persona.getDni();
        Predicate<Persona> predicate = p -> persona.getDni() == dni;
        Optional<Persona> optionalPerson = personaSet.stream().filter(predicate).findFirst();
        if (optionalPerson.isPresent()) {
            this.personaSet.remove(persona);
            System.out.println("Se eliminó satisfactoriamente a la persona");
        } else
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");
    }

    @Override
    void busqueda(int dni) {
        Predicate<Persona> predicate = p -> p.getDni() == (dni);
        Optional<Persona> personaOpt = this.personaSet.stream().filter(predicate).findFirst();
        mostrarPersonaIfPresent(personaOpt, dni);
    }

    @Override
    void mostrar() {
        mostrarPersonaList(new ArrayList<>(this.personaSet));
    }
}
