package com.arpico.ticket.controllers;

 

import org.json.simple.JSONObject; 
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestRestAPIs {
  




    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";

    }
 
 
    @PostMapping("/node/data")
    @PreAuthorize("hasRole('NODE')or hasRole('ADMIN')")
    public String data(@Valid
                           @RequestBody JSONObject data
//    @PathVariable String data
    ) {
        System.out.println(data.toString());
        return data.toString();
    }

}
