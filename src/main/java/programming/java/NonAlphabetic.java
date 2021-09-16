package programming.java;

import java.util.Arrays;
//import java.util.Scanner;

public class NonAlphabetic {

	public static void main(String[] args) {
		//Scanner scan = new Scanner(System.in);
		//String value=scan.nextLine();
		
		String value ="az23xAe12@xY)(b?*Zr";
		
		System.out.println(value.hashCode());
		//String newValue="";
		
//		Step 1 : Remove all non alphabetical characters from the String

		//INStead of using the below approach use String replace all method()
		for( int i=0;i<value.length();i++){
	        if( 
	            ((int)(value.charAt(i))>=65 && (int)(value.charAt(i))<=90)
	        || ((int)(value.charAt(i))>=97 && (int)(value.charAt(i))<=122)
	        
	        ){
	        	value+=value.charAt(i);
	        }
	        
	    }
	   // value=newValue;
	    System.out.println(value);
	    
//	    Step 3 : covert the string into upper case or lower case.
//	    value=value.toLowerCase();
	    value=value.toUpperCase();
	    
	    
	    char arr[]=value.toCharArray();

//		Step 4 : find the occurrence of each characters and its should be in sorted order{ A : 2, B : 1, ......Z : 2}
	   
	    
	    Arrays.sort(arr);
	    System.out.println(arr);
//	    AAbE
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
//	   scan.close();
		
	}

}
