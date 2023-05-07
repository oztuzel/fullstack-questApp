//package com.monozel.questAppbackend.security;
//
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    @Value("${questapp.app.secret}")
//    private String APP_SECRET;
//
//    @Value("{questapp.expires.in}")
//    private long EXPIRES_IN;
//
//    public String generateJwtToken(Authentication auth) {
//        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal(); // returns user we want authenticate
//        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN); // tokens expire time
//        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
//                .setIssuedAt(new Date()).setExpiration(expireDate)
//                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
//        // we give the user we want to authorize as a parameter, we give it to setSubject
//        // created time == setIssuedAt(time)
//        // expire time == setExpiration(time)
//        // signWith(1,2) ? 1 == it is algorithm to create token, 2 String key
//    }
//
//    public Long getUserIdFromJwt (String token) {
//        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
//        return Long.parseLong(claims.getSubject());
//    }
//
//    public boolean validateToken (String token) {
//        try {
//            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
//            return !istokenExpired(token);
//        } catch (SignatureException e){
//            return false;
//        } catch (MalformedJwtException e) {
//            return false;
//        }catch (ExpiredJwtException e) {
//            return false;
//        }catch (UnsupportedJwtException e) {
//            return false;
//        }catch (IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    private boolean istokenExpired(String token) {
//        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
//        return expiration.before(new Date());
//    }
//}
