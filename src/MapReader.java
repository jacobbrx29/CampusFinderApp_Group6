import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapReader {
    public static boolean readMap(String fileName) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException error) {
            System.out.println("Error: file not found: " + fileName);
            return false;
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] names = splitLine(line);
            System.out.println("Read road: " + names[0] + " - " + names[1]);
        }
        scanner.close();
        return true;
    }

    private static String[] splitLine(String line) {
        return line.trim().split("\\s+");
    }
}
