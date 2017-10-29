public class Subset {

    public static void main(String[] args) {
        int k = parseArgs(args);
        if (k != -1) {
            RandomizedBag<String> bag = new RandomizedBag<String>();
            while (!StdIn.isEmpty())
                bag.put(StdIn.readString());
            for (int i = 0; i < k; i++)
                StdOut.println(bag.get());
        } 
        else StdOut.println("Invalid argument: positive integer expected.");
    }

    private static int parseArgs(String[] args) {
        try {
            if (args.length < 1)
                return -1;
            int k = Integer.parseInt(args[0]);
            if (k < 0)
                return -1;
            else
                return k;
        } 
        catch (NumberFormatException e) {
            return -1;
        }
    }
}
