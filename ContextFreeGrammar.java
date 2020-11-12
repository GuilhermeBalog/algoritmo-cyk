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
        input = input.trim();
        if(input.equals(emptyString))
            return doesEmptyProductionExists();

            
        String[] w = input.split(" ");
        String[][] table = buildComputeTable(w);
        
        System.out.println("\nCOMEÇO DA ANÁLISE DA CADEIA: " + input + "(tamanho "+w.length+")");

        System.out.println("Initial Table");
        printTable(table);
        
        examineEachSubStringOfLengthOne(w, table);    

        System.out.println("Table after first examine");
        printTable(table);

        examineOtherSubStrings(w, table);

        System.out.println("Table after last examine");
        printTable(table);

        System.out.println("FIM DA ANÁLISE\n");

        return (hasSymbol(table[0][w.length - 1], initialVariable));
    }

    private String[][] buildComputeTable(String[] w) {
        String[][] table = new String[w.length][w.length];

        for(int i = 0; i < table.length; i++)
            for (int j = 0; j < table[i].length; j++)
                table[i][j] = "";
        
        return table;
    }

    private void printTable(String[][] table) {
        // String[][] table = new String[w.length][w.length];

        for(int i = 0; i < table.length; i++){
            for (int j = 0; j < table[i].length; j++)
                System.out.print("["+table[i][j]+"]");
            System.out.println();
        }
    }

    private boolean doesEmptyProductionExists() {
        for (List<String> rule : productionRules)
            if(rule.get(0).equals(initialVariable))
                for (String symbol : rule) 
                    if(symbol.equals(emptyString))
                        return true;
        return false;
    }

    private void examineEachSubStringOfLengthOne(String[] w, String[][] table) {
        for (int i = 0; i < w.length; i++) 
            for(String A : variables)
                for (List<String> rule : productionRules)
                    if(rule.get(0).equals(A))
                        for(int j = 1; j < rule.size(); j++)
                            if(rule.get(j).equals(w[i]))
                                table[i][i] = A;
    }

    private void examineOtherSubStrings(String[] w, String[][] table) {
        for(int l = 1; l < w.length; l++){
            for(int i = 0; i < w.length - l + 1; i++){
                int j = i + l - 1;

                for(int k = i; k < j - 1; k++)
                    for (List<String> rule : productionRules)
                        if(rule.size() == 3){
                            // A -> BC
                            String A = rule.get(0);
                            String B = rule.get(1);
                            String C = rule.get(2);
                            
                            if(hasSymbol(table[i][k], B) && hasSymbol(table[k + 1][j], C))
                                table[i][j] = (table[i][j] + " " + A).trim();
                        }
            }
        }
    }

    private boolean hasSymbol(String tableCell, String search) {
        String[] symbols = tableCell.trim().split(" ");
        
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
