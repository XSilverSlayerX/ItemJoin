package me.RockinChaos.itemjoin.handlers;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PermissionsHandler {

	public static String customPermissions(String permissionNode, String item, String worldName) {
		if (permissionNode != null) {
			return permissionNode;
		}
		return "itemjoin." + worldName + "." + item;
	}
	
	public static boolean hasCommandPermission(CommandSender sender, String permission) {
		if (sender.hasPermission(permission) || sender.hasPermission("itemjoin.*") || isDeveloper(sender) || (sender instanceof ConsoleCommandSender)) {
			return true;
		} else if (!ConfigHandler.getOPCommandPermissions() && sender.isOp()) {
			if (permission.equalsIgnoreCase("itemjoin.use") || permission.equalsIgnoreCase("itemjoin.reload") || permission.equalsIgnoreCase("itemjoin.updates")
					|| permission.equalsIgnoreCase("itemjoin.autoupdate") || permission.equalsIgnoreCase("itemjoin.creator") || permission.equalsIgnoreCase("itemjoin.purge") 
							|| permission.equalsIgnoreCase("itemjoin.save") || permission.equalsIgnoreCase("itemjoin.get") || permission.equalsIgnoreCase("itemjoin.get.others")
							|| permission.equalsIgnoreCase("itemjoin.remove") || permission.equalsIgnoreCase("itemjoin.remove.others") || permission.equalsIgnoreCase("itemjoin.disable") 
							|| permission.equalsIgnoreCase("itemjoin.disable.others") || permission.equalsIgnoreCase("itemjoin.enable") || permission.equalsIgnoreCase("itemjoin.enable.others")
							|| permission.equalsIgnoreCase("itemjoin.list") || isDeveloper(sender)) {
				return true;
			}
		}
		return false;
	}
	
    /**
     * If Debugging Mode is enabled, the plugin developer will be allowed to execute ONLY this plugins commands for help and support purposes.
     */
	private static boolean isDeveloper(CommandSender sender) {
		if (ServerHandler.hasDebuggingMode()) {
			if (sender instanceof Player) {
				try { if (((Player)sender).getUniqueId().toString().equalsIgnoreCase("ad6e8c0e-6c47-4e7a-a23d-8a2266d7baee")) { return true; }
				} catch (Exception e) { if (sender.getName().equalsIgnoreCase("RockinChaos")) { return true; } }
			}
		}
		return false;
	}
}