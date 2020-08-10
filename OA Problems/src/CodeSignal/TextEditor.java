package CodeSignal;

import java.util.ArrayList;
import java.util.List;

public class TextEditor {
    private final List<String> prevText;
    private String clipBoard;
    private String curText;

    public static void main(String[] args) {
        TextEditor test = new TextEditor();
        String[] ops = {"INSERT Code", "INSERT Signal", "DELETE", "UNDO", "COPY 0", "PASTE"};
        System.out.println(test.doOperations(ops));
    }

    public TextEditor() {
        prevText = new ArrayList<>();
        clipBoard = "";
        curText = "";
    }

    public String doOperations(String[] ops) {
        String res = "";
        for (String s : ops) {
            String[] curOp = s.split(" ");
            String op = curOp[0];
            if (curOp.length == 2) {
                String param = curOp[1];
                if (op.equals("INSERT")) {
                    res = insert(param);
                } else {
                    int index = Integer.parseInt(param);
                    copy(index);
                }
            } else {
                if (op.equals("DELETE")) {
                    res = delete();
                } else if (op.equals("PASTE")) {
                    res = paste();
                } else {
                    res = undo();
                }
            }
        }
        return res;
    }

    public String insert(String s) {
        if (s.length() == 20) {
            return curText;
        }
        prevText.add(curText);
        curText += s;
        if (s.length() > 20) {
            curText = curText.substring(0, 20);
        }
        return curText;
    }

    public String delete() {
        if (curText.length() == 0) {
            return curText;
        }
        prevText.add(curText);
        curText = curText.substring(0, curText.length() - 1);
        return curText;
    }

    public void copy(int index) {
        if (index >= curText.length()) {
            return;
        }
        clipBoard = curText.substring(index);
    }

    public String paste() {
        insert(clipBoard);
        return curText;
    }

    public String undo() {
        if (prevText.size() == 0) {
            return "";
        }
        curText = prevText.get(prevText.size() - 1);
        prevText.remove(prevText.size() - 1);
        return curText;
    }
}
