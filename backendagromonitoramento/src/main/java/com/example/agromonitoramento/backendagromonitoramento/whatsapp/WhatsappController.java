package com.example.agromonitoramento.backendagromonitoramento.whatsapp;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhook")
@Tag(name="Whatsapp")
public class WhatsappController {

    @PostMapping
    public void receberMensagem(@RequestBody String payload){
        System.out.println("Mensagem recebida: " + payload);
    }

}
