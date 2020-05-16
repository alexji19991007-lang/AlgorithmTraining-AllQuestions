import java.io.File;
import java.util.Objects;

public class FindFile {
    public static void find(String dir, FileRule rule) {
        File baseDir = new File(dir);
        if (baseDir.isDirectory()) {
            for (File f : Objects.requireNonNull(baseDir.listFiles())) {
                if (rule.match(f)) {
                    System.out.println(f.getName());
                }
            }
        }
    }
}