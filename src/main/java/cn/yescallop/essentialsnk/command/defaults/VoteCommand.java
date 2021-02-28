package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.command.CommandBase;

public class VoteCommand extends CommandBase {

    public VoteCommand(EssentialsAPI api) {
        super("vote", api);
        // command parameters
        commandParameters.clear();
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        sender.sendMessage(TextFormat.GOLD + "Vote for us every day for in game rewards and extras!");
        sender.sendMessage(TextFormat.GREEN + "www.bit.ly/votesbpe1");
        sender.sendMessage(TextFormat.GREEN + "www.bit.ly/votesbpe2");
        return true;
    }
}

