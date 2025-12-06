package Bankmanagementsystem;

import javax.print.*;
import javax.print.attribute.*;
import javax.swing.*;
import java.awt.print.*;

public class PrintUtils {

    public static void printTextArea(JTextArea area) throws Exception {
        boolean done = area.print();
        // area.print() shows native print dialog
        if (!done) throw new Exception("Print job was cancelled or failed.");
    }
}
