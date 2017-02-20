import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.Iterator;

import Models.Server;
public class Entry {
	public static void main(String[] Args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		int numOfServers = 0;
		while (true) {
			try {
				System.out.println("Enter a date(yyyy/MM/dd) : \n");
				String s = br.readLine();
				date = dateFormat.parse(s);
				System.out
						.println("Enter the number of Servers need be initialized : \n");
				numOfServers =Integer.parseInt(br.readLine());
				break;
			} catch (Exception e) {
				System.out.println("Error : " + e.toString());
				System.out.println("Please try again");
			}
		}
		System.out.println(numOfServers);
		Server[] serverList= InitializeServers(numOfServers,date);
		GenerateLogData(serverList,date);
		//Query query = new Query();
		//query.findCPUUsage("0", "2017-02-03 00:00", "2017-02-03 00:05", "coresLog_1486108800/91.190.248.238.txt");
	}
	
	public static Server[] InitializeServers(int nums, Date date)
	{
		ServersInitialization serverTask = new ServersInitialization(nums,date);
		return serverTask.getServers();
	}
	
	public static void GenerateLogData(Server[] servers,Date date)
	{
		CoresDateGenerator dataGenerator = new CoresDateGenerator(servers,date);
		dataGenerator.generateAllData();
	}
}


