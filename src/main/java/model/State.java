package model;

import java.util.Objects;

/**
 * Reprezentuje jeden stav deterministického konečného automatu.
 * Každý stav je jednoznačně identifikován svým id (např. "A", "q0", "F").
 */
public class State {
    private final String id;

    /**
     * Vytvoří nový stav s daným id.
     *
     * @param id jednoznačný název stavu
     */
    public State(String id) {
        this.id = id;
    }

    /**
     * Vrací id stavu jako řetězec.
     *
     * @return id stavu
     */
    @Override
    public String toString() {
        return id;
    }

    /**
     * Určuje rovnost stavů na základě jejich id.
     *
     * @param o objekt ke srovnání
     * @return {@code true}, pokud mají stavy stejný id
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(id, state.id);
    }

    /**
     * Vrací hash kód založený na id stavu.
     *
     * @return hash kód
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
