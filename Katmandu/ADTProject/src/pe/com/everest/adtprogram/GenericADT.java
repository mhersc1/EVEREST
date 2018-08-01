package pe.com.everest.adtprogram;

import java.util.List;
import java.util.Optional;

import static pe.com.everest.adtprogram.Util.clearScreen;
import static pe.com.everest.adtprogram.Util.leer;

/**
 * Created by MHER_ on 17/03/2018.
 */
public abstract class GenericADT {
    void mostrarPersonaIfPresent(Optional<Persona> personaOpt, int dni) {
        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            System.out.println("Se encontró a la persona: ");
            System.out.printf("%-15d", persona.getDni());
            System.out.printf("%-50s", persona.getNombres());
            System.out.print("\n");
        } else {
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");
        }
    }


    void mostrarPersonaList(List<Persona> personaList) {
        System.out.println("                Listado de Personas                     ");
        System.out.printf("%-15s", "Personas");
        System.out.printf("%-15s", "Dni");
        System.out.printf("%-50s", "Nombres");
        System.out.print("\n");
        for (int i = 0; i <= personaList.size() - 1; i++) {
            Persona persona = personaList.get(i);
            System.out.printf("%-15s", "Persona [" + i + "]");
            System.out.printf("%-15d", persona.getDni());
            System.out.printf("%-50s", persona.getNombres());
            System.out.print("\n");
        }
    }

    int showOwnOperations() {
        int operation;
        int counter = 0;
        do {
            if (counter > 0) {
                clearScreen();
                System.out.println("Ingrese una operacion valida !!!");
            }
            System.out.println("********************************************************");
            System.out.println("**                 OPERACIONES                        **");
            System.out.println("********************************************************");
            System.out.println("1) Inserción");
            System.out.println("2) Actualización");
            System.out.println("3) Eliminación");
            System.out.println("4) Búsqueda");
            System.out.println("5) Ordenamiento");
            System.out.println("0) Regresar Menu Principal");
            System.out.print("Seleccione una operación:");
            operation = Integer.parseInt(leer());
            counter++;
        } while (operation < 1 || operation > 5);
        return operation;
    }

    abstract void ordenar(ORDEREnum OREnum);

    abstract void agregar(Persona persona);

    abstract void actualizar(Persona persona);

    abstract void eliminar(Persona persona);

    abstract void busqueda(int dni);

    abstract void mostrar();

}

