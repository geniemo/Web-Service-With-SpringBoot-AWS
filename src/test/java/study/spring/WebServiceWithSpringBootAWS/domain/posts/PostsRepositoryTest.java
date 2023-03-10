package study.spring.WebServiceWithSpringBootAWS.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndLoadPosts() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("kor98won@gmail.com")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntitySavePosts() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 1, 12, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

    @Test
    public void findAllOrderById() throws Exception {
        // given
        postsRepository.save(Posts.builder()
                .title("posts1")
                .content("content1")
                .author("author1")
                .build());
        postsRepository.save(Posts.builder()
                .title("posts2")
                .content("content2")
                .author("author2")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAllByOrderByIdDesc();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo("posts2");
    }
}