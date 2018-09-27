/**
 *
 * author	: [Gong Changda]
 * matric no: [A0162477X]
 * 
 */

import java.util.*;

public class HelloWorld {
	
	public static void main(String[] args) {

		// declare the necessary variables
        int method,result;String string;
        String[] input=new String[3];
        ArrayList<String> inputs=new ArrayList<String>();


		// declare a Scanner object to read input
        Scanner sc=new Scanner(System.in);

		// read input and process them accordingly
        method=sc.nextInt();
        sc.nextLine();

        if(method == 1){
            int number=sc.nextInt();
            sc.nextLine();
            for(int i=0;i<number;i++){
                string=sc.nextLine();
                inputs.add(string);
                }
            for(int i=0;i<number;i++){
                input=inputs.get(i).split(" ");
                result=0;
                if(input[0].equals("AND")){
                    if(input[1].equals("1") &&input[2].equals("1")){
                        result=1;
                        }
                    }
                else{
                    if(input[1].equals("1") ||input[2].equals("1"))
                        result=1;
                    }
                System.out.println(result);    

                }    


            }
        else if(method==2){
            int counter=0;
            while(true){
                string=sc.nextLine();
                inputs.add(string);
                if(string.equals("0"))
                    break;
                counter+=1;
                }
            for(int i=0;i<counter;i++){
                input=inputs.get(i).split(" ");
                result=0;
                if(input[0].equals("AND")){
                    if(input[1].equals("1") && input[2].equals("1")){
                        result=1;
                        }

                    }
                else{
                    if(input[1].equals("1") || input[2].equals("1"))
                        result=1;
                    }
                
                System.out.println(result);
                
                
                }
            }  
            else{
                int counter=0;
                while(sc.hasNextLine()){
                    string=sc.nextLine();
                    inputs.add(string);
                    counter+=1;
                    
                    }
                for(int i=0;i<counter;i++){
                    input=inputs.get(i).split(" ");
                    result=0;
                    if(input[0].equals("AND")){
                        if(input[1].equals("1") && input[2].equals("1"))
                            result=1;
                        
                        }
                    else{
                        if(input[1].equals("1")||input[2].equals("1"))
                            result=1;
                        }
                    System.out.println(result);
                   }


                }
	}
}
