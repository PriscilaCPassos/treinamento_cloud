package br.com.treinamento.service;

import br.com.treinamento.model.Client;
import br.com.treinamento.repository.ClientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    // Buscar Todos
    public List<Client> findAll() {
        return repository.findAll();
    }

    // Buscar por Id
    public Client findById(String id){
        return repository.findById(id).get();
    }

    // Criar
    public Client create(Client entity) {
        entity.setId(ObjectId.get().toString());
        return repository.save(entity);
        // return new Client();
    }


    // Atualizar
    public Client updateClient(String id, Client client){

        Client entity = repository.findById(id).get();

        entity.setName(client.getName());
        entity.setEmail(client.getEmail());
        entity.setPhone(client.getPhone());
        entity.setCpf(client.getCpf());

        return repository.save(entity);

    }

    // Deletar
    public void deleteClient(String id) {
        repository.deleteById(id);
    }







}
