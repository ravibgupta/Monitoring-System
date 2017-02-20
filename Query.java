import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Query {

	public static void main (String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date = null;
		try {
            date = dateFormat.parse(args[2] + " " + args[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
	
		long unixTime = (long)date.getTime()/1000;

		findCPUUsage(args[0], args[1], args[2] + " " + args[3], args[4] + " " + args[5], "coresLog_" + unixTime + "/" + args[0] + ".txt");
	}
	
	public static void findCPUUsage(String ip, String cpu_id, String startTime, String endTime, String filePath) {

        boolean start = false;

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("No such CPU found!!");
            System.exit(0);
        }

        LinkedHashSet<String> set = new LinkedHashSet<>();

        for (int i = 0; i < lines.size(); i++) {
            String[] temp = lines.get(i).split(" ");
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date((long) Long.valueOf(temp[0]) * 1000));
            if (temp[2].equals(cpu_id) && timeStamp.equals(startTime)) {
                start = true;
            }
            if (temp[2].equals(cpu_id) && timeStamp.equals(endTime)) {
                set.add("(" + timeStamp + ", " + temp[3] + "%)");
                start = false;
                break;
            }
            if (temp[2].equals(cpu_id) && start) {
                set.add("(" + timeStamp + ", " + temp[3] + "%), ");
            }
        }

        System.out.println("\nCPU" + cpu_id + " usage on " + ip + ":\n");
        for (Object aSet : set) {
            System.out.print(aSet);
        }
		System.out.println("\n");
    }
}
