package Y24;

import cn.hutool.aop.interceptor.CglibInterceptor;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.bcel.internal.generic.INEG;

import java.math.BigInteger;
import java.util.*;

class MyQueue {
    //栈实现队列
    List<Integer> in;
    List<Integer> out;
    public MyQueue() {
        in=new ArrayList<>();
        out=new ArrayList<>();
    }

    public void push(int x) {
        in.add(x);
    }

    public int pop() {
        if (out.isEmpty()){
            for (int i=in.size()-1;i>=0;i--){
                out.add(in.get(i));
                in.remove(i);
            }
        }
        int val=out.get(out.size()-1);
        out.remove(out.size()-1);
        return val;
    }

    public int peek() {
        if (out.isEmpty()){
            for (int i=in.size()-1;i>=0;i--){
                out.add(in.get(i));
                in.remove(i);
            }
        }
        return out.get(out.size()-1);
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
//队列实现栈
class MyStack {
    List<Integer> list;
    public MyStack() {
        list=new ArrayList<>();
    }

    public void push(int x) {
        list.add(x);
    }

    public int pop() {
        int val=list.get(list.size()-1);
        list.remove(list.size()-1);
        return val;
    }

    public int top() {
        return list.get(list.size()-1);
    }

    public boolean empty() {
        return list.isEmpty();
    }
}

class FrequencyTracker {
    //<频率次数,该频率的number个数>
    HashMap<Integer, Integer> frequency;
    //<number,频率次数>
    HashMap<Integer,Integer> hashMap;
    public FrequencyTracker() {
        frequency=new HashMap<>();
        hashMap=new HashMap<>();
    }
    //添加频率，之前的频率减1，之后的频率加1
    public void add(int number) {
        int pre=-1;
        if (hashMap.containsKey(number)){
            pre=hashMap.get(number);
        }
        hashMap.put(number,hashMap.getOrDefault(number,0)+1);
        int after=hashMap.get(number);
        if (frequency.containsKey(pre)){
            frequency.put(pre,Math.max(0,frequency.get(pre)-1));
        }
        frequency.put(after,frequency.getOrDefault(after,0)+1);
    }
    //number的频率减去1,(number-1)的频率+1；
    public void deleteOne(int number) {
        if (hashMap.containsKey(number)){
            int f=hashMap.get(number);
            if (f>1){
                hashMap.put(number,f-1);
            }else{
                hashMap.remove(number);
            }
            if (frequency.containsKey(f)){
                frequency.put(f,Math.max(frequency.get(f)-1,0));
            }
            if (frequency.containsKey(f-1)){
                frequency.put(f-1,frequency.get(f-1)+1);
            }
        }
    }

    public boolean hasFrequency(int frequency) {
        return this.frequency.containsKey(frequency)&&this.frequency.get(frequency)>0;
    }
}

public class Daily {
    //2369. 检查数组是否存在有效划分
    public static boolean validPartition(int[] nums) {
        int n=nums.length;
        if (n<2){
            return false;
        }
        // %2==0 全2分组,%2==1 2 3分组或者3 2分组
        //[4,4,4,5,6,7,8,8,9,9]
        //[t,t]
        boolean[] dp=new boolean[nums.length];
        dp[0]=true;
        for (int i=1;i<n-1;i++){
            if (i<2){
                dp[i]=nums[i]==nums[i-1] || (nums[i]==nums[i-1]+1);
            }else {

            }

        }
        return dp[nums.length-1];
    }
    //2917. 找出数组中的 K-or 值
    //统计nums中每个数的0-31位是否为1，count>=k 满足则+pow(2,j)
    public static int findKOr(int[] nums, int k) {
        int n=nums.length;
        int rs = 0;
        for (int j=0;j<31;j++){
            int cnt=0;
            for (int i=0;i<n;i++){
                int res=nums[i]>>j&1;
                if (res==1){
                    cnt++;
                }
            }
            if (cnt>=k){
                rs+= (int) Math.pow(2,j);
            }
        }
        return rs;
    }
    //2575. 找出字符串的可整除数组
    // (a*10+b)%m=(a * 10 % m + b) % m
    public static int[] divisibilityArray(String word, int m) {
        int n=word.length();
        int[] res=new int[n];
        long pre=0;
        for (int len=0;len<n;len++){
            pre=(pre*10+ word.charAt(len)-'0')%m;
            res[len]=pre==0?1:0;
        }
        return res;
    }
    //1976. 到达目的地的方案数
    //0-(n-1)的最短路径方案数
    public int countPaths(int n, int[][] roads) {
        int[] dp=new int[n-1];
        //dp,0-i min weight
        for (int i=0;i<n;i++){
            int[] arr=roads[i];
            if (dp[arr[1]]==0){
                dp[arr[1]]=arr[3];
            }else if (dp[arr[1]]>0){

            }
        }
        return 0;
    }
    //1488. 避免洪水泛滥
    //一边记录湖水，遇到晴天时可以先不抽，遭遇到要发洪水了，选择抽
    public int[] avoidFlood(int[] rains) {
        int n=rains.length;
        int[] res=new int[n];
        //存储 湖水[i]，下雨时间i
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        //记录晴天
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<n;i++){
            //遭遇洪水
            if (rains[i]>0 && !hashMap.containsKey(rains[i])){
                //抽水

            }
            if (rains[i]==0){
                list.add(i);
            } else if (rains[i]>0){
                hashMap.put(rains[i],i);
            }
        }
        return res;
    }
    //2834. 找出美丽数组的最小和
    // 等差数列 累加target的一半(注意奇数、偶数)，填补个数 从target开始 满足n退出
    public static int minimumPossibleSum(int n, int target) {
        long sum=0;
        if (n<=target/2){
            return n*(1+n)/2;
        }
        if (target%2==0){
            sum= (long) target / 2 *(1+target/2)/2;
            n-=target/2;
        }else{
            sum=(long) (target-1) / 2 *(1+(target-1)/2)/2;
            n-=(target-1)/2;
        }
        //累加 target+n
        if (n>0){
           long temp=n*((long) target+(long) target+n-1)/2;
           sum+= temp ;
        }
        return (int) (sum%(Math.pow(10,9)+7));
    }
    //capiTalIze tHe titLe
    //Capitalize The Title
    //2129. 将标题首字母大写
    public static String capitalizeTitle(String title) {
        int n=title.length();
        if (n<1){
            return null;
        }
        String[] words=title.split(" ");
        StringBuilder rs=new StringBuilder();
        for (int i=0;i<words.length;i++){
            if (words[i].length()<=2){
                for (int j=0;j<words[i].length();j++){
                    char c=words[i].charAt(j);
                    if (c<='Z' && c >='A'){
                        c+=32;
                    }
                    rs.append(c);
                }
                rs.append(" ");
            }else{
                char head=words[i].charAt(0);
                if (head<='z' && head>='a'){
                    head-=32;
                }
                rs.append(head);
                for (int j=1;j<words[i].length();j++){
                    char c=words[i].charAt(j);
                    if (c>='A' && c<='Z'){
                        c+=32;
                    }
                    rs.append(c);
                }
                rs.append(" ");
            }
        }
        rs.replace(rs.length()-1,rs.length(),"");
        return rs.toString();
    }

    //2864. 最大二进制奇数
    public static String maximumOddBinaryNumber(String s) {
        int n=s.length();
        StringBuilder rs=new StringBuilder();
        StringBuilder zeros=new StringBuilder();
        for (int i=0;i<n;i++){
            if (s.charAt(i)=='1'){
                rs.append(s.charAt(i));
            }else{
                zeros.append(s.charAt(i));
            }
        }
        int zeroIdx=rs.length();
        rs.append(zeros);
        if (zeroIdx<1){
            return s;
        }
        rs.replace(zeroIdx-1,zeroIdx,"0");
        rs.replace(rs.length()-1,rs.length(),"1");
        return rs.toString();
    }
    //2386. 找出数组的第 K 大和
    // -2,-1,0,1,2,3,4,5
    //累加正数求出最大和，从最小正数使用sum依次递减nums[i]为第k大(负数需要相加)
    public static long kSum(int[] nums, int k) {
        return 0;
    }
    //2789. 合并后数组中的最大元素
    //从后往前遍历，只要存在left<right就相加
    public static long maxArrayValue(int[] nums) {
        int n=nums.length;
        if (n<1){
            return 0;
        }
        long max=nums[n-1];
        for (int i=n-1;i>=1;i--){
            if (nums[i-1]<=max){
                max+=nums[i-1];
                nums[i]=0;
            }else {
                max=nums[i-1];
            }
        }
        return max;
    }
    //303. 区域和检索 - 数组不可变
    class NumArray {
        int[] pre;
        public NumArray(int[] nums) {
            pre=new int[nums.length];
            pre[0]=nums[0];
            for (int i=1;i<nums.length;i++){
                pre[i]=pre[i-1]+nums[i];
            }
        }
        public int sumRange(int left, int right) {
            if (left==0) return pre[right];
            return pre[right]-pre[left-1];
        }
    }
    //310. 最小高度树
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans=new ArrayList<>();

        return ans;
    }

