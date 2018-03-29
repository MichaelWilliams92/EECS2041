import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*; 
import java.io.*;

public class MW_capital {
	

	  public static void main(String[] args)
	  {

	      //
	      //  Print the required CGI header.
	      //
	      System.out.println(cgi_lib.Header());

	      //
	      //  Parse the form data into a Hashtable.
	      //
	      Hashtable form_data = cgi_lib.ReadParse(System.in);

	      // store in java variables the data sent by the browser
	     String firstName = (String)form_data.get("MW_first_name");
	     String lastName = (String)form_data.get("MW_last_name");
	     String capital = (String)form_data.get("MW_capital");
	     String rate = (String)form_data.get("MW_rate");
	     String years = (String)form_data.get("MW_years");

		double MW_capital = Double.parseDouble(capital);
		double MW_rate = Double.parseDouble(rate);
		int MW_years = Integer.parseInt(years); 
		int i;
		double MW_amount;
		double MW_total = MW_capital;
	      
	      //print out html top
	      System.out.println(cgi_lib.HtmlTop("Client Message" ));
	      
	      //print out body portion (yellow section)
	      
	      System.out.println("<div style=\"background-color:yellow; text-align:center; font-weight:bold; margin-left:40px; margin-right:40px\">");

		System.out.println("<h3>First name: " + firstName + "</h3>");
		System.out.println("<h3>Last name: " + lastName + "</h3>");
		System.out.println("<h3>Your initial capital: " + MW_capital + "</h3>");
		System.out.println("<h3>Your interest rate: " + MW_rate + "</h3>");

	      System.out.println("</div>");

		System.out.println("<div style=\"background-color:lightgrey; text-align:center; font-weight:bold; margin-left:40px; margin-right:40px\">");

		for(i = 1; i <= MW_years; i++){

			MW_amount = MW_capital * MW_rate; 
			MW_capital = MW_capital + MW_amount;

			if(i == 1){
			System.out.println("<h4>Interest amount after " + i + " year is " + MW_amount + "</h4>");
			}//if statement

			else{
			System.out.println("<h4>Interest amount after " + i + " years is " + MW_amount + "</h4>");
			}//else statement


		}//for loop to keep printing interest amount per year

		System.out.println("</div>");

		if(MW_years == 1)
		System.out.println("<h2 style=\"text-align:center;\">Your capital after 1 year is: " + MW_capital + "</h2>");
		else
		System.out.println("<h2 style=\"text-align:center;\">Your capital after " + MW_years + " years is: " + MW_capital + "</h2>");

	      
	      // Create the Bottom of the returned HTML page.
	      System.out.println(cgi_lib.HtmlBot());
	  }
	}

