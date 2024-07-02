package lesson9_1;

import java.util.*;

public class WordArray {
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
    }
}


