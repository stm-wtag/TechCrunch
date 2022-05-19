package src.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "Token_BlackList")
public class TokenBlockList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String token;

    public TokenBlockList() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
