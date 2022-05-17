import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageParser {
    public ImageParser() {
    }

    public static void saveImage(StringBuilder json) throws IOException {
        Pattern namePattern = Pattern.compile("/([strDrink\"])+([A-z]+)/gm");
        Matcher nameMatcher = namePattern.matcher(json);

        String name = "";
        if (nameMatcher.find()){
            name = nameMatcher.group(2);
        }

        Pattern imagePattern = Pattern.compile("/(strDrinkThumb\":\")(https:\\\\\\/\\\\\\/www.thecocktaildb.com\\\\\\/images\\\\\\/media\\\\\\/drink\\\\\\/[\\w]+.[A-z]+)/gm");
        Matcher imageMatcher = imagePattern.matcher(json);

        String image = "";
        if (imageMatcher.find()){
            image = imageMatcher.group(2);
        }
//        System.out.println(imageURL);
        File imageFile = new File("C:\\Users\\muzik\\Desktop\\5.jpg");

        URL url = new URL(image);
        URLConnection uc = url.openConnection();
        //uc.addRequestProperty("User-Agent", "Opera");
        uc.connect();
        try (DataInputStream in = new DataInputStream(uc.getInputStream());
             DataOutputStream out = new DataOutputStream(new FileOutputStream(imageFile))) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } catch(IOException ex) {
            throw new IOException("Some problems with reading/writing", ex);
        }
    }
}
