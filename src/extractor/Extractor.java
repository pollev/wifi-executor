package extractor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commands.DetectWifiPassCommand;
import shellcommand.Command;
import shellcommand.CommandExecutor;
import shellcommand.NonMatchingOSException;
import shellcommand.ProcessNotYetStartedException;

public class Extractor {

	final static Logger logger = LoggerFactory.getLogger(CommandExecutor.class);
	
	public static void main(String[] args) {
		String wifiData = gatherWifiDetails();
		System.out.println(wifiData);
	}
	
	
	public static String gatherWifiDetails(){
		String wifiData = null;
		
		CommandExecutor exec = CommandExecutor.getCommandExecutor();
		Command detectWifiCommand = new DetectWifiPassCommand();
		try {
			exec.executeCommand(detectWifiCommand);
			detectWifiCommand.waitForCompletion();
			if(detectWifiCommand.getExitCode() == 0){
				wifiData = detectWifiCommand.getNormalOutput();				
			}else{
				System.out.println(detectWifiCommand.getErrorOutput());
				Extractor.logger.error("Subprocess returned error: " + detectWifiCommand.getErrorOutput());
			}
		} catch (NonMatchingOSException | IOException | ProcessNotYetStartedException | InterruptedException e) {
			Extractor.logger.error("Failed to extract wifi credentials. " + e.getMessage());
			e.printStackTrace();
		}
		
		return wifiData;
	}

}
