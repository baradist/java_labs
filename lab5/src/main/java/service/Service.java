package service;

import dao.EducatorDao;
import dao.StudentDao;
import model.Educator;
import model.Person;
import model.Student;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Service {
    private static final Service INSTANCE = new Service(new StudentDao(), new EducatorDao());
    private StudentDao studentDao;
    private EducatorDao educatorDao;

    private Service() {
    }

    private Service(StudentDao studentDao, EducatorDao educatorDao) {
        this.studentDao = studentDao;
        this.educatorDao = educatorDao;
    }

    public static Service getInstance() {
        return INSTANCE;
    }

    public Long saveStudent(Student student) {
        return studentDao.create(student);
    }

    public List<Student> getStudents() {
        return studentDao.getList();
    }

    public Long saveEducator(Educator educator) {
        return educatorDao.create(educator);
    }

    public List<Educator> getEducators() {
        return educatorDao.getList();
    }

    public List<Person> getPersons() {
        return Stream.of(educatorDao.getList(), studentDao.getList())
                .flatMap(Collection::stream)
                .map(e -> (Person) e)
                .collect(Collectors.toList());
    }
}
