package formatter;

import model.Automaton;
import model.State;
import model.Symbol;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Formatter pro zápis deterministického konečného automatu do textového formátu DKAR.
 * DKAR je jednoduchý řádkový formát s pevnou strukturou vhodný pro snadné načtení.
 */
public class DkarFormatter implements Formatter {

    /**
     * Znak pro reprezentaci chybějicího přechodu, nepoužitého vstupu v tabulce
     */
    private final String MISSING_INPUT = "_";

    /**
     * Zapíše zadaný {@link Automaton} do výstupního souboru ve formátu DKAR.
     *
     * @param automaton      automat, který se má zapsat
     * @param outputFileName cesta k výstupnímu souboru
     * @throws IOException pokud dojde k chybě při zápisu
     */
    @Override
    public void write(Automaton automaton, Path outputFileName) throws IOException {
        // Seřazení stavů a symbolů pro konzistentní výstup
        List<State> sortedStates = automaton.getStates().stream()
                .sorted(Comparator.comparing(State::toString))
                .toList();

        List<Symbol> sortedAlphabet = automaton.getInputAlphabet().stream()
                .sorted(Comparator.comparing(Symbol::toString))
                .toList();

        try (BufferedWriter writer = Files.newBufferedWriter(outputFileName, StandardCharsets.UTF_8)) {
            // Hlavička formátu
            writer.write("DKAR");
            writer.newLine();
            writer.write(String.valueOf(sortedStates.size())); // počet stavů
            writer.newLine();
            writer.write(String.valueOf(sortedAlphabet.size())); // počet symbolů
            writer.newLine();

            // Přechodová tabulka: každý řádek pro jeden stav
            for (State state : sortedStates) {
                String row = sortedAlphabet.stream()
                        .map(sym ->
                                automaton.getTransitions()
                                        .getOrDefault(state, Collections.emptyMap())
                                        .getOrDefault(sym, new State(MISSING_INPUT))
                                        .toString()
                        )
                        .collect(Collectors.joining(" "));
                writer.write(state + ": " + row);
                writer.newLine();
            }

            // Počáteční stav
            writer.write(automaton.getStartState().toString());
            writer.newLine();

            // Koncové stavy (počet + seznam jmen)
            String finals = automaton.getFinalStates().stream()
                    .map(State::toString)
                    .sorted()
                    .collect(Collectors.joining(" "));
            writer.write(automaton.getFinalStates().size() + " " + finals);
            writer.newLine();
        }
    }
}
