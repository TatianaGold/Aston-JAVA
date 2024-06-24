package lesson_7_1;

public class Cat extends Animal {
    private static final int maxRunDistance = 200;
    private static int catCount = 0;
    private boolean satiety;

    public Cat(String name) {
        super(name);
        catCount++;
        this.satiety = false;
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void eat (Bowl bowl, int amount) {
        if (bowl.getFood() >= amount) {
            bowl.dropFood(amount);
            satiety = true;
            System.out.println(name + " покушал и стал сытым.");
        } else {
            System.out.println(name + " не хватило еды.");
        }
    }

    public static int getCatCount() {
        return catCount;
    }
}
