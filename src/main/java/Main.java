import converter.Converter;
import formatter.DkarFormatter;
import parser.JflapParser;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Hlavní třída aplikace. Slouží k převodu automatu ze vstupního JFLAP XML souboru
 * do textového formátu DKAR.
 */
public class Main {

    /**
     * Vstupní bod programu.
     * Očekává dva argumenty:
     * <ul>
     *     <li><b>args[0]</b>: cesta ke vstupnímu souboru (např. JFLAP XML)</li>
     *     <li><b>args[1]</b>: cesta k výstupnímu souboru (např. DKAR)</li>
     * </ul>
     *
     * @param args argumenty příkazové řádky
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Použití: java Main <vstupní_soubor> <výstupní_soubor>");
            System.exit(1);
        }

        String inputFilename = args[0];
        String outputFilename = args[1];

        JflapParser parser = new JflapParser();
        DkarFormatter formatter = new DkarFormatter();
        Converter converter = new Converter(parser, formatter);

        try {
            converter.convert(Path.of(inputFilename), Path.of(outputFilename));
            System.out.println("Převod byl úspěšně dokončen.");
        } catch (IOException e) {
            System.err.println("Chyba při převodu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
