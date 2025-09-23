import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PizzaFactoryTest {
    
    private PizzaFactory factory;
    
    @Before
    public void setUp() {
        factory = new PizzaFactory();
    }
    
    @Test
    public void testCreateCheesePizza() {
        Pizza pizza = factory.pizzaOrder(PizzaType.CHEESE);
        assertTrue("Should create CheesePizza", pizza instanceof CheesePizza);
    }
    
    @Test
    public void testCreateGreekPizza() {
        Pizza pizza = factory.pizzaOrder(PizzaType.GREEK);
        assertTrue("Should create GreekPizza", pizza instanceof GreekPizza);
    }
    
    @Test
    public void testCreatePepperoniPizza() {
        Pizza pizza = factory.pizzaOrder(PizzaType.PEPPERONI);
        assertTrue("Should create PepperoniPizza", pizza instanceof PepperoniPizza);
    }
    
    @Test
    public void testCreateGlutenFreePizza() {
        Pizza pizza = factory.pizzaOrder(PizzaType.GLUTEN_FREE);
        assertTrue("Should create GlutenFreePizza", pizza instanceof GlutenFreePizza);
    }
    
    @Test
    public void testCreateVeganPizza() {
        Pizza pizza = factory.pizzaOrder(PizzaType.VEGAN);
        assertTrue("Should create VeganPizza", pizza instanceof VeganPizza);
    }
}