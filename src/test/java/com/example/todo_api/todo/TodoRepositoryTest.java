package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TodoRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TodoRepository todoRepository;

    @Test
    @Transactional
    @Rollback(false)
    void todoSaveTest() {
        // 트랜잭션의 시작
        Todo todo = new Todo("todo context", false, null);
        todoRepository.save(todo);

        Assertions.assertThat(todo.getId()).isNotNull();

        //트랜잭션 종료 => 커밋
        // 에러 발생시 자동 롤백

        // 테스트 환경 기준에서는 에러 없어도 자동으로 롤백 => 수동으로 롤백 꺼주면 됨
    }

    @Test
    @Transactional
    void todoFindOneByIdTest() {
        // 데이터베이스에 테스트용 데이터 넣어줌
        // given
        Todo todo = new Todo("todo content", false, null);
        todoRepository.save(todo);
        todoRepository.flushAndClear();

        // when
        Todo findTodo = todoRepository.findById(todo.getId());

        // then
        Assertions.assertThat(findTodo.getId()).isEqualTo(todo.getId());

    }

    @Test
    @Transactional
    void todoFindAllTest() {
        Todo todo1 = new Todo("todo content1", false, null);
        Todo todo2 = new Todo("todo content2", false, null);
        Todo todo3 = new Todo("todo content3", false, null);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> todoList = todoRepository.findAll();

        Assertions.assertThat(todoList).hasSize(3);
        System.out.println(todoList);
    }

    @Test
    @Transactional
    void todoFindAllByMemberTest() {
        Member member1 = new Member();
        Member member2 = new Member();
        memberRepository.save(member1);
        memberRepository.save(member2);

        Todo todo1 = new Todo("todo content1", false, member1);
        Todo todo2 = new Todo("todo content2", false, member2);
        Todo todo3 = new Todo("todo content3", false, member2);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> Member1todoList = todoRepository.findAllByMember(member1);
        List<Todo> Member2todoList = todoRepository.findAllByMember(member2);

        Assertions.assertThat(Member1todoList).hasSize(1);
        Assertions.assertThat(Member2todoList).hasSize(2);


    }

    @Test
    @Transactional
    @Rollback(false)
    void todoUpdateTest() {
        Todo todo1 = new Todo("todo content1", false, null);
        todoRepository.save(todo1);
        todoRepository.flushAndClear();
        Todo findTodo1 = todoRepository.findById(todo1.getId());
        findTodo1.updateContent("new content");
    }

    @Test
    @Transactional
    @Rollback(false)
    void todoDeleteTest() {
        Todo todo1 = new Todo("todo content1", false, null);
        Todo todo2 = new Todo("todo content2", false, null);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.flushAndClear();

        todoRepository.deleteById(todo2.getId());


    }

    @AfterAll
    static void doNotFinish() {
        System.out.println("test finished");
        while (true) {
        }
    }

}
