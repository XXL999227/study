public class TestDemo {
    public static void main(String[] args) {
        Cook cook = new Cook();
        Foodie foodie = new Foodie();
        cook.setName("吃货");
        foodie.setName("厨师");

        cook.start();
        foodie.start();
    }
}
