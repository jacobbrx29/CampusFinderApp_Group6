import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Reads and validates map files (supports 2-word or weighted 3-word lines).
public class MapReader {
    public static boolean readMap(String fileName, Graph graph) {
        Scanner scanner;
        try {
            scanner = new Scanner(resolveFile(fileName));
        } catch (FileNotFoundException error) {
            System.out.println("Error: file not found: " + fileName);
            return false;
        }
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineNumber++;
            String[] parts = splitLine(line);
            if (isValidLine(line, parts, lineNumber)) {
                int distance = 1;
                if (parts.length == 3) {
                    distance = parseDistance(parts[2]);
                }
                graph.addPlace(parts[0]);
                graph.addPlace(parts[1]);
                graph.addRoad(parts[0], parts[1], distance);
            }
        }
        scanner.close();
        return true;
    }

    // Checks common project folders so short names and IDE runs still work.
    private static File resolveFile(String fileName) {
        String[] candidates = {
            fileName,
            "data/" + fileName,
            "../" + fileName,
            "../data/" + fileName,
            "CampusFinderApp_Group6/" + fileName,
            "CampusFinderApp_Group6/data/" + fileName
        };
        for (int i = 0; i < candidates.length; i++) {
            File file = new File(candidates[i]);
            if (file.exists() && file.isFile()) {
                return file;
            }
        }
        return new File(fileName);
    }

    private static String[] splitLine(String line) {
        return line.trim().split("\\s+");
    }

    // Validates line formatting safely so malformed input never crashes.
    private static boolean isValidLine(String line, String[] parts,
            int lineNumber) {
        if (line.trim().isEmpty()) {
            printSkipped(lineNumber, "line is blank");
            return false;
        }
        if (parts.length < 2) {
            printSkipped(lineNumber, "need at least 2 place names");
            return false;
        }
        if (parts.length > 3) {
            printSkipped(lineNumber, "too many values on line");
            return false;
        }
        if (parts[0].equals(parts[1])) {
            printSkipped(lineNumber, "a place cannot connect to itself");
            return false;
        }
        if (parts.length == 3 && parseDistance(parts[2]) == -1) {
            printSkipped(lineNumber, "distance must be a positive number");
            return false;
        }
        return true;
    }

    private static int parseDistance(String text) {
        if (text.isEmpty() || text.length() > 6) {
            return -1;
        }
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch < '0' || ch > '9') {
                return -1;
            }
        }
        int value = Integer.parseInt(text);
        if (value <= 0) {
            return -1;
        }
        return value;
    }

    private static void printSkipped(int lineNumber, String reason) {
        System.out.println("Line " + lineNumber + " skipped: " + reason);
    }
}
