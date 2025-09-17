public class PizzaShop {
  
    private String name;
    private String address;
    private PizzaFactory factory;

    public static void main(String[] args) {
        PizzaShop myPizzaShop = new PizzaShop("Gourmet Pizza", "123 Flavor  St"); 
        myPizzaShop.openShop();
        myPizzaShop.demonstrateFactory();
        myPizzaShop.closeShop();
    }

    public PizzaShop(String name, String address) {
        this.name = name;
        this.address = address;
        this.factory = new PizzaFactory();
    }



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void openShop() {
        System.out.println(name + " at " + address + " is now open!");
    }

    public void closeShop() {
        System.out.println(name + " is now closed.");
    }

    public void demonstrateFactory(){
      System.out.println("Making pizzas using the factory....");
      Pizza cheesePizza = factory.pizzaOrder(PizzaType.CHEESE);
      Pizza pepperoniPizza = factory.pizzaOrder(PizzaType.PEPPERONI);
      Pizza greekPizza = factory.pizzaOrder(PizzaType.GREEK);

      makePizza(cheesePizza);
      System.out.println();
      makePizza(pepperoniPizza);
      System.out.println();
      makePizza(greekPizza);
      System.out.println();

    }

    public void makePizza(Pizza pizza){
      pizza.prepare();
      pizza.bake();
      pizza.cut();
      pizza.box();
      System.out.println("Your " + pizza.getName() + " pizza is ready!!");
    }
}