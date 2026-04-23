

package org.td2.prog_3.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.prog_3.Model.Payment;
import org.td2.prog_3.Services.PaymentServices;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private PaymentServices paymentService;

    public PaymentController(PaymentServices paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody Map<String, Object> request) {
        Double amount = ((Number) request.get("amount")).doubleValue();
        Long memberId = ((Number) request.get("memberId")).longValue();
        Long contributionId = ((Number) request.get("contributionId")).longValue();
        String mode = (String) request.get("mode");

        Payment payment = paymentService.makePayment(amount, memberId, contributionId, mode);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}