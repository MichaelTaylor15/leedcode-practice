package Y24;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Matrix {
    //73. 矩阵置零
    //预先标记0，再次遍历置0
    public static void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        boolean[] row=new boolean[m];
        boolean[] col=new boolean[n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (matrix[i][j]==0){
                    row[i]=true;
                    col[j]=true;
                }
            }
        }
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (row[i] || col[j]){
                    matrix[i][j]=0;
                }
            }
        }
    }
    //54. 螺旋矩阵
    //循环依次处理：上方、右方、下方、左方
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int firstRow=0,lastRow=m-1,firstColumn=0,lastColumn=n-1;
        List<Integer> list=new ArrayList<>();
        boolean[][] isSet=new boolean[m][n];
        while (list.size()<m*n){
            //deal with the first row.example:[0][0]->[0][1]->[0][2]
            for (int r=0;r<n;r++){
                if (!isSet[firstRow][r]){
                    isSet[firstRow][r]=true;
                    list.add(matrix[firstRow][r]);
                }
            }
            //deal with the last column.example:[0][3]->[1][3]->[2][3]
            for (int c=firstRow;c<m;c++){
                if (!isSet[c][lastColumn]){
                    isSet[c][lastColumn]=true;
                    list.add(matrix[c][lastColumn]);
                }
            }
            //deal with the last row.example:[3][3]->[3][2]->[3][1]->[3][0]
            for (int t=lastColumn;t>=0;t--){
                if (!isSet[lastRow][t]){
                    isSet[lastRow][t]=true;
                    list.add(matrix[lastRow][t]);
                }
            }

            //deal with the first column.example:[3][0]->[2][0]->[1][0]
            for (int f=lastRow;f>=0;f--){
                if (!isSet[f][firstColumn]){
                    isSet[f][firstColumn]=true;
                    list.add(matrix[f][firstColumn]);
                }
            }
            firstRow++;
            firstColumn++;
            lastColumn--;
            lastRow--;
        }
        return list;
    }
    //48. 旋转图像
    //第一行旋转到最后一列，第二行旋转到最后一列……以此类推
    public static void rotate(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        //row to column,first row to last column,second row to front of last column
        List<List<Integer>> list=new ArrayList<>();
        //逐行读取
        for (int i=0;i<m;i++){
            List<Integer> temp=new ArrayList<>();
            for (int j=0;j<n;j++){
                temp.add(matrix[i][j]);
            }
            list.add(temp);
        }
        int i=0;
        //逐列设置
        for (int col=n-1;col>=0;col--){
            List<Integer> item=list.get(i++);
            for (int row=0;row<m;row++){
                matrix[row][col]=item.get(row);
            }
        }
    }

    //240. 搜索二维矩阵 II
    //遍历每个一维数组，在一维数组里面进行二分查找，时间复杂度：O(m*Log2N)
    public static boolean searchMatrix(int[][] matrix, int target) {
        int n=matrix[0].length;
        for (int[] arr:matrix){
            int cur=arr[n-1];
            if (cur==target){
                return true;
            }else if (cur>target){
                int start=0;
                int end=n-1;
                while (start<=end){
                    int mid=(start+end+1)/2;
                    if (arr[mid]==target){
                        return true;
                    }else if (arr[mid]<target){
                        start=mid+1;
                    }else{
                        end=mid-1;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //int[][] matrix={{1,1,1},{1,0,1},{1,1,1}};
        //setZeroes(matrix);

        //int[][] matrix={{1, 2, 3, 4, 5},
        //                {6, 7, 8, 9, 10},
        //                {11,12,13,14,15},
        //                {16,17,18,19,20}};
        //System.out.println(spiralOrder(matrix));

        //int[][] matrix={                   //upper:[0][0]->[0][right] [0][1]->[1][right] [0][2]->[2][right]
        //                {5, 1, 9, 11},     //left:[0][0]->[0][right] [1][0]->[0][1] [2][0]->[0][2]
        //                {2, 4, 8, 10},
        //                {13,3, 6, 7 },
        //                {15,14,12,16}};
                      //{15,13, 2,  5}
                      //{14, 3, 4,  1},
                      //{12, 6, 8,  9},
                      //{16, 7, 10,11}}
        //rotate(matrix);
        //System.out.println(Arrays.deepToString(matrix));

        //increasing:upper to down,left to right
        int[][] matrix={{-1,3}};
        int target=-1;
        System.out.println(searchMatrix(matrix,target));
    }
}
