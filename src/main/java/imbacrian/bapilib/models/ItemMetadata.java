package imbacrian.bapilib.models;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public class ItemMetadata extends Item {

	private final String languagePrefix;
	private final IntFunction<String> metadataName;

	public ItemMetadata(
		@NotNull String translationKey,
		@NotNull String namespaceID,
		int id,
		@NotNull IntFunction<String> metadataName
	) {
		super(translationKey, namespaceID, id);
		this.languagePrefix = translationKey;
		this.metadataName = metadataName;
	}

	@Override
	public @NotNull String getLanguageKey(@NotNull ItemStack stack) {
		int metadata = stack.getMetadata() & 15;
		return languagePrefix + "." + metadataName.apply(metadata);
	}
}
