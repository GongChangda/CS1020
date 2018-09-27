import java.util.*;
public class palindrome{
    public static void main(String[] args){
        String first,second;int flag=1;int i;
        Scanner sc=new Scanner(System.in);
        first=sc.next();
        second=sc.next();
        for(i=0;i<first.length();i++){
            if(first.charAt(i)!=second.charAt(first.length()-i-1))
                flag=0;
                
            }
        if(flag==0)
            System.out.println("NO");
        else
            System.out.println("YES");
        }
    
    }
