package com.islington.summer.collectiondemo.auth.config;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements Serializable {

    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;

    @Value("${jwt.signing.key}")
    public String SIGNING_KEY;

    @Value("${jwt.authorities.key}")
    public String AUTHORITIES_KEY;

    /**
     * Gets the username from the token.
     *
     * @param token The token to get the username from.
     * @return The username from the token.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Returns the expiration date contained in the specified token.
     *
     * @param token the token from which the expiration date will be retrieved
     * @return the expiration date contained in the specified token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Gets a claim from a JWT token.
     *
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        // The token is a JWT Token
        final Claims claims = getAllClaimsFromToken(token);
        // The claimsResolver is a Function that takes a Claims object and returns a
        // boolean value
        return claimsResolver.apply(claims);
    }

    /**
     * Gets all claims from a JWT token.
     *
     * @param token The JWT token to get the claims from.
     * @return The claims from the JWT token.
     */
    public Claims getAllClaimsFromToken(String token) {
        // 1. Validate the JWT format
        return Jwts.parser()
                // 2. Sign the JWT using the HS512 algorithm and secret key.
                .setSigningKey(SIGNING_KEY)
                // 3. Parses the JWT claims
                .parseClaimsJws(token)
                // 4. Return the claims
                .getBody();
    }

    /**
     * Checks if the token is expired.
     */
    private Boolean isTokenExpired(String token) {
        // get the expiration date from the token
        final Date expiration = getExpirationDateFromToken(token);
        // return true if the expiration date is before the current date
        return expiration.before(new Date());
    }

    /**
     * Generates a JWT token containing the username as subject, and userId and role
     * as additional claims. These properties are taken from the user passed to the
     * method.
     * Tokens validity is infinite.
     *
     * @param authentication the authentication for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(Authentication authentication) {
        // Create a string of authorities separated by commas
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Create a JWT token using the JJWT library
        return Jwts.builder()
                // Set the subject to be the user name
                .setSubject(authentication.getName())
                // Set the authorities to be the string of authorities
                .claim(AUTHORITIES_KEY, authorities)
                // Set the issued at date to be now
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Set the expiration date to be now plus the number of seconds in
                // TOKEN_VALIDITY
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                // Sign the token with the HS256 algorithm and the signing key
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                // Compress the token
                .compact();
    }

    /**
     * This function validates the token.
     *
     * @param token
     * @param userDetails
     * @return true if the token is valid, false otherwise
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * This function returns the authentication token.
     *
     * @param token
     * @param existingAuth
     * @param userDetails
     * @return
     */
    UsernamePasswordAuthenticationToken getAuthenticationToken(final String token, final Authentication existingAuth,
                                                               final UserDetails userDetails) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}
