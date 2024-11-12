import java.io.*;
class SymTab {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\aasth\\Downloads\\input.txt"));
        String line;
        int lineCount = 0, LC = 0, symTabLine = 0, opTabLine = 0, litTabLine = 0, poolTabLine = 0;
        final int MAX = 100;
        String[][] SymbolTab = new String[MAX][3];
        String[][] OpTab = new String[MAX][3];
        String[][] LitTab = new String[MAX][2];
        int[] PoolTab = new int[MAX];
        System.out.println("___________________________________________________");
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split("\t");
            if (lineCount++ == 0) {
                LC = Integer.parseInt(tokens[1]);  // Set LC to operand of START
            } else {
                if (!tokens[0].isEmpty()) {  // Symbol table entry
                    SymbolTab[symTabLine++] = new String[]{tokens[0], Integer.toString(LC), "1"};
                } else if (tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC")) {
                    SymbolTab[symTabLine++] = new String[]{tokens[0], Integer.toString(LC), "1"};
                }
                if (tokens.length == 3 && tokens[2].startsWith("=")) {
                    LitTab[litTabLine++] = new String[]{tokens[2], Integer.toString(LC)};
                }
                if (tokens[1] != null) {
                    OpTab[opTabLine][0] = tokens[1];
                    OpTab[opTabLine][1] = switch (tokens[1].toUpperCase()) {
                        case "START", "END", "ORIGIN", "EQU", "LTORG" -> "AD";
                        case "DS", "DC" -> "DL";
                        default -> "IS";
                    };
                    OpTab[opTabLine][2] = tokens[1].matches("DS|DC") ? "R7" : "(04,1)";
                    opTabLine++;
                }
            }
            LC++;
        }
        System.out.println("___________________________________________________");
        printTable("SYMBOL TABLE", "SYMBOL\tADDRESS\tLENGTH", SymbolTab, symTabLine);
        printTable("OPCODE TABLE", "MNEMONIC\tCLASS\tINFO", OpTab, opTabLine);
        printTable("LITERAL TABLE", "LITERAL\tADDRESS", LitTab, litTabLine);
        for (int i = 0; i < litTabLine - 1; i++) {
            if (LitTab[i][0] != null && LitTab[i + 1][0] != null && 
                (i == 0 || Integer.parseInt(LitTab[i][1]) < Integer.parseInt(LitTab[i + 1][1]) - 1)) {
                PoolTab[poolTabLine++] = i + 1 + (i > 0 ? 1 : 0);
            }
        }
        printPoolTable(PoolTab, poolTabLine);
        br.close();
    }
    private static void printTable(String title, String headers, String[][] table, int lines) {
        System.out.println("\n\n " + title);
        System.out.println("--------------------------");
        System.out.println(headers);
        System.out.println("--------------------------");
        for (int i = 0; i < lines; i++)
            System.out.println(String.join("\t", table[i]));
        System.out.println("--------------------------");
    }
    private static void printPoolTable(int[] PoolTab, int lines) {
        System.out.println("\n\n POOL TABLE ");
        System.out.println("-----------------");
        System.out.println("LITERAL NUMBER");
        System.out.println("-----------------");
        for (int i = 0; i < lines; i++)
            System.out.println(PoolTab[i]);
        System.out.println("------------------");
    }
}
