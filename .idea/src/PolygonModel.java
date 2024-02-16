import java.awt.Polygon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PolygonModel {
    public Polygon readPolygon(String filepath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filepath));
        int numVertices = Integer.parseInt(scanner.nextLine());
        int[] x = new int[numVertices];
        int[] y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            String line = scanner.nextLine();
//            System.out.println(line);
            line = line.replace("(", "");
            line = line.replace(")", "");
//            System.out.println(line);
            String[] parts = line.split(",\\s*");
//            System.out.println(parts);
//            System.out.println(parts.length);
//            System.out.println(parts[0]);
//            System.out.println(parts[1]);
            x[i] = Integer.parseInt(parts[0]);
            y[i] = Integer.parseInt(parts[1]);
        }
        return new Polygon(x, y, numVertices);
    }

    public boolean isConvex(Polygon polygon) {
        boolean isConvex = true;
        int index = 0;
        while (isConvex && index < polygon.npoints) {
            int p2 = (index + 1) % polygon.npoints;
            Vector v1 = new Vector(polygon.xpoints[index] - polygon.xpoints[p2],
                    polygon.ypoints[index] - polygon.ypoints[p2]);
            int p3 = (index + 2) % polygon.npoints;
            Vector v2 = new Vector(polygon.xpoints[p2] - polygon.xpoints[p3],
                    polygon.ypoints[p2] - polygon.ypoints[p3]);
            double crossProduct = v1.x * v2.y - v1.y * v2.x;
            if (crossProduct < 0) {
                isConvex = false;
            }
            index++;
        }
        return isConvex;
    }

    private class Vector {
        double x;
        double y;

        public Vector(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
