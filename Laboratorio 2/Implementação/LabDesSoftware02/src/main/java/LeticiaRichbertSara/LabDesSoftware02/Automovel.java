package LeticiaRichbertSara.LabDesSoftware02;

import javax.persistence.Entity;


@Entity
public class Automovel {
    private int ano;
    private String matricula;
    private String marca;
    private String modelo;
    private String placa;
    private String proprietario;


    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getProprietario() {
        return this.proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario.toUpperCase();
    }

    @GetMapping("/cadastrarAutomovel")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "othername", defaultValue = "World²") String othername) {
		return String.format("Hello %s %s!", name, othername);
	}

}
