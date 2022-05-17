import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IngridientsParser {
    public IngridientsParser() {
    }

    public static void saveIngredients(StringBuilder json) {
        File file = new File("C:\\Users\\muzik\\Desktop\\kr\\prop");
        Properties props = new Properties();

        Pattern IngredientPattern = Pattern.compile("(strIngredient[0-9]{1}[\"]+[:]+)[\"\\w]+");
        Matcher IngredientMatcher = IngredientPattern.matcher(json);


        ArrayList<String> al = new ArrayList<>();
        int count = 0;
        while (IngredientMatcher.find()){
            al.add(count, IngredientMatcher.group(count));
            count++;
        }

        Pattern amountPattern = Pattern.compile("(strMeasure[\\d]+)\":[\\w]+");
        Matcher amountMatcher = amountPattern.matcher(json);

        ArrayList<String> al1 = new ArrayList<>();
        int i = 0;
        while (amountMatcher.find()){
            al1.add(count, amountMatcher.group(i));
            i++;
        }

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
             DataInputStream in = new DataInputStream(new FileInputStream(file))) {
            props.load(in);
            propertySetter(props, al, al1);
            props.store(out, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void propertySetter(Properties props, ArrayList<String> al, ArrayList<String> al1) {
        int count = 0;
            for (String str : al) {
                props.setProperty(al.get(count), al1.get(count));
                count++;
            }
    }
}
