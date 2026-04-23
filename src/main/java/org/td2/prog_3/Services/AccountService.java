package org.td2.prog_3.Services;


import org.springframework.stereotype.Service;
import org.td2.prog_3.Exception.BadRequestException;
import org.td2.prog_3.Exception.NotFoundException;
import org.td2.prog_3.Model.FinancialAccount;
import org.td2.prog_3.Repository.CollectivityRepository;
import org.td2.prog_3.Repository.AccountRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    private AccountRepository compteRepository;
    private CollectivityRepository collectivityRepository;

    public AccountService(AccountRepository compteRepository,
                          CollectivityRepository collectivityRepository) {
        this.compteRepository = compteRepository;
        this.collectivityRepository = collectivityRepository;
    }

    public List<Map<String, Object>> getFinancialAccounts(Long collectivityId, String atDate) {
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new NotFoundException("Collectivité non trouvée avec l'id: " + collectivityId);
        }

        LocalDate targetDate = LocalDate.now();
        if (atDate != null && !atDate.isEmpty()) {
            try {
                targetDate = LocalDate.parse(atDate, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                throw new BadRequestException("Format de date invalide. Utilisez YYYY-MM-DD");
            }
        }

        List<FinancialAccount> comptes = compteRepository.findByCollectivityId(collectivityId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (FinancialAccount compte : comptes) {
            Double soldeAtDate = compteRepository.getSoldeAtDate(compte.getId(), targetDate);
            compte.setSolde(soldeAtDate);
            result.add(compte.toMap());
        }

        return result;
    }
}