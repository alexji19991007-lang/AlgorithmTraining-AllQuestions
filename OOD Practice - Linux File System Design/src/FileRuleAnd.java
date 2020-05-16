import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileRuleAnd implements FileRule {
    private final List<FileRule> andRules;

    public FileRuleAnd(FileRule ... andRules) {
        this.andRules = Arrays.asList(andRules);
    }

    @Override
    public boolean match(File f) {
        for (FileRule rule : andRules) {
            if (!rule.match(f)) {
                return false;
            }
        }
        return true;
    }
}

