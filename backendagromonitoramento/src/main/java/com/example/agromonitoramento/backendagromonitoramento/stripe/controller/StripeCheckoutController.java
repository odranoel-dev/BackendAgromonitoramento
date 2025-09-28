//package com.example.agromonitoramento.backendagromonitoramento.stripe.controller;
//
//import com.example.agromonitoramento.backendagromonitoramento.stripe.StripeService;
//import com.example.agromonitoramento.backendagromonitoramento.stripe.dto.StripeRequestDTO;
//import com.stripe.net.StripeResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/product/v1")
//public class StripeCheckoutController {
//
//    private StripeService stripeService;
//
//    public StripeCheckoutController (StripeService stripeService) {
//        this.stripeService = stripeService;
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody StripeRequestDTO stripeRequestDTO) {
//        StripeResponse stripeResponse = stripeService.checkoutProducts(stripeRequestDTO);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(stripeResponse);
//    }
//}
