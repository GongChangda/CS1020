/*
 * Name		:Gong Changda
 * Matric No.		:A0162477X
 * Plab Account	:
 */

import java.util.*;

public class Sudoku {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean row,column,submatrix,isValid;
        String result;
        int n;
        n=sc.nextInt();
        int[][] arr=new int[n*n][n*n];
        for(int i=0;i<n*n;i++){
            for(int j=0;j<n*n;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        row=rowChecker(n,arr);
        column=columnChecker(n,arr);
        submatrix=submatrixChecker(n,arr);
        isValid=row && column && submatrix;
        if(isValid){
            result="VALID";
        }
        else{
            result="INVALID";
        }
        System.out.println(result);
    }
    public static boolean rowChecker(int n,int[][] arr){
        int numOfRows=n*n;
        for(int i=0;i<numOfRows;i++){
            int[] array=new int[numOfRows];
            for(int j=0;j<numOfRows;j++){
                int num=arr[i][j];
                array[num-1]=1;
            }
            for(int k=0;k<numOfRows;k++){
                if(array[k]!=1){
                    return false;
                }

            }


        }
        return true;
    }

    public static boolean columnChecker(int n,int[][] arr){
        int numOfRows=n*n;
        for(int i=0;i<numOfRows;i++){
            int[] array=new int[numOfRows];
            for(int j=0;j<numOfRows;j++){
                int num=arr[j][i];
                array[num-1]=1;

            }
            for(int k=0;k<numOfRows;k++){
                if(array[k]!=1){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean submatrixChecker(int n,int[][] arr){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int[] array=new int[n*n];
                for(int k=0;k<n;k++){
                    for(int m=0;m<n;m++){
                        int num=arr[n*i+k][n*j+m];
                        array[num-1]=1;
                    }
                }
                for(int r=0;r<n*n;r++){
                    if(array[r]!=1){
                        return false;
                    }

                }
            }

        }
        return true;
    }
}    

