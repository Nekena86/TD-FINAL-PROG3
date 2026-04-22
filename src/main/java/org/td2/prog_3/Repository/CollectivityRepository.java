package org.td2.prog_3.Repository;

import org.springframework.stereotype.Repository;
import org.td2.prog_3.Model.Collectivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CollectivityRepository {

    private Map<Long, Collectivity> collectivities;
    private AtomicLong currentId;

    public CollectivityRepository() {
        this.collectivities = new ConcurrentHashMap<>();
        this.currentId = new AtomicLong(1);
    }

    public Collectivity save(Collectivity collectivity) {
        if (collectivity.getId() == null) {
            Long newId = this.currentId.getAndIncrement();
            collectivity.setId(newId);
        }
        this.collectivities.put(collectivity.getId(), collectivity);
        return collectivity;
    }

    public Collectivity findById(Long id) {
        return this.collectivities.get(id);
    }

    public List<Collectivity> findAll() {
        return new ArrayList<>(this.collectivities.values());
    }

    public boolean existsByNumero(String numero) {
        if (numero == null) {
            return false;
        }
        for (Collectivity c : this.collectivities.values()) {
            if (numero.equals(c.getNumero())) {
                return true;
            }
        }
        return false;
    }

    public boolean existsByNom(String nom) {
        if (nom == null) {
            return false;
        }
        for (Collectivity c : this.collectivities.values()) {
            if (nom.equals(c.getNom())) {
                return true;
            }
        }
        return false;
    }

    public Collectivity update(Collectivity collectivity) {
        if (collectivity.getId() != null && this.collectivities.containsKey(collectivity.getId())) {
            this.collectivities.put(collectivity.getId(), collectivity);
            return collectivity;
        }
        return null;
    }

    public void deleteById(Long id) {
        this.collectivities.remove(id);
    }
}


