import java.util.*;

// LeetCode 1152
public class A009_AnalyzeUserVisitorPattern {
    // TC: O(n ^ 3)
    // SC: O(n ^ 3)
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Visit> visitList = new ArrayList<>();
        for (int i = 0; i < username.length; ++i) {
            visitList.add(new Visit(username[i], timestamp[i], website[i]));
        }

        visitList.sort(Comparator.comparingInt(v -> v.time));

        Map<String, List<String>> userWebsitesMap = new HashMap<>();
        for (Visit v : visitList) {
            userWebsitesMap.putIfAbsent(v.user, new ArrayList<>());
            userWebsitesMap.get(v.user).add(v.website);
        }

        Map<List<String>, Integer> sequenceUserFrequencyMap = new HashMap<>();
        for (List<String> websiteList : userWebsitesMap.values()) {
            if (websiteList.size() < 3) {
                continue;
            }
            Set<List<String>> sequenceSet = generate3Seq(websiteList);
            for (List<String> seq : sequenceSet) {
                sequenceUserFrequencyMap.put(seq, sequenceUserFrequencyMap.getOrDefault(seq, 0) + 1);
            }
        }
        List<String> res = new ArrayList<>();
        int max = 0;
        for (Map.Entry<List<String>, Integer> entry : sequenceUserFrequencyMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                res = entry.getKey();
            } else if (entry.getValue() == max) {
                if (entry.getKey().toString().compareTo(res.toString()) < 0) {
                    res = entry.getKey();
                }
            }
        }
        return res;
    }

    public Set<List<String>> generate3Seq(List<String> websiteList) {
        Set<List<String>> setOfListSeq = new HashSet<>();
        for (int i = 0; i < websiteList.size(); ++i) {
            for (int j = i + 1; j < websiteList.size(); ++j) {
                for (int k = j + 1; k < websiteList.size(); ++k) {
                    List<String> list = new ArrayList<>();
                    list.add(websiteList.get(i));
                    list.add(websiteList.get(j));
                    list.add(websiteList.get(k));
                    setOfListSeq.add(list);
                }
            }
        }
        return setOfListSeq;
    }

    static class Visit {
        String user;
        int time;
        String website;

        public Visit(String user, int time, String website) {
            this.user = user;
            this.time = time;
            this.website = website;
        }
    }
}
