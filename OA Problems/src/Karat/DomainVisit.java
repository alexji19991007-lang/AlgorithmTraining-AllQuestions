package Karat;

import java.util.*;

public class DomainVisit {
    public static void main(String[] args) {
        DomainVisit test = new DomainVisit();
        String[] purchasedIds = {"11", "22", "33", "55"};
        String[] clicks = {"11-abc,9999-99-99,Wool Coats", "22-abc,9999-99-99,I Love You", "33-abc,9999-99-99,Wool Coats",
                "99-abc,9999-99-99,Wool Coats", "88-abc,9999-99-99,I Love You", "44-abc,9999-99-99,Donald Trump", "55-abc,9999-99-99,Donald Trump",
                "77-abc,9999-99-99,Magic Is Real"};
        String[] allUserIPs = {"11,11-abc", "22,22-abc", "33,33-abc", "44,44-abc", "55,55-abc", "66,66-abc", "77, 77-abc", "88,88-abc", "99,99-abc"};
        test.adsClicks(purchasedIds, clicks, allUserIPs);
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<>();
        for (String domain : cpdomains) {
            String[] cpinfo = domain.split(" ");
            String[] fragments = cpinfo[1].split("\\.");
            int count = Integer.parseInt(cpinfo[0]);
            String cur = "";
            for (int i = fragments.length - 1; i >= 0; --i) {
                cur = fragments[i] + (i < fragments.length - 1 ? "." : "") + cur;
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }
        return res;
    }

    public String[] longestChain(String[] user0, String[] user1) {
        int len = 0, endingIndex = 0;
        int[][] memo = new int[user0.length + 1][user1.length + 1];
        for (int i = 0; i < user0.length; ++i) {
            for (int j = 0; j < user1.length; ++j) {
                memo[i + 1][j + 1] = user0[i].equals(user1[j]) ? memo[i][j] + 1 : 0;
                if (len < memo[i + 1][j + 1]) {
                    len = memo[i + 1][j + 1];
                    endingIndex = j;
                }
            }
        }
        int startingIndex = endingIndex - len + 1;
        String[] res = new String[len];
        for (int i = 0; i < len; ++i) {
            res[i] = user1[startingIndex + i];
        }
        return res;
    }

    public void adsClicks(String[] purchasedUsers, String[] adClicks, String[] allUserIPs) {
        Map<String, String> ipToAdName = new HashMap<>();
        Map<String, Advertisement> adNameToAd = new HashMap<>();
        for (String adClick : adClicks) {
            String[] oneClick = adClick.split(",");
            String ip = oneClick[0], adName = oneClick[2];
            Advertisement ad = adNameToAd.getOrDefault(adName, new Advertisement(adName));
            ad.clicks++;
            adNameToAd.put(adName, ad);
            ipToAdName.put(ip, adName);
        }
        Set<String> purchasedUserIds = new HashSet<>(Arrays.asList(purchasedUsers));
        List<String> purchasedIP = new ArrayList<>();
        for (String userIP : allUserIPs) {
            String[] oneUser = userIP.split(",");
            String userId = oneUser[0], ip = oneUser[1];
            if (purchasedUserIds.contains(userId)) {
                purchasedIP.add(ip);
            }
        }
        for (String ip : purchasedIP) {
            String adName = ipToAdName.get(ip);
            Advertisement ad = adNameToAd.get(adName);
            ad.purchases++;
            adNameToAd.put(adName, ad);
        }
        for (Map.Entry<String, Advertisement> entry : adNameToAd.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }

    static class Advertisement {
        String ad;
        int clicks;
        int purchases;

        public Advertisement(String ad) {
            this.ad = ad;
            this.clicks = 0;
            this.purchases = 0;
        }

        public String toString() {
            return this.purchases + " of " + this.clicks + "  " + ad;
        }
    }
}
