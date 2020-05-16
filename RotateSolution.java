class Rotate {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide a number and a ',' separated list of strings.");
        }
        else {
            int n = Integer.valueOf(args[0]);
            String[] arr = args[1].split(",");

            // Print elements `n` and onwards
            for (int i = n; i < arr.length; i += 1) {
                System.out.println(arr[i]);
            }

            // Print elements up to `n`
            for (int i = 0; i < n; i += 1) {
                System.out.println(arr[i]);
            }
        }
    }
}