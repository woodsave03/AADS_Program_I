import java.awt.Polygon;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PolygonModel helper = new PolygonModel();
        boolean done = false;
        while (!done) {
            System.out.println("Enter the file path of the polygon (_C_lose Program): ");
            String filepath = ".idea/Files/" + scanner.nextLine() + ".txt";
            if (filepath.equals(".idea/Files/C.txt")) {
                done = true;
                continue;
            }
            try {
                Polygon polygon = helper.readPolygon(filepath);
                System.out.println("The polygon has " + polygon.npoints + " vertices.");
                if (helper.isConvex(polygon)) {
                    System.out.println("The polygon is convex.");
                } else {
                    System.out.println("The polygon is not convex.");
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Try again.");
            }
        }
    }
}
