package ByteBazaar.ByteBazaarBackend.security.config;

import ByteBazaar.ByteBazaarBackend.entity.TokenEntity;
import ByteBazaar.ByteBazaarBackend.exception.TokenNotFoundException;
import ByteBazaar.ByteBazaarBackend.repository.TokenRepository;
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
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);

            try {
                userEmail = jwtService.extractUsername(jwt);

            } catch (ExpiredJwtException ex){
                TokenEntity tokenEntity = tokenRepository.findByToken(jwt).orElseThrow(TokenNotFoundException::new);
                tokenEntity.setExpired(true);
                tokenRepository.save(tokenEntity);

                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Invalid JWT: " + ex.getMessage());
                return;
            }

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println("Loading user details for: " + userEmail);
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(userEmail);
                System.out.println("User details loaded: " + userDetails.getUsername());

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    System.out.println("Token is valid");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (MalformedJwtException | SignatureException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid JWT: " + ex.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
