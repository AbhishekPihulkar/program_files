public class pass1 {
    public static void main(String[] args) {
        String[] code = {
            "START 100",
            "LOOP MOVER AREG, ='5'",
            "ADD BREG, ='2'",
            "LOOP2 SUB AREG, ONE",
            "STOP",
            "ONE DC 1",
            "END"
        };

        String[] symbols = new String[20];
        int[] symAddr = new int[20];
        int symCount = 0;

        String[] literals = new String[20];
        int[] litAddr = new int[20];
        int litCount = 0;

        int locCounter = 0;

        System.out.println("Intermediate Code:");

        for (String line : code) {
            String[] parts = line.trim().split("\\s+");

            if (parts[0].equals("START")) {
                locCounter = Integer.parseInt(parts[1]);
                System.out.println("(AD,01) (C," + parts[1] + ")");
                continue;
            }

            if (parts[0].equals("END")) {
                System.out.println("(AD,02)");
                break;
            }

            if (!isOpcode(parts[0])) {
                symbols[symCount] = parts[0];
                symAddr[symCount] = locCounter;
                symCount++;
                // Remove label from line for further processing
                line = line.substring(line.indexOf(' ') + 1).trim();
                parts = line.split("\\s+");
            }

            System.out.print(locCounter + "\t");
            System.out.print(parts[0] + "\t");

            if (parts.length > 1) {
                System.out.print(parts[1] + "\t");

                // Handle literals correctly
                if (parts[1].contains("=")) {
                    String lit = parts[1].substring(parts[1].indexOf('='));
                    int existingIndex = findLiteral(literals, litCount, lit);
                    if (existingIndex == -1) {
                        literals[litCount] = lit;
                        litAddr[litCount] = -1;
                        System.out.print("(L," + litCount + ")");
                        litCount++;
                    } else {
                        System.out.print("(L," + existingIndex + ")");
                    }
                }
            } else {
                System.out.print("\t");
            }

            System.out.println();

            locCounter++;
        }

        // Assign addresses to literals after code
        for (int i = 0; i < litCount; i++) {
            litAddr[i] = locCounter;
            locCounter++;
        }

        // Print Symbol Table
        System.out.println("\nSymbol Table:");
        for (int i = 0; i < symCount; i++)
            System.out.println(symbols[i] + "\t" + symAddr[i]);

        // Print Literal Table
        System.out.println("\nLiteral Table:");
        for (int i = 0; i < litCount; i++)
            System.out.println(literals[i] + "\t" + litAddr[i]);
    }

    static boolean isOpcode(String word) {
        String[] opcodes = {"MOVER", "ADD", "SUB", "STOP"};
        for (String op : opcodes) {
            if (word.equals(op))
                return true;
        }
        return false;
    }

    static int findLiteral(String[] lits, int count, String lit) {
        for (int i = 0; i < count; i++) {
            if (lits[i].equals(lit))
                return i;
        }
        return -1;
    }
}