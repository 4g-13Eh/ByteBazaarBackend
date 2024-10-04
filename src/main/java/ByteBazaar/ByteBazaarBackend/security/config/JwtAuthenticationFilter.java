package ByteBazaar.ByteBazaarBackend.security.config;

import ByteBazaar.ByteBazaarBackend.security.service.JwtService;
import ByteBazaar.ByteBazaarBackend.security.service.impl.MyUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("Entering filter chain");

        String jwt = jwtService.getTokenFromRequest(request);

        if (jwt == null) {
            System.out.println("No JWT found, continuing filter chain.");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String userEmail = jwtService.extractUsername(jwt);
            if (userEmail == null && SecurityContextHolder.getContext().getAuthentication() != null) return;
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid Token: " + ex.getMessage());
        }

        System.out.println("Filter finished");
        filterChain.doFilter(request, response);
    }
}
