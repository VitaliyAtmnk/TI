package formatter;

import model.Automaton;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Rozhraní pro formátování a zápis automatů do výstupních souborů.
 * Implementace tohoto rozhraní zapisují zadaný {@link Automaton} do určitého formátu
 * (např. DKAR, GraphViz DOT apod.).
 */
public interface Formatter {

    /**
     * Zapíše automat do výstupního souboru ve specifickém formátu.
     *
     * @param automaton      automat, který má být zapsán
     * @param outputFileName cesta k výstupnímu souboru
     * @throws IOException pokud dojde k chybě při zápisu souboru
     */
    void write(Automaton automaton, Path outputFileName) throws IOException;
}
