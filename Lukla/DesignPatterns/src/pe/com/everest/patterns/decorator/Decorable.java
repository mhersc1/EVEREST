package pe.com.everest.patterns.decorator;

import java.math.BigDecimal;


interface Decorable {
    Pizza getPizza();
    String descripcionDecorada();
    BigDecimal precioDecorado();
}
