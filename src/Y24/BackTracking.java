package Y24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTracking {

    //46. 全排列
    //使用path数组记录每个数是否已记录
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        boolean[] path=new boolean[nums.length];
        helper(nums,res,list,path);
        return res;
    }
    /**
     * @param path path[i]搜索记录 true|false
     */
    public static void helper(int[] nums,List<List<Integer>> res,List<Integer> list,boolean[] path){
        if (list.size()==nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i=0;i<nums.length;i++){
            if (!path[i]){
                list.add(nums[i]);
                path[i]=true;
                helper(nums,res,list,path);
                list.remove((Integer)nums[i]);
                path[i]=false;
            }
        }
    }
    /**
     * todo
     * 78. 子集
     * 子集数量 2^n - 1
     * 使用二进制枚举所有子集，001，1表示选
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        boolean[] path=new boolean[nums.length];
        for (int i=0;i<nums.length;i++){
            //res.add()
        }
        subsetsHelper(path,nums,list,res);
        return res;
    }
    public static void subsetsHelper(boolean[] path,int[] nums,List<Integer> list,List<List<Integer>> res){
        for (int i=0;i<nums.length;i++){
            list.add(nums[i]);
        }
    }

    /**
     * 39. 组合总和
     * @param candidates 无重复数组
     * @param target 目标和
     * @return 所有组合
     * Notes：
     *  递归出口：1.当前求和等于目标和 sum==target，触发回溯
     *          2.当前求和大于目标和 sum>target，触发回溯
     *  回溯逻辑：sum、list减掉当前添加的candidates[i]，继续下一步for循环，
     *  这里限制了相同的数但是不同的位置
     *  例如：[2,2,3]、[2,3,2]、[3,2,2]，实际上只能算一种
     *  递归的时候要传递i，只能从当前位置i开始选数，不能从0开始，会导致重复
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        combinedHelper(0,target,candidates,new ArrayList<>(),res,0);
        return res;
    }
    public static void combinedHelper(int sum, int target, int[] candidates, List<Integer> list, List<List<Integer>> res,int index){
        //触发回溯
        if (sum==target){
            res.add(new ArrayList<>(list));
            return;
        }
        if (sum>target){
            return;
        }
        for (int i=index;i<candidates.length;i++){
            sum+=candidates[i];
            list.add(candidates[i]);
            combinedHelper(sum,target,candidates,list,res,i);
            //回溯减去当前值 继续i的下一个for循环
            sum-=candidates[i];
            list.remove((Integer)candidates[i]);
        }
    }

    /**
     * 22. 括号生成
     * @param n 括号的对数
     * @return 所有出现的情况
     * 例如：n=3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * DFS
     * item字串在任何时候 ) 都不能比 ( 多
     * left==right 只能先 ( 开始
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res=new ArrayList<>();
        generateHelper("",res,n,n);
        return res;
    }
    public static void generateHelper(String item,List<String> res,int left,int right){
        //剩余 ')' 比 '(' 少,剪枝
        if (right<left){
            return;
        }
        if (right==0 && left==0){
            res.add(item);
            return;
        }
        if (left>0){
            generateHelper(item+"(",res,left-1,right);
        }
        if (left<right){
            generateHelper(item+")",res,left,right-1);
        }
    }


    static boolean rs=false;
    /**
     * 79. 单词搜索
     * @param board 字符网格
     * @param word 要查找的单词
     * @return 单词是否存在网格中
     * 遍历网格，找到单词的开头字符，开始进行dfs搜索
     * dfs搜索：对当前位置开始，寻找 上下左右 四个位置字符与单词的下一个字符相匹配
     */
    public static boolean exist(char[][] board, String word) {
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                if (board[i][j]==word.charAt(0)){
                    boolean[][] path=new boolean[board.length][board[0].length];
                    path[i][j]=true;
                    existHelper(board,word,i,j,0,path);
                }
                if (rs){
                    return true;
                }
            }
        }
        return false;
    }
    public static void existHelper(char[][] board,String word,int i,int j,int index,boolean[][] path){
        if (index==word.length()-1){
            rs=true;
            return;
        }
        if (!inArea(board,i,j)){
            return;
        }
        //bottom
        if (inArea(board,i+1,j) && index<word.length()-1 && board[i+1][j]==word.charAt(index+1) && !path[i+1][j]){
            path[i+1][j]=true;
            existHelper(board,word,i+1,j,index+1,path);
        }
        //right
        if (inArea(board,i,j+1) && index<word.length()-1 && board[i][j+1]==word.charAt(index+1) && !path[i][j+1]){
            path[i][j+1]=true;
            existHelper(board,word,i,j+1,index+1,path);
        }
        //left
        if (inArea(board,i,j-1) && index<word.length()-1 && board[i][j-1]==word.charAt(index+1) && !path[i][j-1]){
            path[i][j-1]=true;
            existHelper(board,word,i,j-1,index+1,path);
        }
        //top
        if (inArea(board,i-1,j) && index<word.length()-1 && board[i-1][j]==word.charAt(index+1) && !path[i-1][j]){
            path[i-1][j]=true;
            existHelper(board,word,i-1,j,index+1,path);
        }
        //无法找到下一个目标，标记false，触发回溯点
        path[i][j]=false;
    }
    public static boolean inArea(char[][] board,int i,int j){
        return i>=0 && i<board.length && j>=0 && j<board[i].length;
    }

    /**
     * todo
     * 131. 分割回文串
     * @param s 字符串 "aab"
     * @return 分割的所有方案
     * [["a","a","b"],["aa","b"]]
     */
    public List<List<String>> partition(String s) {
        return null;
    }

    /**
     * todo 优化 check方法 对角线判断、列判断
     * 51. N 皇后
     * @param n n*n网格
     * @return 返回所有不同的 n 皇后问题 的解决方案
     * 使用一个数组存储每行放置皇后的位置
     * path[i]=val,表示当前皇后的位置放在 [i,val]
     * 限制条件：不能同一行，不能同一列，不能同对角线
     * check方法：当前想要放置皇后的位置是否合法，遍历现有的所有queens
     * dfs出口：row==n，说明n*n的表格中每行都放置了皇后，找到一个可行解
     * 回溯：将已经设置皇后的位置标记为未使用
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<>();
        //记录皇后位置
        char[][] queens=new char[n][n];
        NQueensHelper(0,n,res,queens);
        return res;
    }
    public static void NQueensHelper(int row,int n,List<List<String>> res,char[][] queens){
        if (n==row){
            List<String> list=new ArrayList<>();
            for (int i=0;i<n;i++){
                StringBuilder str= new StringBuilder();
                for (int j=0;j<n;j++){
                    if (queens[i][j]=='Q'){
                        str.append('Q');
                    }else{
                        str.append('.');
                    }
                }
                list.add(str.toString());
            }
            res.add(list);
            return;
        }
        for (int col=0;col<n;col++){
            if (check(row,col,queens,n)){
                queens[row][col]='Q';
                NQueensHelper(row+1,n,res,queens);
                queens[row][col]='.';
            }
        }
    }
    public static boolean check(int row,int col,char[][] queens,int n){
        //判断对角线 '\'  即[row+1~n,col+1~n] 与 [row-1~0,col-1~0]
//        for (int i=row,j=col;i<n && j<n;i++,j++){
//            if (queens[i][j]=='Q'){
//                return false;
//            }
//        }
        //判断对角线 '/' 即[row+1~n,col-1~0] 与 [row-1~0,col+1~0];
        //判断列
        for (int i=0;i<n;i++){
            if (queens[i][col]=='Q'){
                return false;
            }
        }
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                //判断是否同行、同列、同斜线
                if (queens[i][j]=='Q' && (row==i || col==j || (Math.abs(row-i)==Math.abs(col-j)))){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] nums={2,3,6,7};
        //System.out.println(permute(nums));
//        System.out.println(combinationSum(nums,7));
        //System.out.println(generateParenthesis(1));
//        String word="ABCESEEEFS";
//        char[][] board={
//                {'A','B','C','E'},
//                {'S','F','E','S'},
//                {'A','D','E','E'}};
//        System.out.println(exist(board,word));

        System.out.println(solveNQueens(4));

    }
}
