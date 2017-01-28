package extractor;

import java.io.IOException;

import commands.DetectWifiPassCommand;
import shellcommand.Command;
import shellcommand.CommandExecutor;
import shellcommand.NonMatchingOSException;
import shellcommand.ProcessNotYetStartedException;

public class Extractor {

	public static void main(String[] args) {

		CommandExecutor exec = CommandExecutor.getCommandExecutor();
		Command detectWifiCommand = new DetectWifiPassCommand();
		
		try {
			exec.executeCommand(detectWifiCommand);
			detectWifiCommand.waitForCompletion();
			if(detectWifiCommand.getExitCode() == 0){
				System.out.println(detectWifiCommand.getNormalOutput());				
			}else{
				System.out.println(detectWifiCommand.getErrorOutput());
			}
		} catch (NonMatchingOSException | IOException | ProcessNotYetStartedException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
