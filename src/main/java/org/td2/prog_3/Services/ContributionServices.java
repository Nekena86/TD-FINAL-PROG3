
package org.td2.prog_3.Services;

import org.springframework.stereotype.Service;
import org.td2.prog_3.Exception.NotFoundException;
import org.td2.prog_3.Model.Contribution;
import org.td2.prog_3.Repository.ContributionRepository;
import org.td2.prog_3.Repository.MemberRepository;
import org.td2.prog_3.Repository.CollectivityRepository;

import java.time.LocalDate;

@Service
public class ContributionServices {

    private ContributionRepository contributionRepository;
    private MemberRepository memberRepository;
    private CollectivityRepository collectivityRepository;

    public ContributionServices(ContributionRepository contributionRepository,
                                MemberRepository memberRepository,
                                CollectivityRepository collectivityRepository) {
        this.contributionRepository = contributionRepository;
        this.memberRepository = memberRepository;
        this.collectivityRepository = collectivityRepository;
    }

    public Contribution createContribution(Double amount, Long memberId, Long collectivityId, String type, String paymentMode) {
        if (!memberRepository.existsById(memberId)) {
            throw new NotFoundException("Member not found with id: " + memberId);
        }
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new NotFoundException("Collectivity not found with id: " + collectivityId);
        }

        Contribution contribution = new Contribution();
        contribution.setAmount(amount);
        contribution.setDate(LocalDate.now());
        contribution.setMemberId(memberId);
        contribution.setCollectivityId(collectivityId);
        contribution.setType(type);
        contribution.setPaymentMode(paymentMode);

        return contributionRepository.save(contribution);
    }
}
