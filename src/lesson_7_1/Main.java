package lesson_7_1;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Бобик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");

        System.out.println("Создано животных: " + Animal.getAnimalCount());
        System.out.println("Создано собак: " + Dog.getDogCount());
        System.out.println("Создано котов: " + Cat.getCatCount());

        dog1.run(150);
        dog1.swim(5);
        dog1.run(600);
        dog1.swim(15);

        cat1.run(150);
        cat1.swim(5);
        cat1.run(250);

        Bowl bowl = new Bowl(20);

        Cat[] cats = {cat1, cat2};
        for (Cat cat : cats) {
            cat.eat(bowl, 10);
            System.out.println(cat.name + " сытость: " + cat.isSatiety());
        }

        bowl.addFood(15);
        for (Cat cat : cats) {
            if (!cat.isSatiety()) {
                cat.eat(bowl, 10);
                System.out.println(cat.name + " сытость: " + cat.isSatiety());
            }
        }
    }
}