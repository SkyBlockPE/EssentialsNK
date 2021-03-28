package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;

public class GiveTokenCommand extends CommandBase {

    public GiveTokenCommand(EssentialsAPI api) {
        super("givetoken", api);
        this.setAliases(new String[]{"givet"});
        this.setUsage("/givetoken <name>");
        this.setPermission("essentialsnk.givetoken");

        this.commandParameters.clear();
        this.commandParameters.put("player", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, false)
        });
        this.commandParameters.put("uuid", new CommandParameter[]{
                new CommandParameter("uuid", CommandParamType.STRING, false)
        });
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender) && !this.testIngame(sender)) {
            return false;
        }
        if (args.length != 2 && !(sender instanceof Player)) {
            return false;
        }

        Player player;
        if (args.length == 0) {
            player = (Player) sender;
        } else  {
        	player = sender.getServer().getPlayer(args[0]);
        }
        if (player == null || player.isOnline()) {
            sender.sendMessage(TextFormat.RED + Language.translate("commands.generic.player.notfound", args[0]));
            return true;
        }
        switch (args[1]) {
		case "repair":
			player.getInventory().addItem(new Item(Item.FIREWORKSCHARGE, 0, 1, TextFormat.GREEN + "Repair Token").setLore("Do /repair with the tool in your hand"));
			break;
		case "rename":
			player.getInventory().addItem(new Item(Item.FIREWORKSCHARGE, 0, 1, TextFormat.GREEN + "Rename Token").setLore("Do /rename with the item in your hand"));
			break;

		default:
			break;
		}
        return true;
    }

}
