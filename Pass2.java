import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
public class Pass2 {
    public static void main(String[] args) throws IOException {
        BufferedReader b1 = new BufferedReader(new FileReader("C:\\Users\\aasth\\Downloads\\intermediate.txt"));
        BufferedReader b2 = new BufferedReader(new FileReader("C:\\Users\\aasth\\Downloads\\symtab.txt"));
        BufferedReader b3 = new BufferedReader(new FileReader("C:\\Users\\aasth\\Downloads\\littab.txt"));
        FileWriter f1 = new FileWriter("C:\\Users\\aasth\\Downloads\\Pass2.txt");
        HashMap<Integer, String> symSymbol = new HashMap<>();
        HashMap<Integer, String> litAddr = new HashMap<>();
        int index = 1;
        String line;
        while ((line = b2.readLine()) != null) {
            String[] parts = line.split("\t+");
            if (parts.length >= 1) symSymbol.put(index++, parts[0]);
        }
        index = 1;
        while ((line = b3.readLine()) != null) {
            String[] parts = line.split("\t+");
            if (parts.length >= 2) litAddr.put(index++, parts[1]);
        }
        while ((line = b1.readLine()) != null) {
            String output;
            if (line.substring(1, 6).equals("IS,00")) {
                output = "+ 00 0 000\n";
            } else if (line.startsWith("(IS,")) {
                int offset = line.charAt(9) == ')' ? 3 : 0;
                String operand = line.charAt(8 + offset) == 'S' 
                                 ? symSymbol.get(Integer.parseInt(line.substring(10 + offset, line.length() - 1)))
                                 : litAddr.get(Integer.parseInt(line.substring(10 + offset, line.length() - 1)));
                output = "+ " + line.substring(4, 6) + " " + (offset > 0 ? line.charAt(8) : '0') + " " + operand + "\n";
            } else if (line.substring(1, 6).equals("DL,01")) {
                String s1 = String.format("%03d", Integer.parseInt(line.substring(10, line.length() - 1)));
                output = "+ 00 0 " + s1 + "\n";
            } else {
                output = "\n";
            }
            f1.write(output);
        }
        b1.close();
        b2.close();
        b3.close();
        f1.close();
    }
}
