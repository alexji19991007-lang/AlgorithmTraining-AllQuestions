import java.io.File;

public class FileRuleSize implements FileRule {
    private final int size;
    private final FileRuleSizeOp op;

    public FileRuleSize(int size, FileRuleSizeOp op) {
        this.size = size;
        this.op = op;
    }

    @Override
    public boolean match(File f) {
        switch (op) {
            case LT:
                return f.length() < size;
            case LTE:
                return f.length() <= size;
            case EQ:
                return f.length() == size;
            case GT:
                return f.length() > size;
            case GTE:
                return f.length() >= size;
        }

        return false;
    }

}
