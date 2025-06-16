package model;

import java.util.Objects;

/**
 * Reprezentuje vstupní symbol automatu.
 * Symbol je jednoznačně identifikován svým řetězcovým obsahem (např. "a", "0", ε).
 */
public class Symbol {
    private final String symbol;

    /**
     * Vytvoří nový symbol s daným řetězcovým označením.
     *
     * @param symbol název symbolu (nesmí být {@code null})
     */
    public Symbol(String symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol nesmí být null");
        }
        this.symbol = symbol;
    }

    /**
     * Vrací textovou reprezentaci symbolu.
     *
     * @return název symbolu
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Vrací název symbolu pro výpis.
     *
     * @return název symbolu
     */
    @Override
    public String toString() {
        return symbol;
    }

    /**
     * Určuje rovnost symbolů na základě jejich názvu.
     *
     * @param o objekt ke srovnání
     * @return {@code true}, pokud mají symboly stejný řetězec
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        return Objects.equals(symbol, symbol1.symbol);
    }

    /**
     * Vrací hash kód symbolu.
     *
     * @return hash kód založený na textu symbolu
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(symbol);
    }
}
