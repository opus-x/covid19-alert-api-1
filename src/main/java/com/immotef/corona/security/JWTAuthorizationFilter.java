package com.immotef.corona.security;

import com.immotef.corona.security.authorities.CompanyIdGrantedAuthority;
import com.immotef.corona.admin_user.UserEntity;
import com.immotef.corona.admin_user.repository.AdminUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private AdminUserRepository repository;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AdminUserRepository repository) {
        super(authenticationManager);
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);

        if (null == header || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (null != token) {
            String user = UserTokenBuilder.parseUserEmailFromToken(token);

            if (null != user) {
                UserTokenBuilder.setCompanyUserContext(token);
                UserEntity userEntity = repository.findByEmailSafe(user);
                return new UsernamePasswordAuthenticationToken(new VCUserDetails(userEntity), null, Arrays.asList(new CompanyIdGrantedAuthority(userEntity.getCompanyId())));
            }

            return null;
        }

        return null;
    }
}
