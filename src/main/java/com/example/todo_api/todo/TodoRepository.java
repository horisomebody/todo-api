package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {
    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Todo todo) {
        em.persist(todo);
    }
    // 단건 조회
    public Todo findById(Long todoId) {
        return em.find(Todo.class, todoId);
    }

    // 다건 조회
    public List<Todo> findAll() {
        return em.createQuery("select t from Todo as t", Todo.class).getResultList();
    }

    // 조건 조회
    public List<Todo> findAllByMember(Member member) {
        return em.createQuery("select t from Todo as t where t.member =: todo_member" ,Todo.class)
                .setParameter("todo_member", member)
                .getResultList();

    }
    // 삭제
    public void deleteById(Long todoId) {
        Todo todo = findById(todoId);
        em.remove(todo);

    }
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
