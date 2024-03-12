package br.com.treinamento.model;

import br.com.treinamento.dto.ClientDTO;
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
public class Client implements Serializable {

    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String phone;

    public Client (ClientDTO entityDTO){
        BeanUtils.copyProperties(entityDTO, this);
    }

    public Client() {
        super();
    }

}
