package com.santt4na.health_check.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.santt4na.health_check.dto.securityDTO.TokenDTO;
import com.santt4na.health_check.exception.InvalidJwtAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {
	
	private final UserDetailsService userDetailsService;
	private final String issuer;
	private final long accessTokenValidity;
	private final long refreshTokenValidity;
	private final Algorithm algorithm;
	
	public JwtTokenProvider(
		UserDetailsService userDetailsService,
		@Value("${security.jwt.token.secret-key}") String secretKey,
		@Value("${security.jwt.token.issuer:health-check}") String issuer,
		@Value("${security.jwt.token.access-expire-length:3600000}") long accessTokenValidity,
		@Value("${security.jwt.token.refresh-expire-length:2592000000}") long refreshTokenValidity) {
		
		this.userDetailsService = userDetailsService;
		this.issuer = issuer;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		
		if (secretKey == null || secretKey.length() < 32) {
			throw new IllegalArgumentException("Secret key must be at least 32 characters");
		}
		this.algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public TokenDTO createAccessToken(String username, List<String> roles) {
		Date now = new Date();
		Date accessValidity = new Date(now.getTime() + accessTokenValidity);
		String accessToken = buildToken(username, roles, now, accessValidity);
		String refreshToken = buildRefreshToken(username, roles, now);
		return new TokenDTO(username, true, now, accessValidity, accessToken, refreshToken);
	}
	
	public TokenDTO refreshToken(String refreshToken) {
		if (StringUtils.isBlank(refreshToken)) {
			throw new InvalidJwtAuthenticationException("Refresh token is missing");
		}
		
		String token = extractToken(refreshToken);
		DecodedJWT decodedJWT = decodedToken(token);
		
		String username = decodedJWT.getSubject();
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
		return createAccessToken(username, roles);
	}
	
	private String buildToken(String username, List<String> roles, Date now, Date validity) {
		return JWT.create()
			.withClaim("roles", roles)
			.withIssuedAt(now)
			.withExpiresAt(validity)
			.withSubject(username)
			.withIssuer(issuer)
			.sign(algorithm);
	}
	
	private String buildRefreshToken(String username, List<String> roles, Date now) {
		Date validity = new Date(now.getTime() + refreshTokenValidity);
		return buildToken(username, roles, now, validity);
	}
	
	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	private DecodedJWT decodedToken(String token) {
		try {
			return JWT.require(algorithm).build().verify(token);
		} catch (JWTDecodeException e) {
			throw new InvalidJwtAuthenticationException("Invalid JWT token: " + e.getMessage());
		}
	}
	
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		return extractToken(bearerToken);
	}
	
	private String extractToken(String bearerToken) {
		if (StringUtils.isNotBlank(bearerToken)){
			bearerToken = bearerToken.trim();
			if (bearerToken.startsWith("Bearer ")) {
				return bearerToken.substring(7);
			}
			return bearerToken;
		}
		return null;
	}
	
	public boolean validateToken(String token) {
		try {
			decodedToken(token); // Valida assinatura e expiração
			return true;
		} catch (TokenExpiredException e) {
			throw new InvalidJwtAuthenticationException("Expired JWT token");
		} catch (JWTVerificationException e) {
			throw new InvalidJwtAuthenticationException("Invalid JWT token: " + e.getMessage());
		}
	}
}

//@Service
//public class JwtTokenProvider {
//
//	@Value("${security.jwt.token.secret-key:secret}")
//	private String secretKey = "secret";
//
//	@Value("${security.jwt.token.expire-lenght:3600000}")
//	private final long validityInMilliseconds = 3600000; // 1h
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	Algorithm algorithm = null;
//
//	@PostConstruct
//	protected void init() {
//		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//		algorithm = Algorithm.HMAC256(secretKey.getBytes());
//	}
//
//	public TokenDTO createAccessToken(String username, List<String> roles) {
//		Date now = new Date();
//		Date validity = new Date(now.getTime() + validityInMilliseconds);
//		String accessToken = getAccessToken(username, roles, now, validity);
//		String refreshToken = getRefreshToken(username, roles, now);
//		return new TokenDTO(username, true, now, validity, accessToken, refreshToken);
//	}
//
//	public TokenDTO refreshToken(String refreshToken) {
//		var token = "";
//		if(refreshTokenContainsBearer(refreshToken)) {
//			token = refreshToken.substring("Bearer ".length());
//		}
//
//		JWTVerifier verifier = JWT.require(algorithm).build();
//		DecodedJWT decodedJWT = verifier.verify(token);
//
//		String username = decodedJWT.getSubject();
//		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
//		return createAccessToken(username, roles);
//	}
//
//	private String getRefreshToken(String username, List<String> roles, Date now) {
//		Date refreshTokenValidity = new Date(now.getTime() + (validityInMilliseconds * 3));
//		return JWT.create()
//			.withClaim("roles", roles)
//			.withIssuedAt(now)
//			.withExpiresAt(refreshTokenValidity)
//			.withSubject(username)
//			.sign(algorithm);
//	}
//
//	private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
//
//		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
//		return JWT.create()
//			.withClaim("roles", roles)
//			.withIssuedAt(now)
//			.withExpiresAt(validity)
//			.withSubject(username)
//			.withIssuer(issuerUrl)
//			.sign(algorithm);
//	}
//
//	public Authentication getAuthentication(String token) {
//		DecodedJWT decodedJWT = decodedToken(token);
//		UserDetails userDetails = this.userDetailsService
//			.loadUserByUsername(decodedJWT.getSubject());
//		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//	}
//
//	private DecodedJWT decodedToken(String token) {
//		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
//		JWTVerifier verifier = JWT.require(alg).build();
//		return verifier.verify(token);
//	}
//
//	public String resolveToken(HttpServletRequest request) {
//		String bearerToken = request.getHeader("Authorization");
//
//		//Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZWFuZHJvIiwicm9sZXMiOlsiQURNSU4iLCJNQU5BR0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCIsImV4cCI6MTY1MjcxOTUzOCwiaWF0IjoxNjUyNzE1OTM4fQ.muu8eStsRobqLyrFYLHRiEvOSHAcss4ohSNtmwWTRcY
//		if(refreshTokenContainsBearer(bearerToken)) return bearerToken.substring("Bearer ".length());
//		return null;
//	}
//
//	private static boolean refreshTokenContainsBearer(String refreshToken) {
//		return StringUtils.isNotBlank(refreshToken) && refreshToken.startsWith("Bearer ");
//	}
//
//	public boolean validateToken(String token){
//		DecodedJWT decodedJWT = decodedToken(token);
//		try {
//			return !decodedJWT.getExpiresAt().before(new Date());
//		} catch (Exception e) {
//			throw new InvalidJwtAuthenticationException("Expired or Invalid JWT Token!");
//		}
//	}
//}