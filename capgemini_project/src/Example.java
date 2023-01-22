import java.sql.*;
public class Example {
	
	public static void main(String args[]) {
		
		String s1 = "sattu";
		String s2 = new String("sattu");
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
	}

}
