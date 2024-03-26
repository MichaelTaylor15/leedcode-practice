package Y24;

public class Greedy {
    //121. 买卖股票的最佳时机
    //min存储前缀(0-i)最小值，使用当前的num减去最小值
    public static int maxProfit(int[] prices) {
        int max=0;
        int min=prices[0];
        int n=prices.length;
        if (prices.length<2){
            return 0;
        }
        for (int i=0;i<n;i++){
            min=Math.min(min,prices[i]);
            max=Math.max(max,max-min);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices={2,4,1};
        System.out.println(maxProfit(prices));
    }
}
