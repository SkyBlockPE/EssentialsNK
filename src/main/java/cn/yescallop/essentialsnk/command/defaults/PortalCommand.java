package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;

public class PortalCommand extends CommandBase {

    public PortalCommand(EssentialsAPI api) {
        super("portal", api);
        // command parameters
        commandParameters.clear();
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (!this.testIngame(sender)) {
            return false;
        }
        
        Player player = (Player) sender;
        // check if two then create
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("remove")) {
                player.sendMessage(api.removeRegion(args[1]) ? TextFormat.GREEN + "Region removed"
                        : TextFormat.RED + "Region with that name not found!");
                return true;
            }
            player.sendMessage(TextFormat.RED + "/portal remove <name>");
            return true;
        }
        if (args.length != 3) {
            player.sendMessage(TextFormat.RED + "/portal create <name> <address>");
            return true;
        }
        if (!args[0].equalsIgnoreCase("create")) {
            player.sendMessage(TextFormat.RED + "/portal create <name> <address>");
            return true;
        }
        player.sendMessage(api.addRegion(player, args[1], args[2]) ? TextFormat.GREEN + "Portal created"
                : TextFormat.RED + "Portal wasn't created");
        return true;
    }
}
