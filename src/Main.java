

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.*;


class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        this.val=x;
    }
    public static ListNode getList(int length){
        ListNode head=new ListNode(0);
        ListNode list=head;
        for (int i=1;i<=length;i++){
            list.next=new ListNode(i);
            list=list.next;
        }
        return head;
    }
    public static void setLoop(ListNode listNode,int end,int begin){
        ListNode head=listNode.next;
        while (listNode!=null){
            if (listNode.val==end){
                while (head!=null){
                    if (head.val==begin){
                        listNode.next=head;
                        return;
                    }
                    head=head.next;
                }
            }
            listNode=listNode.next;
        }
    }
    public static void printNode(ListNode listNode){
        while (listNode!=null){
            System.out.print(listNode.val+" ");
            listNode=listNode.next;
        }
    }
}

class FriendCircles {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        boolean[] visited = new boolean[n];
        int circleCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                circleCount++;
                dfs(M, visited, i);
            }
        }

        return circleCount;
    }

    private void dfs(int[][] M, boolean[] visited, int person) {
        visited[person] = true;
        for (int friend = 0; friend < M.length; friend++) {
            if (M[person][friend] == 1 && !visited[friend]) {
                dfs(M, visited, friend);
            }
        }
    }

    public static void main(String[] args) {
        FriendCircles friendCircles = new FriendCircles();

        int[][] matrix = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        int result = friendCircles.findCircleNum(matrix);
        System.out.println("朋友圈的数量为: " + result);
    }
}

//240. Search a 2D Matrix II (Medium)
class Search2DMatrix {

