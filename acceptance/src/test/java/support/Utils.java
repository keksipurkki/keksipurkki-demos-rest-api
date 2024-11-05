package support;

import java.util.HexFormat;
import java.util.Random;

public class Utils {

    public static String randomString(int numBytes) {
        var format = HexFormat.of();
        var bytes = new byte[numBytes];
        new Random().nextBytes(bytes);
        return format.formatHex(bytes);
    }
}
