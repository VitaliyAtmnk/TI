package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Reprezentuje deterministický konečný automat (DKA).
 * Uchovává množinu stavů, vstupní abecedu, přechodovou funkci,
 * počáteční stav a množinu koncových stavů.
 */
public class Automaton {
    private Set<State> states;
    private Set<Symbol> inputAlphabet;
    private Map<State, Map<Symbol, State>> transitions;
    private State startState;
    private Set<State> finalStates;

    /** Vytvoří prázdný automat s inicializovanými množinami a mapami. */
    public Automaton() {
        states = new HashSet<>();
        inputAlphabet = new HashSet<>();
        transitions = new HashMap<>();
        finalStates = new HashSet<>();
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public Set<Symbol> getInputAlphabet() {
        return inputAlphabet;
    }

    public void setInputAlphabet(Set<Symbol> inputAlphabet) {
        this.inputAlphabet = inputAlphabet;
    }

    public Map<State, Map<Symbol, State>> getTransitions() {
        return transitions;
    }

    public void setTransitions(Map<State, Map<Symbol, State>> transitions) {
        this.transitions = transitions;
    }

    public State getStartState() {
        return startState;
    }

    public void setStartState(State startState) {
        this.startState = startState;
        this.states.add(startState); // automaticky přidat do stavů
    }

    public Set<State> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(Set<State> finalStates) {
        this.finalStates = finalStates;
        this.states.addAll(finalStates); // zajistit, že koncové stavy jsou známy
    }

    /**
     * Přidá stav do množiny stavů.
     * @param state nový stav
     */
    public void addState(State state) {
        states.add(state);
    }

    /**
     * Přidá symbol do vstupní abecedy.
     * @param symbol nový symbol
     */
    public void addSymbol(Symbol symbol) {
        inputAlphabet.add(symbol);
    }

    /**
     * Přidá přechod mezi dvěma stavy na základě vstupního symbolu.
     * @param from výchozí stav
     * @param symbol vstupní symbol
     * @param to cílový stav
     */
    public void addTransition(State from, Symbol symbol, State to) {
        states.add(from);
        states.add(to);
        inputAlphabet.add(symbol);

        transitions
                .computeIfAbsent(from, k -> new HashMap<>())
                .put(symbol, to);
    }

    /**
     * Přidá koncový stav.
     * @param state nový koncový stav
     */
    public void addFinalState(State state) {
        finalStates.add(state);
        states.add(state);
    }

    /**
     * Ověří, zda je automat konzistentní.
     * @throws IllegalStateException pokud je nalezena nekonzistence
     */
    public void validate() {
        if (startState == null) {
            throw new IllegalStateException("Automat nemá nastavený počáteční stav.");
        }

        if (!states.contains(startState)) {
            throw new IllegalStateException("Počáteční stav není v množině stavů.");
        }

        for (State finalState : finalStates) {
            if (!states.contains(finalState)) {
                throw new IllegalStateException("Koncový stav '" + finalState + "' není v množině stavů.");
            }
        }

        for (Map.Entry<State, Map<Symbol, State>> entry : transitions.entrySet()) {
            State from = entry.getKey();
            if (!states.contains(from)) {
                throw new IllegalStateException("Zdrojový stav '" + from + "' v přechodu není v množině stavů.");
            }

            for (Map.Entry<Symbol, State> transition : entry.getValue().entrySet()) {
                Symbol symbol = transition.getKey();
                State to = transition.getValue();

                if (!inputAlphabet.contains(symbol)) {
                    throw new IllegalStateException("Symbol '" + symbol + "' v přechodu není ve vstupní abecedě.");
                }
                if (!states.contains(to)) {
                    throw new IllegalStateException("Cílový stav '" + to + "' v přechodu není v množině stavů.");
                }
            }
        }
    }
}
