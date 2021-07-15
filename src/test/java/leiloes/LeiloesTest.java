package leiloes;

import login.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.quit();
    }

    @Test
    public void deveriaCadastrarLeilao(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        CadastroLeilaoPage paginaDeCadastro = paginaDeLeiloes.carregaFormulario();

        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leila do dia" +hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
        Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
    }


}
