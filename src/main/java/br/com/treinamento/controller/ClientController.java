package br.com.treinamento.controller;

import br.com.treinamento.model.Client;
import br.com.treinamento.repository.ClientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

        @Autowired
        private ClientRepository repository;

        // Buscar Todos
        @GetMapping
        public List<Client> list() {
                return repository.findAll();
                /*return Arrays.asList(Client.builder()
                        .name("Priscila")
                        .email("priscila.passos@gmail.com")
                        .phone("9994545429").build());
                        */
        }

       // Buscar por Id
       @GetMapping("/{id}")
        public Client findById(@PathVariable String id){
                return repository.findById(id).get();
        }

        // Criar
        @PostMapping
        public Client create(@RequestBody Client entity) {
                entity.setId(ObjectId.get().toString());
               return repository.save(entity);
               // return new Client();
        }


        // Atualizar
        @PutMapping("/{id}")
        public Client updateClient(@PathVariable String id, @RequestBody Client client){

            Client entity = repository.findById(id).get();

            entity.setName(client.getName());
            entity.setEmail(client.getEmail());
            entity.setPhone(client.getPhone());
            entity.setCpf(client.getCpf());

            return repository.save(entity);

        }

        // Deletar
        @DeleteMapping("/{id}")
        public void deleteClient(@PathVariable String id) {
            repository.deleteById(id);
        }

}

