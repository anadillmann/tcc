package Conexao;

import java.util.List;

public interface Dao<E> {

    public void adiciona(E m);

    public boolean pesquisa(E m);

    public List<E> pesquisaTodos();
}
