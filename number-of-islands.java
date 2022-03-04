class Solution {
    /*
    Start: 10:47pm
    
    Naive/first thoughts
    
    if a 1 has no 1s to its top, bottom, left, or right --> it's an island
    need to find the "edge" pieces
    
    Brute force:
    store mxn matrix with -1=0, 1,2,3,4=island #
    keep track of current island #
    start at 0,0
    go through items until reach a 1
    start at a 1.
    reach top,bottom,left,right to find edges of island
    
    seems recursive or like bfs--> queue? bidirectional graph?
    keep track of the x,y axis and potential island #
    
    if island ends, how do you reach unreached lands/coordinates?
    A: iterate through to get another 1
    
    Start2 11:44pm end2 12:03am
    
    
    */
    public int numIslands(char[][] grid) {
        /*
        nested for loop
            start bfs if found a '1'
            return # of times started bfs
        */
        int numIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(i, j, grid);
                    numIslands++;
                }
            }
        }
        return numIslands;
    }
    
    private void bfs(int i, int j, char[][] grid) {
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(new Node(i, j));
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            i = node.i;
            j = node.j;
            grid[i][j] = '0';
            // put up, down, left, right into the queue if == 1
            if (i - 1 >= 0 && grid[i-1][j] == '1') {
                queue.add(new Node(i-1, j));
                grid[i-1][j] = '0';
            }
            if (i + 1 < grid.length && grid[i+1][j] == '1') {
                queue.add(new Node(i+1, j));
                grid[i+1][j] = '0';
            }
            if (j - 1 >= 0 && grid[i][j-1] == '1') {
                queue.add(new Node(i, j-1));
                grid[i][j-1] = '0';
            }
            if (j + 1 < grid[0].length && grid[i][j+1] == '1') {
                queue.add(new Node(i, j+1));
                grid[i][j+1] = '0';
            }
        }
    }
    
    private class Node {
        int i;
        int j;
        
        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}