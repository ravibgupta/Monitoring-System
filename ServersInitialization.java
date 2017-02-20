import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Models.Server;

public class ServersInitialization {
	private Server[] serversList;
	private Set<String> _uniqueIpCollection;
	private File _logDir;
	public ServersInitialization(int numbers,java.util.Date date) {
		serversList = new Server[numbers];
		_uniqueIpCollection = new HashSet<String>();
		_logDir= new File("coresLog_"+date.getTime()/1000L);
		_logDir.mkdir();
		_serversGenerator();
	}

	public Server[] getServers() {
		return serversList;
	}
	
	private void _serversGenerator() {
		Random rand = new Random();
		File oneFile;
		for (int i = 0; i < serversList.length; i++) {

			String newIp = "";
			do {
				newIp = (rand.nextInt(256)) + "." + (rand.nextInt(256)) + "."
						+ (rand.nextInt(256)) + "." + (rand.nextInt(256));
			} while (!_uniqueIpCollection.add(newIp));
			serversList[i]=new Server(newIp);
			oneFile=new File(_logDir,newIp+".txt");
			try{
			oneFile.createNewFile();
			serversList[i].setLogFilePath(oneFile.getPath());
			}
			catch(Exception e){
				System.out.println(e.toString());
				return;
			}
		}
	}
}
