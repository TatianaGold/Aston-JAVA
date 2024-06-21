public class Park {

    private String name;
    private String location;

    public Park(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public class Attraction {
        private String attractionName;
        private String workingHours;
        private double cost;

        public Attraction(String attractionName, String workingHours, double cost) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost);
            System.out.println(); // Пустая строка для разделения информации об аттракционах
        }
    }

    public void printParkInfo() {
        System.out.println("Парк: " + name);
        System.out.println("Расположение: " + location);
        System.out.println(); // Пустая строка для разделения информации о парке и аттракционах
    }
}