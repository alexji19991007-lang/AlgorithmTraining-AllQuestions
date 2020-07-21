public class SearchInUnknownSizedArray {
//    public int search(Dictionary dict, int target) {
//        // Write your solution here
//        if (dict.get(0) == null) {
//            return -1;
//        }
//        if (dict.get(0) != null && dict.get(0) == target) {
//            return 0;
//        }
//        int end = 1;
//        while (dict.get(end) != null && dict.get(end) < target) {
//            end *= 2;
//        }
//        int left = 0, right = end;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (dict.get(mid) == null || dict.get(mid) > target) {
//                right = mid - 1;
//            } else if (dict.get(mid) < target) {
//                left = mid + 1;
//            } else {
//                return mid;
//            }
//        }
//        return -1;
//    }


    // 2倍2倍跳好还是10倍10倍跳好？
    // 定性分析：
    // To jump out: 10 times is better
    // To jump in: 2 times is better since the over-shot is shorter for 2 times
    //  定量分析：         2 times             10 times
    // To jump out       log_2(n)             log_10(n)
    // To jump in        log_2(2n)            log_2(10n)
    // 2 times - 10 times = log_2(n) + log_2(2n) - (log_10(n) + log_2(10n)) = a
    // Take n as large as infinity, if a < 0, 2 times is better. Otherwise, 10 times is better
}
