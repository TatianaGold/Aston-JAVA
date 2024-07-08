import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] words = {
                "кот", "кролик", "кот", "улитка", "кролик", "хомяк",
                "рыбка", "улитка", "кот", "рыбка", "попугай", "попугай",
                "кролик", "хомяк", "попугай", "кот", "кролик", "хомяк"
        };

        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        System.out.println("\nУникальные слова: " + wordCount.keySet());
        System.out.println("\nЧастота каждого слова: ");

        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


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
