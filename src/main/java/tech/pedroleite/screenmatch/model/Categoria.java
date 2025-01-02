package tech.pedroleite.screenmatch.model;

public enum Categoria {
    ACAO("Action"),
    ROMANCE("Romance"),
    CRIME("Crime"),
    COMEDIA("Comedy"),
    DRAMA("Drama");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text) {
        for(Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equals(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida" + text);
    }
}
