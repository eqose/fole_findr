package findr.fole.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;

@ControllerAdvice
public class GlobalCORSConfig {
    @CrossOrigin(origins = "http://localhost:4200")
    public void addCorsMapping() {}
}