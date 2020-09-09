package CodeSignal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RectangleBoxes {
    public static void main(String[] args) {
        RectangleBoxes test = new RectangleBoxes();
        int[][] operations = {{0, 1, 3}, {0, 4, 2}, {1, 3, 4}, {1, 3, 2}};
        System.out.println(Arrays.toString(test.rectangleBoxes(operations)));
    }

    public boolean[] rectangleBoxes(int[][] operations) {
//        Queue<Rectangle> savedRectangles = new PriorityQueue<>((r1, r2) -> {
//            if (r1.equals(r2)) {
//                return 0;
//            } else if (r1.area != r2.area) {
//                return r1.area > r2.area ? -1 : 1;
//            } else {
//                return Math.max(r1.length, r1.width) > Math.max(r2.length, r2.width) ? -1 : 1;
//            }
//        });
        List<Rectangle> savedRectangles = new ArrayList<>();
        List<Boolean> output = new ArrayList<>();
        for (int[] op : operations) {
            if (op[0] == 0) {
                Rectangle r = new Rectangle(op[1], op[2]);
                savedRectangles.add(r);
            } else if (savedRectangles.isEmpty()) {
                output.add(true);
            } else {
                for (int i = 0; i < savedRectangles.size(); ++i) {
                    if (!savedRectangles.get(i).canFitIn(op[1], op[2])) {
                        output.add(false);
                        break;
                    }
                    if (i == savedRectangles.size() - 1) {
                        output.add(true);
                    }
                }
            }
        }
        boolean[] res = new boolean[output.size()];
        for (int i = 0; i < output.size(); ++i) {
            res[i] = output.get(i);
        }
        return res;
    }

    static class Rectangle {
        int length;
        int width;
        int area;

        public Rectangle(int length, int width) {
            this.length = length;
            this.width = width;
            this.area = length * width;
        }

        public boolean canFitIn(int len1, int len2) {
            return area <= len1 * len2 && ((length <= len1 && width <= len2) || (length <= len2 && width <= len1));
        }
    }
}
