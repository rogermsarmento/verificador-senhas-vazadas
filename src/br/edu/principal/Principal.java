package br.edu.principal;

import br.edu.ui.swing.TelaPrincipal;

public class Principal {

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
        
        /**
         * O Swing recomenda que a criação/manipulação da interface seja feita na Event Dispatch Thread — EDT.
         * O invokeLater() está dizendo, de forma simplificada: Swing, execute este código na sua thread de interface.
         * new TelaPrincipal().setVisible(true)
         * Primeiro: new TelaPrincipal() cria nossa janela.
         * .setVisible(true) torna a janela visível. 
         * 
         */
        
        /*
        try(Scanner sc = new Scanner(System.in)) {
            
            AppFactory appFactory = new AppFactory();

            VerificadorSenha verificador = appFactory.criarVerificadorSenha();
            
            ConsoleUI consoleUI = new ConsoleUI();
                        
            consoleUI.exibirCabecalho();
            
            String senha = consoleUI.lerSenha(sc);
            
            consoleUI.exibirConsultando();
            
            try{
                ResultadoVerificacao resultado = verificador.verificar(senha);
                
                consoleUI.exibirStatusHttp(resultado.getStatusCode());
                
                consoleUI.exibirResultado(resultado.getQuantidadeEncontrada());
            }
            catch (NoSuchAlgorithmException e) {
                consoleUI.exibirErroHash();
            }
            catch (IOException e) {
                consoleUI.exibirErroConsulta();
            }
            catch (InterruptedException e) {
               consoleUI.exibirConsultaInterrompida();
            }
        }
        */
    }
}