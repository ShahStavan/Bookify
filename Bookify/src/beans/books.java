package beans;
import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class books{
	String name,date,author,category,img_url,insights;
	String category_list[] = {"Self-Growth", "Happiness", "Productivity", "Business & Career", "Money & Investments", "Leadership", "Negotiation" , "Love & Sex", "Health", "Sports & Fitness", "Society & Tech", "Personalities", "Spirituality", "Family", "Home & Environment", "Fiction" };
	books(String name, String date, String author, int choice, String imgurl, String insights)
	{
		this.name = name;
		this.date = date;
		this.author = author;
		this.category = category_list[choice - 1];
		this.img_url = imgurl;
		this.insights = insights;
	}	
	// To display the books which is inserted
	public void Display()
	{
		System.out.println("Book Data");
		System.out.println("Name:" + name);
		System.out.println("Author:" + author);
		System.out.println("Published Date:" + date);
		System.out.println("Category:" + category);
	}
	// To display all the books
	public void display_all_books()
	{
		DatabaseConnection dbConnection = new DatabaseConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/","root","","bookify");
		// TODO Auto-generated method stub
		try {
			Connection conn = dbConnection.initilizeConnection();
			Statement st = (Statement) conn.createStatement();
			String sql = "select * from `books` ";
			ResultSet rs = (ResultSet) st.executeQuery(sql);
			while(rs.next())
			{	
				name = rs.getString("book_name");
				category = rs.getString("category");
				author = rs.getString("author");
				date = rs.getString("published_date");
				img_url = rs.getString("img_url");
				insights = rs.getString("insights");
			}
			conn.close();
		} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
		System.out.println("Class Not Found Exception!");
		e.printStackTrace();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("SQL Exception!");
		e.printStackTrace();
		}	
	}
	// Display books based on Category
	public void categorical_books(String category)
	{
		DatabaseConnection dbConnection = new DatabaseConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/","root","","bookify");
		// TODO Auto-generated method stub
		try {
			Connection conn = dbConnection.initilizeConnection();
			Statement st = (Statement) conn.createStatement();
			String sql = "select * from `books` where category = " + category;
			ResultSet rs = (ResultSet) st.executeQuery(sql);
			while(rs.next())
			{	
				name = rs.getString("book_name");
				category = rs.getString("category");
				author = rs.getString("author");
				date = rs.getString("published_date");
				img_url = rs.getString("img_url");
				insights = rs.getString("insights");
			}
			conn.close();
		} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
		System.out.println("Class Not Found Exception!");
		e.printStackTrace();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("SQL Exception!");
		e.printStackTrace();
		}	
	}
	public void insert()
	{
		DatabaseConnection dbConnection = new DatabaseConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/","root","","bookify");
		// TODO Auto-generated method stub
		try {
			Connection conn = dbConnection.initilizeConnection();
			Statement st = (Statement) conn.createStatement();
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("insert into books (book_name,category,author,published_date,img_url,insights)values(?,?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, category);
			pstmt.setString(3, author);
			pstmt.setString(4, date);
			pstmt.setString(5, img_url);
			pstmt.setString(6, insights);
			int i = pstmt.executeUpdate();
			System.out.println("Data Inserted!");
			conn.close();
		} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
		System.out.println("Class Not Found Exception!");
		e.printStackTrace();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("SQL Exception!");
		e.printStackTrace();
		}	
	}
}

	