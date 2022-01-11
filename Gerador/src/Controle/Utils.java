package Controle;

/**
 *
 * @author Alisson Jaques
 */
public class Utils {

    public static String preencheCom(String linha_a_preencher, String letra, int tamanho, int direcao) {

        // Checa se Linha a preencher é nula ou branco
        if (linha_a_preencher == null || linha_a_preencher.trim() == "") {
            linha_a_preencher = "";
        }

        /*
        // Enquanto Linha a preencher possuir 2 espaços em branco seguidos, substitui por 1 espaço apenas
        while (linha_a_preencher.contains(" ")) {
            linha_a_preencher = linha_a_preencher.replaceAll("  ", " ").trim();
        }
         */
        // Retira caracteres estranhos
        linha_a_preencher = linha_a_preencher.replaceAll("[./-]", "");

        String caixaAlta = linha_a_preencher.toUpperCase();
        StringBuffer sb = new StringBuffer(caixaAlta);

        if (direcao == 1) { // a Esquerda
            for (int i = sb.length(); i < tamanho; i++) {
                sb.insert(0, letra);
            }
        } else if (direcao == 2) { // a Direita
            for (int i = sb.length(); i < tamanho; i++) {
                sb.append(letra);
            }
        }
        return sb.toString();
    }

    public static double truncar(double d, int p) {
        String str = Double.toString(d);
        int contador = 0;
        for (int i = str.indexOf("."); i < str.length() - 1; i++) {
            if (str.charAt(i) == '.') {

            } else {
                contador++;
            }
        }
        if (contador < 2) {
            return d;
        } else {
            str = str.substring(0, str.indexOf(".") + p + 1);
            return Double.parseDouble(str);
        }
    }
}