    //todo 1793. 好子数组的最大分数
    public static int maximumScore(int[] nums, int k) {
        int n=nums.length;
        if (n<1) return 0;
        int left=n/2,right=left;
        int min=nums[left];
        int max=0;
        int cur=left;
        while (left>=0 && right<n){
            min=Math.min(min,nums[cur]);
            max=Math.max(max,(left-right+1)*min);
            if (left==right){

            } else if (nums[left]>nums[right]){
                left--;
                cur=left;
            }else {
                right++;
                cur=right;
            }
        }
        while (left>=0){
            min=Math.min(min,nums[left]);
            max=Math.max(max,(right-left)*min);
            left--;
        }
        while (right<n){
            min=Math.min(min,nums[right]);
            max=Math.max(max,(right-left)*min);
            right++;
        }
        return max;
    }

    //todo 322. 零钱兑换
    public static  int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int count=0;
        Arrays.sort(coins);
        for (int i=n-1;i>=0;i--){
            if (amount % coins[i]==0){
                return count+amount/coins[i];
            }else {
                count+=amount / coins[i];
                amount%=coins[i];
                if (i==0){
                    return -1;
                }
            }
        }
        return count;
    }
    public static int countWays(int[][] ranges) {
        long rs=0;
        Arrays.sort(ranges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int n=ranges.length;
        int cnt=1;
        for (int i=1;i<n;i++){
            if (ranges[i][0]<=ranges[i-1][1]){
                cnt++;
            }
        }
        if (cnt==n) return 2;
        if (n>2){
            int t=1;
            for (int i=cnt;i>=1;i--){
                t*=i;
            }
            rs+=t;
        }
        rs+=2;
        return (int) (rs%(Math.pow(10,7)+7));
    }
    public static void main(String[] args) {
        //int[] nums={4,4,4,5,6,7,8,8,9,9};
        //System.out.println(validPartition(nums));
//        MyQueue myQueue = new MyQueue();
//        myQueue.push(1); // queue is: [1]
//        myQueue.pop(); // return 1, queue is [2]
//        myQueue.empty(); // return false
        //int[] nums= {7,12,9,8,9,15};
        //int k = 4;
        //System.out.println(findKOr(nums,k));

//        String word="86217457695827338571";
//        int m=3;
//        System.out.println(Arrays.toString(divisibilityArray(word,m)));
        //int[][] roads={
        //        {0,6,7},
        //        {0,1,2},
        //        {1,2,3},
        //        {1,3,3},
        //        {6,3,3},
        //        {3,5,1},
        //       {6,5,1},
        //        {2,5,1},
        //        {0,4,5},
        //        {4,6,2}};
        //int n=7;

//        int m=1000000000,target=1000000000;
//        System.out.println(minimumPossibleSum(m,target));

        //String title="First leTTeR of EACH Word";
        //System.out.println(capitalizeTitle(title));

//        String s="010";
//        System.out.println(maximumOddBinaryNumber(s));

        //int k=5;
        //int[] nums={2,4,-2};
        //System.out.println(kSum(nums,k));

//        int[] nums={59,17,33};
//        System.out.println(maxArrayValue(nums));

        //int[] nums={5,5,4,5,4,1,1,1};
        //int k=0;
        //System.out.println(maximumScore(nums,k));
//        FrequencyTracker f = new FrequencyTracker();
//        f.hasFrequency(1); // false
//        f.add(3);f.add(1); // f:[1]=2
//        f.hasFrequency(1); // true
//        f.add(3);          // f:[1]=1,[2]=1
//        f.hasFrequency(2); // true
//        f.add(1);          // f:[1]=0,[2]=2
//        f.hasFrequency(2); // true
//        f.deleteOne(10);
//        f.hasFrequency(2); // true
//        f.deleteOne(6);
//        f.add(7);          // f:[1]=1,[2]=2
//        f.deleteOne(10);
//        f.hasFrequency(2);f.hasFrequency(1);//true,true
//        f.add(1);f.add(2);f.add(3); //
//        f.hasFrequency(1);
//        f.deleteOne(3);f.hasFrequency(2);f.hasFrequency(3);
        //int[] coins={186,419,83,408};
        //int amount=6249;
        //System.out.println(coinChange(coins,amount));
        int[][] ranges={{1,3},{10,20},{2,5},{4,8}};
        System.out.println(countWays(ranges));
    }
}
