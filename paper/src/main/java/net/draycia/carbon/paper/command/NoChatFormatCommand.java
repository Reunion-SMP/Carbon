package net.draycia.carbon.paper.command;

import com.google.inject.Inject;
import net.draycia.carbon.common.command.CarbonCommand;
import net.draycia.carbon.common.command.CommandSettings;
import net.draycia.carbon.common.command.Commander;
import net.draycia.carbon.common.command.PlayerCommander;
import net.draycia.carbon.common.messages.CarbonMessages;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.incendo.cloud.CommandManager;

import static org.incendo.cloud.minecraft.extras.RichDescription.richDescription;

@DefaultQualifier(NonNull.class)
public final class NoChatFormatCommand extends CarbonCommand {

    private final CommandManager<Commander> commandManager;
    private final CarbonMessages messages;

    @Inject
    public NoChatFormatCommand(final CommandManager<Commander> commandManager, final CarbonMessages messages) {
        this.commandManager = commandManager;
        this.messages = messages;
    }

    @Override
    public CommandSettings defaultCommandSettings() {
        return new CommandSettings("nochatformat");
    }

    @Override
    public Key key() {
        return Key.key("carbon", "nochatformat");
    }

    @Override
    public void init() {
        final var command = this.commandManager.commandBuilder(this.commandSettings().name(), this.commandSettings().aliases())
            .permission("carbon.nochatformat")
            .commandDescription(richDescription(Component.text("Toggle viewing of MiniMessage formatting in chat messages.")))
            .handler(handler -> {
                if (!(handler.sender() instanceof PlayerCommander player)) {
                    handler.sender().sendMessage(Component.text("Only players can use this command."));
                    return;
                }
                final var carbon = player.carbonPlayer();
                final boolean newState = !carbon.noChatFormat();
                carbon.noChatFormat(newState);
                handler.sender().sendMessage(Component.text("Chat formatting is now " + (newState ? "disabled" : "enabled") + "."));
            })
            .build();

        this.commandManager.command(command);
    }
}
