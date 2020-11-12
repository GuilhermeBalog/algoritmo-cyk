import java.util.List;

public class ContextFreeGrammar {
    public static final String emptyString = "&";

    private String[] variables;
    private String[] terminalSymbols;
    private List<String>[] productionRules;
    private String initialVariable;

    ContextFreeGrammar(String[] variables, String[] terminalSymbols, List<String>[] productionRules) {
        this.variables = variables;
        this.terminalSymbols = terminalSymbols;
        this.productionRules = productionRules;
        this.initialVariable = variables[0];
    }

    public boolean validate(String input){
        if(input.equals(emptyString)){
            return doesEmptyProductionExists();
        }
        
        String[] splitInput = input.split(" ");
        String[][] table = new String[splitInput.length][splitInput.length];

        for (String symbol : splitInput) {
            // seguir o algoritmo
        }        

        return true;
    }

    private boolean doesEmptyProductionExists() {
        /*String[] productions = this.productionRules.get(initialVariable);

        for (String production : productions) 
            if(production.equals(emptyString))
                return true;*/

        return false;
    }

    public String toString(){
        String output = "Variables: ";
        for (String variable : variables) {
            output += variable + " ";
        }
        output += "\nTerminal Symbols: ";
        for (String terminalSymbol : terminalSymbols) {
            output += terminalSymbol + " ";
        }
        output += "\nProduction Rules: " + productionRules;

        return output;
    }
}
