package com.example.agromonitoramento.backendagromonitoramento.whatsapp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WhatsappService {

    private static final String API_URL = "http://localhost:8080/message/sendText/AgroMonitoramento";
    private static final String API_KEY = "B26F59251F90-4830-A4FB-22FD73DCB62B";

    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarMensagem(String numero, String mensagem){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", API_KEY);

        WhatsappDTO resquest = new WhatsappDTO(numero,mensagem);

        HttpEntity<WhatsappDTO> request = new HttpEntity<>(resquest, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(API_URL,request,String.class);
    }

    public void enviarMensagemBoasVindas(String name, String numero) {
        String mensagem = "Seja bem-vindo, " + name + ", ao AgroMonitoramento! 🌱 \n\n" +
                "Eu sou o Lavi 🤖, seu assistente virtual na lavoura de soja.\n" +
                "Comigo, você pode tirar dúvidas sobre sua plantação 🌾 e enviar imagens das folhas de soja para análise de possíveis doenças.\n\n" +
                "Estou aqui para te ajudar no que precisar! ✅";

        enviarMensagem(numero, mensagem);
    }
}
