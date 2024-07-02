package lesson_7_1;

public class Bowl {
    private int food;
    public Bowl (int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void dropFood(int amount) {
        if (food <= amount) {
            food -= amount;
        } else {
            System.out.println("\nНедостаточно еды в миске");
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("\nДобавлено " + amount + " единиц еды в миску");
        } else {
            System.out.println("\nКоличество добавляемой еды должно быть положительным");
        }
    }
}
