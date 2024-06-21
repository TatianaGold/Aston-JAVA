public class Main {
    public static void main(String[] args) {
        System.out.println("\nЗадание 1.");
        Employee employee = new Employee("Иван Иванов", "Инженер", "ivanov@example.com", "123456789", 50000, 30);
        employee.printInfo();

        System.out.println("\nЗадание 2.");
        Employee[] persArray = new Employee[5];
        persArray[0] = new Employee("Илья Семенов", "Директор", "semenov@gmail.com", "892312312", 300000, 30);
        persArray[1] = new Employee("Пётр Сухов", "Менеджер", "suhov@gmail.com", "892312313", 40000, 35);
        persArray[2] = new Employee("Ольга Сидорова", "Разработчик", "sidorova@gmail.com", "892312314", 50000, 28);
        persArray[3] = new Employee("Артём Смирнов", "Аналитик", "smirnov@gmail.com", "892312315", 45000, 32);
        persArray[4] = new Employee("Дарья Кузнецова", "Тестировщик", "kuznetsovag@mail.com", "892312316", 35000, 25);

        for (Employee employee1 : persArray) {
            employee1.printInfo();
        }

        System.out.println("\nЗадание 3.\n");
        Park park = new Park("Центральный Парк", "Центр города");

        park.printParkInfo();

        Park.Attraction[] attractions = new Park.Attraction[3];

        attractions[0] = park.new Attraction("Американские горки", "10:00 - 18:00", 50.0);
        attractions[1] = park.new Attraction("Колесо обозрения", "09:00 - 20:00", 30.0);
        attractions[2] = park.new Attraction("Дом с привидениями", "11:00 - 22:00", 40.0);

        for (Park.Attraction attraction : attractions) {
            attraction.printInfo();
        }
    }
}
