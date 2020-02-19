package pt.skyblock;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.BUGFIX;

public class PTSkyblockSettings
{
    @Rule(desc = "Fixes exit end portal generating too low", extra = "Fixes MC-93185", category = {BUGFIX})
    public static boolean endPortalFix = true;
}
