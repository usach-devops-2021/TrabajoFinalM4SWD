package com.devops.dxc.devops.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

// import com.google.gson.Gson;

public class Util {

    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en 
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getDxc(int ahorro, int sueldo){
        if(((ahorro*0.1)/getUf()) > 150 ){
            // System.out.println("Se utiliza UF");
            return (int) (150*getUf()) ;
        } else if((ahorro*0.1)<=1000000 && ahorro >=1000000){
            return (int) 1000000;
        } else if( ahorro <=1000000){
            return (int) ahorro;
        } else {
            return (int) (ahorro*0.1);
        }
    }

    /**
     * Método para cacular el impuesto
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getImpuesto(int ahorro, int sueldo){
        double factor = 0;
        int sueldoAnual = sueldo * 12;
        int dxc = getDxc(ahorro, sueldo);
        if(sueldo >= 1500000){
            if(sueldoAnual <= 8266698) {
                factor = 0;
            } else if (sueldoAnual <= 18370440) {
                factor = 0.04;
            } else if (sueldoAnual <= 30617400) {
                factor = 0.08;
            } else if (sueldoAnual <= 42864360) {
                factor = 0.135;
            } else if (sueldoAnual <= 55111320) {
                factor = 0.23;
            } else if (sueldoAnual <= 73481760) {
                factor = 0.304;
            } else if (sueldoAnual <= 189827880) {
                factor = 0.35;
            } else {
                factor = 0.4;
            }
        }
        return (int)(dxc * factor);
    }

    /**
     * Método para calcular el saldo restante
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     *
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getSaldo(int ahorro, int sueldo){
        int dxc = getDxc(ahorro,sueldo);
        // Agregar cálculo.
        return ahorro - dxc;
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     * @return
     */
    public static int getUf(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> call= restTemplate.getForEntity("https://mindicador.cl/api/uf" ,String.class);
        String valorUFString = call.getBody().toLowerCase().split("valor")[1].split(":")[1].split("}")[0];
        int valorUF = (int)Double.parseDouble(valorUFString);
        // int valorUF = 31000; Valor Estático
        return valorUF;
    }
}
