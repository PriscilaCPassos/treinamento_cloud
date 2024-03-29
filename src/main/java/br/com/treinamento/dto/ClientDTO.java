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

        public  ClientDTO (Client entity){
        BeanUtils.copyProperties(entity, this);

        }

}
