package pe.com.everest.adtprogram;

import static pe.com.everest.adtprogram.Util.leer;

/**
 * Created by MHER_ on 18/03/2018.
 */
public class OperationHandler {
    static int displayMenuPrincipal() {
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("**   ___________     ____________       ____________  **");
        System.out.println("**   -----------     ------------       ------------  **");
        System.out.println("**  ||         ||   ||           \\\\          ||       **");
        System.out.println("**  ||         ||   ||           ||          ||       **");
        System.out.println("**  ||         ||   ||           ||          ||       **");
        System.out.println("**  ||_________||   ||           ||          ||       **");
        System.out.println("**  ||---------||   ||           ||          ||       **");
        System.out.println("**  ||         ||   ||           ||          ||       **");
        System.out.println("**  ||         ||   ||___________//          ||       **");
        System.out.println("**  ||         ||     -----------            ||       **");
        System.out.println("**                                                    **");
        System.out.println("**                  -/-  PROGRAM -/-                  **");
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("                                                        ");
        System.out.println("                                                        ");
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("**                 MENU - OPCIONES                    **");
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("1) Operaciones con ADT Lista");
        System.out.println("2) Operaciones con ADT HashTahle");
        System.out.println("3) Operaciones con ADT Arbol");
        System.out.println("4) Operaciones con ADT Pila");
        System.out.println("5) Operaciones con ADT Grafo");
        System.out.println("0) Salir");
        System.out.print("Seleccione una ADT: ");

        int opcion = Integer.parseInt(leer());

        if (opcion == 0)
            System.exit(0);
        return opcion;
    }


    static void operacionesConADT(GenericADT genericADT) {
        switch (genericADT.showOwnOperations()) {
            case 0:
                Util.clearScreen();
                break;
            case 1:
                genericADT.agregar(displayAgregarMenu());
                genericADT.mostrar();
                Util.pressKeyToContinue();
                Util.clearScreen();
                break;
            case 2:
                genericADT.actualizar(displayActualizarMenu());
                genericADT.mostrar();
                Util.pressKeyToContinue();
                Util.clearScreen();
                break;
            case 3:
                genericADT.eliminar(displayEliminarMenu());
                genericADT.mostrar();
                Util.pressKeyToContinue();
                Util.clearScreen();
                break;
            case 4:
                genericADT.busqueda(displayBusquedaMenu());
                Util.pressKeyToContinue();
                Util.clearScreen();
                break;
            case 5:
                genericADT.ordenar(displayOrdenarMenu());
                genericADT.mostrar();
                Util.pressKeyToContinue();
                Util.clearScreen();
                break;
            case 6:
                ADTGraphHandler graphHandler = (ADTGraphHandler) genericADT;
                graphHandler.showShortPath();
                Util.pressKeyToContinue();
                Util.clearScreen();
                break;
        }
    }

    private static Persona displayAgregarMenu() {
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("            Inserción de Nueva Persona                  ");
        System.out.print("Ingrese DNI: ");
        int dni = Integer.parseInt(leer());
        System.out.print("Ingrese Nombres: ");
        String nombres = leer();
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        return new Persona(dni, nombres);
    }

    private static Persona displayActualizarMenu() {
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("                Actualizar Persona                      ");
        System.out.print("Ingrese el DNI de la persona a actualizar: ");
        int dni = Integer.parseInt(leer());
        System.out.print("Ingrese Nombres a modificar: ");
        String nombres = leer();
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        return new Persona(dni, nombres);
    }

    private static Persona displayEliminarMenu() {
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("                Eliminar Persona                        ");
        System.out.print("Ingrese el DNI de la persona a eliminar: ");
        int dni = Integer.parseInt(leer());
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        return new Persona(dni, "");
    }

    private static int displayBusquedaMenu() {
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("                Buscar Persona                          ");
        System.out.print("Ingrese el DNI de la persona a buscar:");
        int dni = Integer.parseInt(leer());
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        return dni;
    }

    private static ORDEREnum displayOrdenarMenu() {
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("                Tipos de Ordenamiento de Personas       ");
        System.out.println("1) ASCENDENTE");
        System.out.println("2) DESCENDENTE");
        System.out.print("Seleccione tipo de ordenamiento:");
        int orden = Integer.parseInt(leer());
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        if (orden == 1)
            return ORDEREnum.ASC;
        else if (orden == 2)
            return ORDEREnum.DESC;
        else
            return null;
    }
}
