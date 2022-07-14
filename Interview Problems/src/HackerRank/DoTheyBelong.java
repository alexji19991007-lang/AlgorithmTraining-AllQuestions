package HackerRank;

public class DoTheyBelong {
    public static void main(String[] args) {
        DoTheyBelong test = new DoTheyBelong();
        System.out.println(test.pointsBelong(2, 2, 7, 2, 5, 4, 4, 3, 7, 4));
    }

    public int pointsBelong(int x1, int y1, int x2, int y2, int x3, int y3, int xp, int yp, int xq, int yq) {
        double areaABC = getArea(x1, y1, x2, y2, x3, y3);
        if (areaABC == 0) {
            return 0;
        }
        double areaABP = getArea(x1, y1, x2, y2, xp, yp);
        double areaBCP = getArea(x2, y2, x3, y3, xp, yp);
        double areaACP = getArea(x1, y1, x3, y3, xp, yp);
        boolean isPInside = areaABC == areaABP + areaBCP + areaACP;
        double areaABQ = getArea(x1, y1, x2, y2, xq, yq);
        double areaBCQ = getArea(x2, y2, x3, y3, xq, yq);
        double areaACQ = getArea(x1, y1, x3, y3, xq, yq);
        boolean isQInside = areaABC == areaABQ + areaBCQ + areaACQ;
        if (!isPInside && !isQInside) {
            return 4;
        }
        if (isPInside && isQInside) {
            return 3;
        }
        return isPInside ? 1 : 2;
    }

    public double getArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }
}
