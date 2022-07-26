// packages
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;  
import javax.crypto.CipherInputStream;
import java.awt.event.*;
import javax.swing.*;

// class containing GUI and encrypt and decrypt functions
class miniProject extends JFrame implements ActionListener{
    JButton b1 = new JButton("Encrypt");
    JButton b2 = new JButton("Decrypt");
    JButton b3 = new JButton("Choose file");
    JLabel l1 = new JLabel("Encryption Key:");
    JTextField t1 = new JTextField(32);
    JLabel l2 = new JLabel("Decryption Key:");
    JTextField t2 = new JTextField(32);
    Cipher cipher;
    Key key;

    // initializing GUI
    miniProject() throws NoSuchAlgorithmException, NoSuchPaddingException{
        cipher=Cipher.getInstance("AES");
        setTitle("Image Crytography");
        b1.setBounds(30, 40, 85, 30);
        add(b1);
        l1.setBounds(150, 15, 200, 30);
        add(l1);
        t1.setBounds(150, 40, 200, 30);
        add(t1);
        b2.setBounds(30, 100, 85, 30);
        add(b2);
        t2.setBounds(150, 100, 200, 30);
        add(t2);
        l2.setBounds(150, 75, 200, 30);
        add(l2);
        add(b3);
        setVisible(true);
        setSize(400, 210);
        setLayout(null);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    // function for encryption
    public void encrypt(File file, Key key) throws InvalidKeyException, FileNotFoundException, IOException{
        cipher.init(Cipher.ENCRYPT_MODE, key); 
        CipherInputStream cipt=new CipherInputStream(new FileInputStream(file), cipher);
        FileOutputStream fileip=new FileOutputStream(new File("C:\\Users\\USER\\OneDrive\\Desktop\\College\\Mini Project\\Image Cryptography\\encrypted.jpeg"));
        int i;
        while((i=cipt.read())!=-1){
            fileip.write(i);
        }
        JOptionPane.showMessageDialog(this, "File Encrypted Successfully!");
    }

    // function for decryption
    public void decrypt(File file, Key key) throws InvalidKeyException, FileNotFoundException, IOException{
        cipher.init(Cipher.DECRYPT_MODE, key);
        CipherInputStream ciptt=new CipherInputStream(new FileInputStream(file), cipher);
        FileOutputStream fileop=new FileOutputStream(new File("C:\\Users\\USER\\OneDrive\\Desktop\\College\\Mini Project\\Image Cryptography\\decrypted.jpeg"));
        int j;
        while((j=ciptt.read())!=-1)
        {
            fileop.write(j);
        }
        JOptionPane.showMessageDialog(this, "File Decrypted Successfully!");
    }

    // handling input and output operations in GUI
    public void actionPerformed(ActionEvent e){
        String s1=t1.getText(), s2=t2.getText();
        try{
            if(e.getSource()==b1){
                if(t1.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter Key!");
                }
                else{
                    s1 = t1.getText();
                    key = new SecretKeySpec(s1.getBytes(), "AES");
                    JFileChooser fc=new JFileChooser();
                    fc.showOpenDialog(null);
                    File file = fc.getSelectedFile();
                    encrypt(file, key);
                }
            }
            if(e.getSource()==b2){
                if(t2.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter Key!");
                }
                else{
                    s2 = t2.getText();
                    if(s2.equals(s1)){
                        JFileChooser fc2 = new JFileChooser();
                        fc2.showOpenDialog(null);
                        File file2 = fc2.getSelectedFile();
                        decrypt(file2, key);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Incorrect Key!");
                    }
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

public class code{
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
        miniProject m = new miniProject();
    }
}