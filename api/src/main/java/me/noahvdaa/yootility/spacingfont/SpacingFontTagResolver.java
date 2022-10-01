package me.noahvdaa.yootility.spacingfont;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class SpacingFontTagResolver {

    private SpacingFontTagResolver() {
    }

    @NotNull
    public static TagResolver of(@NotNull SpacingFont font) {
        return of(font, "space");
    }

    @NotNull
    public static TagResolver of(@NotNull SpacingFont font, @NotNull String tagName) {
        Objects.requireNonNull(font, "font may not be null");
        Objects.requireNonNull(tagName, "tagName may not be null");

        return TagResolver.resolver(tagName, (args, ctx) -> resolve(font, args));
    }

    private static Tag resolve(SpacingFont font, ArgumentQueue args) {
        int length = args.pop().asInt().orElse(0);

        return Tag.selfClosingInserting(Component.text(font.getCharacters(length)));
    }

}
