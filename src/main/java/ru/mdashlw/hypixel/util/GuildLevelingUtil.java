package ru.mdashlw.hypixel.util;

/**
 * Guild leveling utils.
 *
 * @author Plancke
 */
public class GuildLevelingUtil {
    public static final int[] EXP_NEEDED = new int[]{
            100000,
            150000,
            250000,
            500000,
            750000,
            1000000,
            1250000,
            1500000,
            2000000,
            2500000,
            2500000,
            2500000,
            2500000,
            2500000,
            3000000
    };

    public static int getLevel(int exp) {
        int level = 0;

        for (int i = 0; ; i++) {
            int need = i >= EXP_NEEDED.length ? EXP_NEEDED[EXP_NEEDED.length - 1] : EXP_NEEDED[i];

            exp -= need;

            if (exp < 0) {
                return level;
            }

            level++;
        }
    }
}
