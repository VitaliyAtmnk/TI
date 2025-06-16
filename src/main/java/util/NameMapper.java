package util;

/**
 * Pomocná třída pro převod názvů stavů z různých formátů do interní podoby.
 */
public class NameMapper {

    /**
     * Převede jméno stavu z JFLAP formátu (např. "q0") na jednoznakový název (např. "A").
     * Pokud název nezačíná písmenem 'q', vrátí se nezměněný.
     *
     * @param jflapName název stavu ve formátu JFLAP
     * @return mapovaný název stavu pro interní použití
     */
    public static String mapStateName(String jflapName) {
        if (jflapName == null || jflapName.isBlank()) {
            throw new IllegalArgumentException("Název stavu nesmí být prázdný.");
        }

        if (jflapName.charAt(0) == 'q') {
            try {
                int number = Integer.parseInt(jflapName.substring(1));
                return String.valueOf((char) ('A' + number));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Neplatný formát stavu: " + jflapName);
            }
        } else {
            return jflapName;
        }
    }
}
