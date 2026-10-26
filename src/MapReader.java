import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapReader {
    public static boolean readMap(String fileName, Graph graph) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException error) {
            System.out.println("Error: file not found: " + fileName);
            return false;
        }
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineNumber++;
            String[] names = splitLine(line);
            if (isValidLine(line, names, lineNumber)) {
                graph.addPlace(names[0]);
                graph.addPlace(names[1]);
                graph.addRoad(names[0], names[1]);
            }
        }
        scanner.close();
        return true;
    }

    private static String[] splitLine(String line) {
        return line.trim().split("\\s+");
    }

    private static boolean isValidLine(String line, String[] names,
            int lineNumber) {
        if (line.trim().isEmpty()) {
            printSkipped(lineNumber, "line is blank");
            return false;
        }
        if (names.length < 2) {
            printSkipped(lineNumber, "only 1 name, need exactly 2");
            return false;
        }
        if (names.length > 2) {
            printSkipped(lineNumber, names.length + " names, need exactly 2");
            return false;
        }
        if (names[0].equals(names[1])) {
            printSkipped(lineNumber, "a place cannot connect to itself");
            return false;
        }
        return true;
    }

    private static void printSkipped(int lineNumber, String reason) {
        System.out.println("Line " + lineNumber + " skipped: " + reason);
    }
}