    public static boolean search(int[][] a,int goal) {
        int y = a[0].length-1;
        int x=0;
        while (x<=a.length && y>=0){
            if (a[x][y]==goal){
                System.out.println("the location of goal is:a["+x+"]["+y+"]");
                return true;
            }else if (a[x][y]>goal){
                y--;
            }else {
                x++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = {
                {1,  4,  7,  11, 15},
                {2,  5,  8,  12, 19},
                {3,  6,  9,  16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };
        int goal=2;
        boolean res=search(a,goal);
        System.out.println(res);
    }
}

//275. H 指数 II 20231030
class Mid{
    public static int solve1(int[] a){
        int n=a.length;
        int ans=0;
        for (int i=n-1;i>=0;i--){
            if (n-i<=a[i]){
                ans++;
            }else {
                return ans;
            }
        }
        return ans;
    }

    public static int solve2(int[] a){
        int n=a.length;
        int left=1,right=n;
        while (left<=right){
            int mid=(left+right)/2;
            if (a[n-mid]>=mid){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        int[] a={1,2,100};
        int[] b={0};
        int[] c={0,1,3,5,6};
        int[] d={11,15};
        int[] e={1,2};
        System.out.println(solve2(a));
    }
}

//2530. 执行 K 次操作后的最大分数
class Knum{

    public static int ceil(int num){
        if (num%3==0)
            return num/3;
        return num/3+1;
    }

    public static long calculate(int[] a){
        long ans=0;
        for (int i=0;i<a.length;i++){
            ans+=a[i];
        }
        return ans;
    }
    public static long solve(int[] a,int k){
        int n=a.length-1;
        int max_index=n;
        int left=0,right=n;
        long ans=0;
        sort(a);
        while (k-->0){
            ans+=a[n];
            a[n]=ceil(a[n]);
            if (n!=0 && a[max_index]>a[n-1]){
                continue;
            }else {
                n--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a={10,10,10,10,10};
        int[] b={672579538,806947365,854095676,815137524};
        int[] c={756902131,995414896,95906472,149914376,387433380,848985151};
        PriorityQueue<Long> queue=new PriorityQueue<>();
        System.out.println(solve(c,6));
        //System.out.println(calculate(c));
    }
}
//2003. 每棵子树内缺失的最小基因值 parents[i] 是节点 i 的父节点
class TreeNode{

    int val;
    List<TreeNodeII> children;
    //build a tree,0-(n-1)
    public TreeNode(int val){
        this.val=val;
        this.children=new ArrayList<>();
    }
    public static TreeNodeII buildTree(int[] parents){
        TreeNodeII root=new TreeNodeII(0);
        for (int i=0;i<parents.length;i++){
            int j=0;
            while (parents[j]!=i){
                j++;
            }
            root.children.add(new TreeNodeII(j));
        }
        return root;
    }

    public static void solve(int[] parents,int[] nums){
        int n=parents.length;
        for (int i=0;i<n;i++){

        }
    }

    public static void main(String[] args) {
        int[] parents={-1,0,0,2};

    }
}
//
class GridNum{

    public static int solve(int num){
        int num1=0,num2=0;
        char[] arr=Integer.toString(num).toCharArray();
        Arrays.sort(arr);
        for (int i=0;i<arr.length;i++){
            if (i % 2 == 0) {
                num1 = num1 * 10 + (arr[i] - '0');
            } else {
                num2 = num2 * 10 + (arr[i] - '0');
            }
        }
        return num1+num2;
    }

    public static void main(String[] args) {
        int a=1234;
        System.out.println(solve(a));
    }
}

class FindCombineNum{

    public static long solve(int[] nums){
        String[] str=new String[nums.length];
        int n=nums.length;
        if (n==1) return nums[0];
        for (int i=0;i<n;i++){
            str[i]=Integer.toString(nums[i]);
        }
        long ans=0;
        int i=0,j=n-1;
        while (i<j){
            ans+=Integer.parseInt(str[i]+str[j]);
            i++;
            j--;
        }
        if (i==j) ans+=nums[i];
        return ans;
    }
    public static long solve2(int[] nums){
        int n=nums.length;
        if (n==1) return nums[0];
        long ans=0;
        int i=0,j=n-1;
        while (i<j){
            ans+=Integer.parseInt(nums[i] +Integer.toString(nums[j]));
            i++;
            j--;
        }
        if (i==j) ans+=nums[i];
        return ans;
    }
    public static void main(String[] args) {
        int[] a={5,14,13,8,12};
        int[] b={7,52,2,4};
        int[] c={3432,1461,9408,1439,2367,7539,2927,1015,5397,8879,6827,4187,524,4113,7001,1300,9667,2058,4258,4406,2469,2774,573,7398,9586,6870,7355,4020,6147,5830,3953,6760,1786,5161,5919,7692,23,9298,4719,9116,4637,487,9865,9222,309,6642,6707,3603,3914,4549,8037,6815,6730,3256,5081,7388,5339,5956,5857,3933,8948,94,6793,5300,7199,3989,4371,4013,5622,5433,3157,8211,532,2249,7579,1074,8921,4570,9082,1279,4940,1616,6683,89,8620,9859,727,6688,801,9045,8488,7193,8972,597,8881,8163,7410,640,9424,5487,4823,4968,6647,2037,8535,8826,5583,8041,3650,4036,971,3350,3832,9908,1889,7753,7671,1872,5011,6872,3268,2253,9001,8491,7841,1749,7600,3885,1693,4690,9461,9283,2497,1129,1552,7392,9360,879,158,7077,695,6910,463,4782,8278,688,537,9359,285,2070,4984,7337,891,8877,8111,8084,5614,400,565,2404,2547,1996,9896,438,8499,1542,2358,7657,7397,9974,8929,5349,3398,6553,5431,3271,9861,7441,6113,5526,1172,1928,3370,4326,2738,2977,1113,7514,872,5017,9110,2921,1288,4935,8882,4149,3412,9310,4724,3934,3461,1535,3922,9830,2168};

        System.out.println(solve2(a));
    }
}

class FindAriseOnceNum{

    public static int solve(int[] nums){
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        int n=nums.length;
        int ans=-1;
        for (int i=0;i<n;i++){
            if (hashMap.get(nums[i])==null){
                hashMap.put(nums[i],1);
                ans=nums[i];
            }else {
                hashMap.put(nums[i],hashMap.get(nums[i])+1);
            }
        }
        for (Integer temp:hashMap.keySet()){
            if (hashMap.get(temp)==1) return temp;
            //System.out.println("key:"+temp+",value:"+hashMap.get(temp));
        }
        return ans;
    }

    public static int[] solve2(int[] nums){
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        int ans[]=new int[2],j=0;
        for (int i=0;i<nums.length;i++){
            hashMap.put(nums[i],hashMap.getOrDefault(nums[i],0)+1);
        }
        for (Integer temp:hashMap.keySet()){
            if (hashMap.get(temp)==1) ans[j++]=temp;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a={2,2,3,2};
        int[] b={0,1,0,1,0,1,99};
        int[] c={30000,500,100,30000,100,30000,100};
        int[] d={1,2,1,3,2,5};
        System.out.println(Arrays.toString(solve2(d)));
        //System.out.println(solve(c));
    }
}


//2127. 参加会议的最多员工数
class ParticipationEmployee{
    public static int statistic(int[] favorites){
        int n=favorites.length;
        int max=0,ans=0;


        return 0;
    }

    public static void main(String[] args) {
        int[] a={1,2,0};
        System.out.println(statistic(a));
    }
}

//1. 两数之和
class NumberSum{
    public static int[] solve(int[] nums,int target){
        int n=nums.length;
        for (int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                if (nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
    //HashTable
    public static int[] solve2(int[] nums,int target){
        int n=nums.length;
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int i=0;i<n;i++){
            //存在相加==target
            if (hashMap.containsKey(target-nums[i])){
                return new int[]{hashMap.get(target-nums[i]),i};
            }
            hashMap.put(nums[i],i);
        }
        return null;
    }
    public static void main(String[] args) {
        int[] a={3,3};
        System.out.println(Arrays.toString(solve2(a,6)));
    }
}

//49. 字母异位词分组,String words convert to CharArray and sorting,put in the HashMap<String,List<String>>,key is original words,value is the same group
class GroupingWords{
    public static List<List<String>> solve(String[] words){
        HashMap<String,List<String>> hashMap=new HashMap<>();
        if (words.length==0){
            return new ArrayList<>();
        }
        for (String word : words) {
            char[] str = word.toCharArray();
            Arrays.sort(str);
            String temp = new String(str);
            //List<String> list=hashMap.getOrDefault(temp,new ArrayList<String>());
            List<String> list = new ArrayList<>();
            if (hashMap.containsKey(temp)) {
                list = hashMap.get(temp);
            }
            list.add(word);
            hashMap.put(temp, list);
        }
        return new ArrayList<>(hashMap.values());
    }
    public static void print(List<List<String>> list){
        for (int i=0;i<list.size();i++){
            for (int j=0;j<list.get(i).size();j++){
                System.out.print(list.get(i).get(j)+",");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        String[] words ={"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] a={"a"};
        List<List<String>> ans=solve(a);
        print(ans);
    }
}
class FindMaxLengthSequence{
    public static int solve(int[] nums){
        int maxLength=0;
        Map<Integer,Integer> hashMap=new LinkedHashMap<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            hashMap.put(nums[i],1);
        }
        int temp=1;
        for (int key:hashMap.keySet()){
            if (hashMap.containsKey(key+1)){
                temp+=1;
            }else{
                if (temp>maxLength)
                    maxLength=temp;
                temp=1;
            }
        }
        return maxLength;
    }
    public static int solve2(int[] nums){
        int maxlength=0;
        HashSet<Integer> hashSet=new HashSet<>();
        for (int num:nums){
            hashSet.add(num);
        }
        //选择小序列中最小的进行计数
        for (int num:hashSet){
            int currentLength=1;
            int currentNum=num;
            if (!hashSet.contains(currentNum-1)){
                while (hashSet.contains(currentNum+1)){
                    currentLength++;
                    currentNum++;
                }
                if (currentLength>maxlength) maxlength=currentLength;
            }
        }
        return maxlength;
    }
    public static void main(String[] args) {
        int[] a={100,4,200,1,3,2};
        int[] b={0,3,7,2,5,8,4,6,0,1};
        int[] c={9,1,4,7,3,-1,0,5,8,-1,6};
        int[] d={9,1,-3,2,4,8,3,-1,6,-2,-4,7};
        System.out.println(solve2(d));
    }
}


//2103. 环和杆,
// (1)sort by number to map,key is number,value is character
// (2)consider red is 1,green is 2,blue is 4,
class Test{
    public static int solve(String str){
        char[] arr=str.toCharArray();
        HashMap<Character,String> hashMap=new HashMap<>();
        int ans=0;
        for (int i=0;i<arr.length;i++){
            if (i%2==0){
                if (hashMap.get(arr[i+1])!=null){
                    String temp=hashMap.get(arr[i+1]);
                    hashMap.put(arr[i+1],temp+(arr[i]));
                }else {
                    hashMap.put(arr[i+1],String.valueOf(arr[i]));
                }
            }
        }
        for (char key:hashMap.keySet()){
            boolean blue=false,red=false,green=false;
            char[] temp=hashMap.get(key).toCharArray();
            if (temp.length<3) continue;
            for (char c : temp) {
                if (c == 'G') {
                    green = true;
                } else if (c == 'R') {
                    red = true;
                } else {
                    blue = true;
                }
            }
            if (green&&red&&blue){
                ans+=1;
            }
        }
        return ans;
    }

    public static void print(HashMap<Character,String> hashMap){
        for (char key:hashMap.keySet()){
            System.out.println(hashMap.get(key));
        }
    }
    public static int solve2(String str){
        HashMap<Character,Integer> hashMap=new HashMap<>();
        char[] arr=str.toCharArray();
        int ans=0;
        for (int i=0;i<arr.length;i+=2){
            Integer temp = hashMap.get(arr[i+1]);
            if (temp!=null && temp>=7) continue;
            if (arr[i]=='R'){
                hashMap.put(arr[i+1],temp==null?1:temp+1);
            }else if (arr[i]=='G'){
                hashMap.put(arr[i+1],temp==null?2:temp+2);
            }else {
                hashMap.put(arr[i+1],temp==null?4:temp+4);
            }
        }
        for (Character key:hashMap.keySet()){
            if (hashMap.get(key)==7){
                ans+=1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String rings="B9R9G0R4G6R8R2R9G9";
        System.out.println(solve2(rings));
    }
}
//动态规划
class Climb{
    //70. 爬楼梯
    public static int solve(int n){
        int[] arr=new int[n+1];
        arr[0]=1;
        arr[1]=1;
        for(int i=2;i<=n;i++){
            arr[i]=arr[i-2]+arr[i-1];
        }
        return arr[n];
    }
    //509. 斐波那契数
    public static int febor(int n){
        int[] arr=new int[n+1];
        if (n<1) return 0;
        arr[0]=1;
        arr[1]=1;
        for (int i=2;i<n;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr[n-1];
    }
    //第 N 个泰波那契数
    public static int tabor(int n){
        int[] arr=new int[n+2];
        if (n<1) return 0;
        arr[0]=0;
        arr[1]=1;
        arr[2]=1;
        for (int i=3;i<=n;i++){
            arr[i]=arr[i-3]+arr[i-2]+arr[i-1];
        }
        return arr[n];
    }

    /**
     * 746. 使用最小花费爬楼梯
     * compute min-cost on every index,current min-cost is sum of min-cost prefix and current cost,
     * in the end,min-cost in the site of cost[n-1] and cost[n-2]
     */
    public static int minCost(int[] cost){
        int n=cost.length;
        for (int i=2;i<cost.length;i++){
            cost[i]+=Math.min(cost[i-2],cost[i-1]);
        }
        return Math.min(cost[n-1],cost[n-2]);
    }
    //198. 打家劫舍
    public static int rob(int[] nums) {
        int ans1=0,ans2=0;
        int[] dp=new int[10];
        dp[0]=0;
        for (int i=0;i<nums.length;i+=2){
            ans1+=nums[i];
        }
        for (int i=1;i<nums.length;i+=2){
            ans2+=nums[i];
        }
        return Math.max(ans1,ans2);
    }
    public static void main(String[] args) {
        //System.out.println(solve(13));
        //System.out.println(febor(0));
        //System.out.println(tabor(1));
        //int[] cost={1,100,1,1,1,100,1,1,100,1};
        //int[] cost2={10,15,20};
        //System.out.println(minCost(cost));
        int[] nums={2,1,1,2};
        System.out.println(rob(nums));
    }
}


// II Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
//117. 填充每个节点的下一个右侧节点指针
class SetTree{

    public Node connect(Node root) {
        if (root==null) return null;
        return root;
    }
    public static void print(int[] root){

    }
    public static void main(String[] args) {
        int[] root={};
        print(root);
    }
}
//双指针

class Pointers{
    //283. 移动零
    // use zeroIndex to recoding site of zero,use i to recording site of none-zero
    public static int[] moveZeros(int[] nums){
        int zeroIndex=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                nums[zeroIndex]=nums[i];
                //zeroIndex为0的时候不会递增，此时说明有0
                if (i!=zeroIndex){
                    nums[i]=0;
                }
                zeroIndex++;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums={0,1,0,3,12};
        int[] b={0,1};
        int[] c={0,0};
        System.out.println(Arrays.toString(moveZeros(nums)));
    }
}

//318. 最大单词长度乘积
//给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
class MaxProduct{

    public static int solve(String[] words){
        int k=0;
        int max=0,len=words.length;
        int temp=0;
        for (int i=0;i<len;i++){
            for (int j=i+1;j<len;j++){
                temp=words[i].length()*words[j].length();
                if (temp>max && checkSame(words[i],words[j])){
                    max=temp;
                }
                k++;
            }
        }
        return max;
    }
    public static boolean checkSame(String word1,String word2){
        boolean[] check=new boolean[26];
        for (char temp:word1.toCharArray()){
            check[temp-'a']=true;
        }
        for (char temp:word2.toCharArray()){
            if (check[temp-'a']) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String[] a={"abcw","baz","foo","bar","xtfn","abcdef"};
        String[] b={"a","ab","abc","d","cd","bcd","abcd"};
        String[] c={"a","aa","aaa","aaaa"};
        System.out.println(solve(c));
        //System.out.println(checkSame("abcw","bar"));
    }
}
//16. 最接近的三数之和
//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
//返回这三个数的和。假定每组输入只存在恰好一个解。
class Sum{
    public static int solve(int[] nums,int target){
        int gap=999999;
        int ans=0;
        int n=nums.length;
        for (int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                for (int k=j+1;k<n;k++){
                    int temp=Math.abs(target-(nums[i]+nums[j]+nums[k]));
                    if (temp<gap){
                        gap=temp;
                        ans=nums[i]+nums[j]+nums[k];
                    }
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums={1,1,1,0};
        int[] b={-13,592,-501,770,-952,-675,322,-829,-246,657,608,485,-112,967,-30,182,-969,559,-286,-64,24,365,-158,701,535,-429,-217,28,948,-114,-536,-711,693,23,-958,-283,-700,-672,311,314,-712,-594,-351,658,747,949,70,888,166,495,244,-380,-654,454,-281,-811,-168,-839,-106,877,-216,523,-234,-8,289,-175,920,-237,-791,-976,-509,-4,-3,298,-190,194,-328,265,150,210,285,-176,-646,-465,-97,-107,668,892,612,-54,-272,-910,557,-212,-930,-198,38,-365,-729,-410,932,4,-565,-329,-456,224,443,-529,-428,-294,191,229,112,-867,-163,-979,236,-227,-388,-209,984,188,-549,970,951,-119,-146,801,-554,564,-769,334,-819,-356,-724,-219,527,-405,-27,-759,722,-774,758,394,146,517,870,-208,742,-782,336,-364,-558,-417,663,-914,536,293,-818,847,-322,408,876,-823,827,167};
        int target=7175;
        System.out.println(solve(b,target));
    }
}
//最长回文子串
class LongestPalindromeSubstring{
    public static String solve(String str){
        String maxans="";
        int len;
        int n=str.length();
        boolean[][] dp =new boolean[n][n];
        for (len=1;len<=n;len++){
            for (int start=0;start<n;start++){
                int end=start+len-1;
                if (end>=n) break;
                if (len==1){
                    dp[start][end]=true;
                }else if (len==2){
                    dp[start][end]=str.charAt(start)==str.charAt(end);
                }else{
                    dp[start][end]=str.charAt(start)==str.charAt(end) && dp[start+1][end-1];
                }
                if (dp[start][end] && len > maxans.length()){
                    maxans=str.substring(start,end+1);
                }
            }
        }
        return maxans;
    }

    public static void main(String[] args) {
        String str="cbbd";
        System.out.println(solve(str));
    }
}
//2586. 统计范围内的元音字符串数
class Test1{
    public static int solve(String[] words,int left,int right){
        char[] target="aeiou".toCharArray();
        HashMap<Character,Integer> hashMap=new HashMap<>();
        int ans=0;
        for (int i=0;i<target.length;i++){
            hashMap.put(target[i],1);
        }
        while (left<=right){
            char[] temp=words[left].toCharArray();
            if (hashMap.containsKey(temp[0])&&hashMap.containsKey(temp[temp.length-1])){
                ans++;
            }
            left++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] a={"hey","aeo","mu","ooo","artro"};
        System.out.println(solve(a,1,a.length-1));
    }
}
//No.370 week competition
class Week370{
    public static int solve1(int[][] grid){
        int ans=0;
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        boolean[][] history=new boolean[grid.length][grid.length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j< grid.length;j++){
                history[i][j]=false;
            }
        }
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                int winner=0;
                int loser=0;
                if (i!=j){
                    //winner：i
                    if (grid[i][j]==1){
                        winner=i;
                        loser=j;
                    }else {
                        winner=j;
                        loser=i;
                    }
                    //0:1=0,1:0=1,检测是否记录过比赛
                    if (history[winner][loser]){
                        continue;
                    }
                    history[winner][loser]=true;
                    int val=hashMap.get(winner)!=null?hashMap.get(winner)+1:1;
                    hashMap.put(winner,val);
                }
                if (hashMap.get(winner)!=null&&hashMap.get(winner)==grid[i].length-1){
                    return winner;
                }
            }
        }
        return ans;
    }
    public static int solve2(int[][] grid){
        int ans=0;
        for (int i=0;i< grid.length;i++){
            boolean res=true;
            for (int j=0;j<grid[i].length;j++){
                if (i!=j&&grid[i][j]==0){
                    res=false;
                }
            }
            if (res) return i;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][]a ={{0,1},{0,0}};
        int[][]b ={{0,0,1},{1,0,1},{0,0,0}};
        int[][]c ={{1, 0, 0},{1, 1, 0},{1, 1, 0}};
        int[][]d ={{0,0,0,0},{1,0,0,0},{1,1,0,0},{1,1,1,0}};
        System.out.println(solve2(d));
    }
}
class Day1107{
    //7. 整数反转
    public static int reverse(int input){
        StringBuilder ans= new StringBuilder();
        char[] nums=Integer.toString(input).toCharArray();
        int right=nums.length-1;
        int left=0;
        String begin="";
        if (nums[0]=='-'){
            begin=Character.toString(nums[0]);
            left++;
        }
        while (nums[right]==0){
            right--;
        }
        while (right>=left){
            ans.append(nums[right]);
            right--;
        }
        long parse=Long.parseLong(begin+ans);
        if (parse<Integer.MIN_VALUE || parse>Integer.MAX_VALUE){
            return 0;
        }
        return (int) parse;
    }

    public static void main(String[] args) {
        int a=1534236469;
        System.out.println(reverse(a));
    }
}
class Day1108{
    //2609. 最长平衡子字符串
    public static int findTheLongestBalancedSubstring(String s){
        int max=0;
        int cnt=0;
        int zero=0;
        int one=0;
        char[] c=s.toCharArray();
        while (cnt < c.length) {
            zero=0;
            one=0;
            while (cnt<c.length && c[cnt]=='0'){
                zero++;
                cnt++;
            }
            while (cnt<c.length && c[cnt]=='1'){
                one++;
                cnt++;
            }
            max=Math.max(max,2*Math.min(zero,one));
        }
        return max;
    }
    public static void main(String[] args) {
        String str="001";
        System.out.println(findTheLongestBalancedSubstring(str));
    }
}
class Day1109{
    //2258. 逃离火灾,from (0,0) to (m-1,n-1),0:grass,1:fire,2:wall
    public static int maximumMinutes(int[][] grid) {
        int max=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){

            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] grid1={{0,2,0,0,0,0,0},{0,0,0,2,2,1,0},{0,2,0,0,1,2,0},{0,0,2,2,2,0,2},{0,0,0,0,0,0,0}};
        System.out.println(maximumMinutes(grid1));
    }
}
class Day1110{
    //2300. 咒语和药水的成功对数
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n=spells.length;
        int m=potions.length;
        int[] pairs=new int[n];
        Arrays.sort(potions);
        for (int i=0;i<n;i++){
            int left=0;
            int right=m;
            while (left<right){
                int mid=(left+right)/2;
                if ((long) potions[mid] *spells[i]>=success){
                    right=mid;
                }else {
                    left=mid+1;
                }
            }
            pairs[i]=m-right;
        }
        return pairs;
    }
    public static void main(String[] args) {
        int[] spells={56029,39759,11542,85170,95989,20920,68729,33661,6800,40169,67278,49278,20991,30077,11317,26005,6472,20781,66087,76437,18280,29948,20501,86720,44165,44744,57600,91555,76962,25738,6462,9878,74373,59864,96227,45111,49909,5251,44454,93449,65551,25378,19317,88784,78096,21799,76916,2968,25597,85909,66433,30293,69683,83371,26369,62625,45474,76244,14660,71429,89672,65452,61700,2057,60003,73712,41126,74674,56909,146,82767,93324,11951,23116,16913,9170,43279,15270,29438,76151,31771,2011,17329,97735,68065,42884,99388,88209,15022,23053,49075,72160,45745,91955,37631,1977,21421,83647,16079,55281,69343,64213,99301,66886,66089,28786,9818,31351,37524,47653,27572,4953,67844,82840,80010,1802,89017,63780,24729,73723,88021,8985,53187,10646,8200,41925,34313,87107,83850,30375,86506,55096,86109,1749,20403,75909,55336,28349,5015,23182,86181,93949,80938,66234,1929,66729,27546,46754,36693,28648,12400,33503,45146,32117,52447,57291,21665,31865,59714,75297,87268,71968,37515,88077,15858,58991,22082,37721,78059,69899};
        int[] potions={68460,91879,36597,48406,45552,29774,99922,50056,76873,96948,97065,88424,24302,26425,74052,67581,39559,8998,96343,97778,71166,84896,76277,65473,85053,26893,78671,54557,80936,78558,82375,22708,96804,43485,35901,22691,50553,94487,19269,34222,54280,81544,55841,70039,87821,38329,46395,74111,72898,77519,82154,82306,36301,76945,75266,75411,67620,35122,70944,32270,45708,53475,15903,31213,13463,87647,43179,52106,21952,93616,86371,94033,68174,97928,66037,62327,55193,50503,79185,82701,68202,49527,48758,59736,90353,86946,69521,39136,42360,78421,82053,98931,98897,95139,65457,72698,78169,76496,94287,64882,43227,97918,83874,92930,23154,96543,58749,96360,73641,83588,90969,89443,97526,24679,12039,63493,51487,79221,53671,32922,82821,48357,68164,84511,98399,73050,86878,97010,93343,56906,13122,87289,73880,98801,57054,89870,74357,90777,87314,72731,24560,89206,83497,76527,33534,74881,66674,44007,61306,93890,62048,99364,64296,80532,20125,30382,47123,57707,43700,97044,82909,92190,97022,58982,48350,67826,7399};
        long success=1651505078;
        System.out.println(Arrays.toString(successfulPairs(spells,potions,success)));
        //System.out.println((long)spells[0]*potions[0]);
    }
}

class Day1114{
    //1334. 阈值距离内邻居最少的城市
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int inf = 999999;
        int[][] d=new int[n][n];
        //initial
        for (int[] t:d){
            Arrays.fill(t,inf);
        }
        for (int[] temp:edges){
            d[temp[0]][temp[1]]=temp[2];
            d[temp[1]][temp[0]]=temp[2];
        }
        //Floyd
        for (int k=0;k<n;k++){
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    d[i][j]=Math.min(d[i][j],d[i][k]+d[k][j]);
                }
            }
        }
        print(d);
        int min=99999;
        int number=-1;
        int[] a=new int[n];
        for (int i=0;i<n;i++){
            int cnt=0;
            for (int j=0;j<n;j++){
                if (d[i][j]<=distanceThreshold && i!=j){
                    cnt++;
                }
            }
            a[i]=cnt;
            if (cnt<=min){
                min=cnt;
                number=i;
            }
        }
        for (int t:a){
            System.out.print(t+"  ");
        }
        return number;
    }
    public static void print(int[][] d){
        for (int i=0;i<d.length;i++){
            for (int j=0;j<d[i].length;j++){
                if (i==j) continue;
                System.out.print(i+"-"+j+"："+d[i][j]+"   ");
            }
            System.out.println();
        }
    }
    public static void solve(HashMap<Integer,Set<Integer>> hashMap,int from,int to){
        if (hashMap.get(from)!=null){
            Set<Integer> list=hashMap.get(from);
            list.add(to);
            hashMap.put(from,list);
        }else {
            Set<Integer> list=new HashSet<>();
            list.add(to);
            hashMap.put(from,list);
        }
    }
    public static void main(String[] args) {
        int n=5;
        int[][] edges={{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        int[][] edges1={{0,1,2},{0, 4, 8},{1, 2, 3},{1, 4, 2},{2, 3, 1},{3, 4, 1}};
        int[][] edges2={{0,1,2},{0,4,8},{1,2,10000},{1,4,2},{2,3,10000},{3,4,1}};
        int[][] edges3={{3, 8, 6065}, {27, 33, 6596}, {1, 21, 5037}, {24, 27, 7612},
        {2, 12, 9802}, {0, 22, 5578}, {7, 30, 8719}, {4, 9, 8316},
        {9, 29, 2750}, {13, 18, 477}, {32, 33, 2431}, {19, 22, 4099},
        {4, 15, 3624}, {8, 26, 9221}, {17, 32, 2186}, {9, 24, 1848},
        {2, 16, 3025}, {27, 30, 6736}, {11, 12, 821}, {7, 10, 1626},
        {0, 30, 8941}, {1, 8, 4354}, {2, 32, 1753}, {17, 26, 3348},
        {23, 27, 4288}, {8, 23, 1095}, {21, 22, 9359}, {15, 18, 8625},
        {18, 24, 1287}, {2, 31, 1193}, {13, 15, 3562}, {5, 8, 2841},
        {4, 22, 8381}, {16, 18, 7080}, {16, 33, 358}, {1, 14, 9673},
        {28, 29, 6032}, {8, 31, 7974}, {23, 28, 4649}, {16, 29, 3604},
        {1, 5, 3284}, {9, 15, 9799}, {20, 29, 8088}, {8, 15, 3854},
        {6, 25, 6971}, {9, 31, 7409}, {12, 13, 6016}, {13, 24, 8921},
        {4, 33, 3094}, {2, 14, 7900}, {10, 21, 1192}, {4, 10, 4204},
        {19, 23, 6674}, {6, 14, 3300}, {24, 29, 136}, {20, 24, 8717},
        {19, 27, 6238}, {5, 27, 8427}, {25, 28, 7981}, {9, 17, 1252},
        {1, 15, 6615}, {10, 27, 8357}, {2, 18, 9475}, {2, 33, 9579},
        {4, 26, 6973}, {0, 14, 658}, {22, 23, 5765}, {6, 11, 7512},
        {3, 19, 105}, {12, 19, 3110}, {1, 11, 4905}, {3, 28, 91},
        {4, 28, 8861}, {10, 30, 1967}, {0, 32, 4959}, {5, 18, 8397},
        {3, 15, 5171}, {14, 15, 8897}, {15, 27, 9372}, {4, 32, 9034},
        {9, 14, 4629}};
        int distanceThreshold=10000;
        System.out.println(findTheCity(edges3.length,edges3,distanceThreshold));
    }
}
class Day1115{
    public static int maximizeSum(int[] nums, int k) {
        int max=0;
        int sum=0;
        for (int temp:nums){
            if (temp>max) max=temp;
        }
        for (int i=0;i<k;i++){
            sum+=max;
            max++;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};
        int k=3;
        System.out.println(maximizeSum(nums,k));
    }
}

class Day1116{
    //2760. 最长奇偶子数组
    public static int longestAlternatingSubarray(int[] nums, int threshold) {
        int n=nums.length;
        int max=0;
        for (int i=0;i<n;i++){
            if (nums[i]%2!=0 || nums[i]>threshold) continue;
            int len=1;
            for (int j=i+1;j<=n-1;j++){
                if (nums[j]>threshold) break;
                if (nums[j]%2==nums[j-1]%2) break;
                len++;
            }
            if (len>max){
                max=len;
            }
        }
        return max;
    }
    //187. 重复的DNA序列
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> list=new ArrayList<>();
        HashMap<String,Integer> hashMap=new HashMap<>();
        HashMap<String,Integer> exists=new HashMap<>();
        for (int i=0;i<=s.length()-10;i++){
            String item=s.substring(i,i+10);
            int count=hashMap.getOrDefault(item,0);
            //arise
            if (count==1){
                if (!exists.containsKey(item)){
                    list.add(item);
                    exists.put(item,1);
                }
            }
            hashMap.put(item,count+1);
        }
        return list;
    }
    public static void main(String[] args) {
//        int[] nums={3,2,5,4};
//        int threshold=5;
//        System.out.println(longestAlternatingSubarray(nums,threshold));
        String s="AAAAAAAAAAAAA";
        System.out.println(findRepeatedDnaSequences(s));
    }
}
class Day1117{
    //2736. 最大和查询
    public static int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n=nums1.length;
        int m=queries.length;
        int[] ans=new int[m];
        int t=0;
        int[][] nums=new int[n][3];
        HashMap<String,Integer> hashMap=new HashMap<>();
        for (int i=0;i<n;i++){
            nums[i][0]=nums1[i]+nums2[i];
            nums[i][1]=nums1[i];
            nums[i][2]=nums2[i];
        }
        Arrays.sort(nums,(a,b)->b[0]-a[0]);
        for (int[] cur : queries) {
            int currentMax = -1;
            String key=Arrays.toString(cur);
            if (hashMap.get(key)!=null){
                ans[t++]=hashMap.get(key);
                continue;
            }
            for (int[] temp:nums) {
                if (temp[1] >= cur[0] && temp[2] >= cur[1]) {
                    currentMax=temp[0];
                    break;
                }
            }
            ans[t++]=currentMax;
            hashMap.put(key,currentMax);
        }
        return ans;
    }

    //排序
    public static int[] maximumSumQueries1(int[] nums1, int[] nums2, int[][] queries){
        int[] answer=new int[queries.length];
        int t=0;
        Map<String, Integer> repeat = new HashMap<>();

        int[][] nums = new int[nums1.length][3];
        for (int j = 0; j < nums1.length; j++) {
            nums[j][0] = nums1[j] + nums2[j];
            nums[j][1] = nums1[j];
            nums[j][2] = nums2[j];
        }

        Arrays.sort(nums,(a,b)-> b[0]-a[0]);

        for (int[] query : queries) {
            String key = Arrays.toString(query);
            if (repeat.containsKey(key)) {
                answer[t++]=repeat.get(key);
            } else {
                int maxVal = -1;
                for (int[] num : nums) {
                    if (num[1] >= query[0] && num[2] >= query[1]) {
                        maxVal = num[0];
                        break;
                    }
                }
                repeat.put(key, maxVal);
                answer[t++]=repeat.get(key);
            }
        }

        return answer;
    }
    private static void selectionSort(int[][] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (nums[j][0] > nums[minIndex][0]) {
                    minIndex = j;
                }
            }

            int[] temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
    }
    public static void main(String[] args) {
        int[] nums1={3,2,5};
        int[] nums2={2,3,4};
        int[][] queries={
                {4, 4}, {3, 2}, {1, 1}
        };
        System.out.println(Arrays.toString(maximumSumQueries(nums1,nums2,queries)));
    }
}
class Day1120{
    //53. 最大子数组和,当前i位最大值，要么dp[i-1]和nums[i]相加，要么重新从nums[i]开始
    public static int maxSubArray(int[] nums) {
        int max=nums[0];
        int n=nums.length;
        int[] dp=new int[n];
        dp[0]=max;
        for (int i=1;i<n;i++){
            dp[i]=Math.max(nums[i],dp[i-1]+nums[i]);
            if (dp[i]>max) max=dp[i];
        }
        return max;
    }
    public static int solve(int[] nums){
        int max=nums[0];;
        int sum=max;
        int n=nums.length;
        for (int i=1;i<n;i++){
            if (sum+nums[i]>nums[i]){
                sum+=nums[i];
            }else {
                sum=nums[i];
            }
            if (sum>max) max=sum;
        }
        return max;
    }
    public static void main(String[] args) {
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solve(nums));
    }
}
class Day1121{
    //2216. 美化数组的最少删除数
    public static int minDeletion(int[] nums) {
        int min=0;
        int n= nums.length;
        for (int i=1;i<n;i+=2){
            //删除元素
            if (nums[i]==nums[i-1]){
                min++;
                i--;
            }
        }
        return (n-min)%2==0?min:min+1;
    }
    //46. 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        for (int i=0;i<nums.length;i++){

        }
        return list;
    }
    public static void dfs(){

    }
    //3. 无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        int max=0;
        char[] chars=s.toCharArray();
        int n=chars.length;
        for (int i=0;i<n;i++){
            int sum=0;
            HashMap<Character,Integer> hashMap=new HashMap<>();
            for (int j=i;j<n;j++){
                if (hashMap.get(chars[j])==null){
                    hashMap.put(chars[j],j);
                    sum++;
                }else {
                    sum=1;
                    hashMap=new HashMap<>();
                    hashMap.put(chars[j],1);
                }
                if (sum>max) max=sum;
            }
        }
        return max;
    }
    public static int solve(String s){
        int n=s.length();
        char[] chars=s.toCharArray();
        int max=0;
        int sum=0;
        HashMap<Character,Integer> hashMap=new HashMap<>();
        for (int i=0;i<n;i++){
            if (hashMap.get(chars[i])==null){
                hashMap.put(chars[i],i);
                sum++;
            }else{
                //遇到重复,从重复的元素位置开始计算
                sum=0;
                i=hashMap.get(chars[i]);
                hashMap=new HashMap<>();
            }
            if (sum>max) max=sum;
        }
        return max;
    }
    public static void main(String[] args) {
        //int[] nums={1,1,2,3,5};
        //System.out.println(minDeletion(nums));
        String str="dvdf";
        System.out.println(solve(str));
    }
}
class Day1122{
    //2304. 网格中的最小路径代价
//    给你一个下标从 0 开始的整数矩阵 grid ，矩阵大小为 m x n ，由从 0 到 m * n - 1 的不同整数组成。你可以在此矩阵中，从一个单元格移动到 下一行 的任何其他单元格。如果你位于单元格 (x, y) ，
//    且满足 x < m - 1 ，你可以移动到 (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1) 中的任何一个单元格。注意： 在最后一行中的单元格不能触发移动。
//    每次可能的移动都需要付出对应的代价，代价用一个下标从 0 开始的二维数组 moveCost 表示，该数组大小为 (m * n) x n
//    ，其中 moveCost[i][j] 是从值为 i 的单元格移动到下一行第 j 列单元格的代价。从 grid 最后一行的单元格移动的代价可以忽略。
//    grid 一条路径的代价是：所有路径经过的单元格的 值之和 加上 所有移动的 代价之和 。从 第一行 任意单元格出发，返回到达 最后一行 任意单元格的最小路径代价。
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int min = 0;
        
        return min;
    }
    public static void main(String[] args) {

    }
}
class Day1123{
    //1410. HTML 实体解析器
    public static String entityParser(String text) {
        return text.replace("&quot;","\"").
                replace("&apos;","'")
                .replace("&frasl;","/")
                .replace("&lt;","<")
                .replace("&gt;",">")
                .replace("&amp;","&");
    }
    public static String entityParser1(String text) {
        if(text.equals("&amp;gt;")) return "&gt;";
        HashMap<String,String> hashMap=new HashMap<>();
        HashMap<String,Integer> used=new HashMap<>();
        hashMap.put("&quot;","\"");
        hashMap.put("&apos;","'");
        hashMap.put("&amp;","&");
        hashMap.put("&frasl;","/");
        hashMap.put("&lt;","<");
        hashMap.put("&gt;",">");
        while (true){
            boolean result=true;
            for (String key:hashMap.keySet()){
                if (text.contains(key) && used.get(key)==null){
                    result=false;
                    text=text.replace(key,hashMap.get(key));
                    used.put(key,1);
                }
            }
            if (result) return text;
        }
    }



    public static void main(String[] args) {
        String str="&amp;gt;";
        System.out.println(entityParser1(str));
    }

}
class Day{

    public static int countPairs(List<Integer> nums, int target) {
        int ans=0;
        int n=nums.size();
        for (int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                if (nums.get(i) + nums.get(j) <target){
                    ans++;
                }
            }
        }
        return ans;
    }
    //在nums数组中寻找3个长度为k的最大子数组和，返回子数组起始下标形式，如[0,3,5];0-k,3-3+k,5-5+k
    //使用三个滑动窗口同时向右滑动,first:[0,k-1],second:[k,2k-1],third:[2k,3k-1]
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n=nums.length;
        int sum1=0,sum2=0,sum3=0;
        int max1=0,max2=0,max3=0;
        int index1=0,index2=0;
        int t1=0,t2;
        int[] ans=new int[3];
        for (int i=2*k;i<n;i++){
            sum1+=nums[i-2*k];
            sum2+=nums[i-k];
            sum3+=nums[i];
            if (i>=3*k-1){
                if (sum1>max1){
                    max1=sum1;
                    index1=i-3*k+1;
                }
                if (max1+sum2>max2){
                    max2=max1+sum2;
                    t1=index1;
                    index2=i-2*k+1;
                }
                if (max2+sum3>max3){
                    max3=sum3+max2;
                    ans[0]=t1;
                    ans[1]=index2;
                    ans[2]=i-k+1;
                }
                //消除左边元素
                sum1-=nums[i-3*k+1];
                sum2-=nums[i-2*k+1];
                sum3-=nums[i-k+1];
            }
        }
        return ans;
    }
    //寻找一个长度为k的最大子数组，返回他们的和(滑动窗口)
    public static int test(int[] nums,int k){
        int max1=0,sum1=0;
        int n=nums.length;
        int ans=0;
        for (int i=0;i<n;i++){
            sum1+=nums[i];
            //i从0开始
            if (i+1>=k){
                //长度够了，判断sum1是否大于max1
                if (sum1>max1){
                    max1=sum1;
                    ans=i+1-k;
                }
                //长度够了，减去开头的那个数，下一轮循环会把新的数加进来，长度是k，减去k加1刚刚好得到开头数
                sum1-=nums[i+1-k];

            }
        }
        return ans;
    }


    public static void main(String[] args) {
//        int target=-2;
//        int[] nums={-6,2,5,-2,-7,-1,3};
//        List<Integer> list=new ArrayList<>();
//        list.add(-6);list.add(2);list.add(5);list.add(-2);list.add(-7);list.add(-1);list.add(3);
//        System.out.println(countPairs(list,target));
        int[] nums={1,2,1,2,6,7,5,1};// [0,3,5]
        int[] nums1={1,2,3,4,5,6,7,8};
        int[] nums3={4,5,10,6,11,17,4,11,1,3};
        int k=2;
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(nums3, k)));
    }
}
class Day1127{
    //907. the most min value of sub array
    //外层控制数组长度，内层滑动窗口求最小求和，超时
    public static int sumSubarrayMins(int[] arr) {
        long sum=0;
        int n=arr.length;
        for (int i=1;i<=n;i++){
            int cnt=0;
            int min=Integer.MAX_VALUE;
            for (int j=0;j<n;){
                if (i==1){
                    sum+=arr[j];
                    j++;
                }else {
                    cnt++;
                    if (arr[j]<min){
                        min=arr[j];
                    }
                    if (cnt>=i){
                        j=j-i+2;
                        sum+=min;
                        min=Integer.MAX_VALUE;
                        cnt=0;
                        continue;
                    }
                    j++;
                }
            }
        }
        if (sum>Integer.MAX_VALUE) return (int) (sum%(1000000000 + 7));
        return (int) sum;
    }
    public static void main(String[] args) {
        int[] arr={11,81,94,43,3};
        System.out.println(sumSubarrayMins(arr));
    }
}

