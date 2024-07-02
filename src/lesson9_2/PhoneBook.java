package lesson9_2;

import java.util.*;

class PhoneBook {
    private Map<String, List<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        phoneBook.computeIfAbsent(lastName, k -> new ArrayList<>()).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, Collections.emptyList());
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "123456");
        phoneBook.add("Петров", "654321");
        phoneBook.add("Иванов", "111111");
        phoneBook.add("Сидоров", "222222");

        System.out.println("\nТелефоны для Иванов: " + phoneBook.get("Иванов"));
        System.out.println("\nТелефоны для Петров: " + phoneBook.get("Петров"));
        System.out.println("\nТелефоны для Сидоров: " + phoneBook.get("Сидоров"));
        System.out.println("\nТелефоны для Кузнецов: " + phoneBook.get("Кузнецов"));
    }
}