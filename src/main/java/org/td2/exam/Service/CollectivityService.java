package org.td2.exam.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.td2.exam.Exception.BusinessException;
import org.td2.exam.Model.Collectivity;
import org.td2.exam.Model.FinancialAccount;
import org.td2.exam.Model.MembershipFee;
import org.td2.exam.Model.Transaction;
import org.td2.exam.Repository.CollectivityRepository;
import org.td2.exam.Repository.FinancialAccountRepository;
import org.td2.exam.Repository.MembershipFeeRepository;
import org.td2.exam.Repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CollectivityService {

    private CollectivityRepository collectivityRepository;
    private MembershipFeeRepository membershipFeeRepository;
    private TransactionRepository transactionRepository;
    private FinancialAccountRepository financialAccountRepository;

    @Autowired
    public CollectivityService(CollectivityRepository collectivityRepository,
                               MembershipFeeRepository membershipFeeRepository,
                               TransactionRepository transactionRepository,
                               FinancialAccountRepository financialAccountRepository) {
        this.collectivityRepository = collectivityRepository;
        this.membershipFeeRepository = membershipFeeRepository;
        this.transactionRepository = transactionRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    public List<Collectivity> createCollectivities(List<Collectivity> collectivities) {
        for (Collectivity c : collectivities) {
            if (c.getId() == null) {
                c.setId("col-" + UUID.randomUUID().toString().substring(0, 8));
            }
            if (c.getDateCreation() == null) {
                c.setDateCreation(LocalDate.now());
            }
            collectivityRepository.insert(c);
        }
        return collectivities;
    }

    public Collectivity updateCollectivityInfo(String id, String numero, String nom) {
        Collectivity collectivity = collectivityRepository.findById(id);
        if (collectivity == null) {
            throw new BusinessException("Collectivity not found");
        }

        if (numero != null && !numero.equals(collectivity.getNumero())) {
            if (collectivityRepository.existsByNumero(numero)) {
                throw new BusinessException("Numero already exists");
            }
            collectivity.setNumero(numero);
        }

        if (nom != null && !nom.equals(collectivity.getNom())) {
            if (collectivityRepository.existsByNom(nom)) {
                throw new BusinessException("Nom already exists");
            }
            collectivity.setNom(nom);
        }

        collectivityRepository.update(collectivity);
        return collectivity;
    }

    public List<MembershipFee> createMembershipFees(String collectivityId, List<MembershipFee> fees) {
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new BusinessException("Collectivity not found");
        }

        for (MembershipFee fee : fees) {
            if (fee.getId() == null) {
                fee.setId("cot-" + UUID.randomUUID().toString().substring(0, 8));
            }
            fee.setCollectivityId(collectivityId);
            membershipFeeRepository.insert(fee);
        }
        return fees;
    }

    public List<MembershipFee> getMembershipFees(String collectivityId) {
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new BusinessException("Collectivity not found");
        }
        return membershipFeeRepository.findByCollectivityId(collectivityId);
    }

    public List<Transaction> getTransactions(String collectivityId, LocalDate startDate, LocalDate endDate) {
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new BusinessException("Collectivity not found");
        }
        return transactionRepository.findByCollectivityIdAndDateBetween(collectivityId, startDate, endDate);
    }

    public List<FinancialAccount> getFinancialAccounts(String collectivityId, LocalDate atDate) {
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new BusinessException("Collectivity not found");
        }

        List<FinancialAccount> accounts = financialAccountRepository.findByCollectivityId(collectivityId);

        for (FinancialAccount account : accounts) {
            int balance = financialAccountRepository.getCurrentBalance(account.getId(), atDate);
            account.setMontantInitial(balance);
        }

        return accounts;
    }

    public Collectivity getCollectivityById(String id) {
        Collectivity collectivity = collectivityRepository.findById(id);
        if (collectivity == null) {
            throw new BusinessException("Collectivity not found");
        }
        return collectivity;
    }
}