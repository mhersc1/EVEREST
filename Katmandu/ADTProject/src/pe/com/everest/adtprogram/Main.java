package pe.com.everest.adtprogram;

import static pe.com.everest.adtprogram.OperationHandler.operacionesConADT;

public class Main {

    public static void main(String[] args) {
        ADTListHandler adtListHandler = new ADTListHandler();
        ADTMapHandler adtHashTableHandler = new ADTMapHandler();
        ADTSetHandler adtSetHandler = new ADTSetHandler();
        ADTStackHandler adtStackHandler = new ADTStackHandler();
        ADTGraphHandler adtGraphHandler = new ADTGraphHandler();

        for (; ; )
            switch (OperationHandler.displayMenuPrincipal()) {
                case 0:
                    System.exit(0);
                case 1: //ADT Lista
                    operacionesConADT(adtListHandler);
                    break;
                case 2://ADT HashTable
                    operacionesConADT(adtHashTableHandler);
                    break;
                case 3://ADT Arbol
                    operacionesConADT(adtSetHandler);
                    break;
                case 4://ADT Pila
                    operacionesConADT(adtStackHandler);
                    break;
                case 5://ADT Grafo
                    operacionesConADT(adtGraphHandler);
                    break;
            }

    }
}
