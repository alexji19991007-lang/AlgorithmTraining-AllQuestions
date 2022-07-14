package HackerRank;

public class SprintTraining {
    public static void main(String[] args) {
        int[] sprints = {2, 4, 1, 3};
        SprintTraining test = new SprintTraining();
        System.out.println(test.sprintTraining(5, sprints));
    }

    public int sprintTraining(int n, int[] sprints) {
        int[] markers = new int[n];
        for (int i = 0; i < sprints.length - 1; ++i) {
            int leftMarker = Math.min(sprints[i], sprints[i + 1]) - 1;
            int rightMarker = Math.max(sprints[i], sprints[i + 1]) - 1;
            markers[leftMarker]++;
            if (rightMarker + 1 < n) {
                markers[rightMarker + 1]--;
            }
        }
        int maxVisit = markers[0];
        int res = 0;
        for (int i = 1; i < n; ++i) {
            markers[i] += markers[i - 1];
            if (markers[i] > maxVisit) {
                maxVisit = markers[i];
                res = i;
            }
        }
        return res + 1;
    }
}
