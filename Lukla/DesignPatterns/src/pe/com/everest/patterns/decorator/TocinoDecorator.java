package pe.com.everest.patterns.decorator;

import java.math.BigDecimal;

/**
 * Created by MHER on 7/2/2018.
 */
public class TocinoDecorator extends Pizza implements ToppingDecorator {
    Pizza pizza;

    TocinoDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescripcion() {
        return pizza.getDescripcion() + " + Tocino ";
    }

    @Override
    BigDecimal getPrecio() {
        return new BigDecimal("5.50").add(pizza.getPrecio());
    }
}
