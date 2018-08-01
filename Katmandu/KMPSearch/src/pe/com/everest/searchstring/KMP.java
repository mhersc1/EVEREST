package pe.com.everest.searchstring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KMP {

    public static void main(String[] args) {

        String rpta;
        do {
            System.out.println("***********************************************************");
            System.out.println("**                KNUTH - MORRIS - PRATT                 **");
            System.out.println("**                   SEARCHING STRING                    **");
            System.out.println("***********************************************************");
            System.out.print("\n");
            Scanner kb = new Scanner(System.in);
            System.out.print("Ingrese cadena en el que buscar: ");
            String target = kb.next();
            System.out.print("Ingrese cadena a buscar: ");
            String search = kb.next();
            System.out.println("***********************************************************");
            System.out.println("***********************************************************");
            int result = KMP(search, target);
            if (result == -1) {
                System.out.println("La cadena \"" + target + "\"  no contiene a la cadena \"" + search + "\"");
            } else {
                System.out.println("La cadena \"" + target + "\" contiene a la cadena \"" + search + "\"");
            }


            System.out.println("***********************************************************");
            System.out.println("***********************************************************");
            System.out.print("Desea continuar (y/n)?: ");
            rpta = leer();

            if (!rpta.equalsIgnoreCase("y"))
                System.exit(0);
            else
                clearScreen();


        } while (rpta.equalsIgnoreCase("y"));
    }

    public static int KMP(String search, String target) {
        int[] failureTable = failureTable(target);

        int targetPointer = 0; // current char in target string
        int searchPointer = 0; // current char in search string

        while (searchPointer < search.length()) { // while there is more to search with, keep searching
            if (search.charAt(searchPointer) == target.charAt(targetPointer)) { // case 1
                // found current char in targetPointer in search string
                targetPointer++;
                if (targetPointer == target.length()) { // found all characters
                    int x = target.length() + 1;
                    return searchPointer - x; // return starting index of found target inside searched string
                }
                searchPointer++; // move forward if not found target string
            } else if (targetPointer > 0) { // case 2
                // use failureTable to use pointer pointed at nearest location of usable string prefix
                targetPointer = failureTable[targetPointer];
            } else { // case 3
                // targetPointer is pointing at state 0, so restart search with current searchPointer index
                searchPointer++;
            }
        }
        return -1;
    }

    /**
     * Returns an int[] that points to last valid string prefix, given target string
     */
    public static int[] failureTable(String target) {
        int[] table = new int[target.length() + 1];
        // state 0 and 1 are guarenteed be the prior
        table[0] = -1;
        table[1] = 0;

        // the pointers pointing at last failure and current satte
        int left = 0;
        int right = 2;

        while (right < table.length) { // RIGHT NEVER MOVES RIGHT UNTIL ASSIGNED A VALID POINTER
            if (target.charAt(right - 1) == target.charAt(left)) { // when both chars before left and right are equal, link both and move both forward
                left++;
                table[right] = left;
                right++;
            } else if (left > 0) { // if left isn't at the very beginning, then send left backward
                // by following the already set pointer to where it is pointing to
                left = table[left];
            } else { // left has fallen all the way back to the beginning
                table[right] = left;
                right++;
            }
        }
        return table;
    }

    static String leer() {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        try {
            return b.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}
