package com.epam.community.z.spring.testing.post.stat;

import com.epam.community.z.spring.testing.comment.CommentValidator;
import com.epam.community.z.spring.testing.post.Post;
import com.epam.community.z.spring.testing.post.PostRepository;
import com.epam.community.z.spring.testing.post.PostSanitizer;
import com.epam.community.z.spring.testing.post.PostService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = {
        PostService.class,
        StatisticsCollectorAspect.class,
        CommentValidator.class,
        PostSanitizer.class
})
@EnableAspectJAutoProxy
class StatisticsCollectorAspectTest {
    @Autowired
    private PostService postService;

    @Autowired
    private StatisticsCollectorAspect unitUnderTest;

    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        when(postRepository.findById(anyInt())).thenReturn(Optional.of(new Post()));
    }

    @Test
    @DirtiesContext
    void firstAttempt() {
        final Post first = postService.findOne(10);

        assertEquals(1, unitUnderTest.getVisits(10));
        assertEquals(0, unitUnderTest.getVisits(20));
    }

    @Test
    @DirtiesContext
    void secondAttempt() {
        final Post second = postService.findOne(20);

        assertEquals(1, unitUnderTest.getVisits(20));
        assertEquals(0, unitUnderTest.getVisits(10));
    }

    @Test
    void check_contextStarts() {
        assertAll(
                () -> assertNotNull(postService),
                () -> assertNotNull(unitUnderTest)
        );
    }
}