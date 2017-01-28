package extractor;

import java.io.IOException;

import commands.DetectWifiPassCommand;
import shellcommand.Command;
import shellcommand.CommandExecutor;
import shellcommand.NonMatchingOSException;

public class Extractor {

	public static void main(String[] args) {

		CommandExecutor exec = CommandExecutor.getCommandExecutor();
		Command detectWifiCommand = new DetectWifiPassCommand();
		
		try {
			exec.executeCommand(detectWifiCommand);
			System.out.println(detectWifiCommand.getNormalOutput());
		} catch (NonMatchingOSException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
