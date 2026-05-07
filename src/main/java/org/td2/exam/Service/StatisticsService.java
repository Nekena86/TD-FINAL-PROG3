package org.td2.exam.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.td2.exam.Exception.BusinessException;
import org.td2.exam.Model.Collectivity;
import org.td2.exam.Model.Statistics;
import org.td2.exam.Repository.CollectivityRepository;
import org.td2.exam.Repository.MemberRepository;
import org.td2.exam.Repository.MembershipFeeRepository;
import org.td2.exam.Repository.StatisticsRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class StatisticsService {

    private StatisticsRepository statisticsRepository;
    private CollectivityRepository collectivityRepository;
    private MemberRepository memberRepository;
    private MembershipFeeRepository membershipFeeRepository;

    @Autowired
    public StatisticsService(StatisticsRepository statisticsRepository,
                             CollectivityRepository collectivityRepository,
                             MemberRepository memberRepository,
                             MembershipFeeRepository membershipFeeRepository) {
        this.statisticsRepository = statisticsRepository;
        this.collectivityRepository = collectivityRepository;
        this.memberRepository = memberRepository;
        this.membershipFeeRepository = membershipFeeRepository;
    }


    public Map<String, Object> getCollectivityStatistics(String collectivityId, LocalDate startDate, LocalDate endDate) {

        if (!collectivityRepository.existsById(collectivityId)) {
            throw new BusinessException("Collectivity not found");
        }

        Map<String, Object> result = new HashMap<>();

        Map<String, Integer> amountCollected = statisticsRepository.getAmountCollectedByMember(collectivityId, startDate, endDate);
        Map<String, Integer> potentialUnpaid = statisticsRepository.getPotentialUnpaidByMember(collectivityId, startDate);


        Map<String, Statistics.MemberStatistics> memberStats = new HashMap<>();
        Set<String> allMemberIds = new HashSet<>();
        allMemberIds.addAll(amountCollected.keySet());
        allMemberIds.addAll(potentialUnpaid.keySet());

        for (String memberId : allMemberIds) {
            Statistics.MemberStatistics stats = new Statistics.MemberStatistics();
            stats.setTotalCollected(BigDecimal.valueOf(amountCollected.getOrDefault(memberId, 0)));
            stats.setPotentialUnpaid(BigDecimal.valueOf(potentialUnpaid.getOrDefault(memberId, 0)));
            memberStats.put(memberId, stats);
        }

        result.put("memberStatistics", memberStats);
        result.put("period", Map.of("startDate", startDate, "endDate", endDate));

        return result;
    }

    public List<Map<String, Object>> getAllCollectivitiesStatistics(LocalDate startDate, LocalDate endDate) {
        List<Map<String, Object>> result = new ArrayList<>();

        List<Collectivity> collectivities = collectivityRepository.findAll();

        for (Collectivity collectivity : collectivities) {
            Map<String, Object> stats = new HashMap<>();
            stats.put("collectivityId", collectivity.getId());
            stats.put("collectivityName", collectivity.getNom());

            BigDecimal percentageUpToDate = statisticsRepository.getPercentageUpToDateMembers(
                    collectivity.getId(), startDate, endDate);
            stats.put("percentageUpToDate", percentageUpToDate);

            int newMembersCount = statisticsRepository.getNewMembersCount(collectivity.getId(), startDate, endDate);
            stats.put("newMembersCount", newMembersCount);

            result.add(stats);
        }

        return result;
    }
}