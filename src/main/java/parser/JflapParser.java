package parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import model.Automaton;
import model.State;
import model.Symbol;
import util.NameMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parser pro načtení deterministického konečného automatu ze souboru ve formátu JFLAP (XML).
 */
public class JflapParser implements Parser {
    private final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Načte automat ze zadaného XML souboru ve formátu JFLAP.
     * @param source cesta ke vstupnímu souboru
     * @return naplněný objekt Automaton
     * @throws IOException při chybě čtení nebo formátu
     */
    @Override
    public Automaton parse(Path source) throws IOException, IllegalStateException {
        JflapStructure struct = xmlMapper.readValue(source.toFile(), JflapStructure.class);
        JflapAutomaton jm = struct.automaton;

        Automaton automaton = new Automaton();
        Map<Integer, State> idToState = new HashMap<>();

        // Vytvoření a přidání stavů
        for (JflapState js : jm.states) {
            String mapped = NameMapper.mapStateName(js.name);
            State st = new State(mapped);
            automaton.addState(st);
            idToState.put(js.id, st);

            if (js.initial != null) {
                automaton.setStartState(st);
            }
            if (js._final != null) {
                automaton.addFinalState(st);
            }
        }

        // Přidání přechodů a symbolů
        for (JflapTransition jt : jm.transitions) {
            Symbol sym = new Symbol(jt.read);
            State from = idToState.get(jt.from);
            State to = idToState.get(jt.to);
            automaton.addTransition(from, sym, to);
        }

        automaton.validate(); // vyhodí chybu, pokud automat není konzistentní
        return automaton;
    }

    // Vnitřní třídy pro mapování struktury JFLAP XML

    /** Kořenový prvek XML souboru. */
    private static class JflapStructure {
        @JacksonXmlProperty(localName = "type")
        public String _type;

        @JacksonXmlProperty(localName = "automaton")
        public JflapAutomaton automaton;
    }

    /** Reprezentuje automat zapsaný v XML. */
    private static class JflapAutomaton {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "state")
        public List<JflapState> states;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "transition")
        public List<JflapTransition> transitions;
    }

    /** Stav automatu dle XML reprezentace. */
    private static class JflapState {
        @JacksonXmlProperty(isAttribute = true)
        public int id;

        @JacksonXmlProperty(isAttribute = true)
        public String name;

        public Double x; // Nevyužito
        public Double y; // Nevyužito

        @JacksonXmlProperty(localName = "initial")
        public Object initial;

        @JacksonXmlProperty(localName = "final")
        public Object _final;
    }

    /** Přechod mezi stavy dle XML reprezentace. */
    private static class JflapTransition {
        public int from;
        public int to;
        public String read;
    }
}
