package Y24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Competition {
    //字符统计
    public static void strCount(){
        Scanner scanner=new Scanner(System.in);
        String str= scanner.nextLine();
        int n=str.length();
        int max=0;
        int[] arr=new int[n];
        for (int i=0;i<n;i++){
            int idx=str.charAt(i)-'A';
            arr[idx]=arr[idx]+1;
            max=Math.max(max,arr[idx]);
        }
        for (int i=0;i<n;i++){
            if (arr[i]==max){
                char a= (char) ('A'+i);
                System.out.print(a);
            }
        }
    }
    //最少刷题数
    public static void minNum(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] arr=new int[n];
        for (int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
        }
        //寻找中位数
        int[] sorted=Arrays.copyOf(arr,n);
        Arrays.sort(sorted);
        int c=0;
        int mid=n/2;
        if (n%2!=0){
            c++;
        }
        for (int i=0;i<n;i++){
            if (arr[i]<sorted[mid]){
                System.out.print(sorted[mid]-arr[i]+c);
            }else {
                System.out.print(0);
            }
            if (i!=n-1){
                System.out.print(" ");
            }
        }
    }
    public static void main(String[] args) {
        minNum();
    }

}
