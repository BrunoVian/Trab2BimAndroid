package br.unipar.trabalho2bimandroid.model;

public class NotasAluno {
    private Aluno aluno;
    private int nota;
    private String disciplina;
    private String bimestre;

    public NotasAluno() {

    }

    public NotasAluno(Aluno aluno, int nota, String disciplina, String bimestre) {
        this.aluno = aluno;
        this.nota = nota;
        this.disciplina = disciplina;
        this.bimestre = bimestre;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getBimestre() {
        return bimestre;
    }

    public void setBimestre(String bimestre) {
        this.bimestre = bimestre;
    }


}
