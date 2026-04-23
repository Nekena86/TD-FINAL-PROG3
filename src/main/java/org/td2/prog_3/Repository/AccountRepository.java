package org.td2.prog_3.Repository;

import org.springframework.stereotype.Repository;
import org.td2.prog_3.Model.FinancialAccount;
import org.td2.prog_3.Model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccountRepository {

    private Map<Long, FinancialAccount> Accounts;
    private Map<Long, List<Transaction>> transactionsByCompte;
    private AtomicLong currentId;

    public AccountRepository() {
        this.Accounts = new ConcurrentHashMap<>();
        this.transactionsByCompte = new ConcurrentHashMap<>();
        this.currentId = new AtomicLong(1);

        initDefaultAccounts();
    }

    private void initDefaultAccounts() {

        FinancialAccount caisse = new FinancialAccount();
        caisse.setId(currentId.getAndIncrement());
        caisse.setType("CAISSE");
        caisse.setLibelle("Caisse principale");
        caisse.setCollectivityId(1L);
        caisse.setSolde(150000.0);
        Accounts.put(caisse.getId(), caisse);
        transactionsByCompte.put(caisse.getId(), new ArrayList<>());

        FinancialAccount bancaire = new FinancialAccount();
        bancaire.setId(currentId.getAndIncrement());
        bancaire.setType("BANCAIRE");
        bancaire.setLibelle("Compte MCB");
        bancaire.setCollectivityId(1L);
        bancaire.setSolde(500000.0);
        bancaire.setTitulaire("Fédération Agricole");
        bancaire.setBanque("MCB");
        bancaire.setNumeroCompte("12345-67890-12345678901-12");
        Accounts.put(bancaire.getId(), bancaire);
        transactionsByCompte.put(bancaire.getId(), new ArrayList<>());

        FinancialAccount mobile = new FinancialAccount();
        mobile.setId(currentId.getAndIncrement());
        mobile.setType("MOBILE_MONEY");
        mobile.setLibelle("Compte Mvola");
        mobile.setCollectivityId(1L);
        mobile.setSolde(75000.0);
        mobile.setTitulaire("Jean Rakoto");
        mobile.setService("Mvola");
        mobile.setNumeroTelephone("0341234567");
        Accounts.put(mobile.getId(), mobile);
        transactionsByCompte.put(mobile.getId(), new ArrayList<>());
    }

    public List<FinancialAccount> findByCollectivityId(Long collectivityId) {
        List<FinancialAccount> result = new ArrayList<>();
        for (FinancialAccount compte : this.Accounts.values()) {
            if (collectivityId.equals(compte.getCollectivityId())) {
                result.add(compte);
            }
        }
        return result;
    }

    public void addTransaction(Long compteId, Transaction transaction) {
        List<Transaction> transactions = transactionsByCompte.get(compteId);
        if (transactions != null) {
            transactions.add(transaction);
        }
    }

    public Double getSoldeAtDate(Long compteId, LocalDate date) {
        FinancialAccount compte = Accounts.get(compteId);
        if (compte == null) {
            return 0.0;
        }

        Double solde = compte.getSolde();
        List<Transaction> transactions = transactionsByCompte.get(compteId);

        if (transactions != null && date != null) {
            for (Transaction t : transactions) {
                if (t.getDate() != null && t.getDate().isAfter(date)) {
                    if ("ENTREE".equals(t.getType())) {
                        solde -= t.getAmount();
                    } else {
                        solde += t.getAmount();
                    }
                }
            }
        }

        return solde;
    }

    public FinancialAccount save(FinancialAccount compte) {
        if (compte.getId() == null) {
            compte.setId(currentId.getAndIncrement());
        }
        Accounts.put(compte.getId(), compte);
        if (!transactionsByCompte.containsKey(compte.getId())) {
            transactionsByCompte.put(compte.getId(), new ArrayList<>());
        }
        return compte;
    }
}
