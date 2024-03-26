package Y24;

import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;

import javax.swing.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class Main {
}
class Feb20{
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length==1){
            return new TreeNode(preorder[0]);
        }
        TreeNode root=new TreeNode();
        root.val=preorder[0];
        TreeNode node=root;
        int i=0,j=0;
        //left tree
        while (inorder[i++]!=preorder[0]){

        }

        return root;
    }
    //239. 滑动窗口最大值
    //预处理前缀最大值和后缀最大值，当 i%k==0 时说明这是新的一组(k长度)窗口，最大值为nums[i]，i%k!=0 需要和前一个最大值比较
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        int[] result=new int[n-k+1];
        int[] preMax=new int[n];
        int[] sufMax=new int[n];
        for (int i=0;i<n;i++){
            if(i%k==0){
                preMax[i]=nums[i];
            }else{
                preMax[i]=Math.max(preMax[i-1],nums[i]);
            }
        }
        for (int i=n-1;i>=0;i--){
            if ((i+1)%k==0 || i==n-1){
                sufMax[i]=nums[i];
            }else{
                sufMax[i]=Math.max(sufMax[i+1],nums[i]);
            }
        }
        for (int i=0;i<n-k+1;i++){
            result[i]=Math.max(preMax[i+k-1],sufMax[i]);
        }
        return result;
    }
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] preorder={3,9,20,15,7};
        int[] inorder={9,3,15,20,7};
        int[] nums={1,3,-1,-3,5,3,6,7};
        int k=3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums,k)));
    }
}
class Feb21{
    //53. 最大子数组和
    public static int maxSubArray(int[] nums) {
        int sum=nums[0];
        int max=sum;
        for (int i=1;i<nums.length;i++){
            if (sum+nums[i]>nums[i]){
                sum+=nums[i];
            }else{
                sum=nums[i];
            }
            max=Math.max(sum,max);
        }
        return max;
    }
    //56. 合并区间
    public static int[][] merge(int[][] intervals) {
        int n=intervals.length;
        if (n<=1){
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });

        int[][] result=new int[n][2];
        result[0][0]=intervals[0][0];
        result[0][1]=intervals[0][1];
        int t=1;
        for (int i=1;i<n;i++){
            int preEnd=result[t-1][1];
            int curStart=intervals[i][0];
            int curEnd=intervals[i][1];
            if (preEnd>=curStart){
                int max=Math.max(preEnd,curEnd);
                result[t-1][1]=max;
            }else {
                result[t][0]=curStart;
                result[t++][1]=curEnd;
            }
        }
        int[][] arr=new int[t][2];
        int k=0;
        for (int i=0;i<t;i++){
            arr[k][0]=result[i][0];
            arr[k++][1]=result[i][1];
        }
        return arr;
    }

    //189. 轮转数组
    public static void rotate(int[] nums, int k) {
        int n=nums.length;
        int[] arr=Arrays.copyOf(nums,n);
        for (int i=0;i<n;i++){
            int idx=(i+k)%n;
            nums[idx]=arr[i];
        }
    }
    //238. 除自身以外数组的乘积
    public static int[] productExceptSelf(int[] nums) {
        int length=nums.length;
        int[] ans=new int[length];
        int[] pre=new int[length];
        pre[0]=nums[0];
        int[] suf=new int[length];
        suf[length-1]=nums[length-1];
        for (int i=1;i<length;i++){
            pre[i]=pre[i-1]*nums[i];
        }
        for (int i=length-2;i>=0;i--){
            suf[i]=suf[i+1]*nums[i];
        }
        ans[0]=suf[1];
        ans[length-1]=pre[length-2];
        for (int i=1;i<length-1;i++){
            ans[i]=pre[i-1]*suf[i+1];
        }
        return ans;
    }
    //41. 缺失的第一个正数
    public static int firstMissingPositive(int[] nums) {
        int length=nums.length;
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int n:nums){
            hashMap.put(n,n);
        }
        for (int i=1;i<=length+1;i++){
            if (!hashMap.containsKey(i)){
                return i;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        //int[] nums={-2,1,-3,4,-1,2,1,-5,4};
        //System.out.println(maxSubArray(nums));

        //int[][] intervals={{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        //System.out.println(Arrays.deepToString(merge(intervals)));

        //int[] nums={-1,-100,3,99};
        //int k=2;
        //rotate(nums,k);
        //System.out.println(Arrays.toString(nums));

        //int[] nums={-1,1,0,-3,3};
        //System.out.println(Arrays.toString(productExceptSelf(nums)));

        int[] nums={7,8,9,11,12};
        System.out.println(firstMissingPositive(nums));
    }
}

