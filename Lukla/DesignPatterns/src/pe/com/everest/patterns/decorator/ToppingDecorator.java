package pe.com.everest.patterns.decorator;

abstract class ToppingDecorator implements Decorable {
    Pizza pizza;
    public Pizza getPizza() {
        return this.pizza;
    }
}
