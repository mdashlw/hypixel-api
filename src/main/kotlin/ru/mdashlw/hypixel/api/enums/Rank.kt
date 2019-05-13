package ru.mdashlw.hypixel.api.enums

enum class Rank(val uncolorizedName: String, val colorizedName: String) {
    ADMIN("[ADMIN] ", "§c[ADMIN] "),
    MODERATOR("[MOD] ", "§2[MOD] "),
    HELPER("[HELPER] ", "§9[HELPER] "),
    YOUTUBER("[YOUTUBE] ", "§c[§fYOUTUBE§c] "),
    SUPERSTAR("[MVP++] ", "§6[MVP§c++§6] "),
    MVP_PLUS("[MVP+] ", "§b[MVP§c+§b] "),
    MVP("[MVP] ", "§b[MVP] "),
    VIP_PLUS("[VIP+] ", "§a[VIP§6+§a] "),
    VIP("[VIP] ", "§a[VIP] "),
    DEFAULT("", "§7");
}
