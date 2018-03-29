import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class MWListArtObj{

private static String dbURL = "jdbc:derby://red.eecs.yorku.ca:52041/EECS2041;user=student;password=secret"; //may have to change
private static Connection conn = null;
private static Statement stmt = null;
private static String par1=null;
private static String par2=null;
private static String aID=null;

public static void main(String[] args) {
readPar();
createConnection();
insertAssig(par1, par2);
printMsg();
}

private static void readPar(){
Hashtable form_data= cgi_lib.ReadParse(System.in);
par1= (String)form_data.get("a1");
par2= (String)form_data.get("a2");
aID = (String)form_data.get("aID");
}

private static void shutdown(){
try{
	if (stmt != null){
		stmt.close();
	}
	if (conn != null){
		DriverManager.getConnection(dbURL + ";shutdown=true");
		conn.close();
	}           
}
catch (SQLException sqlExcept){
// do nothing for now
}

}

private static ResultSet selectTable(){
ResultSet results = null;
try{
stmt = conn.createStatement();
stmt.executeUpdate("set schema course");
results = stmt.executeQuery("select ARTID, ARTYEAR, ARTTITLE, ARTEPOCH, ARTPRICE from ARTOBJ where ARTISTID='" + aID +"'");// HAve to change this
}

catch (SQLException sqlExcept){
sqlExcept.printStackTrace();
}
return results;
}

private static void createConnection(){
try{
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
//Get a connection
conn = DriverManager.getConnection(dbURL); 
}
catch (Exception except){
except.printStackTrace();
}

}

private static void insertAssig(String a1, String a2){
try{
stmt =conn.createStatement();
stmt.executeUpdate("set schema course");
stmt.execute("insert into assig6 values ( '" +a1 +"', '"+a2 + "')"); //may have to change
stmt.close();
}
catch (SQLException sqlExcept){
sqlExcept.printStackTrace();
}

}

public static void printMsg() {
// Output the header(s).
System.out.print("Content-type: text/html\r\n");
System.out.print("\r\n");
//--------------------------------------------------------------------

// Set up HTML template.
String htmlHead = "" + "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<title>\n" + "Assignment 6 from Java!\n" + "</title>\n" + "</head>\n" + "\n" + "<style type=\"text/css\">\n" + "table, td, th\n" + "{border:1px solid black; background-color:yellow;)\n" + "</style > \n"+ "<body>\n";
System.out.print(htmlHead);

/*createConnection(); */
ResultSet results = selectTable();
try {

// get column names from the database
ResultSetMetaData rsmd = results.getMetaData();
int numberCols = rsmd.getColumnCount();

// begin table
System.out.println("<table>");

// header row
System.out.println("<tr>");

for (int i=1; i<=numberCols; i++){
//print column names
System.out.println("<th>" + rsmd.getColumnLabel(i) + "</th>");  
}

System.out.println("</tr>");

// table data rows
while(results.next()){

// get the results from the db 
System.out.println("<tr>");

for (int i = 1; i <= numberCols; i++){
String col = results.getString(i);
System.out.println("<td>" + col + "</td>");
}

System.out.println("</tr>");
}

// end table
System.out.println("</table>");

// clean up the connection to database
results.close();
stmt.close();
} 

catch (Exception e) {
e.printStackTrace();
}
shutdown();
// finish up html
String htmlFoot = "\n" + "</body>\n" + "</html>\n";
System.out.print(htmlFoot);
}

}

