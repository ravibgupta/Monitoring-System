import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Object;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import Models.Server;

public class CoresDateGenerator {
	private Server[] _servers;
	private long _startTime;

	public CoresDateGenerator(Server[] serverList, Date oneDate) {
		_servers = serverList;
		_startTime = oneDate.getTime() / 1000L;
	}

	public void generateAllData() {
		for (int i = 0; i < _servers.length; i++) {
			generateDataPerServer(_servers[i]);
		}
	}

	private void generateDataPerServer(Server oneServer) {
		String content = GeneateDailyCoreData(oneServer);
		WriteIntoFile(content, oneServer);
	}

	private String GeneateDailyCoreData(Server oneServer) {
		String logPerServer = "";
		Random rand = new Random();
		for (int i = 0; i < 86400; i += 60) {
			logPerServer += (_startTime + i) + " " + oneServer.getIpAddress()
					+ " 0 " + rand.nextInt(101) + "\n";
			logPerServer += (_startTime + i) + " " + oneServer.getIpAddress()
					+ " 1 " + rand.nextInt(101) + "\n";
		}
		System.out.println(oneServer.getIpAddress()+"'s Data is generating");
		return logPerServer;
	}

	private void WriteIntoFile(String content, Server oneServer) {
		try {
			FileWriter fw = new FileWriter(oneServer.getLogFilePath(), true);
			fw.write(content);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return;
		}
	}
}
