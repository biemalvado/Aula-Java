package com.template.controller;

import com.template.model.dto.PassarinhoDTO;
import com.template.service.PassarinhoService;
import com.template.util.DialogUtil;
import com.template.util.PassarinhoValidator;
import com.template.util.Servicos;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PassarinhoController {

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

    private final PassarinhoService passarinhoService = new PassarinhoService();

    @FXML
    private void initialize() {
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colCativeiro.setCellValueFactory(new PropertyValueFactory<>("cativeiro"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        spnIdade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 150, 0));

        configurarBindingsEListener();
        carregarPassarinhos();
    }

    private void configurarBindingsEListener() {
        var selecao = tblPassarinho.getSelectionModel().selectedItemProperty();

        btnCadastrar.disableProperty().bind(selecao.isNotNull());
        btnAtualizar.disableProperty().bind(selecao.isNull());
        btnExcluir.disableProperty().bind(selecao.isNull());

        selecao.addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtEspecie.setText(newVal.getEspecie());
                chkCativeiro.setSelected(newVal.isCativeiro());
                spnIdade.getValueFactory().setValue(newVal.getIdade());
                lblMensagem.setText("");
            }
        });
    }

    @FXML
    private void btnCadastrarAction() {
        if (!isEntradaValida()) return;

        try {
            passarinhoService.cadastrar(txtEspecie.getText(), chkCativeiro.isSelected(), spnIdade.getValue());
            finalizarAcao("Passarinho cadastrado com sucesso!");
        } catch (Exception e) {
            DialogUtil.mostrarErro("Erro no Sistema", e.getMessage());
        }
    }

    @FXML
    private void btnAtualizarAction() {
        PassarinhoDTO selecionado = tblPassarinho.getSelectionModel().getSelectedItem();
        if (selecionado == null || !isEntradaValida()) return;

        try {
            passarinhoService.atualizar(selecionado.getId(), txtEspecie.getText(), chkCativeiro.isSelected(), spnIdade.getValue());
            finalizarAcao("Registro atualizado com sucesso!");
        } catch (Exception e) {
            DialogUtil.mostrarErro("Erro no Sistema", e.getMessage());
        }
    }

    @FXML
    private void btnExcluirAction() {
        PassarinhoDTO selecionado = tblPassarinho.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;

        if (DialogUtil.mostrarConfirmacao("Confirmar", "Deseja excluir '" + selecionado.getEspecie() + "'?")) {
            try {
                passarinhoService.deletar(selecionado.getId());
                finalizarAcao("Passarinho removido com sucesso!");
            } catch (Exception e) {
                DialogUtil.mostrarErro("Erro no Sistema", e.getMessage());
            }
        }
    }

    @FXML
    private void btnLimparAction() {
        Servicos.limparCampos(txtEspecie, chkCativeiro, spnIdade, tblPassarinho);
        lblMensagem.setText("");
    }

    @FXML
    private void carregarPassarinhos() {
        try {
            tblPassarinho.setItems(FXCollections.observableArrayList(passarinhoService.listarTodos()));
        } catch (Exception e) {
            DialogUtil.mostrarErro("Falha", "Erro ao carregar a lista.");
        }
    }

    // ... rest of the controller remains the same ...

    private boolean isEntradaValida() {
        try {
            PassarinhoValidator.validarPassarinho(txtEspecie.getText(), spnIdade.getValue());
            return true;
        } catch (IllegalArgumentException e) {
            DialogUtil.mostrarErro("Validação", e.getMessage());
            txtEspecie.requestFocus();
            return false;
        }
    }

    // ... rest of the controller remains the same ...

    private void finalizarAcao(String mensagem) {

        Servicos.limparCampos(txtEspecie, chkCativeiro, spnIdade, tblPassarinho);


        carregarPassarinhos();
        Servicos.mostrarMensagemSucesso(lblMensagem, mensagem);
    }
}