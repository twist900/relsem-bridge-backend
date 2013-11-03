/**
 * SampleApplet - This is a test applet that demonstrates the use of the Type-III JWDriver
 */

import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.jw.client.JWDriver;
public class SampleApplet extends Applet implements ActionListener
{
	Button btnGetData_;	
	public void init()
		try
		{	
			//Load the com.jw.client.JWDriver class in Client Browser JVM
			Class.forName("com.jw.client.JWDriver");
		}
		catch(Exception ex)
		{
			msg_ = ex.getMessage();
		}

		if(str.equals("Get Data"))
		{
			repaint();
		}
	}
	
	/**
	 * This method gets the connection from the JWDriver and then gets a ResultSet
	 * from the authors table in pubs database.
	 */
    public void getData()
		{
			Properties props = new Properties();
			String server = getCodeBase().getHost();			
			//prepare the JWDriver URL		
			String driverProtocol = JWDriver.getURLPrefix() + server;
			
			//get the connection from driver (we are getting a JWConnection from JWDriver)
			Connection conn = DriverManager.getConnection(driverProtocol,props);
			
			//create the statement (we are getting a JWStatement)
			Statement stmt = conn.createStatement();			
			
			//execute the Select query (we are getting a JWResultSet)
			ResultSet rs = stmt.executeQuery("Select au_lname from authors Where au_id = '409-56-7008'");
			//Iterate over the resultset
			if(rs.next())
            {
				msg_ = "Author Last Name: " + rs.getString(1);
				repaint();				
			}

			//Close the ResultSet, Statement, Connection
			rs.close();
			stmt.close();
			conn.close();			
		}
		catch(Exception ex)
		{	
			msg_ += ex.getMessage();
		}
	
	//Show the first row results obtained from database
    {
			g.drawString("Author ID: 409-56-7008", 150, 90);
    }
}