package com.aula.joaoJava.Utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Base64;

public class AuthUtil {
    public static String extractUsername(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Basic ")) {
            return null;
        }
        var authEncode = authorization.substring("Basic".length()).trim();
        byte[] authDecode = Base64.getDecoder().decode(authEncode);
        var authString = new String(authDecode);
        String[] credenciais = authString.split(":");
        return credenciais[0];
    }
}
