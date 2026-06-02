package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MainController {

    @FXML private TextField txtEspecie;
    @FXML private CheckBox chkCativeiro;
    @FXML private TextField txtIdade;

    @FXML private Button btnCadastrar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;

    @FXML private TableView<PassarinhoDTO> tblPassarinho;
    @FXML private TableColumn<PassarinhoDTO, String> colEspecie;
    @FXML private TableColumn<PassarinhoDTO, Boolean> colCativeiro;
    @FXML private TableColumn<PassarinhoDTO, Integer> colIdade;

    @FXML
    private void initialize() {
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colCativeiro.setCellValueFactory(new PropertyValueFactory<>("cativeiro"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        carregarPassarinhos();
    }

    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        try {
            String especie = txtEspecie.getText();
            boolean cativeiro = chkCativeiro.isSelected();
            int idade = Integer.parseInt(txtIdade.getText());

            PassarinhoDTO novoPassarinho = new PassarinhoDTO(especie, cativeiro, idade);
            PassarinhoDAO dao = new PassarinhoDAO();

            if (dao.cadastrar(novoPassarinho)) {
                carregarPassarinhos();
                limparCampos();
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: A idade deve ser um número válido.");
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        PassarinhoDTO selecionado = tblPassarinho.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            try {
                selecionado.setEspecie(txtEspecie.getText());
                selecionado.setCativeiro(chkCativeiro.isSelected());
                selecionado.setIdade(Integer.parseInt(txtIdade.getText()));

                PassarinhoDAO dao = new PassarinhoDAO();
                if (dao.atualizar(selecionado)) {
                    carregarPassarinhos();
                    limparCampos();
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: A idade deve ser um número válido.");
            }
        }
    }

    @FXML

    private void btnExcluirAction(ActionEvent event) {
        PassarinhoDTO selecionado = tblPassarinho.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            PassarinhoDAO dao = new PassarinhoDAO();

            // Pass the integer ID instead of the whole DTO object
            if (dao.deletar(selecionado.getId())) {
                carregarPassarinhos();
                limparCampos();
            }
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
    }

    private void limparCampos() {
        txtEspecie.clear();
        chkCativeiro.setSelected(false);
        txtIdade.clear();
        tblPassarinho.getSelectionModel().clearSelection();
    }

    @FXML
    private void selecionarPassarinho(MouseEvent event) {
        PassarinhoDTO selecionado = tblPassarinho.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            txtEspecie.setText(selecionado.getEspecie());
            chkCativeiro.setSelected(selecionado.isCativeiro());
            txtIdade.setText(String.valueOf(selecionado.getIdade()));
        }
    }

    @FXML
    private void carregarPassarinhos() {
        PassarinhoDAO dao = new PassarinhoDAO();
        List<PassarinhoDTO> lista = dao.listarTodos();
        tblPassarinho.setItems(FXCollections.observableArrayList(lista));
    }
}