package com.template.util;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Servicos {


    public static void limparCampos(TextField txtEspecie, CheckBox chkCativeiro,
                                    Spinner<Integer> spnIdade, TableView<?> tblPassarinho) {
        txtEspecie.clear();
        chkCativeiro.setSelected(false);
        spnIdade.getValueFactory().setValue(0);
        tblPassarinho.getSelectionModel().clearSelection();
        txtEspecie.requestFocus();
    }


    public static void mostrarMensagemSucesso(Label lblMensagem, String mensagem) {
        lblMensagem.setText(mensagem);
        lblMensagem.setStyle("-fx-text-fill: #2e7d32;");
    }
}