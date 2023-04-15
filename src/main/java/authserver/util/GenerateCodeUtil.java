package authserver.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {
    /**
     * 1000 ~ 9999 (4자리 임의 코드) 사이의 값을 얻는다
     *
     * @return
     */
    public static String generateCode() {
        String code;
        // 임의의 int 값을 생성하는 SecureRandom 의 인스턴스를 얻는다
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int c = random.nextInt(9000) + 1000;
            code = String.valueOf(c);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return code;
    }
}
