package streams;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
public class Pessoa {

	private String nome;
	private int idade;
	private double salario;
	private Genero genero;
	
	public Pessoa() {
		super();
	}
	public Pessoa(String nome, int idade, double salario) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.salario = salario;
	}
	
	
	public Pessoa(String nome, int idade, double salario, Genero genero) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.salario = salario;
		this.setGenero(genero);
	}
	public static List<Pessoa> bancoDePessoas(){
		return asList(
				new Pessoa("Marcelo", 62, 2400, Genero.MASCULINO),
				new Pessoa("Camila", 11, 2000,Genero.FEMININO),
				new Pessoa("Gabriel", 29, 2100, Genero.MASCULINO),
				new Pessoa("Rafael", 14, 2800,Genero.MASCULINO),
				new Pessoa("Jade", 23, 3400, Genero.FEMININO),
				new Pessoa("Moises", 16, 3400, Genero.MASCULINO),
				new Pessoa("Rita", 24, 4500, Genero.FEMININO),
				new Pessoa("David", 34, 4400, Genero.MASCULINO)
				
				);
	}
	@Override
	public int hashCode() {
		return Objects.hash(idade, nome, salario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return idade == other.idade && Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(salario) == Double.doubleToLongBits(other.salario);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", idade=" + idade + ", salario=" + salario + "]";
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
}
