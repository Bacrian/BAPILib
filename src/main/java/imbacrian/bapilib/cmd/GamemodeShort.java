package imbacrian.bapilib.cmd;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.net.command.CommandManager;
import net.minecraft.core.net.command.arguments.ArgumentTypeEntity;
import net.minecraft.core.net.command.CommandSource;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentTypeString;
import com.mojang.brigadier.builder.ArgumentBuilderLiteral;
import com.mojang.brigadier.builder.ArgumentBuilderRequired;
import net.minecraft.core.net.command.helpers.EntitySelector;
import net.minecraft.core.player.gamemode.Gamemodes;

public final class GamemodeShort implements CommandManager.CommandRegistry {
	@Override
	public void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(
			ArgumentBuilderLiteral.<CommandSource>literal("gmc")
				.requires(CommandSource::hasAdmin)
				.executes(ctx -> {
					CommandSource source = ctx.getSource();
					net.minecraft.core.entity.player.Player senderPlayer = source.getSender();

					if (senderPlayer != null) {
						return creativePlayer(source);
					} else {
						source.sendMessage("This command can only be executed by a player via implicit sender.");
						return 0;
					}
				})
				.then(ArgumentBuilderRequired.<CommandSource, EntitySelector>argument("target", ArgumentTypeEntity.username())
					.executes(ctx -> creativePlayer(ctx.getSource()))));
		dispatcher.register(
			ArgumentBuilderLiteral.<CommandSource>literal("gms")
				.requires(CommandSource::hasAdmin)
				.executes(ctx -> {
					CommandSource source = ctx.getSource();
					net.minecraft.core.entity.player.Player senderPlayer = source.getSender();

					if (senderPlayer != null) {
						return survivalPlayer(source);
					} else {
						source.sendMessage("This command can only be executed by a player via implicit sender.");
						return 0;
					}
				})
				.then(ArgumentBuilderRequired.<CommandSource, EntitySelector>argument("target", ArgumentTypeEntity.username())
					.executes(ctx -> survivalPlayer(ctx.getSource()))));
		dispatcher.register(
			ArgumentBuilderLiteral.<CommandSource>literal("gma")
				.requires(CommandSource::hasAdmin)
				.executes(ctx -> {
					CommandSource source = ctx.getSource();
					net.minecraft.core.entity.player.Player senderPlayer = source.getSender();

					if (senderPlayer != null) {
						return adventurePlayer(source);
					} else {
						source.sendMessage("This command can only be executed by a player via implicit sender.");
						return 0;
					}
				})
				.then(ArgumentBuilderRequired.<CommandSource, EntitySelector>argument("target", ArgumentTypeEntity.username())
					.executes(ctx -> adventurePlayer(ctx.getSource()))));
		dispatcher.register(
			ArgumentBuilderLiteral.<CommandSource>literal("gmsp")
				.requires(CommandSource::hasAdmin)
				.executes(ctx -> {
					CommandSource source = ctx.getSource();
					net.minecraft.core.entity.player.Player senderPlayer = source.getSender();

					if (senderPlayer != null) {
						return spectatorPlayer(source);
					} else {
						source.sendMessage("This command can only be executed by a player via implicit sender.");
						return 0;
					}
				})
				.then(ArgumentBuilderRequired.<CommandSource, EntitySelector>argument("target", ArgumentTypeEntity.username())
					.executes(ctx -> spectatorPlayer(ctx.getSource()))));
	}
	private int creativePlayer(CommandSource source) {
		Player player = source.getSender();

		player.setGamemode(Gamemodes.CREATIVE);
		return 0;
	}
	private int survivalPlayer(CommandSource source) {
		Player player = source.getSender();

		player.setGamemode(Gamemodes.SURVIVAL);
		return 1;
	}
	private int adventurePlayer(CommandSource source) {
		Player player = source.getSender();

		player.setGamemode(Gamemodes.ADVENTURE);
		return 1;
	}
	private int spectatorPlayer(CommandSource source) {
		Player player = source.getSender();

		player.setGamemode(Gamemodes.SPECTATOR);
		return 1;
	}
}
