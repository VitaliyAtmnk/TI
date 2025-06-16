package parser;

import model.Automaton;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Rozhraní pro parsování automatů z externích souborů.
 * Implementace tohoto rozhraní by měly načíst automat (např. z XML/jff, DKAR apod.)
 * a převést jej do jednotné reprezentace {@link Automaton}.
 */
public interface Parser {

    /**
     * Načte a zpracuje soubor se specifikací automatu.
     *
     * @param source cesta ke vstupnímu souboru
     * @return reprezentace automatu ve formě objektu {@link Automaton}
     * @throws IOException pokud dojde k chybě při čtení souboru
     */
    Automaton parse(Path source) throws IOException;
}
