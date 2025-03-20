import java.awt.*;
public class Driver
{
    public static void main(String[] args)
    {
        String title = String.format("Java Color Chooser Demo using java version %s", getJavaVersion());
        System.out.println(title);
        Dimension scaledSize = SwingScreenUtilities.getScaledSize(.45, 10);
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(
                new ColorChooserDemo(title, scaledSize)
        );
    }

    private static String getJavaVersion()
    {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }


}
