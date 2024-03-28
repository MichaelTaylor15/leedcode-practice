package Y24;

public class Greedy {
    //121. 买卖股票的最佳时机
    //dp
    public static int maxProfit(int[] prices) {
        int n=prices.length;
        int max=0;
        for (int i=1;i<n;i++){
            if (prices[i]-prices[i-1]<0){
                prices[i-1]=prices[i];
            }else{
                max=Math.max(max,prices[i]-prices[i-1]);
                prices[i]=prices[i-1];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices={7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
