import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnpackTest {
    @Test
    public void testDefaultUnpackString(){

        String result11 = "3[xyz]4[xy]z";
        String expected1 = "xyzxyzxyzxyxyxyxyz";
        String result22 = "2[3[x]y]";
        String expected2 = "xxxyxxxy";

        assertEquals(Unpack.unpackString(result11), expected1);
        assertEquals(Unpack.unpackString(result22), expected2);
    }



    @Test
    public void testValidSymbols(){

        String randomSymbols = "wwqqwlo";
        String oneSymbol = "a";
        String oneCapsSymbol = "O";
        String capsSymbols = "ASPSDLKKO";


        assertTrue(Unpack.isValid(randomSymbols));
        assertTrue(Unpack.isValid(oneSymbol));
        assertTrue(Unpack.isValid(oneCapsSymbol));
        assertTrue(Unpack.isValid(capsSymbols));
    }

    @Test
    public void testInvalidSymbolsWithBracketsAndDigits(){

        String symbolsWithDigit = "6swqs";
        String symbolsWithBrackets = "asdas[asd]asd";
        String symbolsWithDigitsAndBrackets = "asdwqa32aswq[wqwq]q2wqws";
        assertFalse(Unpack.isValid(symbolsWithDigit));
        assertFalse(Unpack.isValid(symbolsWithBrackets));
        assertFalse(Unpack.isValid(symbolsWithDigitsAndBrackets));

    }

}
