package dev.cammiescorner.invpeek;

import eu.midnightdust.lib.config.MidnightConfig;

public class InvPeekConfig extends MidnightConfig {
	@Entry public static boolean hideAllPreviews = false;
	@Entry public static boolean sneakToPreviewBookshelf = false;
	@Entry public static boolean sneakToPreviewLectern = false;
	@Entry public static boolean sneakToPreviewArmorStand = false;
	@Entry public static boolean sneakToPreviewItemFrame = false;
}
