/**
 * PasswordCracker is a multithreaded brute-force password cracking program
 * designed to find the password of a ZIP file using the Zip4j library.
 * <p>
 * The program generates all possible combinations of a given character set
 * and password length, distributes them to multiple threads, and attempts
 * to extract the contents of the target ZIP file to validate each password.
 * </p>
 * <p>
 * This implementation uses a fixed thread pool and a blocking queue to
 * manage tasks. Once the correct password is found, all threads stop processing.
 * </p>
 * 
 * <p><b>Note:</b> This example assumes small passwords and is for
 * educational purposes only. Brute-forcing larger passwords is
 * computationally intensive and may not finish in reasonable time.</p>
 * 
 * <p>Dependencies:</p>
 * <ul>
 *   <li>Zip4j library (https://github.com/srikanth-lingala/zip4j)</li>
 * </ul>
 * 
 * @author Kevin Turcios
 * @version 1.0
 */
public class PasswordCracker {

    /** Flag indicating whether the password has been found */
    private static volatile boolean passwordFound = false;

    /** Number of threads in the thread pool */
    private static final int numThreads = 6;

    /** Absolute path to the target ZIP file */
    private static final String TARGET_ZIP = "/Users/kevinturcios/Downloads/Computer Science/Final/demo/src/main/java/protected3.zip";

    /** Character set used for password generation */
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /** Thread pool for running password cracking tasks */
    private static final ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

    /** Queue containing password candidates */
    private static final BlockingQueue<String> taskQueue = new LinkedBlockingQueue<>();

    /** Length of the password to crack */
    private static final int PASSWORD_LENGTH = 3;

    /**
     * Main entry point of the program.
     * Initializes the task queue, starts worker threads, and waits for
     * completion of all password cracking tasks.
     * 
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Starting password cracking...");

        long startTime = System.currentTimeMillis();

        loadTasks(PASSWORD_LENGTH);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(new PasswordCrackingTask(startTime));
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
                System.out.println("Timeout reached, shutting down...");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Password cracking process completed.");
    }

    /**
     * Generates all possible password combinations of the specified length
     * from the character set and adds them to the task queue.
     * 
     * @param length The length of the passwords to generate
     */
    private static void loadTasks(int length) {
        int totalCombinations = (int) Math.pow(CHARACTERS.length, length);
        char[] currentPassword = new char[length];
        for (int i = 0; i < totalCombinations; i++) {
            int tempIndex = i;
            for (int j = length - 1; j >= 0; j--) {
                currentPassword[j] = CHARACTERS[tempIndex % CHARACTERS.length];
                tempIndex /= CHARACTERS.length;
            }
            taskQueue.offer(new String(currentPassword));
        }
    }

    /**
     * Attempts to extract the ZIP file using the provided password.
     * 
     * @param zipFilePath The path to the ZIP file
     * @param password The candidate password
     * @return true if the password successfully opens the ZIP, false otherwise
     */
    private static boolean tryPassword(String zipFilePath, String password) {
        if (Thread.interrupted()) {
            return false;
        }
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            zipFile.setPassword(password.toCharArray());
            zipFile.extractFile(zipFile.getFileHeaders().get(0), "output");
            System.out.println("Password found: " + password);
            return true;
        } catch (ZipException e) {
            // Ignore exceptions for incorrect passwords
        }
        return false;
    }

    /**
     * Represents a worker task that takes passwords from the queue
     * and attempts to crack the ZIP file.
     */
    private static class PasswordCrackingTask implements Runnable {

        /** Timestamp when cracking started */
        private final long startTime;

        /**
         * Constructs a PasswordCrackingTask.
         * 
         * @param startTime Timestamp when password cracking started
         */
        public PasswordCrackingTask(long startTime) {
            this.startTime = startTime;
        }

        /**
         * Takes passwords from the queue and tries to unlock the ZIP file.
         * Stops when the correct password is found or the thread is interrupted.
         */
        @Override
        public void run() {
            while (!passwordFound && !Thread.currentThread().isInterrupted()) {
                try {
                    String password = taskQueue.take();
                    if (passwordFound || Thread.currentThread().isInterrupted()) {
                        break;
                    }

                    if (tryPassword(TARGET_ZIP, password)) {
                        passwordFound = true;
                        long endTime = System.currentTimeMillis();
                        long elapsedTime = endTime - startTime;
                        System.out.println("Password found: " + password + " in " + elapsedTime + " milliseconds");
                        break;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
