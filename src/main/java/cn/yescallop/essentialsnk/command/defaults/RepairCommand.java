package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;


public class RepairCommand extends CommandBase {
	
	private final Item token;
    public RepairCommand(EssentialsAPI api) {
        super("repair", api);
        token = new Item(Item.FIREWORKSCHARGE, 0, 1, TextFormat.GREEN + "Repair Token");
        token.setLore("Do /repair with the tool in your hand to fix it.");
        this.setAliases(new String[]{"fix"});
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
        if(!player.getInventory().contains(token)) {
            player.sendMessage(Language.translate("commands.repair.length"));
        	return true;
        }
        if (!api.isRepairable(token)) {
            player.sendMessage(Language.translate("commands.repair.unrepairable"));
        	return true;
        }
        player.getInventory().setItemInHand(player.getInventory().getItemInHand().setDamage(0));
        player.sendMessage(Language.translate("commands.repair.success"));
        return true;
    }
}
