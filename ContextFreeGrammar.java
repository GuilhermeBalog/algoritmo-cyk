/**
 * @author Gabriel de Castro Michelassi - 11208162
 * @author Guilherme Balog Gardino - 11270649
*/

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
        String[] w = parseInputString(input);
        String[][] table = buildComputeTable(w);

        if(w[0].equals(emptyString))
            return doesEmptyProductionExists();
        
        examineEachSubStringOfLengthOne(w, table);    
        examineOtherSubStrings(w, table);

        return (hasSymbol(table[0][w.length-1], initialVariable));
    }

    private String[] parseInputString(String input){
        input = input.trim();
        return input.split(" ");
    }

    private String[][] buildComputeTable(String[] w) {
        String[][] table = new String[w.length][w.length];

        for(int i = 0; i < table.length; i++)
            for (int j = 0; j < table[i].length; j++)
                table[i][j] = "";
        
        return table;
    }

    private void printTable(String[][] table) {
        for(int i = 0; i < table.length; i++){
            for (int j = 0; j < table[i].length; j++)
                System.out.print("["+table[i][j]+"]");
            System.out.println();
        }
    }

    private String vectorToString(String[] vector){
        String result = "";
        for(int i = 0; i < vector.length; i++){
            result += "["+vector[i]+"]";
        }
        return result;
    }

    private boolean doesEmptyProductionExists() {
        for (List<String> rule : productionRules)
            if(rule.get(0).equals(initialVariable))
                if(rule.get(1).equals(emptyString))
                    return true;
        return false;
    }

    private void examineEachSubStringOfLengthOne(String[] w, String[][] table) {
        for (int i = 0; i < w.length; i++)
            for(String A : variables)
                for (List<String> rule : productionRules)
                    if(rule.get(0).equals(A))
                        if(rule.size() == 2)
                            if(rule.get(1).equals(w[i]))
                                table[i][i] = A;
    }

    private void examineOtherSubStrings(String[] w, String[][] table) {
        for(int l = 2; l <= w.length; l++){ // tamanho da substring a ser analisada
            for(int i = 0; i < w.length - l + 1; i++){ // [a][b][a][a][b][b]
                int j = i + l - 1;

                for(int k = i; k <= j - 1; k++){ // a b a = AB a ||  a aBA
                    for (List<String> rule : productionRules){
                        if(rule.size() == 3){
                            // A -> BC
                            String A = rule.get(0);
                            String B = rule.get(1);
                            String C = rule.get(2);

                            if(hasSymbol(table[i][k], B) && hasSymbol(table[k + 1][j], C)){
                                table[i][j] = (table[i][j] + " " + A).trim();
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean hasSymbol(String tableCell, String search) {
        String[] symbols = tableCell.trim().split(" "); // "A B"
        
        for(String symbol : symbols)
            if(symbol.equals(search))
                return true;

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
