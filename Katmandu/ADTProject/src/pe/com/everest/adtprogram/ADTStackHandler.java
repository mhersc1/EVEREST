package pe.com.everest.adtprogram;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Predicate;

/**
 * Created by MHER_ on 23/03/2018.
 */
public class ADTStackHandler extends GenericADT {
    private Stack<Persona> personaStack;

    public ADTStackHandler() {
        this.personaStack = new Stack<>();
        this.personaStack.add(new Persona(12345, "Maycol Espinoza"));
        this.personaStack.add(new Persona(12347, "Melissa Castro"));
        this.personaStack.add(new Persona(12346, "Gonzalo Rodriguez"));
        this.personaStack.add(new Persona(12348, "Andrea Cabrera"));
    }

    @Override
    void ordenar(ORDEREnum OREnum) {
        Comparator<Persona> ascendente = Comparator.comparing(Persona::getDni);
        Comparator<Persona> descendente = Comparator.comparing(Persona::getDni).reversed();
        switch (OREnum) {
            case ASC:
                this.personaStack.sort(ascendente);
                System.out.println("Se ordenó ascendentemente la pila de personas");
                break;
            case DESC:
                this.personaStack.sort(descendente);
                System.out.println("Se ordenó descendentemente la pila de personas");
                break;
        }
    }

    @Override
    void agregar(Persona persona) {
        this.personaStack.push(persona);
        System.out.println("Se agregó satisfactoriamente a la persona");
    }

    @Override
    void actualizar(Persona persona) {
        int dni = persona.getDni();
        Predicate<Persona> predicate = p -> p.getDni() == dni;
        Optional<Persona> optionalPerson = this.personaStack.stream().filter(predicate).findFirst();
        if (optionalPerson.isPresent()) {
            Persona found = optionalPerson.get();
            int realIndex = this.personaStack.size() - this.personaStack.search(found);
            this.personaStack.set(realIndex, persona);
        } else
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");
    }

    @Override
    void eliminar(Persona persona) {
        int dni = persona.getDni();
        Predicate<Persona> predicate = p -> p.getDni() == dni;
        Optional<Persona> optionalPersona = this.personaStack.stream().filter(predicate).findFirst();
        if (optionalPersona.isPresent()) {
            this.personaStack.remove(optionalPersona.get());
            System.out.println("Se eliminó satisfactoriamente a la persona");
        } else
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");
    }

    @Override
    void busqueda(int dni) {
        Predicate<Persona> predicate = p -> p.getDni() == dni;
        Optional<Persona> optional = this.personaStack.stream().filter(predicate).findFirst();
        mostrarPersonaIfPresent(optional, dni);
    }

    @Override
    void mostrar() {
        mostrarPersonaList(new ArrayList<>(this.personaStack));
    }
}
