package com.SpringTask.SpringTask.Security;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.stream.Collectors;

record JwtResponse(String token){}
@RestController
public class JWTAuthService {
    private JwtEncoder jwtEncoder;

    public JWTAuthService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate (Authentication authentication){
        return new JwtResponse(createToken(authentication));
    }


    private String createToken(Authentication authentication){
        var claims= JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60*15))
                .claim("scope",createScope(authentication))
                .build();
        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(e->e.getAuthority())
                .collect(Collectors.joining(" "));
    }

}
