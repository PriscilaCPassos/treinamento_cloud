package br.com.treinamento.controller;

import br.com.treinamento.model.Client;
import br.com.treinamento.repository.ClientRepository;
import br.com.treinamento.service.ClientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
        public List<Client> findAll() {
                return service.findAll();
                /*return Arrays.asList(Client.builder()
                        .name("Priscila")
                        .email("priscila.passos@gmail.com")
                        .phone("9994545429").build());
                        */
        }

       // Buscar por Id
       @GetMapping("/{id}")
        public Client findById(@PathVariable String id){
                return service.findById(id);
        }

        // Criar
        @PostMapping
        public Client create(@RequestBody Client entity) {
                entity.setId(ObjectId.get().toString());
               return service.create(entity);
               // return new Client();
        }


        // Atualizar
        @PutMapping("/{id}")
        public Client updateClient(@PathVariable String id, @RequestBody Client client){
            return service.updateClient(id, client);

        }

        // Deletar
        @DeleteMapping("/{id}")
        public void deleteClient(@PathVariable String id) {
            service.deleteClient(id);
        }

}

