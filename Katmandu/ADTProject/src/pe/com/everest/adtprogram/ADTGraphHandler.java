package pe.com.everest.adtprogram;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static pe.com.everest.adtprogram.Util.*;

/**
 * Created by MHER_ on 24/03/2018.
 */
public class ADTGraphHandler extends GenericADT {
    private int[][] matriz = {
            {0, 2, 2, -1},
            {2, 0, 4, 3},
            {2, 4, 0, 5},
            {-1, 3, 5, 0},
    };
    /*
    private int[][] matriz = {
            {0, 3, 5, 1, -1, -1},
            {3, 0, -1, -1, 9, -1},
            {5, -1, 0, 7, 7, 1},
            {1, -1, 7, 0, -1, 4},
            {-1, 9, 7, -1, 0, -1},
            {-1, -1, 1, 4, -1, 0}
    };*/
    private List<Persona> personaList;

    public ADTGraphHandler() {
        //Filling people's list
        this.personaList = new ArrayList<>();
        this.personaList.add(new Persona(12345, "Maycol Espinoza"));
        this.personaList.add(new Persona(12347, "Melissa Castro"));
        this.personaList.add(new Persona(12346, "Gonzalo Rodriguez"));
        this.personaList.add(new Persona(12348, "Andrea Cabrera"));
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
            System.out.println("6) Camino optimo");
            System.out.println("0) Regresar Menu Principal");
            System.out.print("Seleccione una operación:");
            operation = Integer.parseInt(leer());
            counter++;
        } while (operation < 1 || operation > 6);
        return operation;
    }

    @Override
    void ordenar(ORDEREnum OREnum) {
        Comparator<Persona> ascendente = Comparator.comparing(Persona::getDni);
        Comparator<Persona> descendente = Comparator.comparing(Persona::getDni).reversed();

        //Obtaining the index vector through their dnis
        Map<Integer, Integer> vectorMap = getVectorMap();
        switch (OREnum) {
            case ASC:
                personaList.sort(ascendente);
                sortMatriz(vectorMap);
                System.out.println("Se ordenó ascendentemente el listado de personas");
                break;
            case DESC:
                personaList.sort(descendente);
                sortMatriz(vectorMap);
                System.out.println("Se ordenó descendentemente el listado de personas");
                break;
        }

        System.out.println("Relaciones entre personas (Matriz de adyacencia)");
        printMatrizAdyacencia();
    }

    private void sortMatriz(Map<Integer, Integer> vectorMap) {
        //Getting size people list
        int size = this.personaList.size();
        //New 2D Array
        int[][] newArray = new int[size][size];

        //Emptying old Matrix to new Matrix
        for (int i = 0; i < size; i++) {
            Persona personaI = this.personaList.get(i);
            int iIndex = vectorMap.get(personaI.getDni());

            for (int j = 0; j < size; j++) {
                Persona personaJ = this.personaList.get(j);
                int jIndex = vectorMap.get(personaJ.getDni());
                newArray[i][j] = this.matriz[iIndex][jIndex];
            }
        }

        //Finally emptying new Array into current Array
        this.matriz = newArray;
    }

    private Map<Integer, Integer> getVectorMap() {
        //Indexing vectors by their dnis
        Map<Integer, Integer> vectorMap = new HashMap<>();
        for (int i = 0; i < this.personaList.size(); i++) {
            Persona persona = this.personaList.get(i);
            vectorMap.put(persona.getDni(), i);
        }
        return vectorMap;
    }

    @Override
    void agregar(Persona persona) {
        //Showing people's list
        mostrar();

        System.out.println("Relaciones entre personas (Matriz de adyacencia)");
        printMatrizAdyacencia();

        System.out.println("********************************************************");
        System.out.println("**      Relacion Persona         A ---> B             **");
        System.out.println("**      No conectividad Directa:    -1                **");
        System.out.println("**      Conectividad Directa:      > 1                **");
        System.out.println("********************************************************");

        //Incrementing size of 2D Array ...
        int length = this.matriz.length;
        this.matriz = resize(this.matriz, length + 1, length + 1);

        //Adding into people List
        this.personaList.add(persona);

        //Matching relations with other people ...
        this.matriz[length][length] = 0;
        askForRelations(persona, length);

        //Printing new Adyacent Matriz
        System.out.println("Matriz de adyacencia, luego de agregar a la nueva persona: ");
        printMatrizAdyacencia();
        System.out.println("La persona se agrego satisfactoriamente: ");
    }

    @Override
    void actualizar(Persona persona) {
        //Finding person into people's list
        //Updating person into people's List
        int dni = persona.getDni();
        OptionalInt indexOpt = IntStream.range(0, personaList.size())
                .filter(i -> dni == (personaList.get(i).getDni()))
                .findFirst();
        int index;
        if (indexOpt.isPresent()) {
            index = indexOpt.getAsInt();
            personaList.set(index, persona);
        } else {
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");
            return;
        }

        //Showing people's list
        mostrar();

        System.out.println("Relaciones entre personas (Matriz de adyacencia)");
        printMatrizAdyacencia();

        System.out.println("********************************************************");
        System.out.println("**      Relacion Persona         A ---> B             **");
        System.out.println("**      No conectividad Directa:    -1                **");
        System.out.println("**      Conectividad Directa:      > 1                **");
        System.out.println("********************************************************");

        //Matching relations with other people ...
        this.matriz[index][index] = 0;
        askForRelations(persona, index);

        //Printing new Adyacent Matriz
        System.out.println("Matriz de adyacencia, luego de actualizar a la persona[" + index + "] ");
        printMatrizAdyacencia();
        System.out.println("La persona se actualizo satisfactoriamente: ");
    }

    private void askForRelations(Persona persona, int index) {
        int relation;
        int length = this.matriz.length;
        for (int i = 0; i <= length - 1; i++) {
            if (i != index) {
                int flag = 0;
                do {
                    if (flag > 0) {
                        System.out.println("Ingrese un grado de relacion valido (mayor a -1)");
                        pressKeyToContinue();
                    }

                    Persona person = this.personaList.get(i);
                    System.out.println("********************************************************");
                    System.out.printf("%-100s\n", "Relacion entre las Personas [" + persona.getNombres() + " ---> " + person.getNombres() + "] ");
                    System.out.print("Ingrese el grado para esta relacion:");
                    relation = Integer.parseInt(leer());
                    System.out.println("********************************************************");
                    flag++;
                } while (relation < -1 || relation == 0);
                //Filling row and columns for new Person
                this.matriz[index][i] = this.matriz[i][index] = relation;
            }
        }
    }

    @Override
    void eliminar(Persona persona) {
        //Removing person into people's list
        int dni = persona.getDni();
        OptionalInt indexOpt = IntStream.range(0, personaList.size())
                .filter(i -> dni == (personaList.get(i).getDni()))
                .findFirst();

        int index;
        if (indexOpt.isPresent()) {
            index = indexOpt.getAsInt();
            personaList.remove(index);
        } else {
            System.out.println("No se encontró Persona con el dni: " + dni + " especificado.");
            return;
        }
        //Showing people's list
        mostrar();

        //Removing and moving relations ...
        int length = this.matriz.length;
        //Overstep rows
        for (int i = index; i < length - 1; i++)
            System.arraycopy(this.matriz[i + 1], 0, this.matriz[i], 0, length);

        //Overstep columns
        for (int i = index; i < length - 1; i++)
            for (int j = 0; j <= length - 1; j++)
                this.matriz[j][i] = this.matriz[j][i + 1];

        //Removing last row
        this.matriz = resize(this.matriz, length - 1, length - 1);

        //Printing new Adyacent Matriz
        System.out.println("Matriz de adyacencia, luego de eliminar a la persona[" + index + "]");
        printMatrizAdyacencia();
        System.out.println("La persona se elimino satisfactoriamente: ");
    }

    @Override
    void busqueda(int dni) {
        Predicate<Persona> predicate = p -> p.getDni() == (dni);
        Optional<Persona> personaOpt = personaList.stream().filter(predicate).findFirst();
        mostrarPersonaIfPresent(personaOpt, dni);
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("Relaciones entre personas (Matriz de adyacencia)");
        printMatrizAdyacencia();
    }

    @Override
    void mostrar() {
        mostrarPersonaList(this.personaList);
        System.out.print("\n");
    }

    void showShortPath() {

        int length = this.matriz.length;
        //Inicializacion: Matriz con los caminos minimos entres dos vertices cualesquiera
        int[][] matrizMinimos = resize(this.matriz, length, length);
        //Inicializacion: Matriz con los penultimos nodos que conforman distancia optima
        int[][] pathway = initPathwayMatrix(length);

        for (int k = 0; k < length; k++) {
            for (int i = 0; i < length; i++) {
                if (i == k) {
                    continue;
                }

                for (int j = 0; j < length; j++) {
                    int sumaAdyacentes;
                    if (j == k || j == i) {
                        continue;
                    }

                    if (matrizMinimos[i][k] == -1 || matrizMinimos[k][j] == -1)
                        sumaAdyacentes = -1;
                    else
                        sumaAdyacentes = matrizMinimos[i][k] + matrizMinimos[k][j];


                    if (sumaAdyacentes != -1 &&
                            (matrizMinimos[i][j] == -1 || matrizMinimos[i][j] > sumaAdyacentes)) {
                        matrizMinimos[i][j] = sumaAdyacentes;
                        pathway[i][j] = pathway[k][j];
                    }
                }
            }
        }

        //Menu for PathWays between two nodes
        int x, y, counter = 0;
        do {
            if (counter > 0) {
                clearScreen();
                System.out.println("Ingrese coordenadas validas !!!");
            }
            System.out.println("********************************************************");
            System.out.println("Matriz de Relaciones de Divergencia");
            printMatriz(matrizMinimos);//Printing shortest ways for distinct nodes
            System.out.println("********************************************************");
            System.out.println("Para ver el grupo de personas con el minimo de divergencia ...");
            System.out.println("Ingrese las coordenadas de la relacion de divergencia ...");
            System.out.print("Persona[X] = Coordenada [X]:");
            x = Integer.parseInt(leer());
            System.out.print("Persona[Y] = Coordenada [Y]:");
            y = Integer.parseInt(leer());
            counter++;
        } while ((x < 0 || x >= length) || (y < 0 || y >= length));

        Stack<Persona> pila = new Stack<>();
        int z = y;
        pila.push(this.personaList.get(x));
        do {
            z = pathway[x][z];//Getting Penultimate node
            pila.push(this.personaList.get(z));//Pushing the found person
        } while (z != x);

        //Printing the shortest Path between two nodes
        System.out.println("********************************************************");
        System.out.println("Grupo de Divergencia minima con las personas: ");
        System.out.print(this.personaList.get(x).getNombres());
        System.out.print(" y ");
        System.out.println(this.personaList.get(y).getNombres());
        System.out.println("es: ");
        Persona persona = pila.pop();
        System.out.print(persona.getNombres());
        do {
            System.out.print(" --> ");
            persona = pila.pop();
            System.out.print(persona.getNombres());
        } while (!pila.isEmpty());
        System.out.print("\n");
        System.out.println("********************************************************");
    }

    private void printMatrizAdyacencia() {
        int lastIndex = this.matriz.length - 1;
        System.out.println(StringUtils.repeat("*", (lastIndex + 2) * 30));

        //Printing Header ...
        System.out.printf("%-30s", "Personas");
        for (int i = 0; i <= lastIndex; i++)
            System.out.printf("%-30s", StringUtils.center(this.personaList.get(i).getNombres(), 30));
        System.out.print("\n");

        //Printing Body ...
        for (int i = 0; i <= lastIndex; i++) {
            Persona persona = this.personaList.get(i);
            System.out.printf("%-30s", persona.getNombres());
            for (int j = 0; j <= lastIndex; j++) {
                System.out.printf("%-30s", StringUtils.center(String.valueOf(this.matriz[i][j]), 30));
            }
            System.out.print("\n");
        }
        System.out.println(StringUtils.repeat("*", (lastIndex + 2) * 30));
    }

    private void printMatriz(int matriz[][]) {
        int lastIndex = matriz.length - 1;
        System.out.println(StringUtils.repeat("*", (lastIndex + 2) * 30));

        //Printing Header ...
        System.out.printf("%-30s", "Personas");
        for (int i = 0; i <= lastIndex; i++)
            System.out.printf("%-30s", StringUtils.center("[" + i + "] " + this.personaList.get(i).getNombres(), 30));
        System.out.print("\n");

        //Printing Body ...
        for (int i = 0; i <= lastIndex; i++) {
            Persona persona = this.personaList.get(i);
            System.out.printf("%-30s", "[" + i + "] " + persona.getNombres());
            for (int j = 0; j <= lastIndex; j++) {
                System.out.printf("%-30s", StringUtils.center(String.valueOf(matriz[i][j]), 30));
            }
            System.out.print("\n");
        }
        System.out.println(StringUtils.repeat("*", (lastIndex + 2) * 30));
    }
}
