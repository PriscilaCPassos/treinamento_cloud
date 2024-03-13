package br.com.treinamento.dto;

import br.com.treinamento.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "clients")
public class ClientDTO implements Serializable {

        @Id
        private String id;
        private String name;
        private String cpf;
        private String email;
        private String phone;


        // Convertendo objetos de uma classe para outra classe.
        public  ClientDTO (Client entity){
        BeanUtils.copyProperties(entity, this);

        }


        // OU ...
       /* public ClientDTO(Client client) {
            this.id = client.getId();
            this.name = client.getName();
            this.email = client.getEmail();
            this.cpf = client.getCpf();
            this.phone = client.getPhone();
        } */

}
