package Controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alisson Jaques
 */
public class MovimentoDeTerceiros {

    /**
     * Campos HEADER
     */
    private String header;
    private String codigoEmpresa;
    private String identificacaoEmpresa;
    private String codigoPagamento;
    private String codigoDestino;
    private String data;
    private String fillerHeader;

    /**
     * Campos DETALHE
     */
    private ArrayList<String> detalhe;

    /**
     * Campos TRAILLER
     */
    private String trailler;
    private String quantidadeLancADeb;
    private String valorTotalLancDebito;
    private String quantidadeLancACred;
    private String valorTotalLancCredito;
    private String fillerTrailler;

    public MovimentoDeTerceiros() {
        // HEADER
        header = "017564117";
        codigoEmpresa = "0000000";
        identificacaoEmpresa = "          ";
        codigoPagamento = "03";
        codigoDestino = "03";
        data = retornaDataAtual();

        fillerHeader = " ";
        for (int i = 0; i < 161; i++) {
            fillerHeader += " ";
        }
        
        // DETALHE
        detalhe = new ArrayList<>();

        // TRAILLER
        trailler = "9";
        quantidadeLancADeb = "00000";
        valorTotalLancDebito = "0";
        for (int i = 0; i < 16; i++) {
            valorTotalLancDebito += "0";
        }
        quantidadeLancACred = "00000";
        valorTotalLancCredito = "0";
        for (int i = 0; i < 16; i++) {
            valorTotalLancCredito += "0";
        }
        fillerTrailler = " ";
        for (int i = 0; i < 154; i++) {
            fillerTrailler += " ";
        }
    }
    
    public String retornaDataAtual(){
        String dia = "";
        String mes = "";
        String ano = "";
        Date dataAtual = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateFormat.format(dataAtual);
        for(int i=0; i<dataFormatada.length(); i++){
            if(i<3){
                if(dataFormatada.charAt(i)!='/'){
                    dia += dataFormatada.charAt(i);
                }
            }
            else if(i==3 || i==4){
                if(dataFormatada.charAt(i)!='/'){
                    mes += dataFormatada.charAt(i);
                }
            }
            else{
                if(dataFormatada.charAt(i)!='/'){
                    ano += dataFormatada.charAt(i);
                }
            }
        }
        return ano+mes+dia;
    }
    
    public String retornaHeader() {
        return getHeader() + getCodigoEmpresa() + getIdentificacaoEmpresa() + getCodigoPagamento() + getCodigoDestino() + getData() + getFiller();
    }

    public String retornaTrailler() {
        return getTrailler() + getQuantidadeLancADeb() + getValorTotalLancDebito() + getQuantidadeLancACred() + getValorTotalLancCredito() + getFillerTrailler();
    }
    
    public String retornaDetalhe(){
        String resultado = "";
        if(getDetalhe().isEmpty()){
            return resultado;
        }
        else{
           for(int i = 0; i<getDetalhe().size();i++){
               resultado += getDetalhe().get(i)+ "\r\n";
           }
           return resultado;
        }
    }
    
