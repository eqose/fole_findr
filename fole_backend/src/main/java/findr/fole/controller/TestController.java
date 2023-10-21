package findr.fole.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/hello")
public class TestController {
    @GetMapping
    public ResponseEntity<String> sayHello(Principal principal) {
        var u  = principal;
        return ResponseEntity.ok("Hello");
    }
}
