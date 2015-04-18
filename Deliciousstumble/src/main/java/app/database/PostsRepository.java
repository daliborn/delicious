package app.database;

import org.springframework.data.repository.CrudRepository;

import app.domain.Post;

public interface PostsRepository extends CrudRepository<Post, Long> {

}
