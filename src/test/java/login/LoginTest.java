package login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach() {

        this.paginaDeLogin = new LoginPage();
     }

    @AfterEach
    public void afterEach() {

        this.paginaDeLogin.quit();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {

       paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
       paginaDeLogin.efetuaLogin();
       Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
       Assert.assertEquals("fulano",paginaDeLogin.getNomeUsuarioLogado());
    }
    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        paginaDeLogin.preencherFormularioDeLogin("invalido", "1234");
        paginaDeLogin.efetuaLogin();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
        Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
        Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        paginaDeLogin.navegaParaPaginaDeLances();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));

    }

}
