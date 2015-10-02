package taskmanager;

import taskmanager.helper.Unix;
import java.util.ArrayList;
import java.util.regex.*;

public class Controller{

	public static String[][] getAllProcess(){
		Pattern p = Pattern.compile("^([a-zA-Z\\-_]{1,})[ ]{1,}([0-9]{1,})[ ]{1,}([0-9,]{1,})[ ]{1,}([0-9,]{1,})[ ]{1,}([0-9]{1,})[ ]{1,}([0-9]{1,})[ ]{1,}([\\?|s0-9]{1,})[ ]{1,}([A-Za-z\\<+]{1,})[ ]{1,}([0-9:]{1,})[ ]{1,}([0-9:\\.]{1,})[ ]{1,}(.*)$");
		Matcher m;
		boolean has_match;
		String[][] all;
		String[] split_rows;
		String[] split_cols;
		String pss = Unix.execute("ps aux");

		split_rows = pss.split("\n");
		all = new String[split_rows.length-1][7];

		for (int i=1; i<split_rows.length; i++) {
			m = p.matcher(split_rows[i]);
			has_match = m.find();
			if(has_match){
				all[i-1] = new String[7];
				all[i-1][0] = m.group(2);
				all[i-1][1] = m.group(3);
				all[i-1][2] = m.group(4);
				all[i-1][3] = m.group(1);
				all[i-1][4] = m.group(9);
				all[i-1][5] = m.group(10);
				all[i-1][6] = Controller.getProcessName(m.group(11));
			}
		}

		return all;
	}

	public static String getProcessName(String command){
		String[] arr = command.split(" ");
		String first_command = "";
		String name = new String();
		for (int i=0; i<arr.length; i++) {
			if(i > 0){
				if(first_command.charAt((first_command.length())-1) == '\\'){
					first_command = first_command.substring(0, (first_command.length())-2)+" "+arr[i];
				}
				else break;
			}
			else{
				first_command += arr[i];
			}
		}
		arr = first_command.split("/");
		name = arr[(arr.length)-1];
		return name;
	}

}

/*{"PID", "%CPU", "%MEM", "Usuário", "Início", "Tempo", "Nome"};*/