import java.util.regex.*;

public class test {

    public static void main(String[] args) {
        
        String number = "-?\\b\\d+\\b";

        Pattern p = Pattern.compile(number);
        Matcher m = p.matcher("alisson faloola 90927738");

        m.find();

        String found = m.group();

        System.out.println(found);

    }
    
}
