public class FSClient {

    public static void main(String[] args) {
        int arg = parseArg(args);
        if (arg != -1) {
            FixedSizedStack<String> stack = new FixedSizedStack<String>(parseArg(args));
            while (!StdIn.isEmpty()) {
                String item = StdIn.readString();
                if (!item.equals("-"))
                    stack.push(item);
                else if (!stack.isEmpty())
                    StdOut.print(stack.pop() + " ");
            }
            StdOut.println("(" + stack.size() + " left on stack)");
        }
        else
            StdOut.println("Invalid argument(s); positive integer expected.");
    }

    private static int parseArg(String[] args) {
        try {
            int arg = Integer.parseInt(args[0]);
            return (arg >= 0) ? arg: -1;
        } 
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }
}
