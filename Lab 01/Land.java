/**
 * name	     :Gong Changda
 * matric no.:A0162477X
 */

import java.util.*;


class Land{
    
    
    

	/**
	 *	   checkNoTree   : to check whether the (size x size) square with upper-left coordinate 
	 *                     (x, y) contains a tree
	 *	   Pre-condition :
	 *	   Post-condition:
	 */
	public static boolean checkNoTree(int x, int y, int size,int[][]
    arr) {
		// implementation
        for(int i=x;i<=x+size-1;i++){
            for(int j=y;j<=y+size-1;j++){
                if(arr[i][j]==1)
                    return false;
                }
            }

        return true;
	}



	/**
	 *	   checkValidSize: to check whether it is possible to find a (size x size) square that contains 
	 *                     no tree
	 *	   Pre-condition :
	 *	   Post-condition:
	 */
	public static boolean checkValidSize(int size,int[][] arr,int length) 
    {
        for(int i=0;i<=length-size;i++){
            for(int j=0; j<=length-size;j++){
                if(checkNoTree(i,j,size,arr))
                    return true;
                }

            }
        return false;
	}



	/** 
	 *	   solve         : use this method to find the largest size of a square with no trees
	 *	   Pre-condition :
	 *	   Post-condition:
	 */
	public static int solve(int[][] arr) {
        int largestSize=0;
        for(int i=1;i<=arr.length;i++){
            if(checkValidSize(i,arr,arr.length)==false){
                largestSize=i-1;
                break;
                }
            }
        return largestSize;    
	}




	public static void main(String[] args) {

		// declare the necessary variables
        int length,numOfTree,x,y,result;  
		

		// declare a Scanner object to read input
        Scanner sc=new Scanner(System.in); 
		// read input and process them accordingly
        length=sc.nextInt();
        numOfTree=sc.nextInt();
        int[][] arr=new int[length][length];
        while(sc.hasNextInt()){
            x=sc.nextInt();
            y=sc.nextInt();
            arr[x-1][y-1]=1;
            }
        result=solve(arr);
        System.out.println(result);
        
	}
}
