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

public class RenameCommand extends CommandBase {

	private final Item token;

	public RenameCommand(EssentialsAPI api) {
		super("rename", api);
		this.setAliases(new String[] { "renameme" });
		token = new Item(Item.FIREWORKSCHARGE, 0, 1, TextFormat.GREEN + "Rename Token");
		token.setLore("Do /rename with the tool in your hand to fix it.");

		// command parameters
		commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[] {
                new CommandParameter("name", CommandParamType.STRING, false)
        });
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
			player.sendMessage(Language.translate("commands.rename.missing"));
			return true;
		}
		Item handItem = player.getInventory().getItemInHand();

		if (handItem.isNull()) {
			player.sendMessage(Language.translate("commands.commands.rename.air"));
			return true;
		}
		String newName = String.join(" ", args);
		if (newName.length() < 50) {
			handItem.setCustomName(newName);
			player.getInventory().remove(token);
			player.getInventory().setItemInHand(handItem);
		} else {
			player.sendMessage(Language.translate("commands.rename.length"));
			return true;
		}
		return true;
	}
}
