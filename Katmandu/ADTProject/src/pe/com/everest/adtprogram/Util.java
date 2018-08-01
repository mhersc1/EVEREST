package pe.com.everest.adtprogram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by MHER_ on 18/03/2018.
 */
class Util {

    static String leer() {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        try {
            return b.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    static void pressKeyToContinue() {
        System.out.print("Presiona la tecla Enter para continuar ...");
        try {
            System.in.read();
        } catch (Exception e) {
            //...
        }
    }

    static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    static int[][] resize(int[][] matrix, int w, int h) {
        int[][] temp = new int[h][w];
        h = Math.min(h, matrix.length);
        w = Math.min(w, matrix[0].length);
        for (int i = 0; i < h; i++)
            System.arraycopy(matrix[i], 0, temp[i], 0, w);
        return temp;
    }

    static int[][] initPathwayMatrix(int size) {
        int[][] initMatrix = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                initMatrix[i][j] = i;
        return initMatrix;
    }

    public void instanceOfPersona() {
        Persona persona1 = new Persona(10200030, "Maycol", "Espinoza", "Los Alamos", 24, new BigDecimal("1000.00"), new BigDecimal("900.00"));
        Persona persona2 = new Persona(10200031, "Andrea", "Del Rosario", "Andres Reyes", 25, new BigDecimal("2000.00"), new BigDecimal("1000.00"));

        Persona persona3 = new Persona.Builder(10200030)
                .susNombres("Maycol")
                .susApellidos("Espinoza")
                .suDireccion("Los Alamos")
                .suEdad(25)
                .suSalario(new BigDecimal("25000"))
                .suGastoMensual(new BigDecimal("15000"))
                .build();
    }
}
