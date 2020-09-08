package RobinhoodOnsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TradeMatch {
    public static void main(String[] args) {
        TradeMatch test = new TradeMatch();
        String[] houseTrades = {"AAPL,B,0100,ABC123", "AAPL,B,0100,ABC456", "FB,S,0050,CDC333"};
        String[] streetTrades = {"FB,B,0100,GBGGGG", "AAPL,B,0100,ABC456", "FB,B,0100,ABC123"};
        System.out.println(test.attributeMatch(houseTrades, streetTrades).toString());
    }

    public List<String> exactMatch(String[] houseTrades, String[] streetTrades) {
        Arrays.sort(houseTrades);
        Arrays.sort(streetTrades);
        List<String> unmatched = new ArrayList<>();
        int i = 0, j = 0;
        while (i < houseTrades.length && j < streetTrades.length) {
            int compare = houseTrades[i].compareTo(streetTrades[j]);
            if (compare == 0) {
                i++;
                j++;
            } else if (compare < 0) {
                unmatched.add(houseTrades[i++]);
            } else {
                unmatched.add(streetTrades[j++]);
            }
        }
        while (i < houseTrades.length) {
            unmatched.add(houseTrades[i++]);
        }
        while (j < streetTrades.length) {
            unmatched.add(streetTrades[j++]);
        }
        return unmatched;
    }

    // *********************************************************************************************

    public List<String> attributeMatch(String[] houseTrades, String[] streetTrades) {
        List<List<String>> unmatched = exactMatchHelper(houseTrades, streetTrades);
        List<String> unmatchedHouse = unmatched.get(0);
        List<String> unmatchedStreet = unmatched.get(1);
        List<String> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < unmatchedHouse.size() && j < unmatchedStreet.size()) {
            String[] houseTradeRecord = unmatchedHouse.get(i).split(",");
            String[] streetTradeRecord = unmatchedStreet.get(i).split(",");
            int x = 0;
            while (x < 4) {
                int compare = houseTradeRecord[x].compareTo(streetTradeRecord[x]);
                if (compare < 0 && x < 3) {
                    res.add(unmatchedHouse.get(i++));
                    break;
                } else if (compare > 0 && x < 3) {
                    res.add(unmatchedStreet.get(j++));
                    break;
                }
                x++;
            }
            if (x == 4) {
                i++;
                j++;
            }
        }
        while (i < unmatchedHouse.size()) {
            res.add(unmatchedHouse.get(i++));
        }
        while (j < unmatchedStreet.size()) {
            res.add(unmatchedStreet.get(j++));
        }
        return res;
    }

    private List<List<String>> exactMatchHelper(String[] houseTrades, String[] streetTrades) {
        Arrays.sort(houseTrades);
        Arrays.sort(streetTrades);
        List<String> unmatchedHouse = new ArrayList<>();
        List<String> unmatchedStreet = new ArrayList<>();
        int i = 0, j = 0;
        while (i < houseTrades.length && j < streetTrades.length) {
            int compare = houseTrades[i].compareTo(streetTrades[j]);
            if (compare == 0) {
                i++;
                j++;
            } else if (compare < 0) {
                unmatchedHouse.add(houseTrades[i++]);
            } else {
                unmatchedStreet.add(streetTrades[j++]);
            }
        }
        while (i < houseTrades.length) {
            unmatchedHouse.add(houseTrades[i++]);
        }
        while (j < streetTrades.length) {
            unmatchedStreet.add(streetTrades[j++]);
        }
        List<List<String>> unmatched = new ArrayList<>();
        unmatched.add(unmatchedHouse);
        unmatched.add(unmatchedStreet);
        return unmatched;
    }

    // *********************************************************************************************

    public List<String> offsettingMatch(String[] houseTrades, String[] streetTrades) {
        List<List<String>> remaining = attributeMatchHelper(houseTrades, streetTrades);
        List<String> remainingHouse = remaining.get(0);
        List<String> remainingStreet = remaining.get(1);
        List<String> res = new ArrayList<>();
        int i = 0, j = 1;
        while (j < remainingHouse.size()) {
            String[] houseRecord1 = remainingHouse.get(i).split(",");
            String[] houseRecord2 = remainingHouse.get(j).split(",");
            if (!(houseRecord1[0].equals(houseRecord2[0]) && houseRecord1[2].equals(houseRecord2[2]) && !houseRecord1[1].equals(houseRecord2[1]))) {
                while (i < j) {
                    res.add(remainingHouse.get(i++));
                }
            } else {
                // 这里有问题
                i++;
            }
            j++;
        }
        return res;
    }

    public List<List<String>> attributeMatchHelper(String[] houseTrades, String[] streetTrades) {
        List<List<String>> unmatched = exactMatchHelper(houseTrades, streetTrades);
        List<String> notExactHouse = unmatched.get(0);
        List<String> notExactStreet = unmatched.get(1);
        List<String> notAttributeHouse = new ArrayList<>();
        List<String> notAttributeStreet = new ArrayList<>();
        int i = 0, j = 0;
        while (i < notExactHouse.size() && j < notExactStreet.size()) {
            String[] houseTradeRecord = notExactHouse.get(i).split(",");
            String[] streetTradeRecord = notExactStreet.get(i).split(",");
            int x = 0;
            while (x < 4) {
                int compare = houseTradeRecord[x].compareTo(streetTradeRecord[x]);
                if (compare < 0 && x < 3) {
                    notAttributeHouse.add(notExactHouse.get(i++));
                    break;
                } else if (compare > 0 && x < 3) {
                    notAttributeStreet.add(notExactStreet.get(j++));
                    break;
                }
                x++;
            }
            if (x == 4) {
                i++;
                j++;
            }
        }
        while (i < notExactHouse.size()) {
            notAttributeHouse.add(notExactHouse.get(i++));
        }
        while (j < notExactStreet.size()) {
            notAttributeStreet.add(notExactStreet.get(j++));
        }
        List<List<String>> res = new ArrayList<>();
        res.add(notAttributeHouse);
        res.add(notAttributeStreet);
        return res;
    }
}
