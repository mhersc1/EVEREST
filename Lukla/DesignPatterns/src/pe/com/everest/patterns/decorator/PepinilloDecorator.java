package pe.com.everest.patterns.decorator;

import java.math.BigDecimal;

/**
 * Created by MHER on 7/2/2018.
 */
public class PepinilloDecorator extends Pizza implements ToppingDecorator {
    Pizza pizza;

    PepinilloDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescripcion() {
        return pizza.getDescripcion() + " + Pepinillo ";

    }

    @Override
    BigDecimal getPrecio() {
        return new BigDecimal("3.5").add(pizza.getPrecio());
    }
}
