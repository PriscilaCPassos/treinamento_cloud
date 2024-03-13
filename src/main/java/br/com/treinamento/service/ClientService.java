package br.com.treinamento.service;

import br.com.treinamento.dto.ClientDTO;
import br.com.treinamento.model.Client;
import br.com.treinamento.repository.ClientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    // Buscar Todos
    public List<ClientDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }


    // Buscar por Id
    public ClientDTO findById(String id){
        Client entity = repository.findById(id).get();
        ClientDTO dto = new ClientDTO(entity);
        return dto;

        // OU
       /* public ClientDTO findById(String id){
            repository.findById(id).map(ClientDTO::new);
        */

    }


    // Criar
    public ClientDTO create(ClientDTO entity) {
        Client client = new Client(entity);
        repository.save(client);
        ClientDTO dto = new ClientDTO(client);
        return dto;
    }


    // Atualizar
    public ClientDTO updateClient(String id, ClientDTO clientDTO){

        Client entity = repository.findById(id).get();

        entity.setName(clientDTO.getName());
        entity.setEmail(clientDTO.getEmail());
        entity.setPhone(clientDTO.getPhone());
        entity.setCpf(clientDTO.getCpf());

        repository.save(entity);

        ClientDTO newClientDTO = new ClientDTO(entity);

        return newClientDTO;
    }


    // Deletar
    public void deleteClient(String id) {
        Client entity = repository.findById(id).get();
            repository.deleteById(id);
        }

}
