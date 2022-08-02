package HackerRank;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class PassingIntersection {
    public static void main(String[] args) {
        PassingIntersection test = new PassingIntersection();
        int[] arrivalTime = {0, 0, 0, 0};
        int[] from = {0, 1, 1, 0};
        System.out.println(Arrays.toString(test.passing(arrivalTime, from)));
    }

    public int[] passing(int[] arrivalTime, int[] from) {
        int[] res = new int[arrivalTime.length];
        Queue<int[]> mainStreet = new ArrayDeque<>();
        Queue<int[]> firstStreet = new ArrayDeque<>();
        for (int i = 0; i < arrivalTime.length; ++i)  {
            if (from[i] == 0) {
                mainStreet.offer(new int[] {i, arrivalTime[i]});
            } else {
                firstStreet.offer(new int[] {i, arrivalTime[i]});
            }
        }
        int curTime = 0;
        boolean isFirstStreet = true;
        while (!mainStreet.isEmpty() && !firstStreet.isEmpty()) {
            int[] mainCar = mainStreet.peek();
            int[] firstCar = firstStreet.peek();
            if (mainCar[1] > curTime && firstCar[1] > curTime) {
                curTime++;
                isFirstStreet = true;
                continue;
            }
            if (mainCar[1] <= curTime && firstCar[1] <= curTime) {
                if (isFirstStreet) {
                    res[firstCar[0]] = curTime;
                    firstStreet.poll();
                } else {
                    res[mainCar[0]] = curTime;
                    mainStreet.poll();
                }
            } else if (mainCar[1] <= curTime) {
                res[mainCar[0]] = curTime;
                isFirstStreet = false;
                mainStreet.poll();
            } else {
                res[firstCar[0]] = curTime;
                isFirstStreet = true;
                firstStreet.poll();
            }
            curTime++;
        }
        while (!mainStreet.isEmpty()) {
            int[] mainCar = mainStreet.poll();
            if (mainCar[0] <= curTime) {
                res[mainCar[0]] = curTime;
            } else {
                res[mainCar[0]] = mainCar[1];
                curTime = mainCar[1];
            }
            curTime++;
        }
        while (!firstStreet.isEmpty()) {
            int[] firstCar = firstStreet.poll();
            if (firstCar[0] <= curTime) {
                res[firstCar[0]] = curTime;
            } else {
                res[firstCar[0]] = firstCar[1];
                curTime = firstCar[1];
            }
            curTime++;
        }
        return res;
    }
}
