import java.util.Scanner;

public class compare{
    public static void main(String[] args){
        String firstStr,secondStr;
        Scanner sc=new Scanner(System.in);
        firstStr=sc.next();
        secondStr=sc.next();
        if (firstStr.compareToIgnoreCase(secondStr)>0)
            System.out.println("2");
        else if(firstStr.compareToIgnoreCase(secondStr)<0)
            System.out.println("1");
        else
            System.out.println("0");
        
        }
    
    
    
    
    
    }
