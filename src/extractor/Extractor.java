package extractor;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
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
		// Setup logging
		String log4JPropertyFile = "log4j.properties";
		Properties p = new Properties();

		try {
		    p.load(new FileInputStream(log4JPropertyFile));
		    PropertyConfigurator.configure(p);
		    logger.info("Initialized logger!");
		} catch (IOException e) {
		    // Can't log exception with no logger
			System.err.println(e.getMessage());
		}
		
		
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
