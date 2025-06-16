package converter;

import formatter.Formatter;
import model.Automaton;
import parser.Parser;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Třída {@code Converter} slouží k převodu automatů mezi dvěma formáty.
 * K převodu využívá {@link Parser} pro načtení vstupního souboru a {@link Formatter}
 * pro zápis do výstupního souboru.
 */
public class Converter {
    private final Parser parser;
    private final Formatter formatter;

    /**
     * Vytvoří nový převodník s daným parserem a formátovačem.
     *
     * @param parser    komponenta pro načtení automatu
     * @param formatter komponenta pro zápis automatu
     */
    public Converter(Parser parser, Formatter formatter) {
        this.parser = parser;
        this.formatter = formatter;
    }

    /**
     * Provede konverzi automatu ze vstupního do výstupního formátu.
     *
     * @param inputFile  cesta ke vstupnímu souboru (např. XML, DKAR)
     * @param outputFile cesta k výstupnímu souboru
     * @throws IOException pokud dojde k chybě při čtení nebo zápisu
     */
    public void convert(Path inputFile, Path outputFile) throws IOException {
        Automaton result = parser.parse(inputFile);
        formatter.write(result, outputFile);
    }
}
