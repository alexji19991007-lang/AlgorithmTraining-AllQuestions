import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileRuleOr implements FileRule {
    private final List<FileRule> orRules;

    public FileRuleOr(FileRule ... orRules) {
        this.orRules = Arrays.asList(orRules);
    }

    @Override
    public boolean match(File f) {
        for (FileRule rule : orRules) {
            if (rule.match(f)) {
                return true;
            }
        }
        return false;
    }
}