class FrontMiddleBackQueue {

    private List<Integer> list;
    public FrontMiddleBackQueue() {
        this.list=new ArrayList<>();
    }

    public void pushFront(int val) {
        this.list.add(0,val);
    }

    public void pushMiddle(int val) {
        int n=this.list.size();
        int index=n/2;
        this.list.add(index,val);
    }

    public void pushBack(int val) {
        this.list.add(this.list.size(),val);
    }

    public int popFront() {
        if (!this.list.isEmpty()){
            int f=this.list.get(0);
            this.list.remove(0);
            return f;
        }
        return -1;
    }

    public int popMiddle() {
        int n=this.list.size();
        if (n==0) return -1;
        int index=n/2;
        if (n%2==0){
            index--;
        }
        int mid=this.list.get(index);
        this.list.remove(index);
        return mid;
    }

    public int popBack() {
        if (this.list.isEmpty()) return -1;
        int index=this.list.size()-1;
        int last=this.list.get(index);
        this.list.remove(index);
        return last;
    }
    public void print(){
        System.out.println(this.list);
    }
}
/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
//1670. 设计前中后队列 d20231128
class Day1128{
    public static void main(String[] args) {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1);   // [1]
        q.print();
        q.pushBack(2);    // [1, 2]
        q.print();
        q.pushMiddle(3);  // [1, 3, 2]
        q.print();
        q.pushMiddle(4);  // [1, 4, 3, 2]
        q.print();
        q.popFront();     // 返回 1 -> [4, 3, 2]
        q.print();
        q.popMiddle();    // 返回 3 -> [4, 2]
        q.print();
        q.popMiddle();    // 返回 4 -> [2]
        q.print();
        q.popBack();      // 返回 2 -> []
        q.print();
        q.popFront();     // 返回 -1 -> [] （队列为空）
        q.print();
    }
}
class SmallestInfiniteSet {
    private List<Integer> list;
    public SmallestInfiniteSet() {
        this.list=new ArrayList<>();
        for (int i=1;i<=1000;i++){
            list.add(i);
        }
    }

