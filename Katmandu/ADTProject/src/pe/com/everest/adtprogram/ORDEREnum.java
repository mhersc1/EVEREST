package pe.com.everest.adtprogram;

/**
 * Created by MHER_ on 17/03/2018.
 */
public enum ORDEREnum {

    ASC("ASC"),
    DESC("DESC");

    ORDEREnum(String codigo) {
        this.codigo = codigo;
    }

    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
