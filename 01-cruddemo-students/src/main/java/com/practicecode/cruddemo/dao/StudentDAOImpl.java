package com.practicecode.cruddemo.dao;

import com.practicecode.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    private EntityManager entityManager;
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
    @Override
    public Student findById(Integer id){
        return entityManager.find(Student.class,1);
    }
    @Override
    public List<Student> findAll(){
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        return theQuery.getResultList();
    }
    @Override
    public List<Student> findByLastName(String theLastName){
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);
        theQuery.setParameter("theData", theLastName);
        return theQuery.getResultList();
    }
    @Override
    @Transactional
    public void update(Student theStudent){
        entityManager.merge(theStudent);
    }
    @Override
    @Transactional
    public void delete(Integer id){
        Student myStudent = entityManager.find(Student.class, id);
        entityManager.remove(myStudent);
    }
    @Override
    @Transactional
    public int deleteAllStudents(){
        int numOfRows = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numOfRows;
    }
}
