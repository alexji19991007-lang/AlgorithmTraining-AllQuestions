import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IMC_Artifacts {
    public static void main(String[] args) {
        IMC_Artifacts test = new IMC_Artifacts();
        String artifacts = "1A 1B,2C 2C";
        String searched = "1B";
        System.out.println(Arrays.toString(test.solution(3, artifacts, searched)));

        String artifacts1 = "1B 3D,9A 11A,11B 13C";
        String searched1 = "1B 2B 3B 1C 2C 3C 1D 2D 3D 9A 10A 11B 12B 13B 14B 15B 11C 12C";
        System.out.println(Arrays.toString(test.solution(13, artifacts1, searched1)));
    }

    public int[] solution(int N, String artifacts, String searched) {
        String[] artifactArray = artifacts.split(",");
        Artifact[] myArtifacts = new Artifact[artifactArray.length];
        for (int i = 0; i < artifactArray.length; ++i) {
            String curArtifact = artifactArray[i];
            String[] position = curArtifact.split(" ");
            myArtifacts[i] = new Artifact(position[0], position[1]);
        }
        int discovered = 0, reconstructed = 0;
        String[] searchedPos = searched.split(" ");
        for (String pos : searchedPos) {
            Coordinate curPos = new Coordinate(pos);
            for (Artifact a : myArtifacts) {
                if (a.isOnePiece(curPos)) {
                    discovered += a.hasBeenDiscoveredPiece() ? 0 : 1;
                    boolean canBeReconstructed = a.discoverPiece(curPos);
                    discovered -= canBeReconstructed ? 1 : 0;
                    reconstructed += canBeReconstructed ? 1 : 0;
                }
            }
        }
        return new int[]{reconstructed, discovered};
    }

    static class Artifact {
        Coordinate topLeft;
        Coordinate botRight;
        int size;
        Set<Coordinate> discovered;
        boolean canBeReconstructed;

        public Artifact(String TL, String BR) {
            int rowTL = Integer.parseInt(TL.substring(0, TL.length() - 1));
            int colTL = TL.charAt(TL.length() - 1) - 'A' + 1;
            int rowBR = Integer.parseInt(BR.substring(0, BR.length() - 1));
            int colBR = BR.charAt(BR.length() - 1) - 'A' + 1;
            this.topLeft = new Coordinate(rowTL, colTL);
            this.botRight = new Coordinate(rowBR, colBR);
            this.size = (rowBR - rowTL + 1) * (colBR - colTL + 1);
            this.discovered = new HashSet<>();
            this.canBeReconstructed = false;
        }

        public boolean isOnePiece(Coordinate x) {
            return x.row >= topLeft.row && x.col >= topLeft.col && x.row <= botRight.row && x.col <= botRight.col;
        }

        public boolean discoverPiece(Coordinate x) {
            discovered.add(x);
            if (discovered.size() == size) {
                canBeReconstructed = true;
            }
            return canBeReconstructed;
        }

        public boolean hasBeenDiscoveredPiece() {
            return discovered.size() > 0;
        }
    }

    static class Coordinate {
        int row;
        int col;

        public Coordinate(int x, int y) {
            this.row = x;
            this.col = y;
        }

        public Coordinate(String c) {
            this.row = Integer.parseInt(c.substring(0, c.length() - 1));
            this.col = c.charAt(c.length() - 1) - 'A' + 1;
        }
    }
}
