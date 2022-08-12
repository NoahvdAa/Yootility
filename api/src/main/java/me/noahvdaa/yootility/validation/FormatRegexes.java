package me.noahvdaa.yootility.validation;

import java.util.regex.Pattern;

public final class FormatRegexes {

    private FormatRegexes() {
    }

    /**
     * Matches a UUID containing dashes, e.g. 00000000-0000-0000-0000-000000000000.
     */
    public static final Pattern DASHED_UUID = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
    /**
     * Matches a UUID containing no dashes, e.g. 00000000000000000000000000000000.
     */
    public static final Pattern DASHLESS_UUID = Pattern.compile("^[0-9a-fA-F]{32}$");
    /**
     * Matches a UUID containing either dashes or no dashes, e.g. 00000000-0000-0000-0000-000000000000 and 00000000000000000000000000000000.
     */
    public static final Pattern UUID = Pattern.compile("^(?:[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})|(?:[0-9a-fA-F]{32})$");

}
