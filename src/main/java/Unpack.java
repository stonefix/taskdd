import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unpack {

    public static String unpackString(String line){
        String regex = "\\d+" +"\\[" + "[a-zA-Z\\d+]+" + "\\]";
        String result = line;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            result = matcher.replaceFirst(unpackSubString(matcher.group(0)));
            matcher = pattern.matcher(result);
        }
        return result;
    }

    public static String unpackSubString(String line) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        int number = Integer.parseInt(matcher.group());
        String regex = "\\[" + "[a-zA-Z\\d+]+" + "\\]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(line);
        matcher.find();
        String result = line.substring(matcher.start()+1, matcher.end()-1);
        return repeat(result, number);
    }

    public static String repeat(String str, int count) {
        return count > 0 ? str + repeat(str, --count) : "";
    }

    public static boolean isValid(String line){
        if (checkSymbols(line)) {
            return true;
        }
        if (checkSymbols(line) && digitBeforeBracket(line) && isValidBracketsCount(line)) {
            return true;
        }

        return false;
    }

    public static boolean checkSymbols(String line){
        String regex = "[a-zA-Z]+";
        return line.matches(regex);
    }

    public static boolean digitBeforeBracket(String line){
        String regex = "\\d+" + "\\[";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    public static boolean isValidBracketsCount(String line){
        if (line.isEmpty()) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        bracketsChecker(stack, line, '[', ']');
        return stack.empty();
    }

    public static void bracketsChecker(Stack<Character> stack, String line, char bracket1, char bracket2){

        char[] charArray = line.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char element = charArray[i];
            if (element == bracket1) {
                stack.push(element);
            }
            if (element == bracket2 && !stack.empty() && stack.peek() == bracket1){
                stack.pop();
            } else if (element == bracket2 && stack.empty()) {
                stack.push(element);
            }
        }

}

}
