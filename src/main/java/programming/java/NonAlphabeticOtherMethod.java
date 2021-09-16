package programming.java;

import java.util.Arrays;
import java.util.Scanner;

public class NonAlphabeticOtherMethod {
	
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		String value=scan.nextLine();
		value = value.replaceAll("[^a-zA-Z]", "").toUpperCase();
		System.out.println(value);
		
		char arr[]=value.toCharArray();

	    
	    Arrays.sort(arr);
	    System.out.println(arr);
	    int count = 1;
	    for(int i=0;i<arr.length-1;i++){
	        if(arr[i]==arr[i+1]){
	            count++;
	        }else{
	            System.out.println(arr[i]+":"+count);
	            count =1;
	        }
	    }
	    System.out.println(arr[arr.length-1]+":"+count);
	    scan.close();
		
		
	}

}