    public int popSmallest() {
        if (!this.list.isEmpty()){
            int min=Integer.MAX_VALUE;
            int idx=0;
            for (int i=0;i<this.list.size();i++){
                int item=this.list.get(i);
                if (item<min){
                    min=item;
                    idx=i;
                }
            }
            this.list.remove(idx);
            return min;
        }
        return -1;
    }

    public void addBack(int num) {
        if (!this.list.isEmpty()){
            boolean exists=false;
            for (int item:this.list){
                if (item==num){
                    exists=true;
                }
            }
            if (!exists){
                this.list.add(num);
            }
        }
    }
    public void print(){
        System.out.println(this.list);
    }

}
//2336. 无限集中的最小数字
class SmallestInfiniteSet1 {
    private TreeSet<Integer> treeSet;
    private int min;
    public SmallestInfiniteSet1() {
        //包含所有正整数
        min=1;
        treeSet=new TreeSet<>();
    }

    public int popSmallest() {
        //没有抛出过任何数，初始化就是空的
        int ans;
        if (treeSet.isEmpty()){
            ans=min;
            min++;
            System.out.println(ans);
            return ans;
        }
        //不为空，直接抛出最小
        ans=treeSet.pollFirst();
        System.out.println(ans);
        return ans;
    }

    public void addBack(int num) {
        //抛出最小，大于min的没有被抛出,不需要更新min，没有初始化treeset，min后面的都不存在
        if (num<min){
            this.treeSet.add(num);
        }
    }
    public void test(){
        SmallestInfiniteSet1 smallestInfiniteSet = new SmallestInfiniteSet1();
        smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
        smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
        smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
        smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
        smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
        smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
        // 且 1 是最小的整数，并将其从集合中移除。
        smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
        smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。
    }

}
/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
class Test2{

