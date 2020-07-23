import java.util.*;

//public class FileSystem {
//    private final Directory root;
//
//    public FileSystem() {
//        root = new Directory("/", null);
//    }
//
//    private List<Entry> resolve(String path) {
//        assert path.startsWith("/");
//        String[] components = path.substring(1).split("/");
//        List<Entry> entries = new ArrayList<>(components.length + 1);
//        entries.add(root);
//
//        Entry entry = root;
//        for (String component : components) {
//            if (!(entry instanceof Directory)) {
//                throw new IllegalArgumentException("Invalid Path: " + path);
//            }
//            if (!component.isEmpty()) {
//                entry = ((Directory) entry).getChild(component);
//                entries.add(entry);
//            }
//        }
//    }
//}
