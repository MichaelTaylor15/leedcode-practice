package Y24;

import java.util.*;

public class WeekendCompetition {
    //3014. 输入单词需要的最少按键次数I (不会输入重复字母)
    public static int minimumPushesI(String word) {
        int n=word.length();
        if (n<=8) {
            return n;
        } else if (n<=16) {
            return 8+(n-8)*2;
        } else if (n<=24) {
            return 8+16+(n-16)*3;
        }else{
            return 8+16+24+(n-24)*4;
        }
    }
    //3016. 输入单词需要的最少按键次数 II 可输入重复字母
    //最大化 3*6+2*4 6个3字母组合，2个4字母组合
    //根据字母出现次数，排序 依次计算 按一次、按两次……
    public static int minimumPushesII(String word) {
        int[] arr=new int[26];
        for (int i=0;i<word.length();i++){
            int key=word.charAt(i)-'a';
            arr[key]=arr[key]+1;
        }
        Arrays.sort(arr);
        int sum=0;
        int i=25;
        int basic=1;
        while (i>=0 && arr[i]!=0){
            sum+=arr[i]*basic;
            if (i==18||i==10||i==2){
                basic++;
            }
            i--;
        }
        return sum;
    }
    public static void main(String[] args) {
        String word="avghdcyinjmbopwtrfslzkuxeq";
        System.out.println(minimumPushesII(word));
    }
}