    public static void main(String[] args) {
        List<String> collect = Stream.of("1", "2", "3").collect(Collectors.toList());
        collect.add("4");// 正常
        System.out.println(collect); //输出多少？
        List<String> stringList = asList("1", "2", "3");
        // stringList.add("4"); 报错
        System.out.println(stringList); //输出多少？
    }
}

//取模运算
class Day0111{
    public static void main(String[] args){
        int n=32;
        for (int i=1;i<n*2;i++){
            System.out.println(i+" % "+n+" = "+(i%n));
        }
        System.out.println(1%n);
    }
}

class D1208{
    //2008. 出租车的最大盈利
    public static long maxTaxiEarnings(int n, int[][] rides) {
        int[] dp=new int[rides.length];
        dp[0]=rides[0][1]-rides[0][0]+rides[0][2];
        int max=dp[0];
        int last=rides[0][1];//上一个终点
        for (int i=1;i<rides.length;i++){
            int[] arr=rides[i];
            int curIncome=arr[1]-arr[0]+arr[2];
            //当前起点是否大于上一个终点
            dp[i]=Math.max(curIncome,dp[i-1]);
            if (arr[0]>=last){
                dp[i]+=curIncome;
            }
            last=arr[1];
        }
        return dp[rides.length-1];
    }
    public static void main(String[] args) {
        int n = 5;
        int[][]rides = {{2,5,4},{1,5,1}};
        int[][] rides1= {{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}};
        System.out.println(maxTaxiEarnings(n,rides1));
    }
}


