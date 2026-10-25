package com.vinayaalla.portfolio.contact;

import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactMessageRepository messages;

    public ContactController(ContactMessageRepository messages) {
        this.messages = messages;
    }

    @PostMapping
    public ResponseEntity<ContactReceipt> submit(@Valid @RequestBody ContactRequest request) {
        ContactMessage saved = messages.save(
                new ContactMessage(request.name().trim(), request.email().trim(), request.message().trim()));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ContactReceipt(saved.getId(), "Message received"));
    }

    public record ContactReceipt(Long id, String status) {
    }
}