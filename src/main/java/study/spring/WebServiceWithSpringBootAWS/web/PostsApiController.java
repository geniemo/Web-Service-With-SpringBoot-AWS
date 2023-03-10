package study.spring.WebServiceWithSpringBootAWS.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.spring.WebServiceWithSpringBootAWS.service.posts.PostsService;
import study.spring.WebServiceWithSpringBootAWS.web.dto.PostsResponseDto;
import study.spring.WebServiceWithSpringBootAWS.web.dto.PostsSaveRequestDto;
import study.spring.WebServiceWithSpringBootAWS.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
