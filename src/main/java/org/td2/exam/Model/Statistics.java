package org.td2.exam.Model;

import java.math.BigDecimal;
import java.util.Map;

public class Statistics {

    // Pour GET /collectivites/{id}/statistics
    private Map<String, MemberStatistics> memberStatistics;

    // Pour GET /collectivities/statistics
    private String collectivityId;
    private String collectivityName;
    private BigDecimal percentageUpToDate;
    private Integer newMembersCount;
    private BigDecimal globalAttendanceRate;

    public Statistics() {}

    public Map<String, MemberStatistics> getMemberStatistics() {
        return memberStatistics;
    }

    public void setMemberStatistics(Map<String, MemberStatistics> memberStatistics) {
        this.memberStatistics = memberStatistics;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }

    public String getCollectivityName() {
        return collectivityName;
    }

    public void setCollectivityName(String collectivityName) {
        this.collectivityName = collectivityName;
    }

    public BigDecimal getPercentageUpToDate() {
        return percentageUpToDate;
    }

    public void setPercentageUpToDate(BigDecimal percentageUpToDate) {
        this.percentageUpToDate = percentageUpToDate;
    }

    public Integer getNewMembersCount() {
        return newMembersCount;
    }

    public void setNewMembersCount(Integer newMembersCount) {
        this.newMembersCount = newMembersCount;
    }

    public BigDecimal getGlobalAttendanceRate() {
        return globalAttendanceRate;
    }

    public void setGlobalAttendanceRate(BigDecimal globalAttendanceRate) {
        this.globalAttendanceRate = globalAttendanceRate;
    }

    // Classe interne pour les statistiques par membre
    public static class MemberStatistics {
        private BigDecimal totalCollected;
        private BigDecimal potentialUnpaid;
        private BigDecimal attendanceRate;

        public MemberStatistics() {}

        public MemberStatistics(BigDecimal totalCollected, BigDecimal potentialUnpaid, BigDecimal attendanceRate) {
            this.totalCollected = totalCollected;
            this.potentialUnpaid = potentialUnpaid;
            this.attendanceRate = attendanceRate;
        }

        public BigDecimal getTotalCollected() {
            return totalCollected;
        }

        public void setTotalCollected(BigDecimal totalCollected) {
            this.totalCollected = totalCollected;
        }

        public BigDecimal getPotentialUnpaid() {
            return potentialUnpaid;
        }

        public void setPotentialUnpaid(BigDecimal potentialUnpaid) {
            this.potentialUnpaid = potentialUnpaid;
        }

        public BigDecimal getAttendanceRate() {
            return attendanceRate;
        }

        public void setAttendanceRate(BigDecimal attendanceRate) {
            this.attendanceRate = attendanceRate;
        }
    }
}