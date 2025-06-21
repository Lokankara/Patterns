package patterns.set;

import java.util.HashSet;

public class PairSum {
    public boolean hasPairWithSum(int[] array, int targetSum) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : array) {
            if (set.contains(targetSum - i)) {
                return true;
            }
            set.add(i);
        }
        return false;
    }
}
