package pe.com.everest.patterns.decorator;

import java.math.BigDecimal;

public class Pepinillo extends ToppingDecorator {

    @Override
    public String descripcionDecorada() {
        return pizza.descripcionDecorada() + " + Pepinillo ";
    }

    @Override
    public BigDecimal precioDecorado() {
        return pizza.precioDecorado().add(new BigDecimal("5.00"));
    }

}
