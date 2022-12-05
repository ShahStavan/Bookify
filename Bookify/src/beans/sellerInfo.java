package beans;
import java.util.*;
import java.io.Console;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sellerInfo {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Login(0) | Register(1)");
		int choice = sc.nextInt();
		String username,password,email;
		
		DatabaseConnection dbConnection = new DatabaseConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/","root","","bookify");
		try {
			Connection conn = dbConnection.initilizeConnection();
			switch(choice)
			{
			case 0:
				System.out.println("------------LOGIN------------");
				System.out.println("Enter Username:");
				username = sc.next();
				System.out.println("Enter Password:");
				password = sc.next();
				int id = sellerInfo.login(username, password, conn);
				if(id != 0)
				{
					System.out.println("LOGIN SUCCESSFUL");
				}
				else
					System.out.println("INVALID CREDENTIALS or NO SUCH USER FOUND!");
			break;
			case 1:
				System.out.println("------------RESGISTER------------");
				System.out.println("Enter Username:");
				username = sc.next();
				System.out.println("Enter Email:");
				email = sc.next();
				System.out.println("Enter Password:");
				password = sc.next();
				System.out.println("Password:" + password);
				int value = sellerInfo.register(username, password, conn, email);
				if(value == 0)
					System.out.println("Password doesn't match our Pattern!\n----------------------------------\n 1. Password Length: preferably over 8 characters\n 2. Complexity of Password: Must Contain LowerCase and UpperCase letters\n 3. Must Contain a numeric value\n 4. Must Contain a symbol");
				else
				{
					System.out.println("REGISTRATION SUCCESSFUL");
				}
				
			break;
			default:
				System.out.println("Invalid Choice!");
				
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	public static int login(String username, String password, Connection con) throws SQLException
	{
		Statement st = (Statement) con.createStatement();
		String query = "select uid from account where username = '"+username+"' and password = '"+password+"'";
		ResultSet rs = (ResultSet) st.executeQuery(query);
		int id=0;
		while(rs.next()) {
			id = rs.getInt(1);
		}
		if(id == 0){
			return 0;
		}
		return id;
	}
	public static int register(String username, String password, Connection con, String email) throws SQLException
	{
		
		//String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		//boolean isValidPassword = isValidPassword(password,regex);
		/*
		if(isValidPassword == false)
			return 0;
		else
		{
			Statement st = (Statement) con.createStatement();
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement("insert into seller values(?,?,?)");
			pstmt.setString(2, username);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			int i = pstmt.executeUpdate();
			return i;
		}
		*/
		Statement st = (Statement) con.createStatement();
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement("insert into account (username,email,password)values(?,?,?)");
		pstmt.setString(1, username);
		pstmt.setString(2, email);
		pstmt.setString(3, password);
		int i = pstmt.executeUpdate();
		return i;
		
	
	}
	 /**
     * Method to check if password is valid or not.
     * @param password
     * @return boolean
     */
	/*
    public static boolean isValidPassword(String password, String regex)
    {
            boolean isValid = true;
            if (password.length() > 15 || password.length() < 8)
            {
                    System.out.println("Password must be less than 20 and more than 8 characters in length.");
                    isValid = false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!password.matches(upperCaseChars ))
            {
                    System.out.println("Password must have atleast one uppercase character");
                    isValid = false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!password.matches(lowerCaseChars ))
            {
                    System.out.println("Password must have atleast one lowercase character");
                    isValid = false;
            }
            String numbers = "(.*[0-9].*)";
            if (!password.matches(numbers ))
            {
                    System.out.println("Password must have atleast one number");
                    isValid = false;
            }
            String specialChars = "(.*[@,#,$,%].*$)";
            if (!password.matches(specialChars ))
            {
                    System.out.println("Password must have atleast one special character among @#$%");
                    isValid = false;
            }
            return isValid;
    }
    */
}