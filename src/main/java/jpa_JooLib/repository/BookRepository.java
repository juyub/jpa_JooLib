package jpa_JooLib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpa_JooLib.entity.Book;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, Integer> {

}
