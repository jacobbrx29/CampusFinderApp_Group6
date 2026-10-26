public class Main {
    public static void main(String[] args) {
        String fileName = "data/campus_map.txt";
        if (args.length > 0) {
            fileName = args[0];
        }
        MapReader.readMap(fileName);
    }
}