class Feb22{
    //76. 最小覆盖子串
    public static String minWindow(String s, String t) {
        int tLen=t.length();
        int sLen=s.length();
        int min=Integer.MAX_VALUE;
        int minL=0;
        int minR=0;
        if (sLen<tLen){
            return "";
        }
        int left=0,right=tLen;
        HashMap<Character,Integer> sMap=new HashMap<>();
        HashMap<Character,Integer> tMap=new HashMap<>();
        for (int i=0;i<tLen;i++){
            tMap.put(t.charAt(i),tMap.getOrDefault(t.charAt(i),0)+1);
        }
        for (int i=0;i<tLen;i++){
            sMap.put(s.charAt(i),sMap.getOrDefault(s.charAt(i),0)+1);
        }
        if (sLen==tLen){
            return isContained(sMap,tMap)?s:"";
        }
        while (right<sLen){
            if (isContained(sMap,tMap)){
                if (right-left+1<min){
                    min=right-left+1;
                    minL=left;
                    minR=right;
                }
                sMap.put(s.charAt(left),sMap.get(s.charAt(left))-1);
                left++;
            }else{
                sMap.put(s.charAt(right),sMap.getOrDefault(s.charAt(right),0)+1);
                right++;
            }
        }
        if (min==Integer.MAX_VALUE && !isContained(sMap,tMap)){
            return "";
        }
        while (isContained(sMap,tMap)){
            sMap.put(s.charAt(left),sMap.get(s.charAt(left))-1);
            left++;
        }
        if (min>right-left+1){
            minL=left-1;
            minR=right;
        }
        return s.substring(minL,minR);
    }
    public static boolean isContained(HashMap<Character,Integer> sMap,HashMap<Character,Integer> tMap){
        boolean rs=true;
        for (Character c:tMap.keySet()){
            if (!sMap.containsKey(c) || sMap.get(c)<tMap.get(c)){
                rs=false;
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        String s="ab";
        String t="b";
        System.out.println(minWindow(s,t));
    }
}
class Fev27{
    //2867. 统计树中的合法路径数目
    //预处理n中质数，并查集->路径压缩
    public static long countPaths(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> hashMap=new HashMap<>();
        List<List<Integer>> result=new ArrayList<>();
        long ans=0;
        //构建简单a节点到b节点的map
        for (int[] arr:edges){
            int key=arr[0];
            int value=arr[1];
            List<Integer> list;
            if (hashMap.containsKey(key)){
                list=hashMap.get(key);
            }else{
                list=new ArrayList<>();
            }
            list.add(value);
            hashMap.put(key,list);
        }
        //构建多层 a节点到叶子节点的map
        for(int key:hashMap.keySet()){
            List<Integer> list=hashMap.get(key);
            int i=0;
            List<Integer> item=new ArrayList<>();
            item.add(key);
            item.add(list.get(i));
            result.add(item);
            while (hashMap.containsKey(list.get(i))){
                List<Integer> temp=hashMap.get(list.get(i));


                i++;
                result.add(item);
            }
            item.clear();
        }
        return ans;
    }
    public static int count(List<Integer> list){
        return 1;
    }
    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{2,4},{2,5}};
        int n=5;
        System.out.println(countPaths(n,edges));
    }
}