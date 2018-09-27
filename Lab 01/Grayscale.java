/*
 * Name       :GONG CHANGDA 
 * Matric No. :A0162477X	
 * Plab Acct. :pe12
 */

import java.util.*;

public class Grayscale {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int H,W;
		H=sc.nextInt();
		W=sc.nextInt();
		int[][][] image1=new int[3][H][W];
		int[][][] image2=new int[3][H][W];
		// below is to scan the value into 3-dimensional matrix image1 and image2;
		// first entry of the matrix store red,second for green,third for blue;
		for(int i=0;i<3;i++){
			for(int row=0;row<H;row++){
				for(int col=0;col<W;col++){
					image1[i][row][col]=sc.nextInt();
					}
				}
			}
		for(int j=0;j<3;j++){
			for(int row=0;row<H;row++){
				for(int col=0;col<W;col++){
					image2[j][row][col]=sc.nextInt();
					}
				}
			}
		int[][] grayScale1,grayScale2;
		grayScale1=computeGrayScale(image1,H,W);//compute the gray scale of each image;
		grayScale2=computeGrayScale(image2,H,W);
		int miniSum=computeMiniSum(grayScale1,grayScale2,H,W);// compute the minimal sum 
		System.out.println(miniSum);//print out the result;

	}
	/* this method is used to compute the grayscale given the RGB matrix and the dimension
	   precondition: the 3-dimensional array representation of the image; */
	public static int[][] computeGrayScale(int[][][] image,int H,int W){
		int[][] result=new int[H][W];
		for(int row=0;row<H;row++){
			for(int col=0;col<W;col++){
				result[row][col]=computeValue(image,H,W,row,col);
				}
			}
		return result;	
		}
   // this method compute the grayscale value of the specified row and col given the 3 dimensional array
   // representation of the image and the dimensions.
	public static int computeValue(int[][][] image,int H, int W, int row, int col){
		int numOfValues;
		int counter=3;
		int sum=image[0][row][col]+image[1][row][col]+image[2][row][col];
		int[] direction1={1,1,1,0,0,-1,-1,-1};
		int[] direction2={0,1,-1,1,-1,0,-1,1};
		for(int i=0;i<3;i++){
			for(int j=0;j<8;j++){
				if(row+direction1[j]>=0 && row+direction1[j]<H && col+direction2[j]>=0 && col+direction2[j]<W){
					sum=sum+image[i][row+direction1[j]][col+direction2[j]];
					counter++;
					}
				}
			}
		numOfValues=counter/3;
		return sum/numOfValues;
		}
    // this method compute the minimum sum of absolute difference given two grayscale matrix(2-d array) and the dimension of it.
	public static int computeMiniSum(int[][] grayScale1,int[][] grayScale2,int H, int W){
		int result=0;
		int sum1=0,sum2=0;
		for(int row=0;row<H;row++){
			for(int col=0;col<W;col++){
				sum1=sum1+grayScale1[row][col];
				}
			}
		for(int i=0;i<H;i++){
			for(int j=0;j<W;j++){
				sum2=sum2+grayScale2[i][j];
				}
			}
		// if sum1<sum2, then we consider to increase the value of the first image's grayscale;
	    if(sum1<sum2){
			int[] arr=new int[256];
			for(int inc=0;inc<256;inc++){
				int diff=calculateDiff(grayScale1,grayScale2,inc,H,W);
				arr[inc]=diff;
				if(inc==0){
					continue;
					}
				if(diff>=arr[inc-1] && inc>1){
					result=inc-1;
					break;
					}
			}	
		}		
			
		// otherwise we consider to increase the value of the second image's grayscale;	
		else{
			int[] arr=new int[256];
			for(int inc=0;inc<256;inc++){
				int diff=calculateDiff(grayScale2,grayScale1,inc,H,W);
				arr[inc]=diff;
				if(inc==0){
					continue;
					}
				if(diff>=arr[inc-1] && inc>1){
					result=inc-1;
					break;
					}	
				}
			}
		return result;	
		}
    // compute the sum of absolute difference given two images' grayscale and dimension and the increment;
	public static int calculateDiff(int[][] arr1, int[][] arr2, int inc, int H, int W){
	    int sum=0;
		for(int i=0;i<H;i++){
			for(int j=0;j<W;j++){
				sum=sum+Math.abs(arr1[i][j]+1-arr2[i][j]);
				}
			}
		return sum;	
   
		}	
}
