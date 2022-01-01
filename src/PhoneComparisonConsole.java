public class PhoneComparisonConsole {
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir") + "\\Smartphones\\";
        SCS system = new SCS(directory);

        system.Run();
    }
}