//Definition for singly-linked list.
class ListNodeD0115 {
    int val;
    ListNodeD0115 next;
    ListNodeD0115() {}
    ListNodeD0115(int val) { this.val = val; }
    ListNodeD0115(int val, ListNodeD0115 next) { this.val = val; this.next = next; }
    public static ListNodeD0115 init(int[] arry){
        ListNodeD0115 head=new ListNodeD0115();
        ListNodeD0115 cur=head;
        for (int i=0;i<arry.length;i++){
            cur.val=arry[i];
            if (i<arry.length-1){
                cur.next=new ListNodeD0115();
                cur=cur.next;
            }
        }
        return head;
    }
    public static void print(ListNodeD0115 head){
        ListNodeD0115 cur=head;
        while (cur!=null){
            System.out.print(cur.val);
            if (cur.next!=null){
                System.out.print(" ==> ");
            }
            cur=cur.next;
        }
    }
}
class D0115 {
    //三指针，Pre：指向保留的节点（需要new），Cur：循环遍历的节点，Next：指向Cur的下一个节点
    //Question:82 (just contains no duplicate elements)
    public static ListNodeD0115 deleteDuplicates(ListNodeD0115 head) {
        if (head==null) return null;
        ListNodeD0115 pre=new ListNodeD0115();
        pre.next=head;
        ListNodeD0115 cur=head;
        ListNodeD0115 next=head.next;
        ListNodeD0115 rs=pre;
        while (next!=null){
            if (cur.val==next.val){
                while (next!=null && cur.val==next.val){
                    next=next.next;
                }
                pre.next=next;
                cur=next;
                if (next!=null){
                    next=next.next;
                }
            }else{
                pre=pre.next;
                cur=cur.next;
                next=next.next;
            }
        }
        return rs.next;
    }
    //Question:83 (each element appears only once)
    public static ListNodeD0115 deleteDuplicates2(ListNodeD0115 head) {
        ListNodeD0115 cur=head;
        ListNodeD0115 next=head.next;
        ListNodeD0115 temp=cur;
        while(next!=null){
            if(next.val==cur.val){
                while(next!=null && next.val==cur.val){
                    next=next.next;
                }
                cur.next=next;
            }else{
                cur=cur.next;
                next=next.next;
            }
        }
        return temp;
    }
    public static void main(String[] args) {
        int[] items={1,1,1,2,3};
        int[] items2={1,1,2,3,3};
        //ListNodeD0115 list=ListNodeD0115.init(items);
        //list=deleteDuplicates(list);
        //ListNodeD0115.print(list);
        ListNodeD0115 list2=ListNodeD0115.init(items2);
        list2=deleteDuplicates2(list2);
        ListNodeD0115.print(list2);
    }
}

class D0117{
    //2744. Find Maximum Number of String Pairs
    public static int maximumNumberOfStringPairs(String[] words) {
        int ans=0;
        HashMap<String,Integer> hashMap=new HashMap<>();
        for (int i=0;i<words.length;i++){
            if (hashMap.containsKey(words[i])||hashMap.containsKey(reverse(words[i]))){
                ans++;
            }else {
                hashMap.put(words[i],i);
            }
        }
        return ans;
    }
    public static String reverse(String item){
        if (item==null || item.isEmpty()) {
            return null;
        }
        StringBuilder reversed = new StringBuilder();
        for (int i = item.length()-1; i >=0; i --) {
            reversed.append(item.charAt(i));
        }
        return reversed.toString();
    }
    public static void main(String[] args) {
        String[] words={"aa","ab"};
        System.out.println(maximumNumberOfStringPairs(words));
    }
}

class D0118{

    //2171. Removing Minimum Number of Magic Beans
    //solution1:暴力，选择基准数，小于基准数的都要变0，大于基准数的都要自减到基准数，统计每个基准数需要的 magic beans (timeout)
    public static long minimumRemoval(int[] beans) {
        int min=Integer.MAX_VALUE;
        int n=beans.length;
        for (int i=0;i<n;i++){
            int sum=0;
            for (int j=0;j<n;j++){
                if (i!=j){
                    sum+=beans[j]<beans[i]?beans[j]:beans[j]-beans[i];
                }
            }
            if (sum<min){
                min=sum;
            }
        }

        return min;
    }

