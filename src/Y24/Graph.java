package Y24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        int[][] graph={{1,2},{3},{3},{}};
        System.out.println(allPathsSourceTarget(graph));
    }
}
