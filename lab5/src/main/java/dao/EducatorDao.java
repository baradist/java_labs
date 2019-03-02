package dao;

import model.Educator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class EducatorDao implements Dao<Long, Educator> {
    private static Map<Long, Educator> map = new ConcurrentHashMap<>();
    private static AtomicLong count = new AtomicLong();

    @Override
    public Long create(Educator educator) {
        educator.setId(count.incrementAndGet());
        map.put(educator.getId(), educator);
        return educator.getId();
    }

    @Override
    public Optional<Educator> getById(Long id) {
        return Optional.of(map.get(id));
    }

    @Override
    public void update(Educator educator) {
        map.put(educator.getId(), educator);
    }

    @Override
    public void delete(Long id) {
        map.remove(id);
    }

    @Override
    public List<Educator> getList() {
        return new ArrayList<>(map.values());
    }
}
