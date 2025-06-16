# Konvertor JFLAP → DKAR

Krátký přehled struktury a build instrukce.

## Osobní údaje

Jméno a příjmení: `Vitalij Atamanjuk`
Osobní číslo: `A23B0136P`

## Struktura projektu

- `pom.xml`  
  Maven konfigurace (Java 21, Jackson XML, Woodstox, Assembly plugin)

- `src/main/java/`  
  - `parser/` – `JflapParser`  
  - `converter/` – `Converter`  
  - `formatter/` – `DkarFormatter`  
  - `Main.java` – vstupní třída
  - `Model/` - Model Automatu

- `docs/`  
  LaTeX dokumentace (pdf)

## Klonování a build
```bash
git clone https://github.com/VitaliyAtmnk/TI.git
cd TI/SP
mvn clean package
```
## Výsledný JAR:
target/SP-1.0-jar-with-dependencies.jar

### Spuštění
```bash
java -jar target/SP-1.0-jar-with-dependencies.jar <vstup.jff> <vystup.dkar>
