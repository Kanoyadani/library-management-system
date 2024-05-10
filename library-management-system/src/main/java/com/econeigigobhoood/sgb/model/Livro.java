package com.econeigigobhoood.sgb.model;

public class Livro {
    private int Idlivro;
    private String Nome;
    private String Autor;
    private int Paginas;
    private String Status;

    // Construtorzin sem o ID para criação de livretos novinhos em folha e... com folhas Heheheheheahahahah
    public Livro(String Nome, String Autor, int Paginas) {
        this.Nome = Nome;
        this.Autor = Autor;
        this.Paginas = Paginas;
        this.Status = "Em estoque";
    }

    // Construtorzin com ID para quando precisarmos consultar livretos que estão em nosso H2
    public Livro(int Idlivro, String Nome, String Autor, int Paginas,String Status) {
        this.Idlivro = Idlivro;
        this.Nome = Nome;
        this.Autor = Autor;
        this.Paginas = Paginas;
        this.Status = Status;
    }

    // Getter n Setter cheirosinho
    public int getIdlivro() { return Idlivro; }
    public  int setIdlivro(int Idlivro) { return this.Idlivro = Idlivro; }

    public String getNome() { return Nome; }
    public String setNome(String Nome) { return this.Nome = Nome; }

    public String getAutor() { return Autor; }
    public String setAutor(String Autor) { return this.Autor = Autor; }

    public int getPaginas() { return Paginas; }
    public int setPaginas(int Paginas) { return this.Paginas = Paginas; }

    public String getStatus() { return Status; }
    public String setStatus(String Status) { return this.Status = Status; }

}
