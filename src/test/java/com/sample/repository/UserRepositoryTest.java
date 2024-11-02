package com.sample.repository;

import com.sample.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        // 테스트를 위한 사용자 객체 설정
        user = User.builder()
                .email("test@example.com")
                .password("password") // 실제 테스트에서는 암호화된 비밀번호를 사용해야 합니다.
                .nickname("testuser")
                .selfIntroduction("Hello!")
                .build();
    }

    @Test
    @DisplayName("사용자 정보를 저장하고 저장된 사용자 확인")
    public void testSaveUser() {
        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull(); // ID가 생성되었는지 확인
        assertThat(savedUser.getEmail()).isEqualTo("test@example.com");
        assertThat(savedUser.getNickname()).isEqualTo("testuser");
    }

    @Test
    @Rollback
    @DisplayName("이메일로 사용자를 조회하고 조회된 사용자 확인")
    public void testFindUserByEmail() {
        // Given
        userRepository.save(user); // 먼저 사용자 저장

        // When
        User foundUser = userRepository.findByEmail("test@example.com");

        // Then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getNickname()).isEqualTo("testuser");
        assertThat(foundUser.getSelfIntroduction()).isEqualTo("Hello!");
    }

}