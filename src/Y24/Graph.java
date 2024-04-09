package Y24;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
* 图、网格
* DFS思路：可以考虑这是一颗四叉树，以图中一个格子为基准点(i,j)，下次可以访问的位置为上下左右，即(i-1,j)、(i+1,j)、(i,j-1)、(i,j+1)
* 树的DFS判断出口是 root==null，图的出口是超出网格的范围，即i<0 || j<0 || i>grid.length || j> grid[i].length
* 在递归的过程，还要标记是否访问过该位置，不然会重复访问，例如设置grid[i][j]=n (!1)
*
* */
public class Graph {

    //797. 所有可能的路径
    //dfs传递当前访问节点的value 添加进入path，下次访问 graph[value]
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> list=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        path.add(0);
        pathHelper(list,path,graph,0);
        return list;
    }
    public static void pathHelper(List<List<Integer>> list,List<Integer> path,int[][] graph,int node){
        //完成0到(n-1)的路径,最后一个子数组为空，不需要添加node进入path
        if (node==graph.length-1){
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i=0;i<graph[node].length;i++){
            path.add(graph[node][i]);
            pathHelper(list,path,graph,graph[node][i]);
            path.remove(path.size()-1);
        }
    }
    //695. 岛屿的最大面积
    //以每一个为1的陆地进行dfs扩展
    public static int maxAreaOfIsland(int[][] grid) {
        int res=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j]==1){
                    res=Math.max(res,area(grid,i,j));
                }
            }
        }
        return res;
    }
    //200. 岛屿数量
    //每次循环 当前位置为1时(陆地)，开始dfs，搜索到的位置都设置为2(感染)，后续不能以此位置开始dfs，因为已经计算过
    public static int numIslands(char[][] grid) {
        int res=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j]=='1'){
                    res++;
                    dfsGrid(grid,i,j);
                }
            }
        }
        return res;
    }
    public static void dfsGrid(char[][] grid,int i,int j){
        if (!inGrid(grid,i,j)){
            return ;
        }
        if (grid[i][j]!='1'){
            return ;
        }
        grid[i][j]='2';
        dfsGrid(grid,i-1,j);
        dfsGrid(grid,i+1,j);
        dfsGrid(grid,i,j-1);
        dfsGrid(grid,i,j+1);
    }
    public static int area(int[][] grid,int i,int j){
        if (!inGrid(grid,i,j)){
            return 0;
        }
        if (grid[i][j]!=1){
            return 0;
        }
        //访问标记
        grid[i][j]=2;
        return 1+area(grid,i-1,j)+area(grid,i+1,j)
                +area(grid,i,j-1)+area(grid,i,j+1);
    }
    public static boolean inGrid(char[][] grid, int i, int j){
        return i>=0 && i<grid.length &&  j>=0 && j<grid[i].length;
    }
    public static boolean inGrid(int[][] grid, int i, int j){
        return i>=0 && i<grid.length &&  j>=0 && j<grid[i].length;
    }
    //994. 腐烂的橘子
    //队列存储位置 (i,j)，可以使用Queue<int[]> i=arr[0],j=arr[1]
    //从何处开始？将所有腐烂状态的橘子入队，判断上下左右是否有新鲜橘子，有：更改腐烂状态，时间+1
    public static int orangesRotting(int[][] grid) {
        Queue<int[]> queue=new ArrayDeque<>();
        int n=grid.length;
        //新鲜橘子数量
        int c=0;
        for (int i=0;i<n;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j]==2){
                    queue.add(new int[]{i,j});
                }else if (grid[i][j]==1){
                    c++;
                }
            }
        }
        int minutes=0;
        while (!queue.isEmpty()){
            boolean has=false;
            //遍历当前队列的烂橘子，不计算新的烂橘子
            int size=queue.size();
            while (!queue.isEmpty() && size-->0){
                int[] arr= queue.poll();
                int i=arr[0];
                int j=arr[1];
                //上
                if (inGrid(grid,i-1,j) && grid[i-1][j]==1){
                    grid[i-1][j]=2;
                    has=true;
                    queue.add(new int[]{i-1,j});
                    c--;
                }
                //下
                if (inGrid(grid,i+1,j) && grid[i+1][j]==1){
                    grid[i+1][j]=2;
                    has=true;
                    queue.add(new int[]{i+1,j});
                    c--;
                }
                //左
                if (inGrid(grid,i,j-1) && grid[i][j-1]==1){
                    grid[i][j-1]=2;
                    has=true;
                    queue.add(new int[]{i,j-1});
                    c--;
                }
                //右
                if (inGrid(grid,i,j+1) && grid[i][j+1]==1){
                    grid[i][j+1]=2;
                    has=true;
                    queue.add(new int[]{i,j+1});
                    c--;
                }
            }
            if (has){
                minutes++;
            }
        }
        return c>0?-1:minutes;
    }
    public static void main(String[] args) {
        //int[][] graph={{1,2},{3},{3},{}};
        //System.out.println(allPathsSourceTarget(graph));

        char[][] grid={
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'} };
        //System.out.println(numIslands(grid));
        int[][] g={
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        //System.out.println(maxAreaOfIsland(g));
        int[][] orange={{2,2,2,1,1}};
        System.out.println(orangesRotting(orange));
    }
}
