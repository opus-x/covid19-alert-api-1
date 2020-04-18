package com.immotef.corona.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.immotef.corona.company.CompanyContext;
import com.immotef.corona.security.authorities.CompanyIdGrantedAuthority;
import com.immotef.corona.security.authorities.UserIdGrantedAuthority;
import com.immotef.corona.admin_user.LoginRequestBody;
import com.immotef.corona.admin_user.UserEntity;
import com.immotef.corona.admin_user.repository.AdminUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.immotef.corona.security.SecurityConstants.HEADER_STRING;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private AdminUserRepository repository;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AdminUserRepository repository) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            LoginRequestBody credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequestBody.class);
            UserEntity userEntity = repository.findByEmailSafe(credentials.getUsername());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            new VCUserDetails(userEntity),
                            credentials.getPassword(),
                            Arrays.asList(new CompanyIdGrantedAuthority(userEntity.getCompanyId()),
                                    new UserIdGrantedAuthority(userEntity.getId()))
                    )
            );
        } catch (IOException e) {
            throw new ForbiddenException();
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        User user = ((User) authResult.getPrincipal());
        UserEntity userEntity = repository.findByEmailSafe(user.getUsername());


        String token = UserTokenBuilder.getLoggedUserToken(userEntity);

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization");
        response.addHeader(HEADER_STRING, token);

        CompanyContext.setCompanyId(userEntity.getCompanyId());
        CompanyContext.setLoggedUserId(userEntity.getId());
    }
}
