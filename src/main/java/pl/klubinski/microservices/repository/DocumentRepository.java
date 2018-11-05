package pl.klubinski.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klubinski.microservices.model.Document;


public interface DocumentRepository extends JpaRepository<Document, Long> {

}
