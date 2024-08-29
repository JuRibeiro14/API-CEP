import com.google.gson.annotations.SerializedName;

public class Endereco {
    @SerializedName("cep")
    private String cep;
    @SerializedName("logradouro")
    private String rua;
    @SerializedName("bairro")
    private String bairro;
    @SerializedName("localidade")
    private String cidade;
    @SerializedName("uf")
    private String estado;

    public Endereco(String cep, String rua, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Endereço{" +
                "CEP='" + cep + '\'' +
                ", RUA='" + rua + '\'' +
                ", BAIRRO='" + bairro + '\'' +
                ", CIDADE='" + cidade + '\'' +
                ", ESTADO='" + estado + '\'' +
                '}';
    }
}