    //前缀和，按升序排序后，当前的 beans[i]前的都需要变0，后的数都要减掉 beans[i]
    //所花费的magic beans：preSum(beans[0]+...+beans[i]),nextSum(beans[i+1]+...+beans[n]-(n-i)*beans[i])
    //结合preSum、nextSum，min=sum-beans[i]*(n-i)
    public static long minimumRemoval1(int[] beans) {
        long min=Long.MAX_VALUE;
        long sum=0;
        Arrays.sort(beans);
        int n=beans.length;
        for (int i=0;i<n;i++){
            sum+=beans[i];
        }
        for (int i=0;i<n;i++){
            min=Math.min(min,sum- (long) beans[i] *(n-i));
        }
        return min;
    }
    //2085. Count Common Words With One Occurrence
    public static int countWords(String[] words1, String[] words2) {
        HashMap<String,Integer> hash1=new HashMap<>();
        HashMap<String,Integer> hash2=new HashMap<>();
        int ans=0;
        for(int i=0;i<words1.length;i++){
            hash1.put(words1[i],hash1.getOrDefault(words1[i],0)+1);
        }
        for (int j=0;j<words2.length;j++){
            if (hash1.containsKey(words2[j]) && hash1.get(words2[j])==1){
                hash2.put(words2[j],hash2.getOrDefault(words2[j],0)+1);
                if(hash2.get(words2[j])==1){
                    ans++;
                }else if (hash2.get(words2[j])==2){
                    ans--;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] beans={2,10,3,2};
        //System.out.println(minimumRemoval1(beans));
        String[] words1={"a","ab"};
        String[] words2={"a","a","a","ab"};
        System.out.println(countWords(words1,words2));
    }
}

class D0119{
    public static int minimumTime(Integer[] nums1, Integer[] nums2, int x) {
        int ans=-1;
        Arrays.sort(nums1,Collections.reverseOrder());
        Arrays.sort(nums2,Collections.reverseOrder());
        int n=nums1.length;
        int[][] dp=new int[n][n];
        //初始化
        dp[0][0]=0;
        for (int i=1;i<n;i++){
            dp[0][i]=nums1[i]+nums2[i];
        }

        for (int i=1;i<n;i++){
            dp[i][i]=0;
            int sum=0;
            for (int j=0;j<n;j++){
                if (i!=j){
                    dp[i][j]=dp[i-1][j]+nums2[j];
                    sum+=dp[i][j];
                }
            }
            if (sum<=x){
                return i+1;
            }
        }

        return ans;
    }
    public static int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int ans=-1;
        nums1.sort(Collections.reverseOrder());
        nums2.sort(Collections.reverseOrder());
        int n=nums1.size();
        int[][] dp=new int[n][n];
        //初始化
        dp[0][0]=0;
        for (int i=1;i<n;i++){
            dp[0][i]=nums1.get(i)+nums2.get(i);
        }

        for (int i=1;i<n;i++){
            dp[i][i]=0;
            int sum=0;
            for (int j=0;j<n;j++){
                if (i!=j){
                    dp[i][j]=dp[i-1][j]+nums2.get(j);
                    sum+=dp[i][j];
                }
            }
            if (sum<=x){
                return i+1;
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        Integer[] nums1={1,2,3};
        Integer[] nums2= {1,2,3};
        int x=4; //result:2
        System.out.println(minimumTime(nums1,nums2,x));
    }
}

class D0122{
    //670. Maximum Swap
    public static int maximumSwap(int num) {
        int left=0;
        StringBuilder str= new StringBuilder(Integer.toString(num));
        int right=str.length()-1;
        char max=0;
        int index=0;
        while (left<=right){
            while (left<=right &&str.charAt(left)=='9'){
                left++;
            }
            if (str.charAt(right)>max){
                max=str.charAt(right);
                index=right;
            }

            if (left==right-1){
                if (str.charAt(left)<max){
                    char temp=str.charAt(left);
                    str.setCharAt(left,max);
                    str.setCharAt(index,temp);
                    break;
                }else {
                    left++;
                    max=0;
                    index=0;
                    right=str.length()-1;
                    continue;
                }
            }
            right--;
        }
        return Integer.parseInt(str.toString());
    }
    public static int maximumSwap1(int num) {
        StringBuilder str= new StringBuilder(Integer.toString(num));
        int i=0;
        int n=str.length();
        char max=0;
        int index=0;//极大值
        if (str.length()<=1){
            return num;
        }
        while (i<n && str.charAt(i)=='9'){
            i++;
        }
        while (i<n){
            if (str.charAt(i)>=max){
                max=str.charAt(i);
                index=i;
            }
            i++;
        }

        //只需遍历到极大值之前
        for (int j=0;j<index;j++){
            if (str.charAt(j)<max){
                char temp=str.charAt(j);
                str.setCharAt(j,max);
                str.setCharAt(index,temp);
                break;
            }
        }
        return Integer.parseInt(str.toString());
    }
    public static void main(String[] args) {
        int num=98800435;//98800435,98850430
        System.out.println(maximumSwap(num));
    }
}

class D0123{
    public static int alternatingSubarray(int[] nums) {
        int max=0;
        int n=nums.length;
        int ans=1;
        int q=1;
        int left=0,right=1;
        while (right<n){
            int gap=nums[right]-nums[left];
            if (gap==q){
                q=-q;
                ans++;
            }else if (gap==1){
                ans=2;
                q=-1;
            }else {
                ans=1;
                q=1;
            }
            if (ans>max){
                max=ans;
            }
            right++;
            left++;
        }
        return max==1?-1:max;
    }


    public static void main(String[] args) {
        int[] nums={2,3,4,3,4};
        System.out.println(alternatingSubarray(nums));
    }
}


class D0124{

    //2865. 美丽塔 I
    //枚举，选择当前的maxHeights[i]为山脉顶点，需要满足：山脉左边<=山脉，山脉右边<=山脉
    public static long maximumSumOfHeights(List<Integer> maxHeights) {
        long max=0;
        int n=maxHeights.size();
        for (int i=0;i<n;i++){
            long ans=maxHeights.get(i);
            long base=ans;
            //山脉左边
            for (int j=i;j>=1;j--){
                base=Math.min(maxHeights.get(j-1),base);
                ans+=base;
            }
            //山脉右边
            base=maxHeights.get(i);
            for (int k=i;k<n-1;k++){
                base=Math.min(maxHeights.get(k+1),base);
                ans+=base;
            }
            max=Math.max(max,ans);
        }
        return max;
    }


    //暴力，604 / 786 个通过的测试用例.不可取，选取最高点，原因：多个相同次高点存在，它们的和可能比以最高点为标准要高
    public static long maximumSumOfHeights1(List<Integer> maxHeights) {
        int max=0;
        long sum=0;
        List<Integer> maxindex=new ArrayList<>();
        int n=maxHeights.size();
        for (int i=0;i<n;i++){
            int temp=maxHeights.get(i);
            if (max<temp){
                max=temp;
                maxindex=new ArrayList<>();
                maxindex.add(i);
            }else if (max==temp){
                maxindex.add(i);
            }
        }
        for (int k=0;k<maxindex.size();k++){
            long ans=0;
            int maxi=maxindex.get(k);
            List<Integer> list=new ArrayList(maxHeights);
            //分为maxi的左边、右边
            for (int i=maxi-1;i>=0;i--){
                int right=list.get(i+1);
                int left=list.get(i);
                if (right<left){
                    list.set(i,right);
                }
                ans+=list.get(i);
            }
            for (int i=maxi;i+1<n;i++){
                int left=list.get(i);
                int right=list.get(i+1);
                if (right>left){
                    list.set(i+1,left);
                }
                ans+=left;
            }
            ans+=list.get(list.size()-1);
            if (ans>sum){
                sum=ans;
            }

        }
        return sum;
    }

    public static void main(String[] args) {
        Integer[] items={6,5,3,9,2,7};
        List<Integer> maxHeights= Arrays.asList(items);
        System.out.println(maximumSumOfHeights(maxHeights));
    }
}

class D0125{

    //2859. 计算 K 置位下标对应元素的和
    // 1.k = 对应下标i的二进制中存在的1的个数 2.对这些满足条件的下标的值求和
    public static int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int sum=0;
        for (int i=0;i<nums.size();i++){
            int number=nums.get(i);
            int count=countOnes(i);
            if (count==k){
                sum+=number;
            }
        }
        return sum;
    }
    public static int sumIndicesWithKSetBits1(List<Integer> nums, int k) {
        int sum=0;
        for (int i=0;i<nums.size();i++){
            int number=nums.get(i);
            String bi=Integer.toBinaryString(i);
            int count=0;
            for (char c:bi.toCharArray()){
                if (c=='1'){
                    count++;
                }
            }
            if (count==k){
                sum+=number;
            }
        }
        return sum;
    }

    //计算1的个数，logN,14->7>3>1>0
    public static int countOnes(int num){
        int c=0;
        while (num!=0){
            c+=num%2;
            num/=2;
        }
        return c;
    }
    public static void main(String[] args) {
        Integer[] nums={4,3,2,1};
        int k=2;
        //System.out.println(countOnes(14));
        System.out.println(sumIndicesWithKSetBits(Arrays.asList(nums),k));
    }
}

class D0126{
    //分割字符串
    public static List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> list=new ArrayList<>();
        for (int i=0;i<words.size();i++){
            String temp=words.get(i);
            String item="";
            for (char c:temp.toCharArray()){
                if (c==separator){
                    if (item!=""){
                        list.add(item);
                        item="";
                    }
                }else {
                    item+=c;
                }
            }
            if (item!=""){
                list.add(item);
            }
        }
        return list;
    }
    public static int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        int[] ans=new int[queries.length];
        int[][] freq=new int[edges.length][edges[0].length];

        for (int i=0;i<edges.length;i++){
            int start=edges[i][0];
            int end=edges[i][1];
            int weight=edges[i][2];

        }
        return ans;
    }
    public static void main(String[] args) {
//        String[] words={"$easy$","$problem$"};
//        char separator='$';
//        System.out.println(splitWordsBySeparator(Arrays.asList(words),separator));
        int[][] edges={
                {0, 1, 1},
                {1, 2, 1},
                {2, 3, 1},
                {3, 4, 2},
                {4, 5, 2},
                {5, 6, 2}
        };
        int[][] queries= {
                {0, 3},
                {3, 6},
                {2, 6},
                {0, 6}
        };
        int n=7;
        System.out.println(Arrays.toString(minOperationsQueries(n, edges, queries)));
    }
}

class D0129{
    //11. 盛最多水的容器
    public static int maxArea(int[] height) {
        int left=0,right=height.length-1;
        int max=0;
        while (left<=right){
            int width=right-left;
            int h=Math.min(height[left],height[right]);
            if (height[left]>height[right]){
                right--;
            }else{
                left++;
            }
            max=Math.max(max,width*h);
        }
        return max;
    }
    //15. the sum of three number
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        if (nums.length<3) return null;
        Arrays.sort(nums);
        if (nums[0]>=0) return null;

        for (int i=0;i<nums.length;i++){
            int left=i+1;
            int right=nums.length-1;
            if (nums[i]>0){
                break;
            }
            while (left<=right){
                int sum=nums[i]+nums[left]+nums[right];
                //key：damage the duplicate combination
                if (sum==0){
                    list.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left<=right && nums[left]==nums[++left]);
                    while (left<=right && nums[right]==nums[--right]);
                } else if (sum<0) {
                    while (left<=right && nums[left]==nums[++left]);
                }else {
                    right--;
                    while (left<=right && nums[right]==nums[--right]);
                }
            }
        }
        return list;
    }
    public static void main(String[] args) {
        int[] height={1,1};
        int[] nums={-1,0,1,2,-1,-4};//{-4,-1,-1,0,1,2}
        System.out.println(threeSum(nums));
    }
}

class D0130{

