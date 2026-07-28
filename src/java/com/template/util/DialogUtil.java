package com.template.util; // Mude para com.template se não criou a pasta util

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class DialogUtil {

    /**
     * Mostra uma mensagem de informação/sucesso genérica.
     */
    public static void mostrarInformacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Remove aquele cabeçalho grande padrão
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Mostra uma mensagem de erro.
     */
    public static void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Mostra um diálogo de confirmação (Sim / Não).
     * Retorna 'true' se o usuário clicar em OK, 'false' se cancelar.
     */
    public static boolean mostrarConfirmacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
}