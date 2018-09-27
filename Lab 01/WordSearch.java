/*
 * Name       : Gong Changda
 * Matric No. : A0162477X
 * Plab Acct. :
 */

import java.util.*;

public class WordSearch {

    public static void main(String[] args) {
        int size,horizontal,vertical,diagonalForward,diagonalBackward;
        String word,input;
        Scanner sc =new Scanner(System.in);
        word=sc.next();
        sc.nextLine();
        size=sc.nextInt();
        char[][] arr=new char[size][size];
        for(int i=0;i<size;i++){
            input=sc.next();
            for(int j=0;j<size;j++){
                arr[i][j]=input.charAt(j);
            }


        }
        horizontal=horiSearch(arr,word);
        vertical=vertSearch(arr,word);
        diagonalForward=diagSearchForward(arr,word);
        diagonalBackward=diagSearchBackward(arr, word);
        System.out.println(horizontal+vertical+diagonalForward+diagonalBackward);
    }
    public static int horiSearch(char[][] arr,String word){
        int length,size,counter=0;
        length=word.length();
        size=arr.length;
        if (length>size)
            return 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<=size-length;j++){
                char[] str=new char[length];
                for(int k=j;k<j+length;k++){
                    str[k-j]=arr[i][k];
                if(isEqual(str,word))
                    counter++;
                }
            }
        }
        return counter;
    }
    public static int vertSearch(char[][] arr,String word){
        int length,size,counter=0;
        length=word.length();
        size=arr.length;
        if(length>size)
           return 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<=size-length;j++){
                char[] str=new char[length];
                for(int k=j;k<j+length;k++){
                    str[k-j]=arr[k][i];
                if(isEqual(str,word))
                    counter++;
                }
            }
        }
        return counter;
    }
    public static int diagSearchForward(char[][] arr,String word){
        int length,size,counter=0;
        length=word.length();
        size=arr.length;
        if(length>size)
            return 0;
        for(int i=0;i<=size-length;i++){
            for(int j=0;j<=size-length;j++){
                char[] str=new char[length];
                for(int k=j;k<j+length;k++){
                    str[k-j]=arr[i+k-j][k];
                if(isEqual(str,word))
                    counter++;
                }
            }
        }
        return counter;
    }
    public static int diagSearchBackward(char[][] arr,String word){
        int length,size,counter=0;
        length=word.length();
        size=arr.length;
        if(length>size)
            return 0;
        for(int i=0;i<=size-length;i++){
            for(int j=size-1;j>=length-1;j--){
                char[] str=new char[length];
                for(int k=j;k>j-length;k--){
                    str[j-k]=arr[i+j-k][k];
                if(isEqual(str,word))
                    counter++;
                }
            }
        }
        return counter;
    }    
    public static boolean isEqual(char[] str,String word){
        int counter,length;
        counter=0;
        length=word.length();
        for(int i=0;i<length;i++){
            if(str[i]==(word.charAt(i))){
                counter++;
            }
        }
        if(counter==length){
            return true;
        }
        counter=0;
        for(int i=0;i<length;i++){
            if(str[i]==(word.charAt(length-1-i))){
                counter++;
            }
        }
        if(counter==length){
            return true;
        }
        return false;
    }    
}