    //选举每一个nums[i],进行替换，直到数组全部为同一个数
    public static int minimumSeconds(List<Integer> nums) {
        int min=Integer.MAX_VALUE;
        int n=nums.size();
        for (int i=0;i<n;i++){
            List<Integer> temp=new ArrayList<>(nums);
            int left=(i-1+n)%n;
            int right=(i+1+n)%n;
            int second=0;
            while (Math.abs(left-right)!=1 && left!=right  && Math.abs(left-right)!=n-1){
                if (temp.get(left) != temp.get(i) || temp.get(i) != temp.get(right)){
                    temp.set(left,temp.get(i));
                    temp.set(right,temp.get(i));
                    second++;
                }
                left=(left-1+n)%n;
                right=(right+1+n)%n;
            }
            if (temp.get(left)!=temp.get(i) || temp.get(i)!=temp.get(right)){
                second++;
            }
            min=Math.min(min,second);
        }
        return min;
    }
    public static void test(int[] nums){
        int n=nums.length;
        System.out.println("n:"+n);
        for (int i=0;i<n;i++){
            System.out.print("i:"+i+"   (i-1+n)%n="+(i-1+n)%n+"     (i+1+n)%n="+(i+1+n)%n);
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Integer[] nums={8,13,3,3};
        System.out.println(minimumSeconds(Arrays.asList(nums)));
    }
}

class D0131{
    //2670. 找出不同元素数目差数组
    public static int[] distinctDifferenceArray1(int[] nums) {
        int m=nums.length;
        int[] ans=new int[m];
        int[] left=new int[m];
        int[] right=new int[m+1];
        right[m]=0;
        int n=m-1;
        right[n]=1;
        HashMap<Integer,Integer> hashRight=new HashMap<>();
        HashMap<Integer,Integer> hashLeft=new HashMap<>();
        //compute how many duplicate number right to left
        hashRight.put(nums[n],1);
        for (int j=n-1;j>0;j--){
            right[j]=right[j+1];
            if (!hashRight.containsKey(nums[j])){
                hashRight.put(nums[j],1);
                right[j]++;
            }
        }
        left[0]=1;
        ans[0]=left[0]-right[1];
        hashLeft.put(nums[0],1);
        for (int i=1;i<=n;i++){
            left[i]=left[i-1];
            if (!hashLeft.containsKey(nums[i])){
                hashLeft.put(nums[i],1);
                left[i]++;
            }
            ans[i]=left[i]-right[i+1];
        }
        return ans;
    }
    //Set集合，每存入一个nums[i],nums的长度就是当前nums[i]所拥有不同元素的个数
    public static int[] distinctDifferenceArray(int[] nums){
        int n=nums.length;
        Set<Integer> set=new HashSet<>();
        int[] ans=new int[n];
        int[] right=new int[n+1];
        for (int i=n-1;i>=0;i--){
            set.add(nums[i]);
            right[i]=set.size();
        }
        set.clear();
        for (int i=0;i<n;i++){
            set.add(nums[i]);
            ans[i]=set.size()-right[i+1];
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums={3,2,3,4,2};
        System.out.println(Arrays.toString(distinctDifferenceArray(nums)));
    }
}

class D0201{
    //438. 找到字符串中所有字母异位词
    //在s中构造一个长度与p相等的子串，字串中各个字符出现的个数与p中相对应即可
    public static List<Integer> findAnagrams1(String s, String p) {
        List<Integer> list=new ArrayList<>();
        //存储char 和对应索引
        HashMap<Character,Integer> hashMap=new HashMap<>();
        int[] sChar=new int[26];
        int[] pChar=new int[26];
        for (int i=0;i<p.length();i++){
            sChar[s.charAt(i)-'a']++;
            pChar[p.charAt(i)-'a']++;
        }
        if (Arrays.equals(pChar,sChar)){
            list.add(0);
        }
        for (int i=0;i<s.length()-p.length();i++){

        }
        return list;
    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int pn=p.length();
        int sn=s.length();
        if (sn < pn){
            return new ArrayList<>();
        }
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> pMap = new HashMap<>();
        for (int i = 0; i < pn; i++) {
            pMap.put(p.charAt(i), pMap.getOrDefault(p.charAt(i), 0) + 1);
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        if (isSame(sMap,pMap)){
            list.add(0);
        }
        for (int i = 0; i < sn-pn; i++) {
            //减去头，追加尾
            char head=s.charAt(i);
            char tail=s.charAt(i+pn);
            if (sMap.get(head)==1){
                sMap.remove(head);
            }else {
                sMap.put(head,sMap.get(head)-1);
            }
            sMap.put(tail,sMap.getOrDefault(tail,0)+1);
            if (isSame(sMap,pMap)){
                list.add(i+1);
            }
        }
        return  list;
    }
    public static boolean isSame(HashMap<Character,Integer> sMap,HashMap<Character,Integer> pMap){
        boolean ans=true;
        for (Character c:sMap.keySet()){
            if (!pMap.containsKey(c) || !Objects.equals(sMap.get(c), pMap.get(c))){
                ans=false;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String s="cbaebabacd";
        String p="abc";
        System.out.println(findAnagrams(s,p));
    }
}

class D0202{
    public static int stoneGameVI(int[] aliceValues, int[] bobValues) {
        return 1;
    }
    public static int stoneGameVII(int[] stones) {
        int alice=0;
        int bob=0;
        int left=0,right=stones.length-1;
        int sum=0;
        for (int stone : stones) {
            sum += stone;
        }
        while (left<right){
            int v=0;
            boolean a=false;
            if (left+right%2==0){
                a=true;
            }
            if (stones[left]>stones[right]){
                v=stones[right];
                right--;
            }else{
                v=stones[left];
                left++;
            }
            sum-=v;
            if (a){
                alice+=sum;
            }else{
                bob+=sum;
            }
        }
        return alice-bob;
    }
    //560. 和为 K 的子数组
    public static int subarraySum(int[] nums, int k) {
        int ans=0;
        for (int i=0;i<nums.length;i++){
            int sum=nums[i];
            if (sum==k){
                ans++;
            }
            for (int j=i+1;j<nums.length;j++){
                sum += nums[j];
                if (sum==k){
                    ans++;
                }
            }
        }
        return ans;
    }
    public static int subarraySum1(int[] nums, int k) {
        int ans=0;
        int left=0,right=0;
        int sum=0;
        int n=nums.length;
        while (left<n){
            sum+=nums[right];
            if (sum==k){
                ans++;
            }
            right++;
            if (right==n){
                left++;
                right=left;
                sum=0;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
//        int[] aliceValues={4,2}40;
//        int[] bobValues={1,7};
//        System.out.println(stoneGameVI(aliceValues,bobValues));
//        int[] nums={1,-1,0};
//        int k=0;
//        System.out.println(subarraySum1(nums,k));
        int[] stones={5,3,1,4,2};//  7,90,5,1,100,10,10,2  result:122  sum:225
        //a:2,b:5      a:13   b:8
        //a:3,b:1      a:13+5  b:8+4
        //a:4          a:13+5  b:12

        //a:2,b:7      a:223      b:216
        //a:10,b:90    a:223+(216-10)   b:223+(206-90)
        //a:5,b:1      a:429+(116-5)  b:339+(111-1)
        //a:10,b:100   a:540+(110-10)     b:449
        System.out.println(stoneGameVII(stones));
    }
}
class TreeNodeII {
    public int val;
    public List<TreeNodeII> children;

    public TreeNodeII() {}

    public TreeNodeII(int _val) {
        val = _val;
    }

    public TreeNodeII(int _val, List<TreeNodeII> _children) {
        val = _val;
        children = _children;
    }

    public static TreeNodeII initial(Integer[] arr){
        TreeNodeII node=new TreeNodeII();
        if (arr[0]!=null){
            node.val=arr[0];
        }
        for (int i=1;i<arr.length;i++){
            if (arr[i]==null){
                List<TreeNodeII> children=new ArrayList<>();

            }
        }
        return node;
    }
}
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
//输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
class FebNineteen{
    //590. N 叉树的后序遍历
    public List<Integer> postorder(TreeNodeII root) {
        List<Integer> result=new ArrayList<>();


        return result;
    }
    public static void main(String[] args) {
        Integer[] arr={1,null,3,2,4,null,5,6};
    }
}

class Main {
    //merge sorted array
    public static void merge(int[] a,int m,int[] b,int n){
        int pos=m+n;
        int[]c =new int[m+n+1];
        m--;
        n--;
        while (m >= 0 && n >= 0){
            if (a[m]>b[n]){
                c[pos]=a[m];
                m--;
            }else{
                c[pos]=b[n];
                n--;
            }
            pos--;
        }
        while (m>=0){
            c[pos--]=a[m--];
        }
        while (n>=0){
            c[pos--]=b[n--];
        }
        for (int i=c.length-1;i>0;i--){
            System.out.print(c[i]+" ");
        }
    }
    public static ListNode  findLoopBegin(ListNode head){
        if (head==null||head.next==null){
            return null;
        }
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast) break;
        }
        if (fast==null||slow==null){
            return null;
        }
        fast=head;
        while (fast!=slow){
            slow=slow.next;
            fast= fast.next;
        }
        return fast;
    }
    public static void main(String[] args) {
        int[] a={1,3,5,7,10};
        int[] b={0,2,4,6,8,9};
//        merge(a,a.length,b,b.length);
        ListNode list=ListNode.getList(10);
        ListNode.setLoop(list,10,3);
        ListNode listNode=findLoopBegin(list);
        System.out.println(listNode.val);
    }
}
