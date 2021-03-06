import java.util.Random;

public class ComputePi {

    private static Random rand;
    
    public static void main(String[] args) {
        if (args.length > 1)
            StdOut.println("Invalid argument: only one argument is accepted.");
        else if (!isNumeric(args[0]))
            StdOut.println("Invalid argument: please enter a positive integer.");
        else {
            int arg = Integer.parseInt(args[0]);
            if (arg <= 0) 
                StdOut.println("Invalid argument: please enter a positive integer.");
            else 
                StdOut.println(compute_pi(arg));
        }
    }
    
    private static double compute_pi (int n) {
        if (rand == null) rand = new Random();
        int inside = 0;
        for (int i = 1; i < n; i++) {
            double x = rand.nextDouble(),
                   y = rand.nextDouble();
            if (Math.sqrt(x*x+y*y) <= 1)
                inside++;
        }
        return 4.0*inside/n;
    }
    
    private static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
