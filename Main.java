import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    //Scanner scanner = new Scanner(System.in);
    //System.out.println("Write the name of cocktail: ");
    String cocktailName = "vodka";//scanner.nextLine();
    StringBuilder json = new StringBuilder();
        try {
            getCocktail(cocktailName, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            ImageParser.saveImage(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        IngridientsParser.saveIngredients(json);
    }

    public static void getCocktail(String cocktailName, StringBuilder json) throws IOException {
        URLConnection uc;
        URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + cocktailName);
        uc = url.openConnection();
        uc.addRequestProperty("User-Agent", "Yandex-Browser");
        uc.connect();
        json.append("[ ");
        try (InputStreamReader in = new InputStreamReader(uc.getInputStream())) {
            int b;
            while ((b = in.read()) != -1) {
                json.append((char) b);
            }
            json.append(" ]");
        } catch(IOException ex) {
            throw new IOException("Some problems with reading/writing", ex);
        }
    }
}
