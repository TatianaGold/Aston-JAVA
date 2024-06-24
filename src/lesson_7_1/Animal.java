package lesson_7_1;

public abstract class Animal {
    protected String name;
    protected static int animalCount = 0;

    public Animal (String name) {
        this.name = name;
        animalCount++;
    }

    public void run (int distance) {
        System.out.println(name + " пробежал " + distance + "м.");
    }

    public void swim (int distance) {
        System.out.println(name + " проплыл " + distance + "м.");
    }

    public static int getAnimalCount(){
        return animalCount;
    }
}
