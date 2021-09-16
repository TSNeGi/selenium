package programming.java;

public class StringImmutable {
	
	public static void main(String[] args) {
		
		
		String value1 = "ABC";
		System.out.println(value1);
		System.out.println(value1.hashCode());
		value1=value1+"tarun";
		System.out.println(value1);
		System.out.println(value1.hashCode());
		value1 = "BCD";
		System.out.println(value1);
		System.out.println(value1.hashCode());
		value1 = "ABC";
		System.out.println(value1);
		System.out.println(value1.hashCode());
		
		StringBuilder obj = new StringBuilder("ABC");
		System.out.println(obj.toString());
		System.out.println(obj.hashCode());
		obj.append("BVC");
		System.out.println(obj.toString());
		System.out.println(obj.hashCode());	
	

}
}
