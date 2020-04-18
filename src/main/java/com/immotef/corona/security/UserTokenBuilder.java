package com.immotef.corona.security;

import com.immotef.corona.company.CompanyContext;
import com.immotef.corona.admin_user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultJwtBuilder;

import java.util.Date;

import static com.immotef.corona.security.SecurityConstants.*;

//import org.jetbrains.annotations.NotNull;


public class UserTokenBuilder extends DefaultJwtBuilder {

    public static final String USER_ID_KEY = "userId";
    public static final String COMPANY_ID_KEY = "companyId";

    public static String getLoggedUserToken(UserEntity userEntity) {

        JwtBuilder builder = getBaseBuilderForEntity(userEntity);
        builder = updateWithUserCompanyDetails(builder, userEntity);
        return prepareSignedToken(builder);

    }

//    @NotNull
    private static String prepareSignedToken(JwtBuilder builder) {

        return TOKEN_PREFIX + builder
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

    }

    private static JwtBuilder getBaseBuilderForEntity(UserEntity userEntity) {

        return Jwts.builder()
                .setSubject(userEntity.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("role", userEntity.getRole())
                .claim(USER_ID_KEY, userEntity.getId())
                .claim("company", userEntity.getCompany().getName())
                .claim(COMPANY_ID_KEY, CompanyContext.getCompanyId());

    }

    private static JwtBuilder updateWithUserCompanyDetails(JwtBuilder builder, UserEntity userEntity) {

        builder.claim(COMPANY_ID_KEY, userEntity.getCompanyId());
        return builder;

    }

    public static void setCompanyUserContext(String token) {

        CompanyContext.setCompanyId(parseLongFromTokenWithKey(token, COMPANY_ID_KEY));
        CompanyContext.setLoggedUserId(parseLongFromTokenWithKey(token, USER_ID_KEY));
    }

    private static Long parseLongFromTokenWithKey(String token, String tokenValueKey) {

        return getTokenBody(token)
                .get(tokenValueKey, Long.class);

    }

    public static String parseUserEmailFromToken(String token) {

        return  getTokenBody(token)
                .getSubject();

    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
    }
}
