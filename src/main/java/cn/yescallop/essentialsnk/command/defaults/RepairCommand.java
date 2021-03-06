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
		token = new Item(Item.FIREWORKSCHARGE, 0, 1, TextFormat.GREEN + "Repair Token").setLore("Do /repair with the tool in your hand");
		token.setLore("Do /repair with the tool in your hand to fix it.");
		this.setAliases(new String[] { "fix" });
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
		if (!player.getInventory().contains(token)) {
			player.sendMessage(Language.translate("commands.repair.length"));
			return true;
		}
		Item handItem = player.getInventory().getItemInHand();
		if (!api.isRepairable(handItem)) {
			player.sendMessage(Language.translate("commands.repair.unrepairable"));
			return true;
		}
		player.getInventory().remove(token);
		handItem.setDamage(0);
		player.getInventory().setItemInHand(handItem);
		player.sendMessage(Language.translate("commands.repair.success"));
		return true;
	}
}
