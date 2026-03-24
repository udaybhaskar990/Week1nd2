
package PACKAGE_NAME;
import java.util.*;
 class UsernameChecker {

    HashMap<String, Integer> usernameMap = new HashMap<>();
    HashMap<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {

        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameMap.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {

            String newName = username + i;

            if (!usernameMap.containsKey(newName)) {
                suggestions.add(newName);
            }
        }

        return suggestions;
    }

    public static void main(String[] args) {

        UsernameChecker system = new UsernameChecker();

        system.usernameMap.put("john_doe", 1);

        System.out.println(system.checkAvailability("john_doe"));
        System.out.println(system.checkAvailability("jane_smith"));

        System.out.println(system.suggestAlternatives("john_doe"));
    }
}