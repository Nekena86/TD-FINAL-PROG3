package org.td2.prog_3.Services;


import org.springframework.stereotype.Service;
import org.td2.prog_3.Exception.BadRequestException;
import org.td2.prog_3.Exception.NotFoundException;
import org.td2.prog_3.Model.Paiement;
import org.td2.prog_3.Model.Transaction;
import org.td2.prog_3.Repository.PaiementRepository;
import org.td2.prog_3.Repository.CotisationRepository;
import org.td2.prog_3.Repository.MemberRepository;
import org.td2.prog_3.Repository.TransactionRepository;

import java.time.LocalDate;

@Service
public class PaiementService {

    private PaiementRepository paiementRepository;
    private CotisationRepository cotisationRepository;
    private MemberRepository memberRepository;
    private TransactionRepository transactionRepository;

    public PaiementService(PaiementRepository paiementRepository,
                           CotisationRepository cotisationRepository,
                           MemberRepository memberRepository,
                           TransactionRepository transactionRepository) {
        this.paiementRepository = paiementRepository;
        this.cotisationRepository = cotisationRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
    }

    public Paiement payer(Double montant, Long membreId, Long cotisationId, String mode) {
        if (montant <= 0) {
            throw new BadRequestException("Le montant doit être supérieur à 0");
        }

        if (!memberRepository.existsById(membreId)) {
            throw new NotFoundException("Membre non trouvé avec id: " + membreId);
        }

        if (cotisationRepository.findByMembreId(membreId).isEmpty()) {
            throw new NotFoundException("Cotisation non trouvée pour ce membre");
        }

        Paiement paiement = new Paiement();
        paiement.setMontant(montant);
        paiement.setDate(LocalDate.now());
        paiement.setMode(mode);
        paiement.setMembreId(membreId);
        paiement.setCotisationId(cotisationId);

        Paiement savedPaiement = paiementRepository.save(paiement);

        Transaction transaction = new Transaction();
        transaction.setMontant(montant);
        transaction.setDate(LocalDate.now());
        transaction.setType("ENTREE");
        transaction.setCompteId(1L);
        transactionRepository.save(transaction);
        transactionRepository.updateCompteSolde(1L, montant, "ENTREE");

        return savedPaiement;
    }
}