    public String retornaUmDetalhe(String tipoMovimento, String conta, String nome,
            String numeroDocumento, String valorLancamento, String infoComplementar){
        
        // String testeNumero = Utils.preencheCom("127" , "0" , 7 , 1);
        // String testeTexto = Utils.preencheCom("alisson", " ", 9, 2);
        String detalhe = "1";
        if(tipoMovimento.equals("C") || tipoMovimento.equals("D")){
            detalhe += tipoMovimento;        
        }
        else{
            detalhe += "C";
        }
        detalhe += Utils.preencheCom(conta,"0",10,1);
        if(nome.length()>40){
            detalhe += Utils.preencheCom("Nome muito grande para o campo"," ",40,2);
        }
        else{
            detalhe += Utils.preencheCom(nome," ",40,2);
        }
        detalhe += "99999999999999";
        detalhe += "            ";
        detalhe += "000";
        detalhe += "                    ";
        if(numeroDocumento.length()>10){
            numeroDocumento = "SALARIO   ";
        }
        else{
            detalhe += Utils.preencheCom(numeroDocumento," ",10,2);
        }
        detalhe += Utils.preencheCom(valorLancamento,"0",17,1);
        detalhe += "          ";
        detalhe += "000";
        detalhe += "N";
        if(infoComplementar.length()>40){
            detalhe += Utils.preencheCom("SALARIO"," ",40,2);
        }
        else{
            detalhe += Utils.preencheCom(infoComplementar," ",40,2);
        }
        String fillerDetalhe = " "; 
        for (int i = 0; i < 17; i++) {
            fillerDetalhe += " ";
        }
        detalhe += fillerDetalhe;
        return detalhe;
    }
    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return the codigoEmpresa
     */
    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    /**
     * @param codigoEmpresa the codigoEmpresa to set
     */
    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = Utils.preencheCom(codigoEmpresa,"0",(int)7,(int)1);
    }

    /**
     * @return the identificacaoEmpresa
     */
    public String getIdentificacaoEmpresa() {
        return identificacaoEmpresa;
    }

    /**
     * @param identificacaoEmpresa the identificacaoEmpresa to set
     */
    public void setIdentificacaoEmpresa(String identificacaoEmpresa) {
        this.identificacaoEmpresa = identificacaoEmpresa;
    }

    /**
     * @return the codigoPagamento
     */
    public String getCodigoPagamento() {
        return codigoPagamento;
    }

    /**
     * @param codigoPagamento the codigoPagamento to set
     */
    public void setCodigoPagamento(String codigoPagamento) {
        this.codigoPagamento = Utils.preencheCom(codigoPagamento,"0",2,1);
    }

    /**
     * @return the codigoDestino
     */
    public String getCodigoDestino() {
        return codigoDestino;
    }

    /**
     * @param codigoDestino the codigoDestino to set
     */
    public void setCodigoDestino(String codigoDestino) {
        this.codigoDestino = Utils.preencheCom(codigoDestino,"0",2,1);
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        String dia = "";
        String mes = "";
        String ano = "";
        for(int i=0; i<data.length(); i++){
            if(i<3){
                if(data.charAt(i)!='/'){
                    dia += data.charAt(i);
                }
            }
            else if(i==3 || i==4){
                if(data.charAt(i)!='/'){
                    mes += data.charAt(i);
                }
            }
            else{
                if(data.charAt(i)!='/'){
                    ano += data.charAt(i);
                }
            }
        }
        this.data = ano+mes+dia;
    }

    /**
     * @return the filler
     */
    public String getFiller() {
        return getFillerHeader();
    }

    /**
     * @param filler the filler to set
     */
    public void setFiller(String filler) {
        this.setFillerHeader(filler);
    }

    /**
     * @return the detalhe
     */
    public ArrayList<String> getDetalhe() {
        return detalhe;
    }

    /**
     * @param detalhe the detalhe to set
     */
    public void setDetalhe(ArrayList<String> detalhe) {
        this.detalhe = detalhe;
    }

    /**
     * @return the trailler
     */
    public String getTrailler() {
        return trailler;
    }

    /**
     * @param trailler the trailler to set
     */
    public void setTrailler(String trailler) {
        this.trailler = trailler;
    }

    /**
     * @return the fillerHeader
     */
    public String getFillerHeader() {
        return fillerHeader;
    }

    /**
     * @param fillerHeader the fillerHeader to set
     */
    public void setFillerHeader(String fillerHeader) {
        this.fillerHeader = fillerHeader;
    }

    /**
     * @return the quantidadeLancADeb
     */
    public String getQuantidadeLancADeb() {
        return quantidadeLancADeb;
    }

    /**
     * @param quantidadeLancADeb the quantidadeLancADeb to set
     */
    public void setQuantidadeLancADeb(String quantidadeLancADeb) {
        this.quantidadeLancADeb = Utils.preencheCom(quantidadeLancADeb,"0",5,1);
    }

    /**
     * @return the valorTotalLancDebito
     */
    public String getValorTotalLancDebito() {
        return valorTotalLancDebito;
    }

    /**
     * @param valorTotalLancDebito the valorTotalLancDebito to set
     */
    public void setValorTotalLancDebito(String valorTotalLancDebito) {
        this.valorTotalLancDebito = Utils.preencheCom(valorTotalLancDebito,"0",17,1);
    }

    /**
     * @return the quantidadeLancACred
     */
    public String getQuantidadeLancACred() {
        return quantidadeLancACred;
    }

    /**
     * @param quantidadeLancACred the quantidadeLancACred to set
     */
    public void setQuantidadeLancACred(String quantidadeLancACred) {
        this.quantidadeLancACred = Utils.preencheCom(quantidadeLancACred,"0",5,1);
    }

    /**
     * @return the valorTotalLancCredito
     */
    public String getValorTotalLancCredito() {
        return valorTotalLancCredito;
    }

    /**
     * @param valorTotalLancCredito the valorTotalLancCredito to set
     */
    public void setValorTotalLancCredito(String valorTotalLancCredito) {
        this.valorTotalLancCredito = Utils.preencheCom(valorTotalLancCredito,"0",17,1);
    }

    /**
     * @return the fillerTrailler
     */
    public String getFillerTrailler() {
        return fillerTrailler;
    }

    /**
     * @param fillerTrailler the fillerTrailler to set
     */
    public void setFillerTrailler(String fillerTrailler) {
        this.fillerTrailler = fillerTrailler;
    }

    @Override
    public String toString() {
        return retornaHeader() + "\r\n" + retornaDetalhe() + retornaTrailler();
    }
}
