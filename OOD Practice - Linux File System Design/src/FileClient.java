// Implement Linux find command as an API. The API will support finding files that:
//        Files that have a given size requirement.
//        Files with a certain naming pattern.
// Focus on 2 uses cases at first:
//        Find all files over 5 MB somewhere under a directory.
//        Find all XML files somewhere under a directory.
// Create a library that lets me do this easily.
// Keep in mind that these are just 2 uses cases and that the library should be flexible.

public class FileClient {
    public static void main(String[] args) {
        FileRule rule = new FileRuleOr(
                new FileRuleExtension("java"),
                new FileRuleAnd(
                        new FileRuleExtension("zip"),
                        new FileRuleSize(5711472, FileRuleSizeOp.EQ)
                )
        );
        FindFile.find("/home/local/ANT/lestrozi", rule);
    }
}
