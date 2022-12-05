package beans;

import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class main extends sellerInfo{
	public static void main(String args[])
	{
		books b;
		String name,date,author,imgurl,insights;
		int category;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Book Details");
		System.out.println("Enter Book Name:");
		name = sc.nextLine();
		System.out.println("Enter Author Name:");
		author = sc.nextLine();
		System.out.println("Enter Published Date:");
		date = sc.nextLine();
		System.out.println("Category List:");
		System.out.println("1. Self-Growth \n2. Happiness \n3. Productivity \n4. Business & Career \n5. Money & Investments \n6. Leadership \n7. Negotiation \n8. Love & Sex \n9. Health\n10. Sports & Fitness\n11. Society & Tech\n 12. Personalities\n13. Spirituality\n14. Family\n15. Home & Environment\n16. Fiction");
		try {
		    category = Integer.parseInt(sc.nextLine());
		    if(category > 16 && category < 1)
				System.out.println("Invalid Category");
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		System.out.println("Enter url of image:");
		imgurl = sc.nextLine();
		System.out.println("Enter Insights:");
		insights = sc.nextLine();
		b = new books(name,date,author,category,imgurl,insights);
		b.Display();
		b.insert();
		
		
	}
}