package org.td2.exam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.exam.Model.Collectivity;
import org.td2.exam.Model.FinancialAccount;
import org.td2.exam.Model.MembershipFee;
import org.td2.exam.Model.Transaction;
import org.td2.exam.Service.CollectivityService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    private CollectivityService collectivityService;

    @Autowired
    public CollectivityController(CollectivityService collectivityService) {
        this.collectivityService = collectivityService;
    }

    @PostMapping
    public ResponseEntity<List<Collectivity>> createCollectivities(@RequestBody List<Collectivity> collectivities) {
        List<Collectivity> created = collectivityService.createCollectivities(collectivities);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/informations")
    public ResponseEntity<Collectivity> updateCollectivityInfo(
            @PathVariable String id,
            @RequestBody Map<String, String> request) {
        String numero = request.get("numero");
        String nom = request.get("nom");
        Collectivity updated = collectivityService.updateCollectivityInfo(id, numero, nom);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFee>> createMembershipFees(
            @PathVariable String id,
            @RequestBody List<MembershipFee> fees) {
        List<MembershipFee> created = collectivityService.createMembershipFees(id, fees);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFee>> getMembershipFees(@PathVariable String id) {
        List<MembershipFee> fees = collectivityService.getMembershipFees(id);
        return ResponseEntity.ok(fees);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Transaction> transactions = collectivityService.getTransactions(id, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}/financialAccounts")
    public ResponseEntity<List<FinancialAccount>> getFinancialAccounts(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate at) {
        List<FinancialAccount> accounts = collectivityService.getFinancialAccounts(id, at);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collectivity> getCollectivityById(@PathVariable String id) {
        Collectivity collectivity = collectivityService.getCollectivityById(id);
        return ResponseEntity.ok(collectivity);
    }
}