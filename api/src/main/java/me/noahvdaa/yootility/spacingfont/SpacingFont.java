package me.noahvdaa.yootility.spacingfont;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class SpacingFont {

    public static final SpacingFont AMBERWAT_SPACE_REGULAR = of(Map.ofEntries(
            new AbstractMap.SimpleEntry<>(-1, "\uF801"),
            new AbstractMap.SimpleEntry<>(-2, "\uF802"),
            new AbstractMap.SimpleEntry<>(-3, "\uF803"),
            new AbstractMap.SimpleEntry<>(-4, "\uF804"),
            new AbstractMap.SimpleEntry<>(-5, "\uF805"),
            new AbstractMap.SimpleEntry<>(-6, "\uF806"),
            new AbstractMap.SimpleEntry<>(-7, "\uF807"),
            new AbstractMap.SimpleEntry<>(-8, "\uF808"),
            new AbstractMap.SimpleEntry<>(-16, "\uF809"),
            new AbstractMap.SimpleEntry<>(-32, "\uF80A"),
            new AbstractMap.SimpleEntry<>(-64, "\uF80B"),
            new AbstractMap.SimpleEntry<>(-128, "\uF80C"),
            new AbstractMap.SimpleEntry<>(-256, "\uF80D"),
            new AbstractMap.SimpleEntry<>(-512, "\uF80E"),
            new AbstractMap.SimpleEntry<>(-1024, "\uF80F"),

            new AbstractMap.SimpleEntry<>(1, "\uF821"),
            new AbstractMap.SimpleEntry<>(2, "\uF822"),
            new AbstractMap.SimpleEntry<>(3, "\uF823"),
            new AbstractMap.SimpleEntry<>(4, "\uF824"),
            new AbstractMap.SimpleEntry<>(5, "\uF825"),
            new AbstractMap.SimpleEntry<>(6, "\uF826"),
            new AbstractMap.SimpleEntry<>(7, "\uF827"),
            new AbstractMap.SimpleEntry<>(8, "\uF828"),
            new AbstractMap.SimpleEntry<>(16, "\uF829"),
            new AbstractMap.SimpleEntry<>(32, "\uF82A"),
            new AbstractMap.SimpleEntry<>(64, "\uF82B"),
            new AbstractMap.SimpleEntry<>(128, "\uF82C"),
            new AbstractMap.SimpleEntry<>(256, "\uF82D"),
            new AbstractMap.SimpleEntry<>(512, "\uF82E"),
            new AbstractMap.SimpleEntry<>(1024, "\uF82F")
    ));

    public static final SpacingFont AMBERWAT_SPACE_NO_SPLIT = of(Map.ofEntries(
            new AbstractMap.SimpleEntry<>(-1, "\uF811"),
            new AbstractMap.SimpleEntry<>(-2, "\uF812"),
            new AbstractMap.SimpleEntry<>(-3, "\uF813"),
            new AbstractMap.SimpleEntry<>(-4, "\uF814"),
            new AbstractMap.SimpleEntry<>(-5, "\uF815"),
            new AbstractMap.SimpleEntry<>(-6, "\uF816"),
            new AbstractMap.SimpleEntry<>(-7, "\uF817"),
            new AbstractMap.SimpleEntry<>(-8, "\uF818"),
            new AbstractMap.SimpleEntry<>(-16, "\uF819"),
            new AbstractMap.SimpleEntry<>(-32, "\uF81A"),
            new AbstractMap.SimpleEntry<>(-64, "\uF81B"),
            new AbstractMap.SimpleEntry<>(-128, "\uF81C"),
            new AbstractMap.SimpleEntry<>(-256, "\uF81D"),
            new AbstractMap.SimpleEntry<>(-512, "\uF81E"),
            new AbstractMap.SimpleEntry<>(-1024, "\uF81F"),

            new AbstractMap.SimpleEntry<>(1, "\uF831"),
            new AbstractMap.SimpleEntry<>(2, "\uF832"),
            new AbstractMap.SimpleEntry<>(3, "\uF833"),
            new AbstractMap.SimpleEntry<>(4, "\uF834"),
            new AbstractMap.SimpleEntry<>(5, "\uF835"),
            new AbstractMap.SimpleEntry<>(6, "\uF836"),
            new AbstractMap.SimpleEntry<>(7, "\uF837"),
            new AbstractMap.SimpleEntry<>(8, "\uF838"),
            new AbstractMap.SimpleEntry<>(16, "\uF839"),
            new AbstractMap.SimpleEntry<>(32, "\uF83A"),
            new AbstractMap.SimpleEntry<>(64, "\uF83B"),
            new AbstractMap.SimpleEntry<>(128, "\uF83C"),
            new AbstractMap.SimpleEntry<>(256, "\uF83D"),
            new AbstractMap.SimpleEntry<>(512, "\uF83E"),
            new AbstractMap.SimpleEntry<>(1024, "\uF83F")
    ));

    private TreeMap<Integer, String> characters = new TreeMap<>();

    private SpacingFont() {
    }

    @NotNull
    public static SpacingFont of(@NotNull Map<Integer, String> characters) {
        Objects.requireNonNull(characters, "characters may not be null");

        TreeMap<Integer, String> characterMap = new TreeMap<>(characters);

        SpacingFont font = new SpacingFont();
        font.characters = characterMap;

        return font;
    }

    @NotNull
    public String getCharacters(int width) {
        // We don't need to do anything
        if (width == 0) return "";

        StringBuilder output = new StringBuilder();
        boolean direction = width > 0;
        int remaining = Math.abs(width);
        while (remaining > 0) {
            Map.Entry<Integer, String> entry = direction ? this.characters.floorEntry(remaining) : this.characters.ceilingEntry(-remaining);
            if (entry == null) return output.toString();

            output.append(entry.getValue());
            remaining -= Math.abs(entry.getKey());
        }

        return output.toString();
    }

}
