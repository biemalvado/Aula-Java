package com.template.controller;

import com.template.model.dto.PassarinhoDTO;
import com.template.service.PassarinhoService;
import com.template.util.DialogUtil;
import com.template.util.PassarinhoValidator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class MainController {

    @FXML private TextField txtEspecie;
    @FXML private CheckBox chkCativeiro;
    @FXML private Spinner<Integer> spnIdade;
    @FXML private Label lblMensagem;

    @FXML private Button btnCadastrar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;

    @FXML private TableView<PassarinhoDTO> tblPassarinho;
    @FXML private TableColumn<PassarinhoDTO, String> colEspecie;
    @FXML private TableColumn<PassarinhoDTO, Boolean> colCativeiro;
    @FXML private TableColumn<PassarinhoDTO, Integer> colIdade;

    // Instancia o serviço e o validador (Exatamente como no slide!)
    private final PassarinhoService passarinhoService = new PassarinhoService();
    private final PassarinhoValidator passarinhoValidator = new PassarinhoValidator();

    @FXML
    private void initialize() {
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colCativeiro.setCellValueFactory(new PropertyValueFactory<>("cativeiro"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 150, 0);
        spnIdade.setValueFactory(valueFactory);

        tblPassarinho.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnAtualizar.setDisable(false);
                btnExcluir.setDisable(false);
                btnCadastrar.setDisable(true);

                txtEspecie.setText(newSelection.getEspecie());
                chkCativeiro.setSelected(newSelection.isCativeiro());
                spnIdade.getValueFactory().setValue(newSelection.getIdade());
                lblMensagem.setText("");
            } else {
                btnAtualizar.setDisable(true);
                btnExcluir.setDisable(true);
                btnCadastrar.setDisable(false);
            }
        });

        carregarPassarinhos();
    }

    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        // Delega toda a validação para o PassarinhoValidator
        try {
            passarinhoValidator.validarPassarinho(txtEspecie.getText());
        } catch (IllegalArgumentException e) {
            DialogUtil.mostrarErro("Aviso de Validação", e.getMessage());
            txtEspecie.requestFocus();
            return; // Se a validação falhar, para por aqui
        }

        // Se a validação passou, continua com a lógica de negócio
        try {
            // No slide, o professor cria o DTO no Controller e passa pro Service
            PassarinhoDTO objPassarinho = new PassarinhoDTO(txtEspecie.getText(), chkCativeiro.isSelected(), spnIdade.getValue());

            // Você precisará ajustar o seu Service para receber o objeto inteiro (objPassarinho)
            // em vez de receber os 3 parâmetros separados.
            passarinhoService.cadastrar(objPassarinho.getEspecie(), objPassarinho.isCativeiro(), objPassarinho.getIdade());

            limparCampos();
            carregarPassarinhos();
            mostrarMensagemSucesso("Passarinho cadastrado com sucesso!");

        } catch (Exception e) {
            DialogUtil.mostrarErro("Erro no Sistema", e.getMessage());
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        PassarinhoDTO selecionado = tblPassarinho.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            // Delega toda a validação
            try {
                passarinhoValidator.validarPassarinho(txtEspecie.getText());
            } catch (IllegalArgumentException e) {
                DialogUtil.mostrarErro("Aviso de Validação", e.getMessage());
                txtEspecie.requestFocus();
                return; // Se a validação falhar, para por aqui
            }

            // Se a validação passou, continua com a lógica de negócio
            try {
                passarinhoService.atualizar(selecionado.getId(), txtEspecie.getText(), chkCativeiro.isSelected(), spnIdade.getValue());

                limparCampos();
                carregarPassarinhos();
                mostrarMensagemSucesso("Registro atualizado com sucesso!");

            } catch (Exception e) {
                DialogUtil.mostrarErro("Erro no Sistema", e.getMessage());
            }
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        PassarinhoDTO selecionado = tblPassarinho.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            boolean confirmou = DialogUtil.mostrarConfirmacao(
                    "Confirmar Exclusão",
                    "Tem certeza que deseja excluir o passarinho '" + selecionado.getEspecie() + "'?"
            );

            if (!confirmou) return;

            try {
                passarinhoService.deletar(selecionado.getId());
                limparCampos();
                carregarPassarinhos();
                mostrarMensagemSucesso("Passarinho removido com sucesso!");
            } catch (Exception e) {
                DialogUtil.mostrarErro("Erro no Sistema", e.getMessage());
            }
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
        lblMensagem.setText("");
    }

    private void limparCampos() {
        txtEspecie.clear();
        chkCativeiro.setSelected(false);
        spnIdade.getValueFactory().setValue(0);
        tblPassarinho.getSelectionModel().clearSelection();
        txtEspecie.requestFocus();
    }

    @FXML
    private void carregarPassarinhos() {
        try {
            List<PassarinhoDTO> lista = passarinhoService.listarTodos();
            tblPassarinho.setItems(FXCollections.observableArrayList(lista));
        } catch (Exception e) {
            DialogUtil.mostrarErro("Falha de Conexão", "Erro ao carregar a lista de passarinhos.");
        }
    }

    private void mostrarMensagemSucesso(String mensagem) {
        lblMensagem.setText(mensagem);
        lblMensagem.setStyle("-fx-text-fill: #2e7d32;"); // Verde
    }
}