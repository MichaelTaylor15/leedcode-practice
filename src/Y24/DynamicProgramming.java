package Y24;


import java.util.*;

public class DynamicProgramming {

    //15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){

        }
        return list;
    }
    //42. 接雨水
    //前缀和、后缀和 存储柱子的最大高度，能存储的雨水高度取决于两边的高度中的最低度，最后结果要减去柱子本身占用的高度
    //左边最高和右边最高的最低点就是当前柱子能存储水的高度，再减去原来的柱子高度得到雨水的量
    public static int trap(int[] height) {
        int n=height.length;
        int[] left=new int[n];
        int[] right=new int[n];
        int sum=0;
        left[0]=height[0];
        right[n-1]=height[n-1];
        for (int i=1;i<n;i++){
            left[i]=Math.max(left[i-1],height[i]);
        }
        for (int i=n-2;i>=0;i--){
            right[i]=Math.max(right[i+1],height[i]);
        }
        for (int i=1;i<n-1;i++){
            sum+=Math.max(0,Math.min(left[i-1],right[i+1])-height[i]);
        }
        return sum;
    }

    //198. 打家劫舍
    //每个dp[i]考虑隔一个位置，即max(dp[i-1],nums[i]+dp[i-2])
    public static int rob(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        if (n<1){
            return 0;
        }
        if (n<2){
            return nums[0];
        }
        dp[0]=nums[0];
        dp[1]=Math.max(nums[1],dp[0]);
        for (int i=2;i<n;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[n-1];
    }

    //740. 删除并获得点数
    //排序+动态规划，思想跟打家劫舍一样
    public static int deleteAndEarn(int[] nums) {
        LinkedHashMap<Integer,Integer> hashMap=new LinkedHashMap<>();
        for (int num:nums){
            hashMap.put(num,hashMap.getOrDefault(num,0)+1);
        }
        int[] dp=new int[nums.length];
        int i=0;
        int last=0;
        for (int key:hashMap.keySet()){
            if (i==0){
                dp[i]=key*hashMap.get(key);
            }else if (i<2){
                if (Math.abs(last-key)==1){
                    dp[i]=Math.max(key*hashMap.get(key),dp[i-1]);
                }else{
                    dp[i]=dp[i-1]+key*hashMap.get(key);
                }
            }else{
                if (Math.abs(last-key)==1){
                    dp[i]=Math.max(dp[i-2]+key*hashMap.get(key),dp[i-1]);
                }else{
                    dp[i]=dp[i-1]+key*hashMap.get(key);
                }
            }
            last=key;
            i++;
        }
        return dp[i-1];
    }

    //62. 不同路径
    //[0,i],[j,0]所有边界的走法都是1，并且当i>1且j>1时，当前格子的走法次数等于上一个格子的次数加上左边格子的次数
    //即dp[i][j]=dp[i-1][j]+dp[i][j-1]
    public static int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        for (int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for (int i=0;i<n;i++){
            dp[0][i]=1;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    //64. 最小路径和
    public static int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][] dp=new int[m][n];
        dp[0][0]=grid[0][0];
        for (int i=1;i<m;i++){
            dp[i][0]=grid[i][0]+dp[i-1][0];
        }
        for (int j=1;j<n;j++){
            dp[0][j]=grid[0][j]+dp[0][j-1];
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
    //63. 不同路径 II
    //障碍物的走法需要归零
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int[][] dp=new int[m][n];
        dp[0][0]=1;
        if (obstacleGrid[0][0]==1) return 0;
        for (int i=1;i<m;i++){
            dp[i][0]=obstacleGrid[i][0]==0?dp[i-1][0]:0;
        }
        for (int i=1;i<n;i++){
            dp[0][i]=obstacleGrid[0][i]==0?dp[0][i-1]:0;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (obstacleGrid[i][j]==0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }else{
                    dp[i][j]=0;
                }
            }
        }
        return dp[m-1][n-1];
    }

    //120. 三角形最小路径和
    //可选择相邻：上右
    public static int minimumTotal(List<List<Integer>> triangle) {
        int m=triangle.size();
        int[][] dp=new int[m][m];
        dp[0][0]=triangle.get(0).get(0);
        for (int i=1;i<triangle.size();i++){
            dp[i][0]=dp[i-1][0]+triangle.get(i).get(0);
            for (int j=1;j<i;j++){
                dp[i][j]=triangle.get(i).get(j)+Math.min(dp[i-1][j],dp[i-1][j-1]);
            }
            dp[i][i]=triangle.get(i).get(i)+dp[i-1][i-1];
        }
        int min=Integer.MAX_VALUE;
        int[] nums=dp[m-1];
        for(Integer num:nums){
            min=Math.min(min,num);
        }
        return min;
    }
    //可选择相邻：左上右
    public static int minimumTotal1(List<List<Integer>> triangle) {
        List<List<Integer>> dp=new ArrayList<>();
        dp.add(triangle.get(0));
        for (int i=1;i<triangle.size();i++){
            List<Integer> arr=new ArrayList<>();
            for (int j=0;j<triangle.get(i).size();j++){
                //当前的val
                int cur=triangle.get(i).get(j);
                if(j==0){
                    //上、右可选
                    if (triangle.get(i-1).size()>1){
                        arr.add(cur+Math.min(dp.get(i-1).get(j),dp.get(i-1).get(j+1)));
                    }
                    //只有上可选
                    else{
                        arr.add(cur+dp.get(i-1).get(j));
                    }
                }else{
                    //上一行的size
                    int preL=triangle.get(i-1).size();
                    //别无选择
                    if (j+1-preL>=2){
                        arr.add(Integer.MAX_VALUE);
                    }
                    //只能选择左
                    else if (j+1-preL==1){
                        arr.add(cur+dp.get(i-1).get(j-1));
                    }
                    //选择上、左
                    else if (j+1==preL){
                        arr.add(cur+Math.min(dp.get(i-1).get(j),dp.get(i-1).get(j-1)));
                    }
                    //选择左、上、右
                    else {
                        int min=Math.min(dp.get(i-1).get(j),dp.get(i-1).get(j-1));
                        min=Math.min(min,dp.get(i-1).get(j+1));
                        arr.add(cur+min);
                    }
                }
            }
            dp.add(arr);
        }
        int min=Integer.MAX_VALUE;
        List<Integer> list=dp.get(dp.size()-1);
        for(Integer num:list){
            min=Math.min(min,num);
        }
        return min;
    }

    //931. 下降路径最小和
    //左边边界、中间、右边边界分开处理
    public static int minFallingPathSum(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] dp=new int[m][n];
        for (int i=0;i<m;i++){
            dp[0][i]=matrix[0][i];
        }
        for (int i=1;i<m;i++){
            //处理左边边界
            dp[i][0]=matrix[i][0]+Math.min(dp[i-1][0],dp[i-1][1]);
            for (int j=1;j<n-1;j++){
                int min=Math.min(dp[i-1][j],dp[i-1][j-1]);
                dp[i][j]=matrix[i][j]+Math.min(min,dp[i-1][j+1]);
            }
            //处理右边边界
            dp[i][n-1]=matrix[i][n-1]+Math.min(dp[i-1][n-1],dp[i-1][n-2]);
        }
        int min=Integer.MAX_VALUE;
        for (int num:dp[m-1]){
            min=Math.min(min,num);
        }
        return min;
    }

    //221. 最大正方形
    public int maximalSquare(char[][] matrix) {
        return 0;
    }
    //5. 最长回文子串
    public static String longestPalindrome(String str) {
        String res="";
        for (int len=1;len<str.length();len++){
            int right=len-1;
            int left=0;
            while (left<=right){
                if (str.charAt(left)!=str.charAt(right)){
                    break;
                }
                if (len>res.length()){
                    res=str.substring(left,right+1);
                }
                left++;
                right--;
            }
        }
        return res;
    }

    /**
     * todo
     * 741. 摘樱桃
     * 表格有三种状态，0表示空，1表示樱桃，-1表示荆棘挡路
     * @param grid 网格
     * @return 最多可以摘的樱桃数
     */
    public int cherryPickup(int[][] grid) {

    }
    public static void main(String[] args) {
        //           {0,2,4,1,2,0}
        //int[] height={4,2,0,3,2,5};
        //System.out.println(trap(height));

        //int[] nums={2,1};
        //System.out.println(rob(nums));

        //int[] nums={1,2,3,15,16,17,18};
        //System.out.println(deleteAndEarn(nums));

        //int m=3,n=2;
        //System.out.println(uniquePaths(m,n));

        //int[][] grid={{0,0,0},{0,1,0},{0,0,0}};
        //System.out.println(minPathSum(grid));

        //int[][] obstacleGrid={{0,0}};
        //System.out.println(uniquePathsWithObstacles(obstacleGrid));
        // -1
        //  3  2
        // -3  1  -1
        //

//        List<List<Integer>> triangle=new ArrayList<>();
//        Integer[][] arr={{-1},{3,2},{-3,1,-1}};
//        for (Integer[] a:arr){
//            triangle.add(Arrays.asList(a));
//        }
//        System.out.println(minimumTotal(triangle));

//        int[][] matrix = {{2,1,3},
//                          {6,5,4},
//                          {7,8,9}};
//        System.out.println(minFallingPathSum(matrix));

        String str="cbbd";
        System.out.println(longestPalindrome(str));
    }
}
