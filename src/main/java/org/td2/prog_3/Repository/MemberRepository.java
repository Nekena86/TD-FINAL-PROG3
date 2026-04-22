package org.td2.prog_3.Repository;

import org.springframework.stereotype.Repository;
import org.td2.prog_3.Model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepository {

    private Map<Long, Member> members;
    private AtomicLong currentId;

    public MemberRepository() {
        this.members = new ConcurrentHashMap<>();
        this.currentId = new AtomicLong(1);
    }

    public Member save(Member member) {
        if (member.getId() == null) {
            Long newId = this.currentId.getAndIncrement();
            member.setId(newId);
        }
        this.members.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return this.members.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(this.members.values());
    }

    public List<Member> findAllById(List<Long> ids) {
        List<Member> result = new ArrayList<>();
        for (Long id : ids) {
            Member member = this.members.get(id);
            if (member != null) {
                result.add(member);
            }
        }
        return result;
    }

    public List<Member> findByCollectivityId(Long collectivityId) {
        List<Member> result = new ArrayList<>();
        for (Member m : this.members.values()) {
            if (collectivityId.equals(m.getCollectivityId())) {
                result.add(m);
            }
        }
        return result;
    }

    public boolean existsByTelephone(String telephone) {
        if (telephone == null) {
            return false;
        }
        for (Member m : this.members.values()) {
            if (telephone.equals(m.getTelephone())) {
                return true;
            }
        }
        return false;
    }

    public boolean existsByEmail(String email) {
        if (email == null) {
            return false;
        }
        for (Member m : this.members.values()) {
            if (email.equals(m.getEmail())) {
                return true;
            }
        }
        return false;
    }
}