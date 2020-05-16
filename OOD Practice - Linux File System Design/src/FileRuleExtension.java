import java.io.File;

public class FileRuleExtension implements FileRule {
    final private String ext;

    public FileRuleExtension(String ext) {
        this.ext = "." + ext;
    }

    @Override
    public boolean match(File f) {
        return f.getName().endsWith(ext);
    }
}

