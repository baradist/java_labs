package dao;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class StudentDao implements Dao<Long, Student> {
    private static Map<Long, Student> map = new ConcurrentHashMap<>();
    private static AtomicLong count = new AtomicLong();

    @Override
    public Long create(Student student) {
        student.setId(count.incrementAndGet());
        map.put(student.getId(), student);
        return student.getId();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return Optional.of(map.get(id));
    }

    @Override
    public void update(Student student) {
        map.put(student.getId(), student);
    }

    @Override
    public void delete(Long id) {
        map.remove(id);
    }

    @Override
    public List<Student> getList() {
        return new ArrayList<>(map.values());
    }
}
