package Models;

public class Server {
	private int _core1;
	private int _core2;
    private String _ipAddress;
    private String _logFilePath;
	public Server(String ipAddress) {
		_ipAddress=ipAddress;
	}
	public Server(String ipAddress,int core1, int core2) {
		_core1 = core1;
		_core2 = core2;
	}
	
	public String getIpAddress()
	{
		return _ipAddress;
	}
	public void setIpAddress(String ipAddress)
	{
		_ipAddress=ipAddress;
	}
	
	public void setLogFilePath(String path)
	{
		_logFilePath=path;
	}
	public String getLogFilePath()
	{
		return _logFilePath;
	}
	public int getCore1() {
		return _core1;
	}

	public int getCore2() {
		return _core2;
	}

	public void setCores(int core1,int core2) {
		_core1 = core1;
	}
}
