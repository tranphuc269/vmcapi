package com.vai.vmcapi.security;


import com.google.gson.GsonBuilder;
import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.exception.BusinessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtProvider tokenProvider;
    private CustomUserDetailsService userAuthService;

    public JwtAuthenticationFilter(JwtProvider tokenProvider, CustomUserDetailsService userAuthService) {
        this.tokenProvider = tokenProvider;
        this.userAuthService = userAuthService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse resp,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        boolean isNext = true;

        try {
            String token = getJwtFromRequest(req);

            if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
                authenticateUser(token, req);
            }

        } catch (BusinessException businessException) {
            handleBusinessException(resp, businessException);
            return;
        } catch (Exception exception) {
            handleException(resp);
            return;
        }
        if (!isNext) {
            return;
        }

        filterChain.doFilter(req, resp);
    }

    private void setResponseHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, PATCH, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Expose-Headers", "xsrf-token");
        resp.setContentType("application/octet-stream");
    }


    private void authenticateUser(String token, HttpServletRequest req) {
        String userId = tokenProvider.getUserIdFromToken(token);
        UserContext userDetails = userAuthService.loadUserById(Long.valueOf(userId));
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private void handleBusinessException(HttpServletResponse resp, BusinessException businessException)
            throws IOException {
        resp.setStatus(businessException.getCode());
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(businessException.getCode());

        resp.getWriter().write(new GsonBuilder().setPrettyPrinting().create().toJson(ResponseDTO.errMsg(businessException.getHttpStatus().value(),
                businessException.getMessage())));
    }

    private void handleException(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
