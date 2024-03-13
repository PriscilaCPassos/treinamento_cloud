package br.com.treinamento.controller;

import br.com.treinamento.dto.ClientDTO;
import br.com.treinamento.model.Client;
import br.com.treinamento.repository.ClientRepository;
import br.com.treinamento.service.ClientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

        @Autowired
        private ClientService service;

        @Autowired
        private ClientRepository repository;

        // Buscar Todos
        @GetMapping
        public ResponseEntity <List<ClientDTO>> findAll() {
                return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
                /*return Arrays.asList(Client.builder()
                        .name("Priscila")
                        .email("priscila.passos@gmail.com")
                        .phone("9994545429").build());
                        */
        }

       // Buscar por Id
       @GetMapping("/{id}")
        public ResponseEntity <ClientDTO> findById(@PathVariable String id){
         return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        }

        // Criar
        @PostMapping
        public ResponseEntity <ClientDTO> create(@RequestBody ClientDTO entity) {
               // entity.setId(ObjectId.get().toString());
               return ResponseEntity.status(HttpStatus.OK).body(service.create(entity));
               // return new Client();
        }


        // Atualizar
        @PutMapping("/{id}")
        public ResponseEntity <ClientDTO> updateClient(@PathVariable String id, @RequestBody ClientDTO clientDTO){
            return ResponseEntity.status(HttpStatus.OK).body(service.updateClient(id, clientDTO));

        }

        // Deletar
        @DeleteMapping("/{id}")
        public void deleteClient(@PathVariable String id) {
            service.deleteClient(id);
        }

}

