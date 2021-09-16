package programming.java;

import java.util.*;
import java.util.Scanner;

public class NonAlphabeticUsingMap{

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String value=scan.nextLine();
		String newValue="";
		HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
 		for( int i=0;i<value.length();i++){
	        if( 
	            ((int)(value.charAt(i))>=65 && (int)(value.charAt(i))<=90)
	        || ((int)(value.charAt(i))>=97 && (int)(value.charAt(i))<=122)
	        
	        ){
	            newValue+=value.charAt(i);
	        }
	        
	    }
	    value=newValue;
	    value=value.toUpperCase();
	    
	    char arr[]=value.toCharArray();
	    Arrays.sort(arr);
	    System.out.println(arr);
	    for(int i=0;i<arr.length;i++){
	        if(hm.containsKey(arr[i])){
	            hm.put(arr[i],hm.get(arr[i])+1);
	        }
	        else{
	            hm.put(arr[i],1);
	            
	        }
	    }
	    for(Character keys : hm.keySet()){
	        System.out.println(keys+ ":"+hm.get(keys));
	    }
	    scan.close();
		
	}

}
