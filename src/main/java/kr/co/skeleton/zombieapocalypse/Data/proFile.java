package kr.co.skeleton.zombieapocalypse.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class proFile {

    public static void Addp(String file, UUID u, String txt) {
        String Uid = u.toString();

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prop.setProperty(Uid, txt);

        try {
            prop.save(new FileOutputStream(file), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String Loadp(String file, UUID u) {
        Properties prop = new Properties();
        String Uid = u.toString();

        try {
            prop.load(new FileInputStream(file));

            String result = prop.getProperty(Uid);
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
