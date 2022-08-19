package Amazon;

public class ChooseFleetOfVehiclesWith2And4Wheels {
    public int[] chooseFleets(int[] wheels) {
        int[] ans = new int[wheels.length];
        for (int i = 0; i < wheels.length; i++) {
            ans[i] = wheels[i] % 2 == 0 ? wheels[i] / 4 + 1 : 0;
        }
        return ans;
    }
}
