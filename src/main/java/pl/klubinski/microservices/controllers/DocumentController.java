package pl.klubinski.microservices.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.klubinski.microservices.model.Document;
import pl.klubinski.microservices.repository.DocumentRepository;

@RestController
@RequestMapping(value = "documents")
public class DocumentController {

  private final DocumentRepository documentRepository;

  DocumentController(DocumentRepository documentRepository) {
    this.documentRepository = documentRepository;
  }

  @PostMapping()
  void lendDocument(@RequestBody Document document) {
    documentRepository.saveAndFlush(document);
  }

}
