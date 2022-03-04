class Solution {
    /*
    Start 12:08am
    psuedocode done 12:25am
    End: 12:49. damn 40 minutes bruh
    
    
    if size = 1: return intervals
    
    comparator: 
    starts of intervals are NOT in order
    - mergesort algo- O(nlogn); n = intervals.length: Collections.sort
    merge into intervals
    
      0.    1.     2.    3
    [[1,3],[2,6],[6,10],[15,18]]
    [[1,10],[15,18]]
    
    mergedIntervals = []
    currArr = null
    
    for i in intervals:
        if can merge: currArr = merged interval
        else: add currArr to mergedIntervals, reset currArr
    
    if currArr is not null:
        add to mergedIntervals
    
    
    
    
    
    
    */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new IntervalComparator());
        
        List<Pair<Integer, Integer>> mergedIntervals = new ArrayList<Pair<Integer, Integer>>();
        // [[1,6], [8,10]]
        int[] currArr = null; // 
        for (int i = 0; i < intervals.length; i++) {
            if (currArr == null) {
                currArr = intervals[i];
            } else if (currArr[1] >= intervals[i][0]) { // can merge
                currArr[1] = Math.max(currArr[1], intervals[i][1]);
            } else { // can not merge
                mergedIntervals.add(new Pair<Integer, Integer>(currArr[0], currArr[1]));
                currArr = intervals[i];
            }
        }
        
        mergedIntervals.add(new Pair<Integer, Integer>(currArr[0], currArr[1]));
        
        int[][] mergedIntervalsArr = new int[mergedIntervals.size()][2];
        for (int i = 0; i < mergedIntervals.size(); i++) {
            Pair<Integer, Integer> pair = mergedIntervals.get(i);
            mergedIntervalsArr[i][0] = pair.getKey();
            mergedIntervalsArr[i][1] = pair.getValue();
        }
        return mergedIntervalsArr;
        
    }
    
    class IntervalComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) {
                return Integer.valueOf(a[0]).compareTo(Integer.valueOf(b[0]));
            } else {
                return Integer.valueOf(a[1]).compareTo(Integer.valueOf(b[1]));
            }
        }
        
        
    }
}