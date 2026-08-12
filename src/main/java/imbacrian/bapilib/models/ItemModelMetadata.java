package imbacrian.bapilib.models;

import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemModelMetadata extends ItemModelStandard {

	private final IconCoordinate[] icons;

	public ItemModelMetadata(@NotNull Item item, int metadataCount) {
		super(item, "namespace");
		this.icons = new IconCoordinate[metadataCount];
	}

	public ItemModelMetadata setIcon(int metadata, String texture) {
		if (metadata < 0 || metadata >= icons.length) {
			throw new IllegalArgumentException(
				"Invalid metadata: " + metadata
			);
		}

		icons[metadata] = TextureRegistry.getTexture(texture);
		return this;
	}

	@Override
	public IconCoordinate getIcon(
		@Nullable Entity entity,
		@NotNull ItemStack itemStack
	) {
		int metadata = itemStack.getMetadata() & 15;

		if (metadata >= icons.length) {
			return this.icon;
		}

		IconCoordinate icon = icons[metadata];

		return icon != null ? icon : this.icon;
	}
}
