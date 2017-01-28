package commands;

import shellcommand.Command;
import shellcommand.CommandExecutor;
import shellcommand.CommandExecutor.OS;

/**
 * A command class for detecting stored wifi passwords over multiple OS types.
 * @author polle
 *
 */
public class DetectWifiPassCommand extends Command{

	// The command to be executed
	private final String command;
	private final OS osType;
	
	/**
	 * Create the command for grabbing the wifi passwords from a computer
	 * Autodetects the OS type and modifies commands appropriately.
	 * 
	 */
	public DetectWifiPassCommand() {
		this.osType = CommandExecutor.getCommandExecutor().getOSType();
		
		if(this.osType == OS.UNIX){
			this.command = "sudo cat /etc/NetworkManager/system-connections/*";
		}else if(this.osType == OS.WINDOWS){
			this.command = "echo not implemented";
		}else{
			this.command = "echo OS type not supported";
		}
	}
	
	@Override
	public String getCommand() {
		return this.command;
	}

	@Override
	public OS getOSType() {
		return this.osType;
	}

}
