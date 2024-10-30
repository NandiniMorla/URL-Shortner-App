import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class URLShortener {
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private HashMap<String, String> urlMap;  // To store shortened and original URL mappings
    private HashMap<String, String> shortUrlMap;  // To store reverse mappings for uniqueness

    public URLShortener() {
        urlMap = new HashMap<>();
        shortUrlMap = new HashMap<>();
    }

    // Method to shorten a long URL
    public String shortenURL(String longURL) {
        if (urlMap.containsKey(longURL)) {
            System.out.println("This URL is already shortened.");
            return urlMap.get(longURL);
        }

        String shortURL = generateShortURL();
        
        // Check for collision and generate a new short URL if needed
        while (shortUrlMap.containsKey(shortURL)) {
            shortURL = generateShortURL();
        }

        urlMap.put(longURL, shortURL);
        shortUrlMap.put(shortURL, longURL);

        System.out.println("URL successfully shortened: " + shortURL);
        return shortURL;
    }

    // Method to expand a short URL to the original long URL
    public String expandURL(String shortURL) {
        if (shortUrlMap.containsKey(shortURL)) {
            System.out.println("Original URL found.");
            return shortUrlMap.get(shortURL);
        } else {
            System.out.println("Error: Short URL not found.");
            return null;
        }
    }

    // Generate a short URL using random characters from CHAR_SET
    private String generateShortURL() {
        StringBuilder shortURL = new StringBuilder(SHORT_URL_LENGTH);
        Random random = new Random();

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHAR_SET.length());
            shortURL.append(CHAR_SET.charAt(index));
        }
        
        return shortURL.toString();
    }
}

public class URLShortenerApp {
    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the URL Shortener!");

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Shorten a URL");
            System.out.println("2. Expand a short URL");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter a URL to shorten: ");
                    String longURL = scanner.nextLine();
                    String shortURL = urlShortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                    break;

                case 2:
                    System.out.print("Enter a short URL to expand: ");
                    String shortURLToExpand = scanner.nextLine();
                    String originalURL = urlShortener.expandURL(shortURLToExpand);
                    if (originalURL != null) {
                        System.out.println("Original URL: " + originalURL);
                    }
                    break;

                case 3:
                    System.out.println("Exiting the program.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}






