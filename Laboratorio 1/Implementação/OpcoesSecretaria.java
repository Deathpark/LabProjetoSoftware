public enum OpcoesSecretaria {
    OPCAO_MENU_PROFESSOR("professor(a)"),
    OPCAO_MENU_ALUNO("aluno(a)"),
    OPCAO_MENU_DISCIPLINA("disciplina");

    private String nome;

    private OpcoesSecretaria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
