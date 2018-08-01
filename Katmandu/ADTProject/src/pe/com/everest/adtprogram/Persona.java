package pe.com.everest.adtprogram;

import java.math.BigDecimal;

public class Persona {
    public static class Builder {
        private int dni;
        private String nombres;
        private String apellidos;
        private String direccion;
        private int edad;
        private BigDecimal salario;
        private BigDecimal gastoMensual;

        public Builder(int dni) {
            this.dni = dni;
        }

        public Builder susNombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder susApellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public Builder suDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Persona build() {
            Persona persona = new Persona();
            persona.dni = this.dni;
            persona.nombres = this.nombres;
            persona.apellidos = this.apellidos;
            persona.direccion = this.direccion;
            persona.edad = this.edad;
            persona.salario = this.salario;
            persona.gastoMensual = this.gastoMensual;
            return persona;
        }

        public Builder suEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public Builder suSalario(BigDecimal salario) {
            this.salario = salario;
            return this;
        }

        public Builder suGastoMensual(BigDecimal gastoMensual) {
            this.gastoMensual = gastoMensual;
            return this;
        }


    }

    private int dni;
    private String nombres;
    private String apellidos;
    private String direccion;
    private int edad;
    private BigDecimal salario;
    private BigDecimal gastoMensual;

    //more attributes ...
    public Persona() {
    }

    //Constructor
    public Persona(int dni, String nombres, String apellidos, String direccion, int edad, BigDecimal salario, BigDecimal gastoMensual) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.edad = edad;
        this.salario = salario;
        this.gastoMensual = gastoMensual;
    }

    //other constructor's types
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getGastoMensual() {
        return gastoMensual;
    }

    public void setGastoMensual(BigDecimal gastoMensual) {
        this.gastoMensual = gastoMensual;
    }

    //more getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        if (dni != persona.dni) return false;
        return nombres.equals(persona.nombres);
    }


    @Override
    public int hashCode() {
        int result = dni;
        result = 31 * result + nombres.hashCode();
        return result;
    }


    @Override
    public int compareTo(Persona o) {
        return Integer.compare(this.getDni(), o.getDni());
    }
}
