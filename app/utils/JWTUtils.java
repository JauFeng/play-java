package utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public class JWTUtils {

    // TODO : We need a signing key, so we'll create one just for this example.
    // Usually the key would be read from your application configuration instead.
    private final static Key key = MacProvider.generateKey();

    public static String encode(String json) {
        return Jwts.builder()
                .setPayload(json)
                .signWith(SignatureAlgorithm.HS512, key).compact();
    }

    public static String decode(String encode) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(encode).getBody().toString();
//        return Jwts.parser().setSigningKey(key).parseClaimsJws(encode).getBody().getSubject();
    }


    public static void main(String[] args) {

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjEwMDAzLCJyb2xlTmFtZSI6Im5ubm4iLCJkZXZpY2VJZCI6IiIsInBob25lTnVtYmVyIjoiMTgyMTAxMzQ2MTEiLCJtYWlsQWRkcmVzcyI6bnVsbCwicGFzc3dvcmQiOiIxMjM0NTYiLCJsZXZlbCI6MSwiZXhwIjowLCJzaWduYXR1cmUiOm51bGwsInBvcnRyYWl0IjoiMiIsInNleCI6MCwiYmlydGhEYXkiOm51bGwsInJvbGVDcmVhdGVUaW1lIjoxNDYzNjIzMTM1MDAwLCJhZGRyZXNzIjpudWxsfQ.MKu6IBHdXZ4ZiyLmrmkS1NPBzqHFWh-P-90t_zvhFO6ETUuR0g2W91i-QXdLm2yjTPwu7ktTKC2SQVqPKpQ7QQ";

//        String encode = encode(json);

//        System.out.println(encode);
        System.out.println(decode(token));
    }
}
